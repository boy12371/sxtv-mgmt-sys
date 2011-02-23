package com.vms.service.iface;

import java.util.Date;
import java.util.List;

import com.vms.beans.VedioTapeVO;
import com.vms.db.bean.Playchangelog;
import com.vms.db.bean.Playorder;
import com.vms.db.bean.Subject;

public interface IArrangeService {
	public Date getFirstArrangedDate() throws Exception;
	
	public List<VedioTapeVO> findArrangedTapes(Date month, int subject) throws Exception;
	
	public void savePlayorder(List<Playorder> pos, int userID) throws Exception;
	
	public List<Playorder> findPlayorders(Date month,int subject) throws Exception;
	
	public void deletePlayOrder(List<Playorder> pos, int userID) throws Exception;
	
	public Playchangelog getDelInfoFormArrangeLog(String videoID) throws Exception;
	
	public List<Subject> getSubjects() throws Exception;
}
