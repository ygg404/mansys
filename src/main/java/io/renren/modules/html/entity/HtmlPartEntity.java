package io.renren.modules.html.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 前端源代码
 *
 * @author ygg
 * @date 2020-07-05 22:21:16
 */
@TableName("html_part")
public class HtmlPartEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 关键字
	 */
	private String keys;
	/**
	 * 文件大小（MB）
	 */
	private Float size;
	/**
	 * 图片预览链接
	 */
	private String imgUrl;
	/**
	 * 下载链接
	 */
	private String downloadUrl;
	/**
	 * 是否手机端（0-不是；1-是）
	 */
	private Long isMobile;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 分类
	 */
	private String cate;

	/**
	 * 获取：id
	 */
	public Long getId (){return this.id;};
	/**
	 * 设置：id
	 */
	public void setId (Long id){this.id = id;};
	/**
	 * 获取：标题
	 */
	public String getTitle (){return this.title;};
	/**
	 * 设置：标题
	 */
	public void setTitle (String title){this.title = title;};
	/**
	 * 获取：关键字
	 */
	public String getKeys (){return this.keys;};
	/**
	 * 设置：关键字
	 */
	public void setKeys (String keys){this.keys = keys;};
	/**
	 * 获取：文件大小（MB）
	 */
	public Float getSize (){return this.size;};
	/**
	 * 设置：文件大小（MB）
	 */
	public void setSize (Float size){this.size = size;};
	/**
	 * 获取：图片预览链接
	 */
	public String getImgUrl (){return this.imgUrl;};
	/**
	 * 设置：图片预览链接
	 */
	public void setImgUrl (String imgUrl){this.imgUrl = imgUrl;};
	/**
	 * 获取：下载链接
	 */
	public String getDownloadUrl (){return this.downloadUrl;};
	/**
	 * 设置：下载链接
	 */
	public void setDownloadUrl (String downloadUrl){this.downloadUrl = downloadUrl;};
	/**
	 * 获取：是否手机端（0-不是；1-是）
	 */
	public Long getIsMobile (){return this.isMobile;};
	/**
	 * 设置：是否手机端（0-不是；1-是）
	 */
	public void setIsMobile (Long isMobile){this.isMobile = isMobile;};
	/**
	 * 获取：描述
	 */
	public String getDescription (){return this.description;};
	/**
	 * 设置：描述
	 */
	public void setDescription (String description){this.description = description;};
	/**
	 * 获取：分类
	 */
	public String getCate (){return this.cate;};
	/**
	 * 设置：分类
	 */
	public void setCate (String cate){this.cate = cate;};
}
