package io.renren.modules.man.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 章节与漫画关系表
 * 
 * @author ygg
 * @date 2020-06-23 14:28:23
 */
@TableName("man_section")
public class ManSectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增id
	 */
	@TableId
	private Long id;
	/**
	 * 章节ID
	 */
	private String sectionid;
	/**
	 * 漫画书名Id
	 */
	private Long manid;
	/**
	 * 章节名
	 */
	private String title;
	/**
	 * 视频链接
	 */
	private String videoUrl;
	/**
	 * 窗口链接
	 */
	private String iframeUrl;

	/**
	* 获取：自增id
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：自增id
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：章节ID
	*/
	public String getSectionid (){return this.sectionid;};
	/**
	 * 设置：章节ID
	 */
	public void setSectionid (String sectionid){this.sectionid = sectionid;};
	/**
	* 获取：漫画书名Id
	*/
	public Long getManid (){return this.manid;};
	/**
	 * 设置：漫画书名Id
	 */
	public void setManid (Long manid){this.manid = manid;};
	/**
	* 获取：章节名
	*/
	public String getTitle (){return this.title;};
	/**
	 * 设置：章节名
	 */
	public void setTitle (String title){this.title = title;};
	/**
	* 获取：视频链接
	*/
	public String getVideoUrl (){return this.videoUrl;};
	/**
	 * 设置：视频链接
	 */
	public void setVideoUrl (String videoUrl){this.videoUrl = videoUrl;};
	/**
	* 获取：窗口链接
	*/
	public String getIframeUrl (){return this.iframeUrl;};
	/**
	 * 设置：窗口链接
	 */
	public void setIframeUrl (String iframeUrl){this.iframeUrl = iframeUrl;};
}
