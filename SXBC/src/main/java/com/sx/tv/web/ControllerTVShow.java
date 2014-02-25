package com.sx.tv.web;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sx.tv.entites.Channel;
import com.sx.tv.entites.ChannelComments;
import com.sx.tv.entites.Company;
import com.sx.tv.entites.ContractPayment;
import com.sx.tv.entites.DeptComments;
import com.sx.tv.entites.People;
import com.sx.tv.entites.PlayData;
import com.sx.tv.entites.PlayInfo;
import com.sx.tv.entites.Progress;
import com.sx.tv.entites.ProjectorComments;
import com.sx.tv.entites.RecommendClass;
import com.sx.tv.entites.Role;
import com.sx.tv.entites.Score;
import com.sx.tv.entites.Status;
import com.sx.tv.entites.TVContract;
import com.sx.tv.entites.TVShow;
import com.sx.tv.entites.Theme;
import com.sx.tv.entites.User;
import com.sx.tv.finder.TVShowsFinder;
import com.sx.tv.utils.StatusUtil;
import com.sx.tv.utils.URLStringUtil;
import com.sx.tv.view.JsonData;
import com.sx.tv.view.JsonDataTable;
import com.sx.tv.view.Page;
import com.sx.tv.view.SearchTV;
import com.sx.tv.view.TVShowView;

@Controller
@RequestMapping(value = "/tvshows")
public class ControllerTVShow {

	private static final Logger logger = Logger
			.getLogger(ControllerTVShow.class);

	public void populateEditForm(Model uiModel) {
		uiModel.addAttribute("companys", Company.findAllCompanys());
		uiModel.addAttribute("peoples", People.findAllPeoples());
		uiModel.addAttribute("progresses", Progress.findAllProgresses());
		uiModel.addAttribute("statuses", Status.findAllStatuses());
		uiModel.addAttribute("themes", Theme.findAllThemes());
		uiModel.addAttribute("users", User.findAllUsers());

	}

	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute(
				"date_format",
				DateTimeFormat.patternForStyle("M-",
						LocaleContextHolder.getLocale()));
		uiModel.addAttribute(
				"TVShow__inputdate_date_format",
				DateTimeFormat.patternForStyle("M-",
						LocaleContextHolder.getLocale()));
		uiModel.addAttribute(
				"TVShow__rejectdate_date_format",
				DateTimeFormat.patternForStyle("M-",
						LocaleContextHolder.getLocale()));
	}

	@RequestMapping(value = "/create", params = "toCreate", produces = "text/html")
	public String toCreate(Model uiModel) {
		uiModel.addAttribute("TVShow_", new TVShowView());
		uiModel.addAttribute("people", new People());
		uiModel.addAttribute("company", new Company());
		uiModel.addAttribute("theme", new Theme());
		addDateTimeFormatPatterns(uiModel);
		populateEditForm(uiModel);
		return "tvshows/create";
	}

	@RequestMapping(value = "/generalInfo/{id}", produces = "text/html")
	public String information(@PathVariable("id") Long id,
			@RequestParam(value = "level", required = false) String level,
			Model uiModel, Principal principal) {
		addDateTimeFormatPatterns(uiModel);
		List<Channel> channels = Channel.findAllChannels();
		uiModel.addAttribute("channels", channels);
		TVShow tv = TVShow.findTVShow(id);
		uiModel.addAttribute("tvshow_", tv);
		uiModel.addAttribute("itemId", id);
		User logonUser = User.findUsersByNameEquals(principal.getName())
				.getSingleResult();
		List<ProjectorComments> prjComments = null;
		try {
			prjComments = ProjectorComments.findProjectorCommentsesByTvshow(tv)
					.getResultList();
			uiModel.addAttribute("prjComments", prjComments);
		} catch (Exception e) {
			// TODO: handle exception
		}

		boolean hasAdminView = false;
		boolean isScoreAdmin = false;
		boolean isScoring = false;
		for (Role r : logonUser.getRoles()) {
			if (r.getId() == 3) {
				isScoreAdmin = true;
			}
			if (r.getId() == 2) {
				isScoring = true;
			}
		}

		Role role = Role.findRole(new Long(2));
		List<Role> rlist = new ArrayList<Role>();
		rlist.add(role);
		List<User> uList = User.findUsersByRoles(rlist).getResultList();
		uiModel.addAttribute("scoreUsers", uList);

		List<Score> sList = Score.findScoresByTvshow(tv).getResultList();

		if (null != sList && !sList.isEmpty()) {

			Map<Integer, List<Score>> smap = new HashMap<Integer, List<Score>>();

			for (Channel c : channels) {
				for (Score s : sList) {
					if (c.getId() == s.getRecommendChannel().getId()) {
						if (smap.get(c.getId()) == null) {
							List<Score> sl = new ArrayList<Score>();
							sl.add(s);
							smap.put(c.getId(), sl);
						} else {
							smap.get(c.getId()).add(s);
						}
					}
				}
			}

			Map<Integer, List<User>> umap = new HashMap<Integer, List<User>>();
			Map<Integer, Float> avgmap = new HashMap<Integer, Float>();
			// Iterator it = map.entrySet().iterator();
			List<User> toRemove = null;
			float avgScore = 0;
			for (Iterator<Entry<Integer, List<Score>>> it = smap.entrySet()
					.iterator(); it.hasNext();) {
				Map.Entry<Integer, List<Score>> e = it.next();
				toRemove = new ArrayList<User>();
				avgScore = 0;
				for (Score ss : e.getValue()) {
					avgScore += ss.getAvgScore();
					if (uList.contains(ss.getRatedBy())) {
						toRemove.add(ss.getRatedBy());
					}
				}

				avgmap.put(
						e.getKey(),
						new Float(
								(Math.round((avgScore / toRemove.size()) * 10)) / 10));
				if (null == umap.get(e.getKey())) {
					List<User> ul = new ArrayList<User>();
					for (User r : uList) {
						if (!toRemove.contains(r)) {
							ul.add(r);
						}
					}
					umap.put(e.getKey(), ul);

				} else {
					for (User r : uList) {
						if (!toRemove.contains(r)) {
							umap.get(e.getKey()).add(r);
						}
					}
				}

			}

			uiModel.addAttribute("scoreMap", smap);
			uiModel.addAttribute("unscoreMap", umap);
			uiModel.addAttribute("avgmap", avgmap);

			hasAdminView = true;
			try {
				List<DeptComments> deptCmts = DeptComments
						.findDeptCommentsesByTvshow(tv).getResultList();
				uiModel.addAttribute("deptCmts", deptCmts);
				// ChannelComments.findChannelCommentsesByChannelAndTvshow(channel,
				// tvshow)
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				List<ChannelComments> chnnelCmts = ChannelComments
						.findChannelCommentsesByTvshow(tv).getResultList();
				uiModel.addAttribute("channelCmts", chnnelCmts);
				// ChannelComments.findChannelCommentsesByChannelAndTvshow(channel,
				// tvshow)
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		uiModel.addAttribute("isScoreAdmin", isScoreAdmin);
		uiModel.addAttribute("hasAdminView", hasAdminView);
		uiModel.addAttribute("isScoring", isScoring);
		List<PlayData> pds = PlayData.findPlayDatasByTvshow(tv).getResultList();
		if (null != pds && !pds.isEmpty()) {
			uiModel.addAttribute("playData", pds);
		}
		List<Score> _scores = null;
		try {
			_scores = Score.findScoresByRatedByAndTvshow(logonUser, tv)
					.getResultList();
			uiModel.addAttribute("_scores", _scores);
		} catch (Exception e) {
			// TODO: handle exception
		}

		TVContract tvctc = null;
		try {
			tvctc = TVContract.findTVContractsByTvshow(tv).getSingleResult();
			uiModel.addAttribute("contract", tvctc);
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (null != tvctc) {
			List<ContractPayment> ps = null;
			try {
				ps = ContractPayment.findContractPaymentsByContract(tvctc)
						.getResultList();
				uiModel.addAttribute("contractPayments", ps);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		if (null != level && !"".equals(level)) {

			if ("reciveStore".equalsIgnoreCase(level)) {
				return "tvshows/reciveStoreInformation";
			}
			List<PlayInfo> playInfos = PlayInfo.findPlayInfoesByTvshow(tv)
					.getResultList();
			uiModel.addAttribute("playInfos", playInfos);
			return "tvshows/level2information";
		} else {
			return "tvshows/information";
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html")
	public String doCreate(TVShow TVShow_, Principal principal, Model uiModel,
			HttpServletRequest httpServletRequest) {
		uiModel.asMap().clear();
		User inputer = User.findUsersByNameEquals(principal.getName())
				.getSingleResult();
		TVShow_.setInputter(inputer);
		TVShow_.setInputDate(new Date());
		TVShow_.setStatus(Status.findStatus(StatusUtil.DAI_TUI_JIAN));
		TVShow_.persist();
		return "redirect:/tvshows/generalInfo/"
				+ URLStringUtil.encodeUrlPathSegment(
						TVShow_.getId().toString(), httpServletRequest);

	}

	@RequestMapping(value = "/doAjaxCreate", method = RequestMethod.POST, produces = "text/html")
	public @ResponseBody
	String doCreateAjaxSubmit(TVShowView TVShow_, Model uiModel,
			HttpServletRequest httpServletRequest, Principal principal) {
		String message = "SUCCESS";
		try {
			TypedQuery<TVShow> query = TVShow.findTVShowsByNameEquals(TVShow_
					.getName().trim());
			boolean isNotExsits = query.getResultList().isEmpty();
			if (isNotExsits) {
				User inputer = User.findUsersByNameEquals(principal.getName())
						.getSingleResult();
				TVShow tv = new TVShow();
				tv.setName(TVShow_.getName().trim());
				tv.setCount(TVShow_.getCount());
				tv.setCompany(TVShow_.getCompany());
				tv.setTheme(TVShow_.getTheme());
				tv.setInputDate(new Date());
				tv.setInputter(inputer);
				tv.setIsPurchase(false);
				tv.setOutline(TVShow_.getOutline());
				tv.setProgress(TVShow_.getProgress());
				tv.setProjector(TVShow_.getProjector());
				tv.setRemoved(0);
				tv.setScriptSrc(TVShow_.getScriptSrc());
				tv.setStatus(Status.findStatus(StatusUtil.DAI_TUI_JIAN));
				if (null != TVShow_.getActors()) {
					Set<People> actors = new HashSet<People>();
					actors.addAll(TVShow_.getActors());
					tv.setActors(actors);
				}
				

				if (null != TVShow_.getDirectors()) {
					Set<People> directors = new HashSet<People>();
					directors.addAll(TVShow_.getDirectors());
					tv.setDirectors(directors);
				}
				if (null != TVShow_.getScreenwriters()) {
					Set<People> screenwriters = new HashSet<People>();
					screenwriters.addAll(TVShow_.getScreenwriters());
					tv.setScreenwriters(screenwriters);
				}

				if (null != TVShow_.getPublisher()) {
					Set<People> publisher = new HashSet<People>();
					publisher.addAll(TVShow_.getPublisher());
					tv.setPublisher(publisher);
				}

				if (null != TVShow_.getProducer()) {
					Set<People> pros = new HashSet<People>();
					pros.addAll(TVShow_.getProducer());
					tv.setProducer(pros);
				}
				tv.persist();
				message += "_" + tv.getId();

			} else {
				message = "DUPLICATE";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "FAILED";
		}
		// uiModel.addAttribute("message", message);
		return message;
	}

	@RequestMapping(value = "/update/{id}", params = "toUpdate", produces = "text/html")
	public String toUpdate(@PathVariable("id") Long id, Model uiModel) {
		TVShow tv = TVShow.findTVShow(id);
		TVShowView tvview = new TVShowView();
		tvview.setId(tv.getId());
		List<People> actors = new ArrayList<People>();
		actors.addAll(tv.getActors());
		tvview.setActors(actors);

		List<People> dirs = new ArrayList<People>();
		dirs.addAll(tv.getDirectors());
		tvview.setDirectors(dirs);

		List<People> writers = new ArrayList<People>();
		writers.addAll(tv.getScreenwriters());
		tvview.setScreenwriters(writers);

		List<People> pubs = new ArrayList<People>();
		pubs.addAll(tv.getPublisher());
		tvview.setPublisher(pubs);

		List<People> pro = new ArrayList<People>();
		pro.addAll(tv.getProducer());
		tvview.setProducer(pro);

		tvview.setCompany(tv.getCompany());
		tvview.setCount(tv.getCount());
		tvview.setInputDate(tv.getInputDate());
		tvview.setInputter(tv.getInputter());
		tvview.setIsPurchase(tv.getIsPurchase());
		tvview.setMarketAssessment(tv.getMarketAssessment());
		tvview.setMarketShare(tv.getMarketShare());
		tvview.setName(tv.getName());
		tvview.setOutline(tv.getOutline());
		tvview.setProgress(tv.getProgress());
		tvview.setProjector(tv.getProjector());
		tvview.setRanking(tv.getRanking());
		tvview.setRatings(tv.getRatings());
		tvview.setScriptSrc(tv.getScriptSrc());
		tvview.setStatus(tv.getStatus());
		tvview.setTheme(tv.getTheme());
		tvview.setComments(tv.getComments());
		tvview.setForcePurchase(tv.getForcePurchase());
		uiModel.addAttribute("TVShow_", tvview);
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("people", new People());
		uiModel.addAttribute("company", new Company());
		uiModel.addAttribute("theme", new Theme());
		populateEditForm(uiModel);
		return "tvshows/update";
	}

	@RequestMapping(value = "/update/doUpdate/{sid}", method = RequestMethod.PUT, produces = "text/html")
	public String doUpdate(@PathVariable("sid") Integer sid,
			TVShowView TVShow_, BindingResult bindingResult,
			Principal principal, Model uiModel,
			HttpServletRequest httpServletRequest) {

		uiModel.asMap().clear();
		User logonUser = User.findUsersByNameEquals(principal.getName())
				.getSingleResult();
		Status _newST = TVShow_.getStatus();
		if (_newST.getId() != sid) {
			Status _oldST = Status.findStatus(sid);
			logger.warn("Warning:Status Changed==========================\r\n");
			logger.warn("\r\nTVShow: (id=" + TVShow_.getId() + ", name="
					+ TVShow_.getName() + ") status changed from \r\n"
					+ _oldST.getName() + "(" + _oldST.getId() + ") to "
					+ _newST.getName() + "(" + _newST.getId() + ") by user:("
					+ logonUser.getStaff() + "[" + logonUser.getName() + "="
					+ logonUser.getId() + "])\r\n");
			logger.warn("Warning:Status Changed==========================\r\n");
		}
		TVShow tv = TVShow.findTVShow(TVShow_.getId());
		tv.setCompany(TVShow_.getCompany());
		tv.setCount(TVShow_.getCount());
		tv.setInputDate(TVShow_.getInputDate());
		tv.setInputter(TVShow_.getInputter());
		tv.setIsPurchase(TVShow_.getIsPurchase());
		tv.setMarketAssessment(TVShow_.getMarketAssessment());
		tv.setMarketShare(TVShow_.getMarketShare());
		tv.setName(TVShow_.getName());
		tv.setOutline(TVShow_.getOutline());
		tv.setProgress(TVShow_.getProgress());
		tv.setProjector(TVShow_.getProjector());
		tv.setRanking(TVShow_.getRanking());
		tv.setRatings(TVShow_.getRatings());
		tv.setScriptSrc(TVShow_.getScriptSrc());
		tv.setStatus(TVShow_.getStatus());
		tv.setTheme(TVShow_.getTheme());
		tv.setComments(TVShow_.getComments());
		tv.setForcePurchase(TVShow_.getForcePurchase());
		if (null != TVShow_.getActors() && !TVShow_.getActors().isEmpty()) {
			Set<People> actors = new HashSet<People>();
			actors.addAll(TVShow_.getActors());
			tv.setActors(actors);
		} else {
			tv.setActors(null);
		}

		if (null != TVShow_.getDirectors() && !TVShow_.getDirectors().isEmpty()) {
			Set<People> directors = new HashSet<People>();
			directors.addAll(TVShow_.getDirectors());
			tv.setDirectors(directors);
		} else {
			tv.setDirectors(null);
		}

		if (null != TVShow_.getScreenwriters()
				&& !TVShow_.getScreenwriters().isEmpty()) {
			Set<People> screenwriters = new HashSet<People>();
			screenwriters.addAll(TVShow_.getScreenwriters());
			tv.setScreenwriters(screenwriters);
		} else {
			tv.setScreenwriters(null);
		}

		if (null != TVShow_.getProducer() && !TVShow_.getProducer().isEmpty()) {
			Set<People> pro = new HashSet<People>();
			pro.addAll(TVShow_.getProducer());
			tv.setProducer(pro);
		} else {
			tv.setProducer(null);
		}

		if (null != TVShow_.getPublisher() && !TVShow_.getPublisher().isEmpty()) {
			Set<People> publisher = new HashSet<People>();
			publisher.addAll(TVShow_.getPublisher());
			tv.setPublisher(publisher);
		} else {
			tv.setPublisher(null);
		}

		tv.merge();
		return "redirect:/tvshows/generalInfo/"
				+ URLStringUtil.encodeUrlPathSegment(
						TVShow_.getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id") Long id,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Principal principal, Model uiModel) {

		TVShow TVShow_ = TVShow.findTVShow(id);
		TVShow_.setRemoved(1);
		TVShow_.merge();
		User logonUser = User.findUsersByNameEquals(principal.getName())
				.getSingleResult();
		logger.warn("Warning:TVShow Removed=========================\r\n");
		logger.warn("TVShow: (id=" + TVShow_.getId() + ", name="
				+ TVShow_.getName() + ") removed by by user:("
				+ logonUser.getStaff() + "[" + logonUser.getName() + "="
				+ logonUser.getId() + "])\r\n");
		logger.warn("Warning:Status Changed==========================\r\n");
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/tvshows/list";
	}

	@RequestMapping(value = "/confirmPurchase/{tvid}/{sid}", produces = "text/html")
	public String confirmPurchase(@PathVariable("tvid") Long tvid,
			@PathVariable("sid") Integer sid,
			@RequestParam(required = false) String level, String reason,
			Principal principal, Model uiModel,
			HttpServletRequest httpServletRequest)
			throws UnsupportedEncodingException {
		Status status = Status.findStatus(sid);
		TVShow tv = TVShow.findTVShow(tvid);

		User logonUser = User.findUsersByNameEquals(principal.getName())
				.getSingleResult();
		logger.warn("Warning:Status Changed==========================\r\n");
		if (sid == 8 || sid == 9 || sid == 10 || sid == 11 || sid == 12) {
			tv.setComments((tv.getComments() != null ? tv.getComments() + "   "
					: "") + new String(reason.getBytes("ISO-8859-1"), "UTF-8"));
			logger.warn("\r\nTVShow: (id=" + tv.getId() + ", name="
					+ tv.getName() + ") status changed from \r\n"
					+ tv.getStatus().getName() + "(" + tv.getStatus().getId()
					+ ") to " + status.getName() + "(" + status.getId()
					+ ") by user:(" + logonUser.getStaff() + "["
					+ logonUser.getName() + "=" + logonUser.getId()
					+ "])\r\nReason:\r\n"
					+ new String(reason.getBytes("ISO-8859-1"), "UTF-8"));
		} else {
			logger.warn("\r\nTVShow: (id=" + tv.getId() + ", name="
					+ tv.getName() + ") status changed from \r\n"
					+ tv.getStatus().getName() + "(" + tv.getStatus().getId()
					+ ") to " + status.getName() + "(" + status.getId()
					+ ") by user:(" + logonUser.getStaff() + "["
					+ logonUser.getName() + "=" + logonUser.getId() + "])\r\n");

		}
		logger.warn("Warning:Status Changed==========================\r\n");
		tv.setStatus(status);
		tv.merge();
		uiModel.addAttribute("tvshow_", tv);
		uiModel.addAttribute("itemId", tvid);
		if (null != level) {
			return "redirect:/tvshows/generalInfo/"
					+ URLStringUtil.encodeUrlPathSegment(tv.getId().toString(),
							httpServletRequest) + "?level=level2market";
		} else {
			return "redirect:/tvshows/generalInfo/"
					+ URLStringUtil.encodeUrlPathSegment(tv.getId().toString(),
							httpServletRequest);
		}

	}

	@RequestMapping(value = "list", produces = "text/html")
	public String list(
			@RequestParam(value = "stype", required = false) Integer stype,
			@RequestParam(value = "svalue", required = false) String svalue,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1)
					* sizeNo;
			uiModel.addAttribute("tvshows",
					TVShowsFinder.findTVShowEntries(firstResult, sizeNo));
			float nrOfPages = (float) TVShowsFinder.countTVShows() / sizeNo;
			uiModel.addAttribute(
					"maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			if (stype != null) {
				if (stype == 0) {
					List<TVShow> data = TVShowsFinder.findTVShowByID(Long
							.parseLong(svalue));
					uiModel.addAttribute("tvshows", data);
				} else {
					try {
						uiModel.addAttribute("tvshows",
								TVShowsFinder.findTVShowsByName(svalue));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				uiModel.addAttribute("tvshows", TVShowsFinder.findAllTVShows());
			}

		}
		addDateTimeFormatPatterns(uiModel);
		return "tvshows/list";
	}

	@RequestMapping(value = "/findTVShows", produces = "text/html")
	public String findTVShows(Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("SearchTV", new SearchTV());
		populateDependencies(uiModel, true);
		return "tvshows/filterFind";
	}

	@RequestMapping(value = "/findLevel2Market", produces = "text/html")
	public String findLevel2Market(Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("SearchTV", new SearchTV());
		populateDependencies(uiModel, true);
		return "tvshows/level2market";
	}

	@RequestMapping(value = "/findReciveStore", produces = "text/html")
	public String findReciveStore(Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("SearchTV", new SearchTV());
		populateDependencies(uiModel, true);
		return "tvshows/reciveStore";
	}

	@RequestMapping(value = "/queryTVShows4Json")
	public @ResponseBody
	JsonDataTable queryTVShows4Json(Page page, SearchTV stv)
			throws UnsupportedEncodingException {
		if (null == stv) {
			stv = new SearchTV();
		}
		JsonDataTable jdt = new JsonDataTable();
		if (page != null) {
			int sizeNo = page.getRows() == 0 ? 10 : page.getRows();
			final int firstResult = page == null ? 0 : (page.getPage() - 1)
					* sizeNo;
			List<TVShow> results = TVShowsFinder.findTVShows(stv, firstResult,
					page.getRows(), page.getSidx(), page.getSord());
			long count = TVShowsFinder.countTVShows(stv);
			jdt.setPage(page.getPage());
			List<JsonData> rows = new ArrayList<JsonData>();

			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			/**
			 * 
			 * colNames : [ 'ID', '剧名', '集数', '影视公司', '题材', '剧本来源', '项目负责人',
			 * '状态', '录入时间' ],
			 * 
			 */
			for (TVShow t : results) {
				Object[] o = new Object[] { t.getId(), t.getName(),
						t.getCount(), t.getCompany().getName(),
						t.getTheme().getName(), t.getScriptSrc(),
						t.getProjector().getStaff(), t.getStatus().getName(),
						sdf.format(t.getInputDate()) };
				JsonData jd = new JsonData(t.getId(), o);
				rows.add(jd);
			}

			jdt.setRows(rows);
			jdt.setSize(page.getRows());
			jdt.setTotal(count / page.getRows() + 1);
			jdt.setRecords(count);
		}
		return jdt;
	}

	@RequestMapping(value = "/queryTVShows4JsonLevel2Market")
	public @ResponseBody
	JsonDataTable queryTVShows4JsonLevel2Market(
			@RequestParam(required = false) boolean unexpired, Page page,
			SearchTV stv) throws UnsupportedEncodingException {
		if (null == stv) {
			stv = new SearchTV();
		}
		JsonDataTable jdt = new JsonDataTable();
		if (page != null) {
			int sizeNo = page.getRows() == 0 ? 10 : page.getRows();
			final int firstResult = page == null ? 0 : (page.getPage() - 1)
					* sizeNo;
			jdt.setPage(page.getPage());
			List<JsonData> rows = new ArrayList<JsonData>();
			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			// colNames : [ 'ID', '剧名', '集数', '影视公司', '题材', '项目负责人',
			// '状态', '首播频道', '价格', '版权起始', '版权终止']
			boolean exsit = false;
			long total = TVShowsFinder.countDependsOnContractFields(stv);
			if (total != 0) {
				Date today = new Date();
				List<TVContract> cts = TVShowsFinder
						.findDependsOnContractFields(stv, firstResult,
								page.getRows(), page.getSidx(), page.getSord());
				// round = "首轮";
				for (TVContract tt : cts) {
					TVShow t = tt.getTvshow();
					Object[] o = new Object[] { t.getId(), t.getName(),
							t.getCount(), t.getCompany().getName(),
							t.getTheme().getName(),
							t.getProjector().getStaff(), t.getStatus(),
							tt.getChannel().getName(), tt.getPrice(),
							sdf.format(tt.getCopyrightStart()),
							sdf.format(tt.getCopyrightEnd())

					};
					JsonData jd = new JsonData(t.getId(), o);
					rows.add(jd);
				}
				jdt.setRows(rows);
				jdt.setSize(page.getRows());
				jdt.setTotal(total / page.getRows() + 1);
				jdt.setRecords(total);
				exsit = true;
			}
		}
		return jdt;
	}

	@RequestMapping(value = "/queryTVShows4JsonLevel2MarketPrint")
	public String queryTVShows4JsonLevel2MarketPrint(
			@RequestParam(required = false) boolean unexpired, SearchTV stv,
			Model uiModel) throws UnsupportedEncodingException {
		List<TVContract> cts = TVShowsFinder.findDependsOnContractFields(stv,
				0, 99999, "id", "asc");
		uiModel.addAttribute("data", cts);
		return "tvshows/level2marketPrint";
	}

	@RequestMapping("/{tvid}/updateStatusByPlayDate")
	public String updateStatusByPlayDate(
			@PathVariable("tvid") Long tvid,
			int status,
			@org.springframework.format.annotation.DateTimeFormat(iso = ISO.DATE) Date pdate,
			Principal principal, HttpServletRequest httpServletRequest) {
		TVShow tv = TVShow.findTVShow(tvid);
		Status st = Status.findStatus(status);
		User logonUser = User.findUsersByNameEquals(principal.getName())
				.getSingleResult();
		logger.warn("Warning:Status Changed==========================\r\n");
		logger.warn("\r\nTVShow: (id=" + tv.getId() + ", name=" + tv.getName()
				+ ") status changed from \r\n" + tv.getStatus().getName() + "("
				+ tv.getStatus().getId() + ") to " + st.getName() + "("
				+ st.getId() + ") by user:(" + logonUser.getStaff() + "["
				+ logonUser.getName() + "=" + logonUser.getId() + "])\r\n");
		logger.warn("Warning:Status Changed==========================\r\n");
		if (status == StatusUtil.YI_BO) {
			tv.setPlayDate(pdate);
		} else {
			List<PlayInfo> infos = PlayInfo.findPlayInfoesByTvshow(tv)
					.getResultList();
			for (PlayInfo pi : infos) {
				if (null == pi.getPlayDate()) {
					pi.setPlayDate(pdate);
					pi.merge();
					break;
				}

			}
		}
		tv.setStatus(st);
		tv.merge();
		return "redirect:/tvshows/generalInfo/"
				+ URLStringUtil.encodeUrlPathSegment(tv.getId().toString(),
						httpServletRequest) + "?level=level2market";
	}

	@RequestMapping(value = "/{tvid}/toAddPlayInfo4Level2", produces = "text/html")
	public String addPlayInfo4Level2(@PathVariable("tvid") Long tvid,
			int round, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		TVShow tv = TVShow.findTVShow(tvid);
		uiModel.addAttribute("tvshow", tv);
		TVContract contract = null;
		try {
			contract = TVContract.findTVContractsByTvshow(tv).getSingleResult();
			uiModel.addAttribute("contract", contract);
		} catch (Exception e) {
			// TODO: handle exception
		}
		uiModel.addAttribute("playInfo", new PlayInfo());
		uiModel.addAttribute("round", round);
		List<Channel> _channelList = new ArrayList<Channel>();
		Channel channel = new Channel(new Integer(0), "请选择", 0);
		_channelList.add(channel);
		_channelList.addAll(Channel.findAllChannels());

		uiModel.addAttribute("channels", _channelList);
		// populateDependencies(uiModel, true);
		return "tvshows/playInfo";
	}

	@RequestMapping(value = "/{tvid}/doAddPlayInfo4Level2", produces = "text/html", method = RequestMethod.POST)
	public String doAddPlayInfo4Level2(@PathVariable("tvid") Long tvid,
			PlayInfo playInfo, Model uiModel, Principal principal,
			HttpServletRequest httpServletRequest) {
		TVShow tv = TVShow.findTVShow(tvid);
		Status st = null;
		if (playInfo.getRound() == 2) {
			st = Status.findStatus(StatusUtil.ER_LUN_DAI_BO);
		}
		if (playInfo.getRound() == 3) {
			st = Status.findStatus(StatusUtil.SAN_LUN_DAI_BO);
		}
		if (playInfo.getRound() >= 4) {
			st = Status.findStatus(StatusUtil.SAN_LUN_HOU_DAI_BO);
		}
		// PlayInfo.findPlayInfoesByTvshow(tv).getResultList();
		User logonUser = User.findUsersByNameEquals(principal.getName())
				.getSingleResult();
		logger.warn("Warning:Status Changed==========================\r\n");
		logger.warn("\r\nTVShow: (id=" + tv.getId() + ", name=" + tv.getName()
				+ ") status changed from \r\n" + tv.getStatus().getName() + "("
				+ tv.getStatus().getId() + ") to " + st.getName() + "("
				+ st.getId() + ") by user:(" + logonUser.getStaff() + "["
				+ logonUser.getName() + "=" + logonUser.getId() + "])\r\n");
		logger.warn("Warning:Status Changed==========================\r\n");
		// tv.setPlayDate(pdate);
		tv.setStatus(st);
		tv.merge();
		playInfo.persist();

		return "redirect:/tvshows/generalInfo/"
				+ URLStringUtil.encodeUrlPathSegment(tv.getId().toString(),
						httpServletRequest) + "?level=level2market";
	}

	public void populateDependencies(Model uiModel, boolean emptyOpt) {
		List<Company> _companyList = new ArrayList<Company>();
		List<Channel> _channelList = new ArrayList<Channel>();
		List<People> _popleList = new ArrayList<People>();
		List<Progress> _progressList = new ArrayList<Progress>();
		List<RecommendClass> _recomClass = new ArrayList<RecommendClass>();
		List<Status> _statusList = new ArrayList<Status>();
		List<Theme> _themeList = new ArrayList<Theme>();
		List<User> _usersList = new ArrayList<User>();

		if (emptyOpt) {
			Channel channel = new Channel(new Integer(0), "请选择", 0);
			_channelList.add(channel);

			Company company = new Company(new Integer(0), "请选择");
			_companyList.add(company);

			People people = new People(new Integer(0), "请选择");
			_popleList.add(people);

			Progress progress = new Progress(new Integer(0), "请选择");
			_progressList.add(progress);

			RecommendClass rc = new RecommendClass(new Integer(0), "请选择");
			_recomClass.add(rc);

			Status status = new Status(new Integer(0), "请选择");
			_statusList.add(status);

			Theme theme = new Theme(new Integer(0), "请选择");
			_themeList.add(theme);

			User user = new User(new Integer(0), "请选择");
			user.setStaff("请选择");
			_usersList.add(user);
		}

		_channelList.addAll(Channel.findAllChannels());
		_companyList.addAll(Company.findAllCompanys());
		_popleList.addAll(People.findAllPeoples());
		_progressList.addAll(Progress.findAllProgresses());
		_recomClass.addAll(RecommendClass.findAllRecommendClasses());
		_statusList.addAll(Status.findAllStatuses());
		_themeList.addAll(Theme.findAllThemes());
		_usersList.addAll(User.findAllUsers());

		uiModel.addAttribute("channels", _channelList);
		uiModel.addAttribute("companys", _companyList);
		uiModel.addAttribute("peoples", _popleList);
		uiModel.addAttribute("progresses", _progressList);
		uiModel.addAttribute("recommendclasses", _recomClass);
		// uiModel.addAttribute("scores", Score.findAllScores());
		uiModel.addAttribute("statuses", _statusList);
		uiModel.addAttribute("themes", _themeList);
		uiModel.addAttribute("users", _usersList);
	}

	@RequestMapping(value = "/loadPeopleJsonString", method = RequestMethod.GET)
	public @ResponseBody
	List<People> writeJson(@RequestParam String pname) {
		try {
			pname = new String(pname.getBytes("ISO-8859-1"), "UTF-8");
			if (pname.charAt(0) != '%') {
				pname = "%" + pname;
			}
			if (pname.charAt(pname.length() - 1) != '%') {
				pname = pname + "%";
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EntityManager em = People.entityManager();
		StringBuffer sb = new StringBuffer(
				"SELECT o FROM People AS o WHERE o.name LIKE :name");
		Query query = em.createQuery(sb.toString(), People.class);
		query.setParameter("name", pname);
		List<People> peoples = query.getResultList();
		return peoples;
	}

	@RequestMapping(value = "/loadTVshowJsonString", method = RequestMethod.GET)
	public @ResponseBody
	List<Jtvshow> queryJsonTVshow(@RequestParam String tvname) {
		List<Jtvshow> list = new ArrayList<Jtvshow>();
		try {
			List<TVShow> _data = TVShowsFinder.findTVShowsByName(tvname);
			if (null != _data && !_data.isEmpty()) {
				for (TVShow tvShow : _data) {
					list.add(new Jtvshow(tvShow.getId(), tvShow.getName()));
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/loadCompanyJsonString", method = RequestMethod.GET)
	public @ResponseBody
	List<Jtvshow> queryJsonCompany(@RequestParam String qname) {
		List<Jtvshow> list = new ArrayList<Jtvshow>();
		try {
			qname = new String(qname.getBytes("ISO-8859-1"), "UTF-8");
			if (qname.charAt(0) != '%') {
				qname = "%" + qname;
			}
			if (qname.charAt(qname.length() - 1) != '%') {
				qname = qname + "%";
			}
			EntityManager em = Company.entityManager();
			StringBuffer sb = new StringBuffer(
					"SELECT o FROM Company AS o WHERE o.name LIKE :name");
			Query query = em.createQuery(sb.toString(), Company.class);
			query.setParameter("name", qname);
			List<Company> _data = query.getResultList();
			if (null != _data && !_data.isEmpty()) {
				for (Company c : _data) {
					list.add(new Jtvshow(c.getId(), c.getName()));
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/loadThemeJsonString", method = RequestMethod.GET)
	public @ResponseBody
	List<Jtvshow> queryJsonTheme(@RequestParam String qname) {
		List<Jtvshow> list = new ArrayList<Jtvshow>();
		try {
			qname = new String(qname.getBytes("ISO-8859-1"), "UTF-8");
			if (qname.charAt(0) != '%') {
				qname = "%" + qname;
			}
			if (qname.charAt(qname.length() - 1) != '%') {
				qname = qname + "%";
			}
			EntityManager em = Theme.entityManager();
			StringBuffer sb = new StringBuffer(
					"SELECT o FROM Theme AS o WHERE o.name LIKE :name");
			Query query = em.createQuery(sb.toString(), Theme.class);
			query.setParameter("name", qname);
			List<Theme> _data = query.getResultList();
			if (null != _data && !_data.isEmpty()) {
				for (Theme t : _data) {
					list.add(new Jtvshow(t.getId(), t.getName()));
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public class Jtvshow implements Serializable {
		private static final long serialVersionUID = 1L;
		private long id;
		private String name;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Jtvshow() {
		}

		public Jtvshow(long id, String name) {
			super();
			this.id = id;
			this.name = name;
		}

	}
}
