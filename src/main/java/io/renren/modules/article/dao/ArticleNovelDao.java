package io.renren.modules.article.dao;

import io.renren.modules.article.entity.ArticleNovelEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 书目列表
 * 
 * @author ygg
 * @date 2020-07-11 16:18:39
 */
@Mapper
public interface ArticleNovelDao extends BaseMapper<ArticleNovelEntity> {

}
