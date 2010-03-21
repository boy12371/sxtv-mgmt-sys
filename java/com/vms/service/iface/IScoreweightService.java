package com.vms.service.iface;

import java.util.List;

import com.vms.db.bean.Scoreweight;

public interface IScoreweightService {

	List<Scoreweight> findAllScoreweight() throws Exception;

	void deleteLevel(int id) throws Exception;

	void createLevel(Scoreweight weight) throws Exception;

	void updateLevel(Scoreweight weight) throws Exception;

}
