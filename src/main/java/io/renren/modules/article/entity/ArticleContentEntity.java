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
 * @date 2020-07-18 11:16:26
 */
@TableName("article_content")
public class ArticleContentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 章节Id
     */
    @TableId
    private Long id;
    /**
     * 章名
     */
    private String sectionName;
    /**
     * 内容
     */
    private String content;
    /**
     * 章节父id
     */
    private Long parentId;
    /**
     * 附件
     */
    private String affixFile;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 书名ID
     */
    private Long novelId;
    /**
     * 书名
     */
    private String novelName;
    /**
     * 是否菜单
     */
    private Boolean menuFlag;
    /**
     * 序列号
     */
    private Long orderNum;

    /**
     * 获取：章节Id
     */
    public Long getId (){return this.id;};
    /**
     * 设置：章节Id
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
     * 获取：内容
     */
    public String getContent (){return this.content;};
    /**
     * 设置：内容
     */
    public void setContent (String content){this.content = content;};
    /**
     * 获取：章节父id
     */
    public Long getParentId (){return this.parentId;};
    /**
     * 设置：章节父id
     */
    public void setParentId (Long parentId){this.parentId = parentId;};
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
    /**
     * 获取：书名ID
     */
    public Long getNovelId (){return this.novelId;};
    /**
     * 设置：书名ID
     */
    public void setNovelId (Long novelId){this.novelId = novelId;};
    /**
     * 获取：书名
     */
    public String getNovelName (){return this.novelName;};
    /**
     * 设置：书名
     */
    public void setNovelName (String novelName){this.novelName = novelName;};
    /**
     * 获取：是否菜单
     */
    public Boolean getMenuFlag (){return this.menuFlag;};
    /**
     * 设置：是否菜单
     */
    public void setMenuFlag (Boolean menuFlag){this.menuFlag = menuFlag;};
    /**
     * 获取：序列号
     */
    public Long getOrderNum (){return this.orderNum;};
    /**
     * 设置：序列号
     */
    public void setOrderNum (Long orderNum){this.orderNum = orderNum;};
}
