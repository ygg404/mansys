package io.renren.modules.man.dao;

import io.renren.modules.man.entity.ManSectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 章节与漫画关系表
 * 
 * @author ygg
 * @date 2020-05-11 10:06:03
 */
@Mapper
public interface ManSectionDao extends BaseMapper<ManSectionEntity> {
	
}
