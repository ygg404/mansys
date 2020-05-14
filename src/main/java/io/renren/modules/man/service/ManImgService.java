package io.renren.modules.man.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.man.entity.ManImgEntity;

import java.util.Map;

/**
 * 漫画章节内容
 *
 * @author ygg
 * @date 2020-05-11 10:06:04
 */
public interface ManImgService extends IService<ManImgEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(ManImgEntity entity);

    void update(ManImgEntity entity);

    void deleteBatch(Long[] Ids);
}

