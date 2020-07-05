package io.renren.modules.html.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.html.entity.HtmlPartEntity;

import java.util.Map;
import java.util.List;

/**
 * 前端源代码
 *
 * @author ygg
 * @date 2020-07-05 22:13:00
 */
public interface HtmlPartService extends IService<HtmlPartEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<HtmlPartEntity> queryList(Map<String, Object> params);

    void save(HtmlPartEntity entity);

    void update(HtmlPartEntity entity);

    void deleteBatch(Long[] Ids);
}

