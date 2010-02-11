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

	/**
	 * 查询指定列指定值的数量
	 * 
	 * @param clz
	 *            查询对象
	 * @param propertyName
	 *            列名
	 * @param value
	 *            值
	 * @return
	 * @throws Exception
	 */
	int getObjectTotalCountByFields(Class clz, String propertyName, Object value) throws Exception;

	/**
	 * 查询指定列名的数量
	 * 
	 * @param clz
	 *            查询对象
	 * @param propertyName
	 *            列名
	 * @return
	 * @throws Exception
	 */
	int getObjectTotalCount(Class clz, String propertyName) throws Exception;

	Serializable saveObject(Object object) throws Exception;

	void saveOrUpdateObject(Object object) throws Exception;

	void updateObject(Object object) throws Exception;

	void deleteObject(Object object) throws Exception;

	/***
	 * 根据给定列名的值，查询对象列表 startIndex 或 endIndex =-1 则不分页
	 * 
	 * @param clz
	 *            查询对象
	 * @param propertyName
	 *            列名
	 * @param value
	 *            值
	 * @param startIndex
	 * @param endIndex
	 * @param asceding
	 *            true=升序 false=降序
	 * @return
	 * @throws Exception
	 */
	List findObjectByField(Class clz, String propertyName, Object value, int startIndex, int endIndex, String orderPropertyName, boolean asceding)
			throws Exception;

	/***
	 * 查询对象列表 startIndex 或 endIndex =-1 则不分页
	 * 
	 * @param clz
	 *            查询对象
	 * @param propertiesValues
	 *            匹配条件，键值对 键=列名 值=值， 如果为null或empthy 则将查询所有
	 * @param startIndex
	 * @param endIndex
	 * @param propertyName
	 *            排序列的列名
	 * @param ascending
	 *            true=升序 false=降序
	 * @return
	 * @throws Exception
	 */
	List findObjectByFields(Class clz, Map<String, Object> propertiesValues, int startIndex, int endIndex,
			String propertyName, boolean ascending) throws Exception;

	List findAll(Class clz) throws Exception;

	Object getUniqueResultByProperty(Class clz, Map<String, Object> propertiesValues) throws Exception;

}
