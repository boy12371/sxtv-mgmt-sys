/**
 * 
 */
package com.vms.db.dao.iface;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.SimpleExpression;

/**
 * @author alexdong
 * 
 */
public interface IBaseRootDAO {

	Object get(Class clz, Serializable id) throws Exception;

	Object load(Class clz, Serializable id) throws Exception;

//	List findAll(Order order, Class clz) throws Exception;
//
//	List findFiltered(String propName, SimpleExpression simpleExpression,
//			Order order, Class clz) throws Exception;

	Serializable save(Object object) throws Exception;

	void saveOrUpdate(Object object) throws Exception;

	void update(Object object) throws Exception;

	void delete(Object object) throws Exception;

}
