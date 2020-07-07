package io.renren.modules.html.service.impl;

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

import io.renren.modules.html.dao.HtmlPartDao;
import io.renren.modules.html.entity.HtmlPartEntity;
import io.renren.modules.html.service.HtmlPartService;


@Service("htmlPartService")
public class HtmlPartServiceImpl extends ServiceImpl<HtmlPartDao, HtmlPartEntity> implements HtmlPartService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        String cate = (String)params.get("cate");

        Page<HtmlPartEntity> page = this.selectPage(
                new Query<HtmlPartEntity>(params).getPage(),
                new EntityWrapper<HtmlPartEntity>().like(StringUtils.isNotBlank(key),"id", key)
                .and().eq(StringUtils.isNotBlank(cate),"cate", cate)
        );

        return new PageUtils(page);
    }

    @Override
    public List<HtmlPartEntity> queryList(Map<String, Object> params){
        List<HtmlPartEntity> list = this.selectList(
                new EntityWrapper<HtmlPartEntity>()
        );
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(HtmlPartEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(HtmlPartEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}