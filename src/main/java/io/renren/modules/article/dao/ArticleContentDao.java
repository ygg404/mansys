package io.renren.modules.article.dao;

import io.renren.modules.article.entity.ArticleContentEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.article.entity.ArticleNovelEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 章节列表
 * 
 * @author ygg
 * @date 2020-07-11 17:12:03
 */
@Mapper
public interface ArticleContentDao extends BaseMapper<ArticleContentEntity> {

    List<ArticleContentEntity> getSectionList(Map<String,Object> params);
}
