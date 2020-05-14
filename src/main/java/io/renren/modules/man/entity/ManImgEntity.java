package io.renren.modules.man.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 漫画章节内容
 * 
 * @author ygg
 * @date 2020-05-11 10:06:04
 */
@TableName("man_img")
public class ManImgEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增Id
	 */
	@TableId
	private Long id;
	/**
	 * 章节ID
	 */
	private Long sectionid;
	/**
	 * 图片路径
	 */
	private String imgurl;

	/**
	* 获取：自增Id
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：自增Id
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：章节ID
	*/
	public Long getSectionid (){return this.sectionid;};
	/**
	 * 设置：章节ID
	 */
	public void setSectionid (Long sectionid){this.sectionid = sectionid;};
	/**
	* 获取：图片路径
	*/
	public String getImgurl (){return this.imgurl;};
	/**
	 * 设置：图片路径
	 */
	public void setImgurl (String imgurl){this.imgurl = imgurl;};
}
