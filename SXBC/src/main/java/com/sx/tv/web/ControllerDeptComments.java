package com.sx.tv.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sx.tv.entites.Channel;
import com.sx.tv.entites.DeptComments;
import com.sx.tv.entites.RecommendClass;
import com.sx.tv.entites.Score;
import com.sx.tv.entites.Status;
import com.sx.tv.entites.TVShow;
import com.sx.tv.entites.User;
import com.sx.tv.utils.StatusUtil;
import com.sx.tv.utils.URLStringUtil;

@RequestMapping("/front/deptcommentses")
@Controller
public class ControllerDeptComments {
	private static final Logger logger = Logger
			.getLogger(ControllerDeptComments.class);

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid DeptComments deptComments,
			BindingResult bindingResult, Principal principal, Model uiModel,
			HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, deptComments);
			return "deptcommentses/createDeptCmts";
		}
		uiModel.asMap().clear();
		Status st = Status
				.findStatus(deptComments.getIsRecommend() ? StatusUtil.TUI_JIAN_ZHONG
						: StatusUtil.ZHONG_XIN_TAO_TAI);
		TVShow tv = deptComments.getTvshow();
		User logonUser = User.findUsersByNameEquals(principal.getName())
				.getSingleResult();

		if (st.getId() == 8 || st.getId() == 9 || st.getId() == 10
				|| st.getId() == 11) {
			logger.warn("Warning:Status Changed==========================\r\n");
			tv.setComments((tv.getComments()!=null?tv.getComments()+"   ":"") + deptComments.getComments());
			logger.warn("\r\nTVShow: (id=" + tv.getId() + ", name="
					+ tv.getName() + ") status changed from \r\n"
					+ tv.getStatus().getName() + "(" + tv.getStatus().getId()
					+ ") to " + st.getName() + "(" + st.getId() + ") by user:("
					+ logonUser.getStaff() + "[" + logonUser.getName() + "="
					+ logonUser.getId() + "])\r\nReason:\r\n"
					+ deptComments.getComments());
			logger.warn("Warning:Status Changed==========================\r\n");

		} else {
			logger.warn("Warning:Status Changed==========================\r\n");
			logger.warn("\r\nTVShow: (id=" + tv.getId() + ", name="
					+ tv.getName() + ") status changed from \r\n"
					+ tv.getStatus().getName() + "(" + tv.getStatus().getId()
					+ ") to " + st.getName() + "(" + st.getId() + ") by user:("
					+ logonUser.getStaff() + "[" + logonUser.getName() + "="
					+ logonUser.getId() + "])\r\n");
			logger.warn("Warning:Status Changed==========================\r\n");
		}
		tv.setStatus(st);
		tv.merge();

		deptComments.setCreateDate(new Date());
		deptComments.persist();
		return "redirect:/tvshows/generalInfo/"
				+ URLStringUtil.encodeUrlPathSegment(tv.getId().toString(),
						httpServletRequest);
	}

	@RequestMapping(value = "/create/{tvid}/{ctype}", params = "toCreate", produces = "text/html")
	public String createForm(@PathVariable("tvid") Long tvid,
			@PathVariable("ctype") int ctype, Model uiModel,
			HttpServletRequest httpServletRequest) {
		DeptComments dept = new DeptComments();
		TVShow tv = TVShow.findTVShow(tvid);
		dept.setTvshow(tv);
		List<RecommendClass> rList = RecommendClass.findAllRecommendClasses();
		// List<Score> sList = Score.findScoresByTvshow(tv).getResultList();
		Channel ch = Channel.findChannel(ctype);
		EntityManager em = Score.entityManager();
		TypedQuery<Score> query = em
				.createQuery(
						"SELECT o FROM Score AS o WHERE o.tvshow = :tvshow AND o.recommendChannel = :recommendChannel",
						Score.class);

		query.setParameter("tvshow", tv);
		query.setParameter("recommendChannel", ch);
		List<Score> sList = query.getResultList();
		float avgScore = 0;
		for (Score s : sList) {
			avgScore += s.getAvgScore();
		}
		if (avgScore != 0) {
			avgScore = (float) (Math.round((avgScore / sList.size()) * 10)) / 10;
		}
		for (RecommendClass rc : rList) {
			if (avgScore >= rc.getStart() && avgScore <= rc.getEnd()) {
				dept.setRecommendLevel(rc);
				break;
			}
		}
		populateEditForm(uiModel, dept);
		List<Channel> channels = new ArrayList<Channel>(1);
		channels.add(ch);

		uiModel.addAttribute("channels", channels);
		return "deptcommentses/createDeptCmts";
		// return "redirect:/tvshows/" +
		// encodeUrlPathSegment(tv.getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/update/doUpdate", method = RequestMethod.PUT, produces = "text/html")
	public String doUpdate(@Valid DeptComments deptComments,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, deptComments);
			return "deptcommentses/update";
		}
		uiModel.asMap().clear();
		deptComments.merge();
		return "redirect:/tvshows/generalInfo/"
				+ URLStringUtil.encodeUrlPathSegment(deptComments.getTvshow()
						.getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/update/{id}", params = "toUpdate", produces = "text/html")
	public String toUpdateForm(@PathVariable("id") Integer id, Model uiModel) {
		uiModel.addAttribute("formURL", "/front/deptcommentses/update/doUpdate");
		uiModel.addAttribute("channels", Channel.findAllChannels());
		populateEditForm(uiModel, DeptComments.findDeptComments(id));
		return "deptcommentses/update";
	}

	void populateEditForm(Model uiModel, DeptComments deptComments) {
		uiModel.addAttribute("deptComments", deptComments);
		// uiModel.addAttribute("channels", Channel.findAllChannels());
		List<RecommendClass> rcList = new ArrayList<RecommendClass>();
		RecommendClass rc = new RecommendClass(0, "无");
		rcList.add(rc);
		rcList.addAll(RecommendClass.findAllRecommendClasses());
		uiModel.addAttribute("recommendclasses", rcList);
		// uiModel.addAttribute("tvshows", TVShow.findAllTVShows());
		uiModel.addAttribute("users", User.findAllUsers());
	}

	@RequestMapping(value = "/findScoresByChannelType", method = RequestMethod.GET)
	public @ResponseBody
	Set<Channel> findScoresByChannelType(@RequestParam Long tvid,
			@RequestParam int cid) {
		TVShow tv = TVShow.findTVShow(tvid);
		TypedQuery<Score> query = Score.findScoresByTvshow(tv);
		List<Score> slist = query.getResultList();
		Set<Channel> set = new HashSet<Channel>();
		if (null != slist && !slist.isEmpty()) {
			for (Score s : slist) {
				if (s.getRecommendChannel().getId() != cid) {
					set.add(s.getRecommendChannel());
				}
			}
		}
		List<DeptComments> deptCmts = DeptComments.findDeptCommentsesByTvshow(
				tv).getResultList();
		
		for (DeptComments dp : deptCmts) {
			if(set.contains(dp.getRecommendChannel())){
				set.remove(dp.getRecommendChannel());
			}
		}

		return set;
	}
}
