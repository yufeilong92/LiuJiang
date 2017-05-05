/**
 * Project:newsreader2
 * File:UserVO.java
 * Copyright 2013 WUZHUPC Co., Ltd. All rights reserved.
 */
package com.example.yfphilps.news.vo;

import net.lawyee.mobilelib.utils.JavaLangUtil;
import net.lawyee.mobilelib.vo.BaseVO;

/**
 * 用户信息VO
 * 
 * @author wuzhu
 * @date 2013-4-23 下午8:25:09
 * @version $id$
 */
public class MemberVO extends BaseVO {
	protected static final String TAG = MemberVO.class.getSimpleName();
	/**
	 * 
	 */
	public static final long serialVersionUID = -381414267200578436L;

	public static final int CINT_LENGTH_ACCOUNT_MIN = 4;
	public static final int CINT_LENGTH_ACCOUNT_MAX = 40;

	public static final int CINT_LENGTH_PASSWORD_MIN = 4;
	public static final int CINT_LENGTH_PASSWORD_MAX = 16;

	public static final int CINT_LENGTH_USERNAME_MIN = 4;
	public static final int CINT_LENGTH_USERNAME_MAX = 20;

	
	
	/**
	 * 联系方式
	 */
	private String mobileno;

	/**
	 * 登录帐号
	 */
	private String memberaccount;

	/**
	 * 用户名
	 */
	private String membername;

	/**
	 * 登录密码
	 */
	private String password;

	/**
	 * 邮箱地址
	 */
	private String email;

	/**
	 * 用户是否已经登录
	 */
	private boolean userlogin;

	/**
	 * 联系地址
	 */
	private String address;

	/**
	 * 邮编
	 */
	private String postcode;

	/**
	 * 选择的地址
	 */
	private AreasVO selAreasVO;

	public AreasVO getSelAreasVO() {
		return selAreasVO;
	}

	public void setSelAreasVO(AreasVO selAreasVO) {
		this.selAreasVO = selAreasVO;
	}

	public long getMemberid() {
		return this.id;
	}

	public void setMemberid(long memberid) {
		this.id = memberid;
	}

	public void setMemberid(String memberid) {
		this.id = JavaLangUtil.StrToLong(memberid, 0l);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getMemberaccount() {
		return memberaccount;
	}

	public void setMemberaccount(String memberaccount) {
		this.memberaccount = memberaccount;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public boolean isUserlogin() {
		return userlogin;
	}

	public void setUserlogin(boolean userlogin) {
		this.userlogin = userlogin;
	}

	public void setUserlogin(String userlogin) {
		this.userlogin = JavaLangUtil.StrToBool(userlogin, false);
	}
	
	

	/**
	 * 数据存储文件名
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
}
