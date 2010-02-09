package com.vms.db.dao.iface;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.vms.db.bean.Status;
import com.vms.db.bean.Vediotape;

public interface IVediotapeDAO extends IBaseRootDAO {

	List<Vediotape> findVedioesByStatus(Status status, int startIndex, int endIndex) throws Exception;

	public int getVedioTotalCountByStatus(Status status) throws Exception;

	void saveObjects(List<Vediotape> objects);

	/**
	 * 根据一定条件 查询时间段内的数据。
	 * @param dateStart  开始时间
	 * @param dateEnd	结束时间
	 * @param propertiesValues 匹配条件， 若为空或null,则不匹配条件
	 * @param startIndex  
	 * @param endIndex
	 * @param propertyName	排序列
	 * @param ascending	true=升序, false=降序
	 * @return
	 */
	List<Vediotape> findVedioesInPeriod(Date dateStart, Date dateEnd, Map<String, Object> propertiesValues,
			int startIndex, int endIndex, String propertyName, boolean ascending);

}