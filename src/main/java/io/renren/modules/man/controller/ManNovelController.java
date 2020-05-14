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

import io.renren.modules.man.entity.ManNovelEntity;
import io.renren.modules.man.service.ManNovelService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 漫画内容简介
 *
 * @author ygg
 * @date 2020-05-11 10:06:04
 */
@RestController
@RequestMapping("man/mannovel")
public class ManNovelController {
    @Autowired
    private ManNovelService manNovelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("man:mannovel:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = manNovelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{manid}")
    @RequiresPermissions("man:mannovel:info")
    public R info(@PathVariable("manid") Long manid){
		ManNovelEntity manNovel = manNovelService.selectById(manid);

        return R.ok().put("manNovel", manNovel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("man:mannovel:save")
    public R save(@RequestBody ManNovelEntity manNovel){
		manNovelService.save(manNovel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("man:mannovel:update")
    public R update(@RequestBody ManNovelEntity manNovel){
		manNovelService.updateById(manNovel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("man:mannovel:delete")
    public R delete(@RequestBody Long[] manids){
		manNovelService.deleteBatch(manids);

        return R.ok();
    }

}
