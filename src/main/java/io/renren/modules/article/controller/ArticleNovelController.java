package io.renren.modules.article.controller;

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

import io.renren.modules.article.entity.ArticleNovelEntity;
import io.renren.modules.article.service.ArticleNovelService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 书目列表
 *
 * @author ygg
 * @date 2020-07-11 16:18:39
 */
@RestController
@RequestMapping("article/novel")
public class ArticleNovelController {
    @Autowired
    private ArticleNovelService articleNovelService;

    /**
     * 分页查询
     */
    @RequestMapping("/page")
    @RequiresPermissions("article:novel:page")
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = articleNovelService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<ArticleNovelEntity> list = articleNovelService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("article:novel:info")
    public R info(@PathVariable("id") Long id){
		ArticleNovelEntity articleNovel = articleNovelService.selectById(id);

        return R.ok().put("articleNovel", articleNovel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("article:novel:save")
    public R save(@RequestBody ArticleNovelEntity articleNovel){
		articleNovelService.save(articleNovel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("article:novel:update")
    public R update(@RequestBody ArticleNovelEntity articleNovel){
		articleNovelService.updateById(articleNovel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("article:novel:delete")
    public R delete(@RequestBody Long[] ids){
		articleNovelService.deleteBatch(ids);

        return R.ok();
    }

}
