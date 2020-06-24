package io.renren.modules.man.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.man.entity.ManNovelEntity;

import java.util.Map;
import java.util.List;

/**
 * 漫画内容简介
 *
 * @author ygg
 * @date 2020-06-24 16:25:57
 */
public interface ManNovelService extends IService<ManNovelEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ManNovelEntity findOne(String paurl);

    List<ManNovelEntity> queryList(Map<String, Object> params);

    void save(ManNovelEntity entity);

    void update(ManNovelEntity entity);

    void deleteBatch(Long[] Ids);
}

