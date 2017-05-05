package com.example.yfphilps.news.vo;

import net.lawyee.mobilelib.utils.FileUtil;
import net.lawyee.mobilelib.utils.StringUtil;
import net.lawyee.mobilelib.vo.BaseVO;

import java.util.Comparator;

/**
 * 更多链接信息VO
 * 
 * @author wuzhu
 * @date 2013-4-21 下午6:31:47
 * @version $id$
 */
public class MoreLinkInfoVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3788789808406444456L;

	/**
	 * 链接名称
	 */
	private String linkName = "";

	/**
	 * 顺序
	 */
	private int sort;

	/**
	 * 链接地址
	 */
	private String url = "";

	/**
	 * 备注信息
	 */
	private String note = "";

	private String logo_ld;

	private String logo_md;

	private String logo_hd;

	private String locallogo;

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

	public long getLinkID() {
		return this.id;
	}

	public void setLinkID(String linkID) {
		this.id = Long.parseLong(linkID);
	}

	public int getSort() {
		return sort;
	}

	public void setSort(String sort) {
		try {
			this.sort = Integer.parseInt(sort);
		} catch (Exception e) {
			this.sort = Integer.MAX_VALUE;
		}
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	/**
	 * 数据列表存储文件名
	 * 
	 * @return
	 */
	public static String dataListFileName() {
		return dataListFileName(serialVersionUID);
	}

	public String getLocallogo() {
		return locallogo;
	}

	public void setLocallogo(String locallogo) {
		this.locallogo = locallogo;
	}

	/**
	 * 对进行排序
	 */
	public static class SortByMoreLinkInfo implements
			Comparator<MoreLinkInfoVO> {
		@Override
		public int compare(MoreLinkInfoVO arg0, MoreLinkInfoVO arg1) {
			if (arg0 == null || arg1 == null)
				return 0;
			return arg0.sort - arg1.sort;
		}
	}
}
