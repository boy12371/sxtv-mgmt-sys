package com.vms.action.search;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.vms.beans.JSONDataTable;
import com.vms.beans.VedioTapeVO;
import com.vms.common.BaseAction;
import com.vms.common.JSONDataTableUtils;
import com.vms.common.SearchCondition;
import com.vms.db.bean.Audiencescore;
import com.vms.db.bean.Company;
import com.vms.db.bean.Playorder;
import com.vms.db.bean.Status;
import com.vms.db.bean.Subject;
import com.vms.db.bean.Topic;
import com.vms.db.bean.Vedioscore;
import com.vms.db.bean.Vediotape;
import com.vms.service.iface.ICompanyService;
import com.vms.service.iface.IStatusService;
import com.vms.service.iface.ISubjectService;
import com.vms.service.iface.ITopicService;
import com.vms.service.iface.IVediotapeService;

public class VedioSearchMgmt extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(VedioSearchMgmt.class);
	private IVediotapeService vedioService;
	private ICompanyService companyService;
	private ISubjectService subjectService;
	private ITopicService topicService;
	private IStatusService statusService;
	private JSONDataTable table;
	private Vediotape video;
	private SearchCondition sc;
	private String query;
	private boolean isSequenceOrder;
	private String vid;
	private String jsonData;

	public String toGenericSeaching() throws Exception {
		return this.SUCCESS;
	}

	public String autoCompleteForVideoName() throws Exception {
		List<String> names = vedioService
				.findVideoNamesForAutoComplete(java.net.URLDecoder.decode(
						query, "UTF-8"));
		List<VideoNameJSON> nameList = new ArrayList<VideoNameJSON>();
		table = new JSONDataTable();
		if (names != null && !names.isEmpty()) {
			int size = names.size();
			for (int i = 0; i < size; i++) {
				nameList.add(new VideoNameJSON(names.get(i)));
			}
		}
		table.setRecords(nameList);
		return SUCCESS;
	}

	public String searchVideoByName() throws Exception {
		try {
			video = vedioService.getVediotapeByName(query);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		if (video != null) {
			return SUCCESS;
		} else {
			this.addActionError("影带未找到");
			return INPUT;
		}

	}

	public String toVideoDetail() throws Exception {
		List<Vediotape> list = vedioService.findVediotapeByProperty(
				Vediotape.PROP_ID, vid, -1, -1, "", false);
		if (list != null && !list.isEmpty()) {
			this.video = list.get(0);
			return SUCCESS;
		} else {
			this.addActionError("影带未找到");
			return INPUT;
		}

	}

	public String searchVideos() throws Exception {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<Vediotape> dataList = new ArrayList<Vediotape>();
			List<VideoVO> vList = new ArrayList<VideoVO>();
			dataList = this.vedioService.findVidesByConditions(sc, table
					.getSort(), table.getStartIndex(), table.getRowsPerPage(),
					table.getDir().equals(JSONDataTableUtils.SORT_DIRECTION));
			for (Vediotape vediotape : dataList) {
				VideoVO vv = new VideoVO(vediotape);
				vList.add(vv);
			}
			JSONDataTableUtils.setupJSONDataTable(vList, table,
					vedioService.getTotalCountForVidesByConditions(sc));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);

		}
		return this.SUCCESS;
	}

	public String toPrintVideosReport() throws UnsupportedEncodingException {
		query = java.net.URLDecoder.decode(query, "UTF-8");
		return SUCCESS;
	}

	public String doPrintVideosReport() throws Exception {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<Vediotape> dataList = new ArrayList<Vediotape>();
			List<VedioTapeVO> tapeVOs = new ArrayList<VedioTapeVO>();
			dataList = this.vedioService.findVidesByConditions(sc, table
					.getSort(), table.getStartIndex(), table.getRowsPerPage(),
					table.getDir().equals(JSONDataTableUtils.SORT_DIRECTION));
			for (Vediotape v : dataList) {
				VedioTapeVO tapev = new VedioTapeVO(v);
				tapeVOs.add(tapev);
			}
			JSONDataTableUtils.setupJSONDataTable(tapeVOs, table,
					tapeVOs.size());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		return this.SUCCESS;
	}

	public String toPrintVideosSequenceOrderReport()
			throws UnsupportedEncodingException {
		query = java.net.URLDecoder.decode(query, "UTF-8");
		return SUCCESS;
	}

	public String doPrintVideosSequenceOrderReport() throws Exception {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<Vediotape> dataList = this.vedioService
					.findVideosByRateOrder(
							sc,
							table.getSort(),
							table.getStartIndex(),
							table.getRowsPerPage(),
							table.getDir().equals(
									JSONDataTableUtils.SORT_DIRECTION));
			JSONDataTableUtils.setupJSONDataTable(dataList, table,
					dataList.size());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
		}
		return this.SUCCESS;
	}

	public String toVideoSequence() {
		return SUCCESS;
	}

	public String doSearchAndSequenceVideos() {
		table = JSONDataTableUtils.initJSONDataTable(getRequest());
		try {
			List<Vediotape> dataList = this.vedioService
					.findVideosByRateOrder(
							sc,
							table.getSort(),
							table.getStartIndex(),
							table.getRowsPerPage(),
							table.getDir().equals(
									JSONDataTableUtils.SORT_DIRECTION));
			JSONDataTableUtils.setupJSONDataTable(dataList, table,
					dataList.size());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);

		}
		return this.SUCCESS;
	}

	public List<Company> getComList() throws Exception {
		return companyService.findAllCompany(-1, -1, Company.PROP_ID, true,
				false);
	}

	public List<Topic> getTopList() throws Exception {
		return topicService.findAllTopics(-1, -1, Topic.PROP_ID, true, false);
	}

	public List<Subject> getSubList() throws Exception {
		return subjectService.findAllSubjects(-1, -1, Subject.PROP_ID, true,
				false);
	}

	public List<Status> getStatusList() throws Exception {
		return statusService.findAllStatus(-1, -1, Status.PROP_ID, true);
	}

	public IVediotapeService getVedioService() {
		return vedioService;
	}

	public void setVedioService(IVediotapeService vedioService) {
		this.vedioService = vedioService;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public class VideoNameJSON {
		public String vname;

		public VideoNameJSON(String vname) {
			this.vname = vname;
		}

		public String getVname() {
			return vname;
		}

		public void setVname(String vname) {
			this.vname = vname;
		}

	}

	public class VideoVO {
		private java.lang.String id;

		// fields
		private java.lang.String vedioName;
		private java.util.Date dateComing;
		private java.util.Date dateInput;
		private java.util.Date dateStore;
		private java.util.Date playDate;
		private java.lang.String comments;
		private float marketShare = 0;
		private float audienceRating = 0;
		private com.vms.db.bean.Topic topic;
		private com.vms.db.bean.Subject subject;
		private com.vms.db.bean.Company companyID;
		private Status status;
		// collections

		private String award = "";
		private String purchase = "";
		private String avgScore = "0";
		private String audiScore = "";

		public VideoVO(Vediotape tape) {
			this.setId(tape.getId());
			this.setVedioName(tape.getVedioName());
			this.setDateComing(tape.getDateComing());
			this.setDateInput(tape.getDateInput());
			this.setDateStore(tape.getDateStore());
			Set<Playorder> orders = tape.getPlayorders();
			if(null!=orders && !orders.isEmpty()){
				Playorder[] or = orders.toArray(new Playorder[]{});
				this.setPlayDate(or[0].getPlayDate());
			}else{
				this.setPlayDate(null);
			}
			
			this.setComments(tape.getComments());
			this.setMarketShare(tape.getMarketShare() != null ? tape
					.getMarketShare() : 0);
			this.setAudienceRating(tape.getAudienceRating() != null ? tape
					.getAudienceRating() : 0);
			Topic t = new Topic();
			t.setId(tape.getTopic().getId());
			t.setTopicName(tape.getTopic().getTopicName());
			this.setTopic(t);
			Subject s = new Subject();
			s.setId(tape.getSubject().getId());
			s.setSubjectName(tape.getSubject().getSubjectName());
			this.setSubject(s);
			Company c = new Company();
			c.setId(tape.getCompanyID().getId());
			c.setCompanyName(tape.getCompanyID().getCompanyName());
			this.setCompanyID(c);
			Status st = new Status();
			st.setId(tape.getStatus().getId());
			st.setStatus(tape.getStatus().getStatus());
			this.setStatus(st);
			if (null != tape.getVedioscores()
					&& 0 != tape.getVedioscores().size()) {
				float sum = 0;
				int award = 0;
				int unaward = 0;
				int purchase = 0;
				int unpurchase = 0;
				for (Vedioscore s1 : tape.getVedioscores()) {
					sum += s1.getScore();

					if (s1.getAward() == 1) {
						award++;
					} else {
						unaward++;
					}
					if (s1.getPurchase() == 1) {
						purchase++;
					} else {
						unpurchase++;
					}
				}
				if (0 != sum) {
					float _avgScore = sum / tape.getVedioscores().size();
					DecimalFormat df = new DecimalFormat("###,###,###.##");
					this.avgScore = df.format(_avgScore);
				}
				this.award = award + "是 / " + unaward + "否";
				this.purchase = purchase + "买 / " + unpurchase + " 否";
			}
			if (null != tape.getAudiencescore()
					&& 0 != tape.getAudiencescore().size()) {
				int look = 0;
				int unlook = 0;
				for (Audiencescore as : tape.getAudiencescore()) {
					if (as.getResult() == 1) {
						look++;
					} else {
						unlook++;
					}
				}
				this.audiScore = look + "看 / " + unlook + "不看";

			}

		}

		public java.lang.String getId() {
			return id;
		}

		public void setId(java.lang.String id) {
			this.id = id;
		}

		public java.lang.String getVedioName() {
			return vedioName;
		}

		public void setVedioName(java.lang.String vedioName) {
			this.vedioName = vedioName;
		}

		public java.util.Date getDateComing() {
			return dateComing;
		}

		public void setDateComing(java.util.Date dateComing) {
			this.dateComing = dateComing;
		}

		public java.util.Date getDateInput() {
			return dateInput;
		}

		public void setDateInput(java.util.Date dateInput) {
			this.dateInput = dateInput;
		}

		public java.lang.String getComments() {
			return comments;
		}

		public void setComments(java.lang.String comments) {
			this.comments = comments;
		}

		public com.vms.db.bean.Topic getTopic() {
			return topic;
		}

		public void setTopic(com.vms.db.bean.Topic topic) {
			this.topic = topic;
		}

		public com.vms.db.bean.Status getStatus() {
			return status;
		}

		public void setStatus(com.vms.db.bean.Status status) {
			this.status = status;
		}

		public com.vms.db.bean.Subject getSubject() {
			return subject;
		}

		public void setSubject(com.vms.db.bean.Subject subject) {
			this.subject = subject;
		}

		public com.vms.db.bean.Company getCompanyID() {
			return companyID;
		}

		public void setCompanyID(com.vms.db.bean.Company companyID) {
			this.companyID = companyID;
		}

		public String getAward() {
			return award;
		}

		public void setAward(String award) {
			this.award = award;
		}

		public String getPurchase() {
			return purchase;
		}

		public void setPurchase(String purchase) {
			this.purchase = purchase;
		}

		public String getAvgScore() {
			return avgScore;
		}

		public void setAvgScore(String avgScore) {
			this.avgScore = avgScore;
		}

		public String getAudiScore() {
			return audiScore;
		}

		public void setAudiScore(String audiScore) {
			this.audiScore = audiScore;
		}

		public float getMarketShare() {
			return marketShare;
		}

		public void setMarketShare(float marketShare) {
			this.marketShare = marketShare;
		}

		public float getAudienceRating() {
			return audienceRating;
		}

		public void setAudienceRating(float audienceRating) {
			this.audienceRating = audienceRating;
		}

		
		public java.util.Date getDateStore() {
			return dateStore;
		}

		
		public void setDateStore(java.util.Date dateStore) {
			this.dateStore = dateStore;
		}

		public java.util.Date getPlayDate() {
			return playDate;
		}

		public void setPlayDate(java.util.Date playDate) {
			this.playDate = playDate;
		}
	}

	public JSONDataTable getTable() {
		return table;
	}

	public void setTable(JSONDataTable table) {
		this.table = table;
	}

	public Vediotape getVideo() {
		return video;
	}

	public void setVideo(Vediotape video) {
		this.video = video;
	}

	public ICompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(ICompanyService companyService) {
		this.companyService = companyService;
	}

	public ISubjectService getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubjectService subjectService) {
		this.subjectService = subjectService;
	}

	public ITopicService getTopicService() {
		return topicService;
	}

	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}

	public IStatusService getStatusService() {
		return statusService;
	}

	public void setStatusService(IStatusService statusService) {
		this.statusService = statusService;
	}

	public SearchCondition getSc() {
		return sc;
	}

	public void setSc(SearchCondition sc) {
		this.sc = sc;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public boolean isSequenceOrder() {
		return isSequenceOrder;
	}

	public void setSequenceOrder(boolean isSequenceOrder) {
		this.isSequenceOrder = isSequenceOrder;
	}

}
