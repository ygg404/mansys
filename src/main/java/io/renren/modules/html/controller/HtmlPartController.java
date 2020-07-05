package io.renren.modules.html.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.html.entity.HtmlPartEntity;
import io.renren.modules.html.service.HtmlPartService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 前端源代码
 *
 * @author ygg
 * @date 2020-07-05 22:13:00
 */
@RestController
@RequestMapping("html/part")
public class HtmlPartController {
    @Autowired
    private HtmlPartService htmlPartService;

    /**
     * 分页查询
     */
    @RequestMapping("/page")
    @RequiresPermissions("man:htmlpart:page")
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = htmlPartService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("html:part:list")
    public R list(@RequestParam Map<String, Object> params){
        List<HtmlPartEntity> list = htmlPartService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("man:htmlpart:info")
    public R info(@PathVariable("id") Long id){
		HtmlPartEntity htmlPart = htmlPartService.selectById(id);

        return R.ok().put("htmlPart", htmlPart);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("man:htmlpart:save")
    public R save(@RequestBody HtmlPartEntity htmlPart){
		htmlPartService.save(htmlPart);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("man:htmlpart:update")
    public R update(@RequestBody HtmlPartEntity htmlPart){
		htmlPartService.updateById(htmlPart);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("man:htmlpart:delete")
    public R delete(@RequestBody Long[] ids){
		htmlPartService.deleteBatch(ids);

        return R.ok();
    }

}
