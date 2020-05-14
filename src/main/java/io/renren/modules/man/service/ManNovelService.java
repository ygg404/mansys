package io.renren.modules.man.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.man.entity.ManNovelEntity;

import java.util.Map;

/**
 * 漫画内容简介
 *
 * @author ygg
 * @date 2020-05-11 10:06:04
 */
public interface ManNovelService extends IService<ManNovelEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(ManNovelEntity entity);

    void update(ManNovelEntity entity);

    void deleteBatch(Long[] Ids);
}

