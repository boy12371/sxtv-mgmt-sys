/**
 * 
 */
package com.vms.db.dao.iface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.SimpleExpression;

import com.vms.db.bean.Status;

/**
 * @author alexdong
 * 
 */
public interface IBaseRootDAO {

	Object getObject(Class clz, Serializable id) throws Exception;

	Object loadObject(Class clz, Serializable id) throws Exception;

	
	
	int getObjectTotalCount(Class clz, String propertyName)throws Exception;

	Serializable saveObject(Object object) throws Exception;

	void saveOrUpdateObject(Object object) throws Exception;

	void updateObject(Object object) throws Exception;

	void deleteObject(Object object) throws Exception;
	
	List findObjectByField(Class clz,String propertyName, Object value,
			int startIndex, int endIndex, boolean asceding) throws Exception;

	List findObjectByFields(Class clz, Map<String, Object> propertiesValues,
			int startIndex, int endIndex, String propertyName, boolean ascending)
			throws Exception;

}
