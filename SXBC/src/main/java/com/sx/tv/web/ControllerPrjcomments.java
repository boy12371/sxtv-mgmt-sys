package com.sx.tv.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sx.tv.entites.Channel;
import com.sx.tv.entites.ProjectorComments;
import com.sx.tv.entites.RecommendClass;
import com.sx.tv.entites.Status;
import com.sx.tv.entites.TVShow;
import com.sx.tv.entites.User;
import com.sx.tv.utils.StatusUtil;
import com.sx.tv.utils.URLStringUtil;


@RequestMapping("/front/projectorcommentses")
@Controller
public class ControllerPrjcomments {
private static final Logger logger = Logger.getLogger(ControllerPrjcomments.class);
	
	@RequestMapping(value="/create",method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid ProjectorComments projectorComments, BindingResult bindingResult,Principal principal, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, projectorComments);
			return "projectorcommentses/createPrjComments";
		}

		Status st = Status.findStatus(projectorComments.getIsRecommend() ? StatusUtil.CHU_XUAN : StatusUtil.CHU_XUAN_TAO_TAI);
		TVShow tv = projectorComments.getTvshow();
		
		User logonUser = User.findUsersByNameEquals(principal.getName()).getSingleResult();
		logger.warn("Warning:Status Changed==========================\r\n");
		if (st.getId() == 8 || st.getId() == 9 || st.getId() == 10 || st.getId() == 11) {
			tv.setComments(projectorComments.getComments());
			logger.warn("\r\nTVShow: (id=" + tv.getId() + ", name=" + tv.getName() + ") status changed from \r\n" + tv.getStatus().getName() + "("
					+ tv.getStatus().getId() + ") to " + st.getName() + "(" + st.getId() + ") by user:(" + logonUser.getStaff() + "["
					+ logonUser.getName() + "=" + logonUser.getId() + "])\r\nReason:\r\n" + projectorComments.getComments());
		} else {
			logger.warn("\r\nTVShow: (id=" + tv.getId() + ", name=" + tv.getName() + ") status changed from \r\n" + tv.getStatus().getName() + "("
					+ tv.getStatus().getId() + ") to " + st.getName() + "(" + st.getId() + ") by user:(" + logonUser.getStaff() + "["
					+ logonUser.getName() + "=" + logonUser.getId() + "])\r\n");

		}
		logger.warn("Warning:Status Changed==========================\r\n");
		tv.setStatus(st);
		tv.merge();
		uiModel.asMap().clear();
		projectorComments.persist();
		// return "redirect:/projectorcommentses/recommendFrom/" +
		// encodeUrlPathSegment(projectorComments.getId().toString(),
		// httpServletRequest);
		return "redirect:/tvshows/generalInfo/" + URLStringUtil.encodeUrlPathSegment(projectorComments.getTvshow().getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/recommendFrom/{id}", produces = "text/html")
	public String recommendFrom(@PathVariable("id") Integer id, Model uiModel) {
		uiModel.addAttribute("projectorcomments", ProjectorComments.findProjectorComments(id));
		uiModel.addAttribute("itemId", id);
		return "projectorcommentses/recommendFrom";
	}

	@RequestMapping(value = "/create/{tvid}", params = "toCreate", produces = "text/html")
	public String createForm(@PathVariable("tvid") long tvid, Principal principal, Model uiModel) {

		TVShow tv = TVShow.findTVShow(tvid);
		User logonUser = User.findUsersByNameEquals(principal.getName()).getSingleResult();
		boolean ableToCreate = true;
		if (logonUser.getId() != tv.getProjector().getId()) {
			ableToCreate = false;
		}
		ProjectorComments pc = new ProjectorComments();
		pc.setTvshow(tv);
		uiModel.addAttribute("ableToCreate", ableToCreate);
		populateEditForm(uiModel, pc);
		return "projectorcommentses/createPrjComments";
	}

	void populateEditForm(Model uiModel, ProjectorComments projectorComments) {
		uiModel.addAttribute("projectorComments", projectorComments);
		uiModel.addAttribute("channels", Channel.findAllChannels());
		List<RecommendClass> rcList = new ArrayList<RecommendClass>();
		RecommendClass rc = new RecommendClass(0, "无");
		rcList.add(rc);
		rcList.addAll(RecommendClass.findAllRecommendClasses());
		uiModel.addAttribute("recommendclasses", rcList);
	}
	
	@RequestMapping(value="/update/doUpdate",method = RequestMethod.PUT, produces = "text/html")
    public String doUpdate(@Valid ProjectorComments projectorComments, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, projectorComments);
            return "projectorcommentses/update";
        }
        uiModel.asMap().clear();
        projectorComments.merge();
        //return "redirect:/projectorcommentses/" + encodeUrlPathSegment(projectorComments.getId().toString(), httpServletRequest);
        return "redirect:/tvshows/generalInfo/" + URLStringUtil.encodeUrlPathSegment(projectorComments.getTvshow().getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/update/{id}", params = "toUpdate", produces = "text/html")
    public String toUpdateForm(@PathVariable("id") Integer id, Model uiModel) {
    	uiModel.addAttribute("formURL", "/front/projectorcommentses/update/doUpdate");
        populateEditForm(uiModel, ProjectorComments.findProjectorComments(id));
        return "projectorcommentses/update";
    }
    
}
