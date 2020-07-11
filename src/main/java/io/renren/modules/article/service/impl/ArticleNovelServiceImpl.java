package io.renren.modules.article.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.article.dao.ArticleNovelDao;
import io.renren.modules.article.entity.ArticleNovelEntity;
import io.renren.modules.article.service.ArticleNovelService;


@Service("articleNovelService")
public class ArticleNovelServiceImpl extends ServiceImpl<ArticleNovelDao, ArticleNovelEntity> implements ArticleNovelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<ArticleNovelEntity> page = this.selectPage(
                new Query<ArticleNovelEntity>(params).getPage(),
                new EntityWrapper<ArticleNovelEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<ArticleNovelEntity> queryList(Map<String, Object> params){
        List<ArticleNovelEntity> list = this.selectList(
                new EntityWrapper<ArticleNovelEntity>()
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ArticleNovelEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ArticleNovelEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}