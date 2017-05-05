/**
 * Project:RLSClient
 * File:InfoService.java
 * Copyright 2013 WUZHUPC Co., Ltd. All rights reserved.
 */
package com.example.yfphilps.news.service;

import android.content.Context;

import com.example.yfphilps.news.R;
import com.example.yfphilps.news.config.Constants;
import com.example.yfphilps.news.vo.AreasVO;

import net.lawyee.mobilelib.json.JsonCreater;

/**
 * @author wuzhu
 * @date 2013-6-20 上午11:16:30
 * @version $id$
 */
public class InfoService extends BaseJsonService {
	private String murl;

	/**
	 * @param c
	 */
	public InfoService(Context c) {
		super(c);
	}

	public InfoService(Context c, String url) {
		super(c);
		murl = url;
	}

	/**
	 * 
	 * @param channelID
	 * @param keyword
	 *            搜索关键字
	 * @param bottomnewsInfoID
	 * @param listener
	 */
	public void getJsonNewsList(Long channelID, String subchannelid,AreasVO avo,
			String keyword, String bottomnewsInfoID,
			IResultInfoListener listener) {
		JsonCreater creater = JsonCreater.startJson(getDevID());
		
		creater.setParam("channelID", channelID);
		creater.setParam("subchannelid", subchannelid);
		creater.setParam("keyword", keyword);
		if(avo!=null)
			creater.setParam("aid", avo.getId());
		creater.setParam("bottomnewsInfoID", bottomnewsInfoID);
		creater.setParam("pageSize", Constants.CINT_PAGE_SIZE);
		
		mCommandName = mContext.getString(R.string.cmd_json_info_getnewslist);
		String json = creater.createJson(mCommandName);

		setResultInfoListener(listener);
		setValueType(CINT_VALUETYPE_LIST);
		getData(json, murl);
	}

	/*public void getJsonNewsCommentList(Long channelID, Long newsInfoID,
			Long bottomnewsCommentID, IResultInfoListener listener) {
		JsonCreater creater = JsonCreater.startJson(getDevID());
		creater.setParam("channelID", channelID);
		creater.setParam("newsInfoID", newsInfoID);
		creater.setParam("bottomnewsCommentID", bottomnewsCommentID);
		MemberVO memberVO = getMemberVO();
		if (memberVO != null)
			creater.setParam("memberid", memberVO.getMemberid());
		creater.setParam("pageSize", Constants.CINT_PAGE_SIZE);
		mCommandName = mContext
				.getString(R.string.cmd_json_info_getnewscommentlist);
		String json = creater.createJson(mCommandName);

		setResultInfoListener(listener);
		ArrayList<Integer> valueTypes = new ArrayList<Integer>();
		valueTypes.add(CINT_VALUETYPE_HASHMAP);
		valueTypes.add(CINT_VALUETYPE_LIST);
		setValueType(valueTypes);
		getData(json, murl);
	}
*/
	public void getJsonNewsDetail(Long channelID, Long newsInfoID,
			IResultInfoListener listener) {
		JsonCreater creater = JsonCreater.startJson(getDevID());
		creater.setParam("channelID", channelID);
		creater.setParam("newsInfoID", newsInfoID);
		mCommandName = mContext.getString(R.string.cmd_json_info_getnewsdetail);
		String json = creater.createJson(mCommandName);

		setResultInfoListener(listener);
		setValueType(CINT_VALUETYPE_ENTITY);
		getData(json, murl);
	}

	public void getJsonMoreLinkInfo(IResultInfoListener listener) {
		getJsonMoreLinkInfo(listener, false, null);
	}

	public void getJsonMoreLinkInfo(IResultInfoListener listener,
			boolean showprogress, String progresscontent) {
		JsonCreater creater = JsonCreater.startJson(getDevID());
		mCommandName = mContext
				.getString(R.string.cmd_json_info_getmorelinkinfo);
		String json = creater.createJson(mCommandName);
		setResultInfoListener(listener);
		setValueType(CINT_VALUETYPE_LIST);
		setShowProgress(showprogress);
		setProgressShowContent(progresscontent);
		getData(json, murl);
	}

}
