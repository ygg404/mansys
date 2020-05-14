package io.renren.modules.man.dao;

import io.renren.modules.man.entity.ManImgEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 漫画章节内容
 * 
 * @author ygg
 * @date 2020-05-11 10:06:04
 */
@Mapper
public interface ManImgDao extends BaseMapper<ManImgEntity> {
	
}
