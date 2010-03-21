package com.vms.service.iface;

import java.util.List;

import com.vms.db.bean.Scorelevel;

public interface IScorelevelService {
	
	List<Scorelevel> findAllScorelevel()throws Exception;
	
	void deleteLevel(int id)throws Exception;
	
	void createLevel(Scorelevel level)throws Exception;
	
	void updateLevel(Scorelevel level) throws Exception;

}
