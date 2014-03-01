package com.vms.db.dao.iface;

import java.util.List;

import com.vms.db.bean.User;
import com.vms.db.bean.Vedioscore;
import com.vms.db.bean.Vediotape;


public interface IVedioscoreDAO extends IBaseRootDAO {
	public List<User> findAllExaminer() throws Exception;
	
	public List<Vedioscore> findScoresOfUserAndTapes(User user, String tapes) throws Exception;
}
