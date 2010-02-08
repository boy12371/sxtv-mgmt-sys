package com.vms.action.vediotape;

import java.io.PrintWriter;
import java.util.List;

import com.vms.common.BaseAction;
import com.vms.db.bean.Company;
import com.vms.db.bean.Status;
import com.vms.db.bean.Subject;
import com.vms.db.bean.Topic;
import com.vms.db.bean.Vediotape;
import com.vms.service.iface.*;

public class VediotapeMgmtAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IVediotapeService vedioService;
	private ICompanyService companyService;
	private ISubjectService subjectService;
	private ITopicService topicService;

	private Vediotape vedio;

	public String toAddingVedio() throws Exception {	
		return SUCCESS;
	}

	public String doAddingVedio() {

		return this.SUCCESS;
	}

	public List<Company> getComList() throws Exception{
		return companyService.findAllCompany(-1, -1, Company.PROP_ID, true);		
	}


	public List<Topic> getTopList() throws Exception{
		return topicService.findAllTopics(-1,-1, Topic.PROP_ID, true);
	}
	
	public List<Subject> getSubList() throws Exception{
		return subjectService.findAllSubjects(-1, 01, Subject.PROP_ID, true);
	}
	
	
	
	public String getCompaniesStr() throws Exception {
		StringBuffer sb =new StringBuffer("[");
		List<Company>  coms = companyService.findAllCompany(-1, -1, Company.PROP_ID, true);
		int size =coms.size();
		for (int i=0;i<size;i++) {
			Company c = coms.get(i);
			if(i!=0 && i<size ){
				sb.append(",");
			}
			sb.append("{\"id\":\""+c.getId()+"\",\"companyName\":\""+c.getCompanyName()+"\"}");
			
		}
		sb.append("]");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.close();
		return NONE;
	}

	public ICompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(ICompanyService companyService) {
		this.companyService = companyService;
	}

	public IVediotapeService getVedioService() {
		return vedioService;
	}

	public void setVedioService(IVediotapeService vedioService) {
		this.vedioService = vedioService;
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

	public Vediotape getVedio() {
		return vedio;
	}

	public void setVedio(Vediotape vedio) {
		this.vedio = vedio;
	}

	

}
