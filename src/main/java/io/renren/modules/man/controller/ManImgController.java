package io.renren.modules.man.controller;

import java.io.IOException;
import java.security.Timestamp;
import java.util.Arrays;
import java.util.Map;

import io.renren.common.spider.ContentUitls;
import io.renren.common.utils.DateUtils;
import io.renren.modules.man.entity.ManNovelEntity;
import io.renren.modules.man.service.ManNovelService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
    @Autowired
    private ManNovelService manNovelService;

    @RequestMapping("/pa")
    public R pa(){
        try{
            String baseUrl = "http://www.yhdm.tv";
            String Url = "http://www.yhdm.tv/show/4066.html";
            // 漫画主链接主页面
            Document document = ContentUitls.getContent(Url);
            String manName = document.select("title").eq(0).text();
            // 查找是否存在该漫画
            ManNovelEntity entity = manNovelService.findOne(Url);
            if(entity == null){
                entity = new ManNovelEntity();
                entity.setManname(manName);
                entity.setPaurl(Url);
                entity.setKeys(manName + " " + "免费漫画观看,YGG免费漫画观看");
            }

            String status = document.select("div[class=sinfo]").select("p").text();
            Elements ainfo = document.select("div[class=sinfo]").select("span");
            ainfo.forEach( em ->{

            });

            Elements aherf = document.select("div[class=movurl]").select("ul").select("li");
            for(int i=aherf.size()-1; i>=0; i--){
                Element em = aherf.get(i);
                String sectionHref = em.select("a").attr("href");
                String sectionTitle = em.select("a").text();
                System.out.println(sectionTitle);

                // 动漫的附链接视频
                Document videoDoc = ContentUitls.getContent(baseUrl + sectionHref);
                String videoUrl = "http://tup.yhdm.tv/?vid=" + videoDoc.select("div[class=bofang]").select("div[id=playbox]").attr("data-vid");
                Document videoUrlDoc = ContentUitls.getContent(videoUrl);
                String videoSrc = videoUrlDoc.select("video").attr("src");

                Elements scriptEles = videoUrlDoc.select("script");
                String regex = "video: {.*?";
                scriptEles.forEach( script-> {
//                    script.childNodes.forEach(note ->{
//                        System.out.println(note);
//                    });

                });
            }

//            liele.forEach(em->{
//
////                String title = em.select("a").text().replace("search","");
//
//            });
        }catch (Exception e){
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
