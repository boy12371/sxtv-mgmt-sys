package com.vms.service.iface;

import java.util.List;

import com.vms.db.bean.Audience;

public interface IAudicenceService {

	void addAudience(Audience audience) throws Exception;

	void deleteAudience(Audience audience) throws Exception;

	void deleteAudience(int id) throws Exception;

	List<Audience> findAudience(int startIndex, int endIndex, String propertyName,
			boolean ascending) throws Exception;
	
	int getTotalCount();
}
