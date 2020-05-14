package io.renren.modules.man.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.man.entity.ManSectionEntity;

import java.util.Map;

/**
 * 章节与漫画关系表
 *
 * @author ygg
 * @date 2020-05-11 10:06:03
 */
public interface ManSectionService extends IService<ManSectionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(ManSectionEntity entity);

    void update(ManSectionEntity entity);

    void deleteBatch(Long[] Ids);
}

