package com.vms.db.dao.iface;

import java.util.List;

import com.vms.db.bean.User;


public interface IVedioscoreDAO extends IBaseRootDAO {
	public List<User> findAllExaminer() throws Exception;
}
