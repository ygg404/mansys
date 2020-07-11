package io.renren.modules.article.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.article.entity.ArticleNovelEntity;

import java.util.Map;
import java.util.List;

/**
 * 书目列表
 *
 * @author ygg
 * @date 2020-07-11 16:18:39
 */
public interface ArticleNovelService extends IService<ArticleNovelEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ArticleNovelEntity> queryList(Map<String, Object> params);

    void save(ArticleNovelEntity entity);

    void update(ArticleNovelEntity entity);

    void deleteBatch(Long[] Ids);
}

