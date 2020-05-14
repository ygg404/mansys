package io.renren.modules.man.dao;

import io.renren.modules.man.entity.ManNovelEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 漫画内容简介
 * 
 * @author ygg
 * @date 2020-05-11 10:06:04
 */
@Mapper
public interface ManNovelDao extends BaseMapper<ManNovelEntity> {
	
}
