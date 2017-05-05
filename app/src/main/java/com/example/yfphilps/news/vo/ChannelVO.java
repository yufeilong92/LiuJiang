package com.example.yfphilps.news.vo;

import android.content.Context;
import android.util.Log;

import com.example.yfphilps.news.config.Constants;

import net.lawyee.mobilelib.utils.FileUtil;
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
 * 栏目信息
 * 
 * @author wuzhu
 * @date 2013-4-21 下午5:10:39
 * @version $id$
 */
public class ChannelVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -802998074172715366L;

	/************** 三种反馈信箱 **************/
	public static final String[] CSTRS_XXCHANNELMENUS = { "院长信箱", "举报信箱",
			"涉诉信箱" };

	/**
	 * 父类型的栏目ID
	 */
	public static final int CHANNELID_FATHER = 0;
	/**
	 * 普通,子类型
	 */
	public static final int CHANNEL_TYYPE_NORMAL = 0;
	/**
	 * 新闻,父类型
	 */
	public static final int CHANNEL_TYYPE_NEWS = 1;
	/**
	 * 图片,子类型
	 */
	public static final int CHANNEL_TYYPE_PIC = 5;
	/**
	 * 案件公开,子类型
	 */
	public static final int CHANNEL_TYYPE_LZFYAJGK = 12;
	/**
	 * 信箱,子类型
	 */
	public static final int CHANNEL_TYYPE_LZFYXX = 13;
	/**
	 * 咨询,子类型
	 */
	public static final int CHANNEL_TYYPE_CONSULT = 14;
	/**
	 * 文书,子类型
	 */
	public static final int CHANNEL_TYYPE_LZFYWS = 15;
	/**
	 * 公告,子类型
	 */
	public static final int CHANNEL_TYYPE_LZFYGG = 16;
	/**
	 * 执行公开,子类型
	 */
	public static final int CHANNEL_TYYPE_LZFYZXGK = 17;
	/**
	 * 应用内链接,子类型
	 */
	public static final int CHANNEL_TYYPE_APPLINK = 97;
	/**
	 * 应用外链接,子类型
	 */
	public static final int CHANNEL_TYYPE_OUTLINK = 98;
	/**
	 * 其他类型,子类型
	 */
	public static final int CHANNEL_TYYPE_OTHER = 99;
	/**
	 * 系统设置
	 */
	public static final int CHANNEL_TYYPE_SYSTEMCONFIG = -999;

	/**
	 * 栏目名称
	 */
	private String channelName = "";
	/**
	 * 父栏目编号,顶级栏目的值为CHANNELID_FATHER
	 */
	private long fatherchannelID;

	/**
	 * 是否默认显示：1是；0否
	 */
	private int isdefault;

	/**
	 * 是否展示多区域切换字段：1是；0否
	 */
	private int isshowareas;

	/**
	 * 栏目顺序
	 */
	private int sort;

	/**
	 * 栏目类型: 0 普通 5 图片
	 */
	private int type;

/**
 * 是否有查找框   1显示包含查找框，0时不选择查找框	
 */
	private String ishassearch;
	
	/**
	 * 在手机端是否展示为类别选择，1时，将添加全部选项，并用下拉列表方式展示，0时子频道将以滚动选择来展示
	 */
	private String ishassel;
	
	/**
	 * 备注信息，如果链接类型为APP内链接或外部链接，则此值为链接地址
	 */
	private String remark;

	/**
	 * 是否是第一次载入
	 */
	private boolean isFirstLoad = true;

	/**
	 * 最后一次更新数据时间
	 */
	private Date lastUpdateDataTime;

	/**
	 * 
	 */
	private String logo_ld;

	private String logo_md;

	private String logo_hd;

	private String locallogo;

	public int getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(int isdefault) {
		this.isdefault = isdefault;
	}

	public boolean getIsshowareas() {
		return isshowareas == 1;
	}

	public void setIsshowareas(int isshowareas) {
		this.isshowareas = isshowareas;
	}

	public void setIsshowareas(String isshowareas) {
		this.isshowareas = JavaLangUtil.StrToInteger(isshowareas, 0);
	}

	public String getLogo_ld() {
		return logo_ld;
	}

	public void setLogo_ld(String log_ld) {
		this.logo_ld = log_ld;
	}

	public String getLogo_md() {
		return logo_md;
	}

	public void setLogo_md(String logo_md) {
		this.logo_md = logo_md;
	}

	public String getLogo_hd() {
		return logo_hd;
	}

	public void setLogo_hd(String logo_hd) {
		this.logo_hd = logo_hd;
	}
	
	
	



	public String getIshassearch() {
		return ishassearch;
	}

	public void setIshassearch(String ishassearch) {
		this.ishassearch = ishassearch;
	}

	public String getIshassel() {
		return ishassel;
	}

	public void setIshassel(String ishassel) {
		this.ishassel = ishassel;
	}

	/**
	 * 获取logo文件
	 * 
	 * @param screentwidth
	 * @param musthaslocfile
	 * @return
	 * @note 根据屏幕宽度自动获取所需的图片
	 */
	public String getLogo(int screentwidth, boolean musthaslocfile) {
		// 已经屏幕宽度 240,320,480,540,640,720,800,1020
		String result = logo_ld;
		if (screentwidth > 320)
			result = logo_md;
		if (screentwidth > 540)
			result = logo_hd;
		// 非空
		if (StringUtil.isEmpty(result)) {
			if (!StringUtil.isEmpty(logo_hd))
				result = logo_hd;
			if (!StringUtil.isEmpty(logo_md))
				result = logo_md;
			if (!StringUtil.isEmpty(logo_ld))
				result = logo_ld;
		}
		// 必须本地存在
		if (musthaslocfile) {
			if (FileUtil.isExistFile(result))
				return result;
			if (FileUtil.isExistFile(logo_hd))
				return result;
			if (FileUtil.isExistFile(logo_md))
				return result;
			if (FileUtil.isExistFile(logo_ld))
				return result;
			result = "";
		}

		return result;
	}

	public void setFatherchannelID(long fatherchannelID) {
		this.fatherchannelID = fatherchannelID;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setFirstLoad(boolean isFirstLoad) {
		this.isFirstLoad = isFirstLoad;
	}

	public boolean getIsFirstLoad() {
		return isFirstLoad;
	}

	public void setIsFirstLoad(boolean isFirstLoad) {
		this.isFirstLoad = isFirstLoad;
	}

	/**
	 * 新闻,父类型
	 * 
	 * @return
	 */
	public boolean isNewsChannel() {
		return fatherchannelID == CHANNELID_FATHER
				&& type == CHANNEL_TYYPE_NEWS;
	}

	/**
	 * 是否是普通新闻类型栏目,子类型
	 * 
	 * @return
	 */
	public boolean isNormalChannel() {
		return fatherchannelID != CHANNELID_FATHER
				&& (type == CHANNEL_TYYPE_NORMAL||type == CHANNEL_TYYPE_NEWS);
	}

	/**
	 * 是否是图片类型栏目,子类型
	 * 
	 * @return
	 */
	public boolean isPicChannel() {
		return fatherchannelID != CHANNELID_FATHER && type == CHANNEL_TYYPE_PIC;
	}

	/**
	 * 是否是案件查询类型栏目,子类型
	 * 
	 * @return
	 */
	public boolean isAJGKChannel() {
		return fatherchannelID != CHANNELID_FATHER
				&& type == CHANNEL_TYYPE_LZFYAJGK;
	}

	/**
	 * 是否是信箱类型栏目,子类型
	 * 
	 * @return
	 */
	public boolean isXXChannel() {
		return fatherchannelID != CHANNELID_FATHER
				&& type == CHANNEL_TYYPE_LZFYXX;
	}

	/**
	 * 是否是咨询类型栏目,子类型
	 *
	 * @return
	 */
	public boolean isCONSULTChannel() {
		return fatherchannelID != CHANNELID_FATHER
				&& type == CHANNEL_TYYPE_CONSULT;
	}


	/**
	 * 是否是文书类型栏目,子类型
	 * 
	 * @return
	 */
	public boolean isWSChannel() {
		return fatherchannelID != CHANNELID_FATHER
				&& type == CHANNEL_TYYPE_LZFYWS;
	}

	/**
	 * 是否是公告类型栏目,子类型
	 * 
	 * @return
	 */
	public boolean isGGChannel() {
		return fatherchannelID != CHANNELID_FATHER
				&& type == CHANNEL_TYYPE_LZFYGG;
	}

	/**
	 * 是否是执行公开类型栏目,子类型
	 * 
	 * @return
	 */
	public boolean isZXGKChannel() {
		return fatherchannelID != CHANNELID_FATHER
				&& type == CHANNEL_TYYPE_LZFYZXGK;
	}
	
	/**
	 * 是否其它类型
	 * 
	 * @return
	 */
	public boolean isOTHERChannel() {
		return fatherchannelID != CHANNELID_FATHER
				&& type == CHANNEL_TYYPE_OTHER;
	}

	/**
	 * 是否是外部链接类型栏目,子类型
	 * 
	 * @return
	 */
	public boolean isOutLinkChannel() {
		return fatherchannelID != CHANNELID_FATHER
				&& type == CHANNEL_TYYPE_OUTLINK;
	}

	/**
	 * 是否是其它类型
	 * 
	 * @return
	 */
	public boolean isOHTERChannel() {
		return fatherchannelID != CHANNELID_FATHER
				&& type == CHANNEL_TYYPE_OTHER;
	}
	
	
	/**
	 * 是否是APP内部类型栏目,子类型
	 * 
	 * @return
	 */
	public boolean isAPPLinkChannel() {
		return fatherchannelID != CHANNELID_FATHER
				&& type == CHANNEL_TYYPE_APPLINK;
	}

	/**
	 * 是否是系统设置栏目,子类型
	 * 
	 * @return
	 */
	public boolean isSystemConfigChannel() {
		return type == CHANNEL_TYYPE_SYSTEMCONFIG;
	}

	public int getIsDefault() {
		return isdefault;
	}

	public void setIsdefault(String value) {
		try {
			isdefault = Integer.parseInt(value);
		} catch (Exception e) {
			isdefault = 0;
		}
	}

	public int getType() {
		return type;
	}

	public void setType(String value) {
		try {
			type = Integer.parseInt(value);
		} catch (Exception e) {
			type = 0;
		}
	}

	public int getSort() {
		return sort;
	}

	public void setSort(String value) {
		try {
			sort = Integer.parseInt(value);
		} catch (Exception e) {
			sort = 0;
		}
	}

	public long getChannelID() {
		return this.id;
	}

	public void setChannelID(long channelID) {
		this.id = channelID;
	}

	public void setChannelID(String channelID) {
		this.id = JavaLangUtil.StrToLong(channelID, 0l);
	}

	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public long getFatherchannelID() {
		return this.fatherchannelID;
	}

	public void setFatherchannelID(String fatherchannelID) {
		this.fatherchannelID = JavaLangUtil.StrToLong(fatherchannelID, 0l);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

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

	public static ChannelVO getSystemConfig() {
		ChannelVO cvo = new ChannelVO();
		cvo.setFatherchannelID(CHANNELID_FATHER);
		cvo.setChannelName("系统设置");
		cvo.setChannelID(CHANNEL_TYYPE_SYSTEMCONFIG);
		cvo.setType(CHANNEL_TYYPE_SYSTEMCONFIG);
		return cvo;
	}

	/**
	 * 根据栏目ID获取栏目信息
	 * 
	 * @param channelVOs
	 * @param channelid
	 * @return
	 */
	public static ChannelVO getChannelWithChannelID(
			ArrayList<ChannelVO> channelVOs, long channelid) {
		if (channelVOs == null || channelVOs.isEmpty())
			return null;
		for (int i = 0; i < channelVOs.size(); i++) {
			if (channelVOs.get(i).getChannelID() == channelid)
				return channelVOs.get(i);
		}
		return null;
	}

	/**
	 * 获取父级栏目内容
	 * 
	 * @param channelVOs
	 * @return
	 */
	public static ArrayList<ChannelVO> getFatherChannels(
			ArrayList<ChannelVO> channelVOs) {
		return getChannelsWithFather(channelVOs, null);
	}

	/**
	 * 获取父栏目栏目ID
	 * 
	 * @param channelVOs
	 * @param type
	 * @return
	 */
	public static long getFatherChannelIDFromType(
			ArrayList<ChannelVO> channelVOs, int type) {
		ChannelVO cvo = getFatherChannelFromType(channelVOs, type);
		if (cvo != null)
			return cvo.getChannelID();
		return -1l;
	}

	/**
	 * 取顶级父栏目
	 * 
	 * @param channelVOs
	 * @param type
	 * @return
	 */
	public static ChannelVO getFatherChannelFromType(
			ArrayList<ChannelVO> channelVOs, int type) {
		if (channelVOs == null || channelVOs.isEmpty())
			return null;
		for (int i = 0; i < channelVOs.size(); i++) {
			if (channelVOs.get(i).fatherchannelID == CHANNELID_FATHER) {
				if (channelVOs.get(i).type == type)
					return channelVOs.get(i);
			}
		}
		return null;
	}

	/**
	 * 获取各级栏目内容
	 * 
	 * @param channelVOs
	 * @param fatherCVO
	 * @return
	 */
	public static ArrayList<ChannelVO> getChannelsWithFather(
			ArrayList<ChannelVO> channelVOs, ChannelVO fatherCVO) {
		if (channelVOs == null || channelVOs.isEmpty())
			return null;
		long fatherchannelID;
		if (fatherCVO == null)
			fatherchannelID = CHANNELID_FATHER;
		else
			fatherchannelID = fatherCVO.getChannelID();
		ArrayList<ChannelVO> result = new ArrayList<ChannelVO>();

		for (int i = 0; i < channelVOs.size(); i++) {
			if (channelVOs.get(i).fatherchannelID == fatherchannelID) {
				result.add(channelVOs.get(i));
			}
		}
		if (result.isEmpty())
			return null;
		Collections.sort(result, new SortByChannelInfo());
		return result;
	}

	public Date getLastUpdateDataTime() {
		return lastUpdateDataTime;
	}

	public void setLastUpdateDataTime(Date lastUpdateDataTime) {
		this.lastUpdateDataTime = lastUpdateDataTime;
	}

	/**
	 * 是否必须更新
	 * 
	 * @param c
	 * @return
	 */
	public boolean isMustUpdate(Context c) {
		Date last = getLastUpdateDataTime(c);
		Log.e("date",last+"");
		if (last == null)
			return true;
		return TimeUtil.interval(new Date(), last) > Constants.CINT_EFFECTIVE_NEWS_TIME;
	}

	public Date getLastUpdateDataTime(Context c) {
		if (lastUpdateDataTime == null) {
			// 读取设置项
			lastUpdateDataTime = getChannelLastUpdateTime(c, fatherchannelID,
					id);
		}

		return lastUpdateDataTime;
	}

	public void setLastUpdateDataTime(Context c, Date lastUpdateDataTime) {
		this.lastUpdateDataTime = lastUpdateDataTime;
		// 存取配置信息
		setChannelLastUpdateTime(c, fatherchannelID, id, lastUpdateDataTime);
	}

	/**
	 * 对栏目进行排序,优先级:fatherchannelID useshow sort
	 * 
	 * @author wuzhu email:wuzhupc@gmail.com
	 * @version 创建时间：2012-11-25 下午10:02:00
	 */
	public static class SortByChannelInfo implements Comparator<ChannelVO> {
		@Override
		public int compare(ChannelVO arg0, ChannelVO arg1) {
			if (arg0 == null || arg1 == null)
				return 0;
			// 父栏目小的排在前面
			// if (arg0.fatherchannelID != arg1.fatherchannelID)
			// return arg0.fatherchannelID > arg1.fatherchannelID ? 1 : -1;
			// 用户可见的排在前面
			// if (arg0.useshow != arg1.useshow)
			// return arg0.useshow ? -1 : 1;
			return arg0.sort - arg1.sort;
		}
	}

	/**
	 * 获取栏目列表数据缓存文件名称
	 * 
	 * @param vo
	 * @return
	 */
	public static String getCacheFileName(ChannelVO vo) {
		return Constants.getDataStoreDir() + Constants.CSTR_DETAILDIR
				+ vo.getFatherchannelID() + "_" + vo.getChannelID() + ".cache";
	}

	/**
	 * 获取某栏目最后更新时间
	 * 
	 * @param c
	 * @param channelid
	 * @return　如果没有记录返回null
	 */
	public static Date getChannelLastUpdateTime(Context c,
			long fatherchannelid, long channelid) {
		String key = "channel_lastupdatetime_" + fatherchannelid + "_"
				+ channelid;

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
	public static void setChannelLastUpdateTime(Context c,
			long fatherchannelid, long channelid, Date lastupdatetime) {
		String key = "channel_lastupdatetime_" + fatherchannelid + "_"
				+ channelid;
		SettingUtil.setStringSettingValue(c, key,
				TimeUtil.dateToString(lastupdatetime));
	}

	public String getLocallogo() {
		return locallogo;
	}

	public void setLocallogo(String locallogo) {
		this.locallogo = locallogo;
	}
	
	/**
	 * 获取频道类型最大长度
	 * @param
	 * @return
	 */
	public static int getChannelTypeMaxLen(ArrayList<ChannelVO> cvos)
	{
		if(cvos==null||cvos.isEmpty())
			return 0;
		int result = 0;
		for(int i=0;i<cvos.size();i++)
		{
			ChannelVO cvo = cvos.get(i);
			if(cvo.getChannelName()!=null&&cvo.getChannelName().length()>result)
				result = cvo.getChannelName().length();
		}
		return result;
	}
	
}