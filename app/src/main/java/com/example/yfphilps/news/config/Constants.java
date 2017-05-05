package com.example.yfphilps.news.config;

import net.lawyee.mobilelib.utils.TimeUtil;

/**
 * 常量
 * 
 * @author wuzhu
 * 
 */
public class Constants extends net.lawyee.mobilelib.Constants {

	// APP_ID 替换为你的应用从官方网站申请到的合法appId
	public static final String WXAPP_ID = "wxddb9232109204c8b";

	/**
	 * 是否可以设置服务器信息，如果可以，则在启动时会检测是否能连接上服务器，不能连接就弹出设置框，并且在设置里增加一个服务器设置的选项
	 */
	public static final Boolean CBOOL_CANCHANGESERVER = false;

	/**
	 * 默认请求记录数
	 */
	public static final int CINT_PAGE_SIZE = 20;
	/**
	 * 资讯数据有效时间
	 */
	public static final long CINT_EFFECTIVE_NEWS_TIME = 30l * TimeUtil.CINT_TIME_MINUTE;

}
