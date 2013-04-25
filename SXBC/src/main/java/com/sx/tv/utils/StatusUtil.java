package com.sx.tv.utils;

public class StatusUtil {

	/***
	('待推荐','完成基本信息收集',0),
	('初选','等待评分',0),
	('推荐中','已评分',0),
	('定片','等待购买',0),
	('待播','已购买，等待播放',0),
	('已播','剧目已播放,结束',0),
	('结束','所有程序已完成',0),
	('初选淘汰','初选淘汰',0),
	('评分淘汰','中心审看未通过',0),
	('频道淘汰','频道审核未通过',0),
	('购买失败','未能购买剧目',0);
	('终止合同','终止合同，停止播放',0);
	**/
	
	
	/**
	 * 待推荐
	 */
	public static final int DAI_TUI_JIAN = 1;
	
	/**
	 * 初选
	 */
	public static final int CHU_XUAN = 2;
	
	/**
	 *推荐中
	 */
	public static final int TUI_JIAN_ZHONG= 3;
	
	/**
	 * 定片
	 */
	public static final int DING_PIAN = 4;
	
	/**
	 * 待播
	 */
	public static final int DAI_BO = 5;
	
	/**
	 * 已播
	 */
	public static final int YI_BO = 6;
	
	/**
	 * 结束
	 */
	public static final int JIE_SHU = 7;
	
	/**
	 * 初选淘汰
	 */
	public static final int CHU_XUAN_TAO_TAI = 8;
	
	/**
	 * 评分淘汰
	 */
	public static final int PING_FEN_TAO_TAI = 9;
	
	
	/**
	 * 频道淘汰
	 */
	public static final int PIN_DAO_TAO_TAI = 10;
	

	
	/**
	 * 购买失败
	 */
	public static final int GOU_MAI_SHI_BAI = 11;
	
	

	

}
