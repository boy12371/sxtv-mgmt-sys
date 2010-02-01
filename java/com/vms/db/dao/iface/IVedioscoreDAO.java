package com.vms.db.dao.iface;

import java.util.List;

import com.vms.db.bean.Vedioscore;

public interface IVedioscoreDAO extends IBaseRootDAO {
	public Vedioscore getUniqueVedioscore(String propertyName, Object value) throws Exception;
	public void saveVedioscores(List<Vedioscore> vedios) throws Exception;
}
