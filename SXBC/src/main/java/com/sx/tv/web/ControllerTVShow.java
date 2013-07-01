package com.sx.tv.web;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
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
import com.sx.tv.entites.Progress;
import com.sx.tv.entites.ProjectorComments;
import com.sx.tv.entites.RecommendClass;
import com.sx.tv.entites.Role;
import com.sx.tv.entites.Score;
import com.sx.tv.entites.ScoreDetail;
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

@Controller
@RequestMapping(value = "/tvshows")
public class ControllerTVShow {

	private static final Logger logger = Logger.getLogger(ControllerTVShow.class);

	public void populateEditForm(Model uiModel) {
		uiModel.addAttribute("companys", Company.findAllCompanys());
		uiModel.addAttribute("peoples", People.findAllPeoples());
		uiModel.addAttribute("progresses", Progress.findAllProgresses());
		uiModel.addAttribute("statuses", Status.findAllStatuses());
		uiModel.addAttribute("themes", Theme.findAllThemes());
		uiModel.addAttribute("users", User.findAllUsers());

	}

	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("TVShow__inputdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("TVShow__rejectdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
	}

	@RequestMapping(value = "/create", params = "toCreate", produces = "text/html")
	public String toCreate(Model uiModel) {
		uiModel.addAttribute("TVShow_", new TVShow());
		uiModel.addAttribute("people", new People());
		uiModel.addAttribute("company", new Company());
		uiModel.addAttribute("theme", new Theme());
		addDateTimeFormatPatterns(uiModel);
		populateEditForm(uiModel);
		return "tvshows/create";
	}

	@RequestMapping(value = "/generalInfo/{id}", produces = "text/html")
	public String information(@PathVariable("id") Long id, Model uiModel, Principal principal) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("channels", Channel.findAllChannels());
		TVShow tv = TVShow.findTVShow(id);
		uiModel.addAttribute("tvshow_", tv);
		uiModel.addAttribute("itemId", id);
		User logonUser = User.findUsersByNameEquals(principal.getName()).getSingleResult();
		List<ProjectorComments> prjComments = null;
		try {
			prjComments = ProjectorComments.findProjectorCommentsesByTvshow(tv).getResultList();
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

		List<Score> sList = Score.findScoresByTvshow(tv).getResultList();
		if (null != sList && !sList.isEmpty()) {
			hasAdminView = true;
			Role role = Role.findRole(new Long(2));
			List<Role> rlist = new ArrayList<Role>();
			rlist.add(role);
			List<User> uList = User.findUsersByRoles(rlist).getResultList();
			float avgScore = 0;
			for (Score s : sList) {
				if (uList.contains(s.getRatedBy())) {
					uList.remove(s.getRatedBy());
				}
			}
			for (Score s : sList) {
				avgScore += s.getAvgScore();
			}

			if (avgScore != 0) {
				avgScore = (float) (Math.round((avgScore / sList.size()) * 10)) / 10;
			}

			if (uList.isEmpty()) {
				uiModel.addAttribute("hasUsersNotScoring", false);
			} else {
				uiModel.addAttribute("hasUsersNotScoring", true);
				uiModel.addAttribute("usersNotScoring", uList);
			}
			uiModel.addAttribute("scoresList", sList);
			uiModel.addAttribute("avgScore", avgScore);
			try {
				List<DeptComments> deptCmts = DeptComments.findDeptCommentsesByTvshow(tv).getResultList();
				uiModel.addAttribute("deptCmts", deptCmts);
				// ChannelComments.findChannelCommentsesByChannelAndTvshow(channel,
				// tvshow)
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				List<ChannelComments> chnnelCmts = ChannelComments.findChannelCommentsesByTvshow(tv).getResultList();
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
		Score _score = null;
		try {
			_score = Score.findScoresByRatedByAndTvshow(logonUser, tv).getSingleResult();
			uiModel.addAttribute("_score", _score);
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
				ps = ContractPayment.findContractPaymentsByContract(tvctc).getResultList();
				uiModel.addAttribute("contractPayments", ps);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return "tvshows/information";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html")
	public String doCreate(TVShow TVShow_, Principal principal, Model uiModel, HttpServletRequest httpServletRequest) {
		uiModel.asMap().clear();
		User inputer = User.findUsersByNameEquals(principal.getName()).getSingleResult();
		TVShow_.setInputter(inputer);
		TVShow_.setInputDate(new Date());
		TVShow_.setStatus(Status.findStatus(StatusUtil.DAI_TUI_JIAN));
		TVShow_.persist();
		return "redirect:/tvshows/generalInfo/" + URLStringUtil.encodeUrlPathSegment(TVShow_.getId().toString(), httpServletRequest);

	}

	@RequestMapping(value = "/doAjaxCreate", method = RequestMethod.POST, produces = "text/html")
	public @ResponseBody
	String doCreateAjaxSubmit(TVShow TVShow_, Model uiModel, HttpServletRequest httpServletRequest, Principal principal) {
		String message = "SUCCESS";
		try {
			TypedQuery<TVShow> query = TVShow.findTVShowsByNameEquals(TVShow_.getName());
			boolean isNotExsits = query.getResultList().isEmpty();
			if (isNotExsits) {
				User inputer = User.findUsersByNameEquals(principal.getName()).getSingleResult();
				TVShow_.setInputter(inputer);
				TVShow_.setInputDate(new Date());
				TVShow_.setStatus(Status.findStatus(StatusUtil.DAI_TUI_JIAN));
				TVShow_.persist();
			} else {
				message = "DUPLICATE";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			message = "FAILED";
		}
		// uiModel.addAttribute("message", message);
		return message;
	}

	@RequestMapping(value = "/update/{id}", params = "toUpdate", produces = "text/html")
	public String toUpdate(@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("TVShow_", TVShow.findTVShow(id));
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("people", new People());
		uiModel.addAttribute("company", new Company());
		uiModel.addAttribute("theme", new Theme());
		populateEditForm(uiModel);
		return "tvshows/update";
	}

	@RequestMapping(value = "/update/doUpdate/{sid}", method = RequestMethod.PUT, produces = "text/html")
	public String doUpdate(@PathVariable("sid") Integer sid, @Valid TVShow TVShow_, BindingResult bindingResult, Principal principal,
			Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("TVShow_", TVShow_);
			populateEditForm(uiModel);
			return "tvshows/update";
		}
		uiModel.asMap().clear();
		User logonUser = User.findUsersByNameEquals(principal.getName()).getSingleResult();
		Status _newST = TVShow_.getStatus();
		if (_newST.getId() != sid) {
			Status _oldST = Status.findStatus(sid);
			logger.warn("Warning:Status Changed==========================\r\n");
			logger.warn("\r\nTVShow: (id=" + TVShow_.getId() + ", name=" + TVShow_.getName() + ") status changed from \r\n"
					+ _oldST.getName() + "(" + _oldST.getId() + ") to " + _newST.getName() + "(" + _newST.getId() + ") by user:("
					+ logonUser.getStaff() + "[" + logonUser.getName() + "=" + logonUser.getId() + "])\r\n");
			logger.warn("Warning:Status Changed==========================\r\n");
		}
		TVShow_.merge();
		return "redirect:/tvshows/generalInfo/" + URLStringUtil.encodeUrlPathSegment(TVShow_.getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size, Principal principal, Model uiModel) {

		TVShow TVShow_ = TVShow.findTVShow(id);
		TVShow_.setRemoved(1);
		TVShow_.merge();
		User logonUser = User.findUsersByNameEquals(principal.getName()).getSingleResult();
		logger.warn("Warning:TVShow Removed=========================\r\n");
		logger.warn("TVShow: (id=" + TVShow_.getId() + ", name=" + TVShow_.getName() + ") removed by by user:(" + logonUser.getStaff()
				+ "[" + logonUser.getName() + "=" + logonUser.getId() + "])\r\n");
		logger.warn("Warning:Status Changed==========================\r\n");
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/tvshows/list";
	}

	@RequestMapping(value = "/confirmPurchase/{tvid}/{sid}", produces = "text/html")
	public String confirmPurchase(@PathVariable("tvid") Long tvid, @PathVariable("sid") Integer sid, String reason, Principal principal,
			Model uiModel, HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
		Status status = Status.findStatus(sid);
		TVShow tv = TVShow.findTVShow(tvid);

		User logonUser = User.findUsersByNameEquals(principal.getName()).getSingleResult();
		logger.warn("Warning:Status Changed==========================\r\n");
		if (sid == 8 || sid == 9 || sid == 10 || sid == 11) {
			tv.setComments(new String(reason.getBytes("ISO-8859-1"), "UTF-8"));
			logger.warn("\r\nTVShow: (id=" + tv.getId() + ", name=" + tv.getName() + ") status changed from \r\n"
					+ tv.getStatus().getName() + "(" + tv.getStatus().getId() + ") to " + status.getName() + "(" + status.getId()
					+ ") by user:(" + logonUser.getStaff() + "[" + logonUser.getName() + "=" + logonUser.getId() + "])\r\nReason:\r\n"
					+ new String(reason.getBytes("ISO-8859-1"), "UTF-8"));
		} else {
			logger.warn("\r\nTVShow: (id=" + tv.getId() + ", name=" + tv.getName() + ") status changed from \r\n"
					+ tv.getStatus().getName() + "(" + tv.getStatus().getId() + ") to " + status.getName() + "(" + status.getId()
					+ ") by user:(" + logonUser.getStaff() + "[" + logonUser.getName() + "=" + logonUser.getId() + "])\r\n");

		}
		logger.warn("Warning:Status Changed==========================\r\n");
		tv.setStatus(status);
		tv.merge();
		uiModel.addAttribute("tvshow_", tv);
		uiModel.addAttribute("itemId", tvid);
		return "redirect:/tvshows/generalInfo/" + URLStringUtil.encodeUrlPathSegment(tv.getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "list", produces = "text/html")
	public String list(@RequestParam(value = "stype", required = false) Integer stype,
			@RequestParam(value = "svalue", required = false) String svalue, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("tvshows", TVShowsFinder.findTVShowEntries(firstResult, sizeNo));
			float nrOfPages = (float) TVShowsFinder.countTVShows() / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		} else {
			if (stype != null) {
				if (stype == 0) {
					List<TVShow> data = TVShowsFinder.findTVShowByID(Long.parseLong(svalue));
					uiModel.addAttribute("tvshows", data);
				} else {
					try {
						uiModel.addAttribute("tvshows", TVShowsFinder.findTVShowsByName(svalue));
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

	@RequestMapping(value = "/queryTVShows4Json")
	public @ResponseBody
	JsonDataTable queryTVShows4Json(Page page, SearchTV stv) throws UnsupportedEncodingException {
		if (null == stv) {
			stv = new SearchTV();
		}
		JsonDataTable jdt = new JsonDataTable();
		if (page != null) {
			int sizeNo = page.getRows() == 0 ? 10 : page.getRows();
			final int firstResult = page == null ? 0 : (page.getPage() - 1) * sizeNo;
			List<TVShow> results = TVShowsFinder.findTVShows(stv, firstResult, page.getRows(), page.getSidx(), page.getSord());
			long count = TVShowsFinder.countTVShows(stv);
			jdt.setPage(page.getPage());
			List<JsonData> rows = new ArrayList<JsonData>();

			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			/**
			 * 
			 * colNames : [ 'ID', '剧名', '集数', '影视公司', '题材', '导演', '项目负责人', '状态',
			 * '录入时间' ],
			 * 
			 */
			for (TVShow t : results) {
				Object[] o = new Object[] { t.getId(), t.getName(), t.getCount(), t.getCompany().getName(), t.getTheme().getName(),
						t.getDirectors(), t.getProjector(), t.getStatus(), sdf.format(t.getInputDate()) };
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
		StringBuffer sb = new StringBuffer("SELECT o FROM People AS o WHERE o.name LIKE :name");
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
			tvname = new String(tvname.getBytes("ISO-8859-1"), "UTF-8");
			if (tvname.charAt(0) != '%') {
				tvname = "%" + tvname;
			}
			if (tvname.charAt(tvname.length() - 1) != '%') {
				tvname = tvname + "%";
			}
			EntityManager em = TVShow.entityManager();
			StringBuffer sb = new StringBuffer("SELECT o FROM TVShow AS o WHERE o.name LIKE :name and removed=0");
			Query query = em.createQuery(sb.toString(), TVShow.class);
			query.setParameter("name", tvname);
			List<TVShow> _data = query.getResultList();
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

	public class Jtvshow implements Serializable {
		/**
		 * 
		 */
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
