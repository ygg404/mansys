package io.renren.modules.man.controller;

import java.io.IOException;
import java.security.Timestamp;
import java.util.Arrays;
import java.util.Map;

import io.renren.common.utils.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ManImgService manImgService;

    @RequestMapping("/pa")
    public R pa(){
        try{
            String Url = "https://manhua.fzdm.com/132/";
            Document document = Jsoup.connect(Url)
                    .timeout(10000)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                    .get();

            Elements element = document.select(".w_r").eq(1).select("li");
//            document.select("meta").attr("property","og:title").attr("name","keywords")
            String title = document.select("title").eq(0).text();
            element.forEach(em->{
                String href = em.select("a").attr("href");
//                String title = em.select("a").text().replace("search","");

            });
        }catch (IOException e){
            e.printStackTrace();
        }
        return R.ok();
    }

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
