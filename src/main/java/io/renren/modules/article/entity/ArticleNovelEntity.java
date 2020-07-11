package io.renren.modules.article.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 书目列表
 * 
 * @author ygg
 * @date 2020-07-11 16:18:39
 */
@TableName("article_novel")
public class ArticleNovelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@TableId
	private Long id;
	/**
	 * 书名
	 */
	private String novelName;
	/**
	 * 类目
	 */
	private String cate;

	/**
	* 获取：自增ID
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：自增ID
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：书名
	*/
	public String getNovelName (){return this.novelName;};
	/**
	 * 设置：书名
	 */
	public void setNovelName (String novelName){this.novelName = novelName;};
	/**
	* 获取：类目
	*/
	public String getCate (){return this.cate;};
	/**
	 * 设置：类目
	 */
	public void setCate (String cate){this.cate = cate;};
}
