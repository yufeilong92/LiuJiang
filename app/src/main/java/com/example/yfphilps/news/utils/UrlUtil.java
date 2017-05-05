/**
 * Project:RLSClient
 * File:UrlUtil.java
 * Copyright 2013 WUZHUPC Co., Ltd. All rights reserved.
 */
package com.example.yfphilps.news.utils;

import android.content.Context;

import com.example.yfphilps.news.R;
import com.example.yfphilps.news.config.Constants;
import com.example.yfphilps.news.ui.BaseActivity;
import com.example.yfphilps.news.vo.ServerInfoVO;

import net.lawyee.mobilelib.utils.StringUtil;

/**
 * @author wuzhu
 * @date 2013-6-20 上午9:18:20
 * @version $id$
 */
public class UrlUtil {
	private static final String CSTR_BASE = "$base";
	private static final String CSTR_PRO = "$pro";


	private static ServerInfoVO mServerInfoVO;

	public static String getUrl(Context c, String url) {
		if (StringUtil.isEmpty(url))
			return url;
		if (mServerInfoVO == null)
			mServerInfoVO = getServerInfoVO(c);
		if (mServerInfoVO != null) {
			return getUrl(c, url, mServerInfoVO);
		}

		int rBaseId = BaseActivity.getResId(c, getThemeName(c)+"url_base", "string");
		int rProId = BaseActivity.getResId(c, getThemeName(c)+"url_base_pro", "string");
		if (rBaseId != 0 && rProId != 0) {
			return url.replace(CSTR_BASE, c.getString(rBaseId)).replace(
					CSTR_PRO, c.getString(rProId));
		} else {
			return url.replace(CSTR_BASE, c.getString(R.string.url_base))
					.replace(CSTR_PRO, c.getString(R.string.url_base_pro));
		}
	
	}

	public static String getUrl(Context c, String url, ServerInfoVO sivo) {
		if (StringUtil.isEmpty(url))
			return url;
		if (sivo != null) {
			return url.replace(CSTR_BASE, sivo.getServerIP()).replace(CSTR_PRO,
					sivo.getContextPath());
		}

		int rBaseId = BaseActivity.getResId(c, getThemeName(c)+"url_base", "string");
		int rProId = BaseActivity.getResId(c, getThemeName(c)+"url_base_pro", "string");
		if (rBaseId != 0 && rProId != 0) {
			return url.replace(CSTR_BASE, c.getString(rBaseId)).replace(
					CSTR_PRO, c.getString(rProId));
		} else {
			return url.replace(CSTR_BASE, c.getString(R.string.url_base))
					.replace(CSTR_PRO, c.getString(R.string.url_base_pro));
		}
	}

	public static String getUrl(Context c, int urlresid) {
		return getUrl(c, c.getString(urlresid));
	}

	public static void setServerInfo(ServerInfoVO sivo, boolean bsave) {
		mServerInfoVO = sivo;
		if (bsave)
			ServerInfoVO.saveVO(mServerInfoVO, ServerInfoVO.dataFileName());
	}

	public static ServerInfoVO getServerInfoVO(Context c) {
		ServerInfoVO serverInfoVO = (ServerInfoVO) ServerInfoVO
				.loadVO(ServerInfoVO.dataFileName());
		if (serverInfoVO == null || !Constants.CBOOL_CANCHANGESERVER) {
			
			
			serverInfoVO = new ServerInfoVO();
			// 使用默认的数据
			int rBaseId = BaseActivity.getResId(c,getThemeName(c)+"url_base", "string");
			int rProId = BaseActivity.getResId(c,getThemeName(c)+"url_base_pro", "string");
			if (rBaseId != 0 && rProId != 0) {
				serverInfoVO.setServerIP(c.getString(rBaseId));
				serverInfoVO.setContextPath(c.getString(rProId));
			} else {
				serverInfoVO.setServerIP(c.getString(R.string.url_base));
				serverInfoVO.setContextPath(c.getString(R.string.url_base_pro));
			}
			setServerInfo(serverInfoVO, true);
		}
		return serverInfoVO;
	}
	
	private static String getThemeName(Context c)
	{
		String mName;
		if("".equals(c.getString(R.string.theme_name)))
		{
			mName = "";
		}else
		{
			mName =c.getString(R.string.theme_name) +"_";
		}
		return mName;
		
	}
	
}
