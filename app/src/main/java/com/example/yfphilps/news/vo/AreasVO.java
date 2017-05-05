/**
 * Project:whzyapp
 * File:AreasVO.java
 * Copyright 2013 WUZHUPC Co., Ltd. All rights reserved.
 */
package com.example.yfphilps.news.vo;

import android.content.Context;

import com.example.yfphilps.news.config.Constants;

import net.lawyee.mobilelib.utils.JavaLangUtil;
import net.lawyee.mobilelib.utils.SettingUtil;
import net.lawyee.mobilelib.utils.StringUtil;
import net.lawyee.mobilelib.utils.TimeUtil;
import net.lawyee.mobilelib.vo.BaseVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * @author wuzhu
 * @date 2013-12-17 下午2:12:36
 * @version $id$
 */
public class AreasVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6292576707373842576L;

	public static final long CLONG_FATHERAID = 0l;

	private String areaname;

	private String areaabbr;

	private int fatheraid;

	private String areacode;

	private String areacode2;

	/**
	 * 服务端1为不可见，0为可见
	 */
	private boolean isshow;

	private int sort;

	/**
	 * 客户端排序顺序，初始化时为fatheraid*max(sort)+sort
	 */
	private int clientsort;

	/**
	 * 用户是否选择可见
	 */
	private boolean useshow;

	private Date lastUpdateDataTime;

	/**
	 * 数据存储文件名(注意，这个是读取单个对象的名称，读取列表请使用dataListFileName)
	 * 
	 * @return
	 */
	public static String dataFileName() {
		return dataFileName(serialVersionUID);
	}

	/**
	 * 数据列表存储文件名
	 * 
	 * @return
	 */
	public static String dataListFileName() {
		return dataListFileName(serialVersionUID);
	}

	public long getAid() {
		return this.id;
	}

	public void setAid(String aid) {
		this.id = JavaLangUtil.StrToLong(aid, 0l);
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getAreaabbr() {
		return areaabbr;
	}

	public void setAreaabbr(String areaabbr) {
		this.areaabbr = areaabbr;
	}

	public long getFatheraid() {
		return fatheraid;
	}

	public void setFatheraid(int fatheraid) {
		this.fatheraid = fatheraid;
	}

	public void setFatheraid(String fatheraid) {
		this.fatheraid = JavaLangUtil.StrToInteger(fatheraid, 0);
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getAreacode2() {
		return areacode2;
	}

	public void setAreacode2(String areacode2) {
		this.areacode2 = areacode2;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public void setSort(String sort) {
		this.sort = JavaLangUtil.StrToInteger(sort, 0);
	}

	public int getClientsort() {
		return clientsort;
	}

	public void setClientsort(int clientsort) {
		this.clientsort = clientsort;
	}

	public boolean isUseshow() {
		return useshow;
	}

	public void setUseshow(boolean useshow) {
		this.useshow = useshow;
	}

	public boolean isshow() {
		return isshow;
	}

	public void setIsshow(boolean isshow) {
		this.isshow = isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow =  !JavaLangUtil.StrToBool(isshow,false);
	}

	/**
	 * 合并新旧区域数据（主要是用户选择是否显示），并生成新的排序号
	 * 
	 * @param navos
	 * @param avos
	 * @return
	 */
	public static ArrayList<AreasVO> processNewAreas(ArrayList<AreasVO> navos,
			ArrayList<AreasVO> avos) {
		if (navos == null || navos.isEmpty())
			return null;
		int maxsort = getAreasVOMaxSort(navos);
		if (avos == null || avos.isEmpty()) {
			for (int i = 0; i < navos.size(); i++) {
				AreasVO avo = navos.get(i);
				avo.setUseshow(true);
				avo.setClientsort(avo.fatheraid * maxsort + avo.sort);
			}
			return navos;
		}
		for (int i = 0; i < navos.size(); i++) {
			AreasVO navo = navos.get(i);
			boolean bfind = false;
			for (int j = 0; j < avos.size(); j++) {
				AreasVO avo = avos.get(j);
				if (navo.getAid() == avo.getAid()) {
					bfind = true;
					navo.setUseshow(avo.isUseshow());
					break;
				}
			}
			if (!bfind)
				navo.setUseshow(true);
			navo.setClientsort(navo.fatheraid * maxsort + navo.sort);
		}
		return navos;
	}

	/**
	 * 获取排序后的区域列表
	 * 
	 * @param avos
	 * @param filterusershow
	 *            是否要过滤掉用户选择不显示的
	 * @return
	 */
	public static ArrayList<AreasVO> getAreasVOWithSort(
			ArrayList<AreasVO> avos, boolean filterusershow) {
		if (avos == null || avos.isEmpty())
			return null;
		ArrayList<AreasVO> result = new ArrayList<AreasVO>();
		for (int i = 0; i < avos.size(); i++) {
			AreasVO avo = avos.get(i);
			if (filterusershow && !avo.isUseshow())
				continue;
			result.add(avo);
		}
		Collections.sort(result, new SortByClientSort());
		return result;
	}


	public static ArrayList<AreasVO> getAreasVOWithShow(ArrayList<AreasVO> avos)
	{
		if (avos == null || avos.isEmpty())
			return null;
		ArrayList<AreasVO> result = new ArrayList<AreasVO>();
		for (int i = 0; i < avos.size(); i++) {
			AreasVO avo = avos.get(i);
			if (avo.isshow())
				result.add(avo);
		}
		return result;
	}

	public static AreasVO getDefaultAreasVO(ArrayList<AreasVO> avos) {
		if (avos == null || avos.isEmpty())
			return null;
		for (int i = 0; i < avos.size(); i++) {
			AreasVO avo = avos.get(i);
			if (avo.fatheraid == CLONG_FATHERAID)
				return avo;
		}
		return null;
	}

	private static int getAreasVOMaxSort(ArrayList<AreasVO> avos) {
		if (avos == null || avos.isEmpty())
			return 0;
		int maxsort = 0;
		for (int i = 0; i < avos.size(); i++) {
			if (avos.get(i).sort > maxsort)
				maxsort = avos.get(i).sort;
		}
		return maxsort;
	}

	public Date getLastUpdateDataTime() {
		return lastUpdateDataTime;
	}

	/**
	 * 是否必须更新
	 * 
	 * @param c
	 * @return
	 */
	public boolean isMustUpdate(Context c, long cid) {
		Date last = getLastUpdateDataTime(c, cid);
		if (last == null)
			return true;
		return TimeUtil.interval(new Date(), last) > Constants.CINT_EFFECTIVE_NEWS_TIME;
	}

	public Date getLastUpdateDataTime(Context c, long cid) {
		if (lastUpdateDataTime == null) {
			// 读取设置项
			lastUpdateDataTime = getAreaLastUpdateTime(c, cid, fatheraid, id);
		}

		return lastUpdateDataTime;
	}

	public void setLastUpdateDataTime(Context c, long cid,
			Date lastUpdateDataTime) {
		this.lastUpdateDataTime = lastUpdateDataTime;
		// 存取配置信息
		setAreaLastUpdateTime(c, cid, fatheraid, id, lastUpdateDataTime);
	}
	
	
	public void setLastUpdateDataTime(Context c,
			Date lastUpdateDataTime) {
		this.lastUpdateDataTime = lastUpdateDataTime;
		// 存取配置信息
		setAreaLastUpdateTime(c, fatheraid, id, lastUpdateDataTime);
	}

	/**
	 * 获取父级区域
	 * 
	 * @param avos
	 * @return
	 */
	public AreasVO getFatherAreasVO(ArrayList<AreasVO> avos) {
		if (avos == null || avos.isEmpty() || fatheraid == CLONG_FATHERAID
				|| fatheraid == getAid())
			return null;
		for (int i = 0; i < avos.size(); i++) {
			AreasVO avo = avos.get(i);
			if (fatheraid == (int) avo.getAid())
				return avo;
		}
		return null;
	}

	/**
	 * 取得当前所属级别,从0开始
	 * 
	 * @param avos
	 * @return
	 */
	public int getLevel(ArrayList<AreasVO> avos) {
		if (avos == null || avos.isEmpty())
			return 0;
		if (fatheraid == CLONG_FATHERAID)
			return 0;
		AreasVO favo = getFatherAreasVO(avos);
		if (favo == null)
			return 0;
		return favo.getLevel(avos) + 1;
	}

	/**
	 * 取得当前所属级别,从0开始
	 * 
	 * @param avos
	 * @return
	 */
	public static int getAreaAbbrMaxLen(ArrayList<AreasVO> avos) {
		if (avos == null || avos.isEmpty())
			return 0;
		int result = 0;
		for (int i = 0; i < avos.size(); i++) {
			AreasVO avo = avos.get(i);
			if (avo.getAreaabbr() != null
					&& avo.getAreaabbr().length() > result)
				result = avo.getAreaabbr().length();
		}
		return result;
	}

	/**
	 * 获取栏目列表数据缓存文件名称
	 * 
	 * @param vo
	 * @return
	 */
	public static String getCacheFileName(AreasVO vo, long cid) {
		return Constants.getDataStoreDir() + Constants.CSTR_DETAILDIR + cid
				+ "_" + vo.getFatheraid() + "_" + vo.getId() + ".aca";
	}
	
	/**
	 * 获取栏目列表数据缓存文件名称
	 * 
	 * @param vo
	 * @return
	 */
	public static String getCacheFileName(AreasVO vo) {
		return Constants.getDataStoreDir() + Constants.CSTR_DETAILDIR 
				+ "_" + vo.getFatheraid() + "_" + vo.getId() + ".aca";
	}

	/**
	 * 获取某栏目最后更新时间
	 * 
	 * @param c
	 * @param channelid
	 * @return　如果没有记录返回null
	 */
	public static Date getAreaLastUpdateTime(Context c, long cid,
			long fatherid, long aid) {
		String key = "areas_lastupdatetime_" + cid + "_" + fatherid + "_" + aid;

		String lastupdatetime = SettingUtil.getStringSettingValue(c, key, "");
		if (StringUtil.isEmpty(lastupdatetime))
			return null;
		return TimeUtil.strToDate(lastupdatetime, null);
	}

	/**
	 * 获取某栏目最后更新时间
	 * 
	 * @param c
	 * @param channelid
	 * @return
	 */
	public static void setAreaLastUpdateTime(Context c, long cid,
			long fatherid, long aid, Date lastupdatetime) {
		String key = "areas_lastupdatetime_" + cid + "_" + fatherid + "_" + aid;
		SettingUtil.setStringSettingValue(c, key,
				TimeUtil.dateToString(lastupdatetime));
	}
	
	
	/**
	 * 获取某栏目最后更新时间
	 * 
	 * @param c
	 * @param channelid
	 * @return
	 */
	public static void setAreaLastUpdateTime(Context c,
			long fatherid, long aid, Date lastupdatetime) {
		String key = "areas_lastupdatetime_"  + "_" + fatherid + "_" + aid;
		SettingUtil.setStringSettingValue(c, key,
				TimeUtil.dateToString(lastupdatetime));
	}

	/**
	 * 对区域进行排序,优先级:clientsort
	 * 
	 * @author wuzhu email:wuzhupc@gmail.com
	 * @version 创建时间：2012-11-25 下午10:02:00
	 */
	public static class SortByClientSort implements Comparator<AreasVO> {
		@Override
		public int compare(AreasVO arg0, AreasVO arg1) {
			if (arg0 == null || arg1 == null)
				return 0;
			return arg0.clientsort - arg1.clientsort;
		}
	}

}
