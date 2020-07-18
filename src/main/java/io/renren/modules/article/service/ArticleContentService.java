package io.renren.modules.article.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.article.entity.ArticleContentEntity;
import io.renren.modules.article.entity.ArticleNovelEntity;

import java.util.Map;
import java.util.List;

/**
 * 章节列表
 *
 * @author ygg
 * @date 2020-07-11 17:12:03
 */
public interface ArticleContentService extends IService<ArticleContentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ArticleContentEntity> queryList(Map<String, Object> params);

    List<ArticleContentEntity> getSectionList(Map<String, Object> params);

    void save(ArticleContentEntity entity);

    void update(ArticleContentEntity entity);

    void deleteBatch(Long[] Ids);
}

