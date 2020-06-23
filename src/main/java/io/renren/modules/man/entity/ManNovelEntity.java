package io.renren.modules.man.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 漫画内容简介
 * 
 * @author ygg
 * @date 2020-06-23 14:38:19
 */
@TableName("man_novel")
public class ManNovelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 漫画ID
	 */
	@TableId
	private Long manid;
	/**
	 * 漫画名
	 */
	private String manname;
	/**
	 * 别名
	 */
	private String othername;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 关键字
	 */
	private String keys;
	/**
	 * 简介
	 */
	private String content;
	/**
	 * 地区
	 */
	private String area;
	/**
	 * 类别
	 */
	private String category;
	/**
	 * 爬虫地址
	 */
	private String paurl;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 是否有效（0、无效；1、有效）
	 */
	private Long isenable;
	/**
	 * 标签
	 */
	private String label;
	/**
	 * 上映时间
	 */
	private String displayTime;

	/**
	* 获取：漫画ID
	*/
	public Long getManid (){return this.manid;};
	/**
	 * 设置：漫画ID
	 */
	public void setManid (Long manid){this.manid = manid;};
	/**
	* 获取：漫画名
	*/
	public String getManname (){return this.manname;};
	/**
	 * 设置：漫画名
	 */
	public void setManname (String manname){this.manname = manname;};
	/**
	* 获取：别名
	*/
	public String getOthername (){return this.othername;};
	/**
	 * 设置：别名
	 */
	public void setOthername (String othername){this.othername = othername;};
	/**
	* 获取：作者
	*/
	public String getAuthor (){return this.author;};
	/**
	 * 设置：作者
	 */
	public void setAuthor (String author){this.author = author;};
	/**
	* 获取：关键字
	*/
	public String getKeys (){return this.keys;};
	/**
	 * 设置：关键字
	 */
	public void setKeys (String keys){this.keys = keys;};
	/**
	* 获取：简介
	*/
	public String getContent (){return this.content;};
	/**
	 * 设置：简介
	 */
	public void setContent (String content){this.content = content;};
	/**
	* 获取：地区
	*/
	public String getArea (){return this.area;};
	/**
	 * 设置：地区
	 */
	public void setArea (String area){this.area = area;};
	/**
	* 获取：类别
	*/
	public String getCategory (){return this.category;};
	/**
	 * 设置：类别
	 */
	public void setCategory (String category){this.category = category;};
	/**
	* 获取：爬虫地址
	*/
	public String getPaurl (){return this.paurl;};
	/**
	 * 设置：爬虫地址
	 */
	public void setPaurl (String paurl){this.paurl = paurl;};
	/**
	* 获取：状态
	*/
	public String getStatus (){return this.status;};
	/**
	 * 设置：状态
	 */
	public void setStatus (String status){this.status = status;};
	/**
	* 获取：是否有效（0、无效；1、有效）
	*/
	public Long getIsenable (){return this.isenable;};
	/**
	 * 设置：是否有效（0、无效；1、有效）
	 */
	public void setIsenable (Long isenable){this.isenable = isenable;};
	/**
	* 获取：上映时间
	*/
	public String getDisplayTime (){return this.displayTime;};
	/**
	 * 设置：上映时间
	 */
	public void setDisplayTime (String displayTime){this.displayTime = displayTime;};
	/**
	* 获取：标签
	*/
	public String getLabel (){return this.label;};
	/**
	 * 设置：标签
	 */
	public void setLabel (String label){this.label = label;};
}
