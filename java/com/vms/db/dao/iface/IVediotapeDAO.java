package com.vms.db.dao.iface;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.type.Type;

import com.vms.db.bean.Status;
import com.vms.db.bean.Vediotape;

public interface IVediotapeDAO extends IBaseRootDAO {

	List<Vediotape> findVedioesByStatus(Status status, int startIndex, int endIndex) throws Exception;

	/**
	 * 查询所有状态为待审、重审、修改、通过 的影带总数
	 * @return
	 * @throws Exception
	 */
	int getTotalCountForAllVideotapesForAudit()throws Exception;

	void saveObjects(List<Vediotape> objects);

	/**
	 * 根据一定条件 查询时间段内的数据。
	 * 
	 * @param dateStart
	 *            开始时间
	 * @param dateEnd
	 *            结束时间
	 * @param propertiesValues
	 *            匹配条件， 若为空或null,则不匹配条件
	 * @param startIndex
	 * @param endIndex
	 * @param propertyName
	 *            排序列
	 * @param ascending
	 *            true=升序, false=降序
	 * @return
	 */
	List<Vediotape> findVedioesInPeriod(Date dateStart, Date dateEnd, Map<String, Object> propertiesValues,
			int startIndex, int endIndex, String propertyName, boolean ascending);

	/**
	 * 查询某一范围内的数据
	 * @param scopeName 限定列名
	 * @param propertyName 排序列名
	 * @param startIndex
	 * @param endIndex
	 * @param asceding
	 * @return
	 * @throws Exception
	 */
	List<Vediotape> findAllVideosInScope(String scopeName,Object[] values, String propertyName, int startIndex, int endIndex, boolean asceding)
			throws Exception;

	int getVedioTotalCountByStatus(Status status) throws Exception;
	
	boolean updateVideotape(String hql,Object[] args)throws Exception;
	

	
	Object findVideos(String hql,Map<String,Object[]> valuesTypes,int startIndex,int endIndex)throws Exception;
	
	List<Vediotape> findVideosByFieldsNamePartiallyDateInScope(Map<String,Object> fieldsValues ,int startIndex, int endIndex, String orderProperty, boolean ascending)throws Exception;
}