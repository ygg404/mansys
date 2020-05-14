package io.renren.modules.man.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.man.entity.ManSectionEntity;
import io.renren.modules.man.service.ManSectionService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 章节与漫画关系表
 *
 * @author ygg
 * @date 2020-05-11 10:06:03
 */
@RestController
@RequestMapping("man/mansection")
public class ManSectionController {
    @Autowired
    private ManSectionService manSectionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("man:mansection:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = manSectionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("man:mansection:info")
    public R info(@PathVariable("id") Long id){
		ManSectionEntity manSection = manSectionService.selectById(id);

        return R.ok().put("manSection", manSection);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("man:mansection:save")
    public R save(@RequestBody ManSectionEntity manSection){
		manSectionService.save(manSection);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("man:mansection:update")
    public R update(@RequestBody ManSectionEntity manSection){
		manSectionService.updateById(manSection);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("man:mansection:delete")
    public R delete(@RequestBody Long[] ids){
		manSectionService.deleteBatch(ids);

        return R.ok();
    }

}
