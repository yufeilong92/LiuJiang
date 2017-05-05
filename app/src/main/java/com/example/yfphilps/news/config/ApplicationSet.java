package com.example.yfphilps.news.config;

import android.app.Application;

import com.example.yfphilps.news.vo.AreasVO;
import com.example.yfphilps.news.vo.ChannelVO;
import com.example.yfphilps.news.vo.MoreLinkInfoVO;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 全局变量
 * 
 * @author wuzhu 20110901
 * 
 */
public class ApplicationSet extends Application {
	/**
	 * 更多链接信息
	 */
	private ArrayList<MoreLinkInfoVO> mMoreLinkInfoVOs;
	/**
	 * 用户信息
	 */
//	private MemberVO mUserVO;

	
	/**
	 * 公开案件信息
	 */
//	private OpenVO mOpenVO;
	
	/**
	 * 栏目信息
	 */
	private ArrayList<ChannelVO> mChannelVOs;

	/**
	 * 区域信息
	 */
	private ArrayList<AreasVO> mAreasVOs;

	

	@SuppressWarnings("unchecked")
	public ArrayList<MoreLinkInfoVO> getMoreLinkInfoVOs() {
		if (mMoreLinkInfoVOs == null)
			mMoreLinkInfoVOs = (ArrayList<MoreLinkInfoVO>) MoreLinkInfoVO
					.loadVOList(MoreLinkInfoVO.dataListFileName());
		return mMoreLinkInfoVOs;
	}

	public void setMoreLinkInfoVOs(ArrayList<MoreLinkInfoVO> moreLinkInfoVOs,
			boolean bsave) {
		mMoreLinkInfoVOs = moreLinkInfoVOs;
		if (mMoreLinkInfoVOs != null && !mMoreLinkInfoVOs.isEmpty())
			Collections.sort(mMoreLinkInfoVOs,
					new MoreLinkInfoVO.SortByMoreLinkInfo());
		if (bsave)
			MoreLinkInfoVO.saveVOList(mMoreLinkInfoVOs,
					MoreLinkInfoVO.dataListFileName());
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ChannelVO> getChannelVO() {
		if (mChannelVOs == null)
			mChannelVOs = (ArrayList<ChannelVO>) ChannelVO.loadVOList(ChannelVO
					.dataListFileName());
		return mChannelVOs;
	}

	public void setChannelVO(ArrayList<ChannelVO> channelVOs, boolean bsave) {
		this.mChannelVOs = channelVOs;
		// if(mChannelVOs!=null&&!mChannelVOs.isEmpty())
		// Collections.sort(mChannelVOs,new ChannelVO.SortByChannelInfo());
		if (bsave)
			ChannelVO.saveVOList(mChannelVOs, ChannelVO.dataListFileName());
	}

	@SuppressWarnings("unchecked")
	public ArrayList<AreasVO> getAreasVOs() {
		if (mAreasVOs == null) {
			mAreasVOs = (ArrayList<AreasVO>) AreasVO.loadVOList(AreasVO
					.dataListFileName());
		}
		return mAreasVOs;
	}

	public void setAreasVOs(ArrayList<AreasVO> areasVOs, boolean bsave) {
		this.mAreasVOs = AreasVO.getAreasVOWithShow(areasVOs);
		if (bsave)
			AreasVO.saveVOList(mAreasVOs, AreasVO.dataListFileName());
	}

	
/*	public OpenVO getmOpenVO() {
		return mOpenVO;
	}

	public void setmOpenVO(OpenVO mOpenVO,String loginType,boolean savelogininfo) {
		this.mOpenVO = mOpenVO;
		if(mOpenVO ==null)
			return;
		if (savelogininfo) {
			OpenVO.saveVO(mOpenVO, OpenVO.dataFileName(loginType));
		}
	}

	

/*		*//*
	public MemberVO getMemberVO() {
		return mUserVO;
	}

	public void setMemberVO(MemberVO userVO, boolean savelogininfo) {
		this.mUserVO = userVO;
		if (savelogininfo) {
			MemberVO.saveVO(userVO, MemberVO.dataFileName());
		}
	}*/

}
