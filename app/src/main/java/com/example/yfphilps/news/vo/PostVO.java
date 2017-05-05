package com.example.yfphilps.news.vo;

import android.content.Context;

import com.example.yfphilps.news.R;
import com.example.yfphilps.news.config.Constants;

import net.lawyee.mobilelib.utils.JavaLangUtil;
import net.lawyee.mobilelib.utils.StringUtil;
import net.lawyee.mobilelib.utils.TimeUtil;
import net.lawyee.mobilelib.vo.BaseVO;

import java.util.Date;

/**
 * 活动、投票基本信息类(原新闻资讯也使用此类，2.0后使用NewsVO)
 * 
 * @author wuzhu
 * @date 2013-4-21 下午5:35:44
 * @version $id$
 */
public class PostVO extends BaseVO {

	private static final long serialVersionUID = -1628059787323776612L;

	/**
	 * 是否是新闻头条，数字0和1表示，默认0：普通新闻，1：头条新闻，头条新闻最多展示3条
	 */
	private int headline;

	/**
	 * 新闻类型, 0 普通 5 图片
	 */
	private int newsType;

	/**
	 * 栏目编号
	 */
	private long channelID;
	/**
	 * 子栏目编号
	 */
	private long subchannelid;
	/**
	 * 子栏目，类别
	 */
	private String subchannel;

	/**
	 * 网站上的webid
	 */
	private String webid;

	/**
	 * 新闻标题
	 */
	private String title = "";

	/**
	 * 题图 URL
	 */
	private String titlePic = "";

	/**
	 * 题图缩略图URL
	 */
	private String smallTitlePic = "";

	/**
	 * 新闻创建日期 格式yyyy-MM-dd HH:mm:ss
	 */
	private String createTime;
	/**
	 * 新闻发表日期 格式yyyy-MM-dd HH:mm:ss
	 */
	private String publishTime;
	/**
	 * 新闻信息摘要
	 */
	private String newsSummary = "";
	/**
	 * 新闻作者ID
	 */
	private String autherID;
	/**
	 * 新闻作者
	 */
	private String autherName = "";

	/**
	 * 排序序号
	 */
	private int sort = 0;

	/**
	 * 新闻来源
	 */
	private String source;

	/**
	 * 评论数
	 */
	private int commentnum;

	public int getNewsType() {
		return newsType;
	}

	public String getNewstypeDesc() {
		String desc;
		switch (newsType) {
		case ChannelVO.CHANNEL_TYYPE_PIC:
			desc = "图集";
			break;
		default:
			desc = "新闻";
			break;
		}
		return desc;
	}

	public void setNewsType(int newsType) {
		this.newsType = newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = JavaLangUtil.StrToInteger(newsType, 0);
	}

	public long getNewsInfoID() {
		return this.id;
	}

	public void setNewsInfoID(String newsInfoID) {
		this.id = JavaLangUtil.StrToLong(newsInfoID, 0l);
	}

	public long getChannelID() {
		return this.channelID;
	}

	public void setChannelID(long channelID) {
		this.channelID = channelID;
	}

	public void setChannelID(String channelID) {
		this.channelID = JavaLangUtil.StrToLong(channelID, 0l);
	}

	public long getSubchannelid() {
		return subchannelid;
	}

	public void setSubchannelid(long subchannelid) {
		this.subchannelid = subchannelid;
	}

	public void setSubchannelid(String subchannelid) {
		this.subchannelid = JavaLangUtil.StrToLong(subchannelid, 0l);
		;
	}

	public String getSubchannel() {
		return subchannel;
	}

	public void setSubchannel(String subchannel) {
		this.subchannel = subchannel;
	}

	public String getWebid() {
		return webid;
	}

	public void setWebid(String webid) {
		this.webid = webid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSmallTitlePic() {
		return this.smallTitlePic;
	}

	public void setSmallTitlePic(String smallTitlePic) {
		this.smallTitlePic = smallTitlePic;
	}

	public String getTitlePic() {
		return this.titlePic;
	}

	public void setTitlePic(String titlePic) {
		this.titlePic = titlePic;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getNewsSummary() {
		return this.newsSummary;
	}

	public void setNewsSummary(String newsSummary) {
		this.newsSummary = newsSummary;
	}

	public String getAutherID() {
		return this.autherID;
	}

	public void setAutherID(String autherID) {
		this.autherID = autherID;
	}

	public String getAutherName() {
		return this.autherName;
	}

	public void setAutherName(String autherName) {
		this.autherName = autherName;
	}

	public void setSort(String sort) {
		this.sort = JavaLangUtil.StrToInteger(sort, 0);
	}

	public int getSort() {
		return sort;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getHeadline() {
		return headline;
	}

	public void setHeadline(int headline) {
		this.headline = headline;
	}

	public void setHeadline(String headline) {
		this.headline = JavaLangUtil.StrToInteger(headline, 0);
	}

	public boolean isHeadline() {
		return headline == 1;
	}

	public int getCommentnum() {
		return commentnum;
	}

	public void setCommentnum(int commentnum) {
		this.commentnum = commentnum;
	}

	public void setCommentnum(String commentnum) {
		this.commentnum = JavaLangUtil.StrToInteger(commentnum, 0);
	}

	/**
	 * 生成分享信息内容部分
	 */
	@Override
	public String generateShareText(Context c) {
		if(StringUtil.isEmpty(this.newsSummary))
			return c.getString(R.string.app_name_full);
		return this.newsSummary;
		/*return c.getString(R.string.app_name_full)
				+ " "
				+ getTitle()
				+ " "
				+ String.format(UrlUtil.getUrl(c, R.string.url_newsdetail),
						getNewsInfoID(), getChannelID());*/
	}

	public String getDataFileName() {
		return Constants.getDataStoreDir()
				+ Constants.CSTR_DETAILDIR
				+ getNewsInfoID()
				+ TimeUtil.dateToString(
						TimeUtil.strToDate(getPublishTime(), new Date()),
						"yyyyMMddHHmmss") + ".pd";
	}

	/**
	 * 返回详情HTML标题
	 * 
	 * @return
	 */
	@Override
	public String getHtmlTitle() {
		String result = "<br/><div align=\"center\"><font color=\"#111111\" size=\"4pt\"><strong>"
				+ title + "</strong></font></div><br/>";
		return result;
	}

	/**
	 * 返回详情HTML子标题
	 * 
	 * @return
	 */
	@Override
	public String getHtmlSubTitle() {
		String result = "<div align=\"center\"><font color=\"#666666\" size=\"2pt\">"
				+ getPublishTime()
				+ "&nbsp;&nbsp; "
				+ ((StringUtil.isEmpty(getSource())) ? ""
						: ("来源:" + getSource()))
				+ "</font></div><div style=\"height:0;border-bottom:1px solid #f00\"></div>";
		return result;
	}
}