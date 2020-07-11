package io.renren.modules.article.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 章节列表
 * 
 * @author ygg
 * @date 2020-07-11 17:12:03
 */
@TableName("article_content")
public class ArticleContentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 书名Id
	 */
	@TableId
	private Long id;
	/**
	 * 章名
	 */
	private String sectionName;
	/**
	 * 
	 */
	private Long articleId;
	/**
	 * 书名
	 */
	private String articleName;
	/**
	 * 章节父id
	 */
	private Long parentId;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 附件
	 */
	private String affixFile;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	* 获取：书名Id
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：书名Id
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：章名
	*/
	public String getSectionName (){return this.sectionName;};
	/**
	 * 设置：章名
	 */
	public void setSectionName (String sectionName){this.sectionName = sectionName;};
	/**
	* 获取：
	*/
	public Long getArticleId (){return this.articleId;};
	/**
	 * 设置：
	 */
	public void setArticleId (Long articleId){this.articleId = articleId;};
	/**
	* 获取：书名
	*/
	public String getArticleName (){return this.articleName;};
	/**
	 * 设置：书名
	 */
	public void setArticleName (String articleName){this.articleName = articleName;};
	/**
	* 获取：章节父id
	*/
	public Long getParentId (){return this.parentId;};
	/**
	 * 设置：章节父id
	 */
	public void setParentId (Long parentId){this.parentId = parentId;};
	/**
	* 获取：内容
	*/
	public String getContent (){return this.content;};
	/**
	 * 设置：内容
	 */
	public void setContent (String content){this.content = content;};
	/**
	* 获取：附件
	*/
	public String getAffixFile (){return this.affixFile;};
	/**
	 * 设置：附件
	 */
	public void setAffixFile (String affixFile){this.affixFile = affixFile;};
	/**
	* 获取：创建时间
	*/
	public Date getCreateTime (){return this.createTime;};
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime (Date createTime){this.createTime = createTime;};
}
