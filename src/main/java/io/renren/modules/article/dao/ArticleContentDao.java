package io.renren.modules.article.dao;

import io.renren.modules.article.entity.ArticleContentEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 章节列表
 * 
 * @author ygg
 * @date 2020-07-11 17:12:03
 */
@Mapper
public interface ArticleContentDao extends BaseMapper<ArticleContentEntity> {
	
}
