package io.renren.modules.man.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.spider.ContentUitls;

import io.renren.common.utils.FileUtil;
import io.renren.common.utils.UuidUtil;

import io.renren.modules.man.entity.ManNovelEntity;
import io.renren.modules.man.entity.ManSectionEntity;
import io.renren.modules.man.service.ManNovelService;
import io.renren.modules.man.service.ManSectionService;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.man.entity.ManImgEntity;
import io.renren.modules.man.service.ManImgService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;




/**
 * 漫画章节内容
 *
 * @author ygg
 * @date 2020-05-11 10:06:04
 */
@RestController
@RequestMapping("man/manimg")
public class ManImgController {
    @Value("${spring.file.downImgFolder}")
    private String downImgFolder;

    @Autowired
    private ManImgService manImgService;
    @Autowired
    private ManNovelService manNovelService;
    @Autowired
    private ManSectionService manSectionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("man:manimg:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = manImgService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("man:manimg:info")
    public R info(@PathVariable("id") Long id){
		ManImgEntity manImg = manImgService.selectById(id);

        return R.ok().put("manImg", manImg);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("man:manimg:save")
    public R save(@RequestBody ManImgEntity manImg){
		manImgService.save(manImg);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("man:manimg:update")
    public R update(@RequestBody ManImgEntity manImg){
		manImgService.updateById(manImg);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("man:manimg:delete")
    public R delete(@RequestBody Long[] ids){
		manImgService.deleteBatch(ids);

        return R.ok();
    }

}
