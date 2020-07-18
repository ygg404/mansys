package io.renren.modules.article.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.article.entity.ArticleNovelEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.article.dao.ArticleContentDao;
import io.renren.modules.article.entity.ArticleContentEntity;
import io.renren.modules.article.service.ArticleContentService;


@Service("articleContentService")
public class ArticleContentServiceImpl extends ServiceImpl<ArticleContentDao, ArticleContentEntity> implements ArticleContentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<ArticleContentEntity> page = this.selectPage(
                new Query<ArticleContentEntity>(params).getPage(),
                new EntityWrapper<ArticleContentEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    public List<ArticleContentEntity> queryList(Map<String, Object> params){
        String novelId = (String) params.get("novelId");
        List<ArticleContentEntity> list = this.selectList(
                new EntityWrapper<ArticleContentEntity>().eq(StringUtils.isNotBlank(novelId),"novel_id", novelId)
        );
        return list;
    }

    @Override
    public List<ArticleContentEntity> getSectionList(Map<String, Object> params){
        return  this.baseMapper.getSectionList(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ArticleContentEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ArticleContentEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}