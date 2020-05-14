package io.renren.modules.man.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 章节与漫画关系表
 * 
 * @author ygg
 * @date 2020-05-11 10:06:03
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
	private Long sectionid;
	/**
	 * 漫画书名Id
	 */
	private Long manid;
	/**
	 * 章节名
	 */
	private String title;

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
	public Long getSectionid (){return this.sectionid;};
	/**
	 * 设置：章节ID
	 */
	public void setSectionid (Long sectionid){this.sectionid = sectionid;};
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
}
