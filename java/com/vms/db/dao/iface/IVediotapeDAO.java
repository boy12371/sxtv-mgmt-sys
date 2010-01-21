package com.vms.db.dao.iface;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.vms.db.bean.Vediotape;

public interface IVediotapeDAO extends IBaseRootDAO {

	Vediotape getUniqueVedioByProperty(String propertyName, Object value)
			throws Exception;

	List<Vediotape> findVediosInPeriod(Date start, Date end,
			Map<String, Object> propertiesValues) throws Exception;

	void saveVedios(List<Vediotape> vedios) throws Exception;
}