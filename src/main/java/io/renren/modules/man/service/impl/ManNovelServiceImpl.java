package io.renren.modules.man.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.man.dao.ManNovelDao;
import io.renren.modules.man.entity.ManNovelEntity;
import io.renren.modules.man.service.ManNovelService;


@Service("manNovelService")
public class ManNovelServiceImpl extends ServiceImpl<ManNovelDao, ManNovelEntity> implements ManNovelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<ManNovelEntity> page = this.selectPage(
                new Query<ManNovelEntity>(params).getPage(),
                new EntityWrapper<ManNovelEntity>().like(StringUtils.isNotBlank(key),"id", key)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ManNovelEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ManNovelEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}