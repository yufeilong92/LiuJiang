/**
 * Project:RLSClient
 * File:ModuleVO.java
 * Copyright 2013 WUZHUPC Co., Ltd. All rights reserved.
 */
package com.example.yfphilps.news.vo;

import net.lawyee.mobilelib.vo.BaseVO;

/**
 * @author wuzhu
 * @date 2013-6-27 上午10:42:51
 * @version $id$
 */
public class ModuleVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1954253555106415677L;

	/**
	 * 栏目数据VO,可以是ChannelVO或MoreLinkInfoVO
	 */
	private BaseVO dataVO;

	/**
	 * 栏目数据VO,可以是ChannelVO或MoreLinkInfoVO
	 */
	private BaseVO dataVO2;

	/**
	 * 栏目数据VO,可以是ChannelVO或MoreLinkInfoVO
	 */
	private BaseVO dataVO3;

	public BaseVO getDataVO() {
		return dataVO;
	}

	public boolean setDataVO(BaseVO dataVO) {
		if (dataVO != null
				&& (dataVO instanceof ChannelVO || dataVO instanceof MoreLinkInfoVO)) {
			this.dataVO = dataVO;
			return true;
		}
		return false;
	}

	public BaseVO getDataVO2() {
		return dataVO2;
	}

	public boolean setDataVO2(BaseVO dataVO) {
		if (dataVO != null
				&& (dataVO instanceof ChannelVO || dataVO instanceof MoreLinkInfoVO)) {
			this.dataVO2 = dataVO;
			return true;
		}
		return false;
	}

	public BaseVO getDataVO3() {
		return dataVO3;
	}

	public boolean setDataVO3(BaseVO dataVO) {
		if (dataVO != null
				&& (dataVO instanceof ChannelVO || dataVO instanceof MoreLinkInfoVO)) {
			this.dataVO3 = dataVO;
			return true;
		}
		return false;
	}
}
