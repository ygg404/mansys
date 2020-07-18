package io.renren.modules.article.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.List;

import io.renren.common.utils.FileUtil;
import io.renren.modules.article.entity.ArticleNovelEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.article.entity.ArticleContentEntity;
import io.renren.modules.article.service.ArticleContentService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 章节列表
 *
 * @author ygg
 * @date 2020-07-11 17:12:03
 */
@RestController
@RequestMapping("article/content")
public class ArticleContentController {
    @Value("${spring.file.upReportFolder}")
    private String upReportFolder;

    @Autowired
    private ArticleContentService articleContentService;

    /**
     * 分页查询
     */
    @RequestMapping("/page")
    @RequiresPermissions("article:content:page")
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = articleContentService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("article:content:list")
    public R list(@RequestParam Map<String, Object> params){
        List<ArticleContentEntity> list = articleContentService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 列表
     */
    @RequestMapping("/getSectionList")
    public R getSectionList(@RequestParam Map<String, Object> params){
        List<ArticleContentEntity> list = articleContentService.getSectionList(params);

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("article:content:info")
    public R info(@PathVariable("id") Long id){
		ArticleContentEntity articleContent = articleContentService.selectById(id);

        return R.ok().put("articleContent", articleContent);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("article:content:save")
    public R save(@RequestBody ArticleContentEntity articleContent){
        articleContent.setCreateTime(new Date());
		articleContentService.save(articleContent);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("article:content:update")
    public R update(@RequestBody ArticleContentEntity articleContent){
		articleContentService.updateById(articleContent);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("article:content:delete")
    public R delete(@RequestBody Long[] ids){
		articleContentService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 提交图片
     */
    @RequestMapping("/uploadEditorImg")
    public R uploadEditorImg(MultipartFile file, @RequestParam("sectionNo") String sectionNo) {
        String imgName = sectionNo + ".png";
        try {
            String filePath = FileUtil.setFilePath(upReportFolder, imgName , true);
            File dest = new File(filePath);
            imgName = dest.getName();
            file.transferTo(dest);
        }catch (Exception ex){
            return R.error();
        }

        return R.ok().put("errno",0).put("imgName",imgName);
    }
}
