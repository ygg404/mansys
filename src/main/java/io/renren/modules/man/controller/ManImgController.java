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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.spider.ContentUitls;

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
    @Autowired
    private ManSectionService manSectionService;

    @RequestMapping("/pa")
    public R pa(){
        try{
            String baseUrl = "http://www.imomoe.in";
            String Url = "http://www.imomoe.in/view/7388.html";
            // 漫画主链接主页面
            Document document = ContentUitls.getContent(Url);
            String manName = document.select("title").eq(0).text();
            // 查找是否存在该漫画
            ManNovelEntity entity = manNovelService.findOne(Url);
            if(entity == null){
                entity = new ManNovelEntity();
                // 获取最大的自增ID
                List<ManNovelEntity> list = manNovelService.selectList(new EntityWrapper<ManNovelEntity>().orderBy("manId",false));
                if (list.size() < 1) {
                    entity.setManid(1000001L);
                } else {
                    entity.setManid(list.get(0).getManid() + 1);
                }
                entity.setManname(manName);
                entity.setPaurl(Url);
                entity.setKeys(manName + " " + "免费动漫观看");
            }
            String contInfo = document.select("div[class=info]").first().text();
            entity.setContent(contInfo);
            Elements pinfo = document.select("div[class=alex]").select("p");
            for(Element info : pinfo){
                if (info.text().indexOf("别名：") >= 0) {
                    entity.setOthername(info.text().replace("别名：",""));
                }else {
                    entity.setStatus(info.text());
                }
            }

            Elements infoList = document.select("div[class=alex]").select("span");
            for(Element info : infoList){
                if (info.text().indexOf("地区：") >= 0) {
                    entity.setArea(info.text().replace("地区：",""));
                }
                if (info.text().indexOf("类型：") >= 0) {
                    entity.setCategory(info.text().replace("类型：",""));
                }
                if (info.text().indexOf("年代：") >= 0) {
                    entity.setDisplayTime( info.text().replace("年代：",""));
                }
                if (info.text().indexOf("标签：") >= 0) {
                    entity.setLabel(info.text().replace("标签：",""));
                }
            }

            manNovelService.insertOrUpdate(entity);

            Elements aherf = document.select("div[class=movurl]").select("ul").select("li");
            for(int i=aherf.size()-1; i>=0; i--){
                Element em = aherf.get(i);
                String sectionHref = em.select("a").attr("href");
                String sectionTitle = em.select("a").text();
                System.out.println(sectionTitle);

                // 动漫的附链接视频
                Document videoDoc = ContentUitls.getContent(baseUrl + sectionHref);
                String jsUrl = baseUrl + videoDoc.select("div[class=player]").select("script[type=text/javascript]").first().attr("src");
                //通过url获得连接
                URL u = new URL(jsUrl);
                URLConnection yc = u.openConnection();
                //读取返回的数据
                BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF-8"));
                String inputline = null;
                String listJson = "";
                while((inputline=in.readLine())!=null){
                    listJson += inputline;
                }
                in.close();
                // 正则匹配
                Pattern p = Pattern.compile("\\[\'(.*?)\\\']");
                Matcher m = p.matcher(listJson);
                if(m.find()){
                    String[] jsonList = m.group(0).split("','");
                    for(int index = jsonList.length - 1; index >= 0 ; index--){
                        String urlJson = jsonList[index];
                        String videoUrl = urlJson.substring(urlJson.indexOf("$") + 1 , urlJson.lastIndexOf("$"));
                        String videoName = urlJson.substring(urlJson.indexOf("\\u") , urlJson.indexOf("$"));
                        videoName = new String(StringEscapeUtils.unescapeJava(videoName).getBytes(),"utf-8");

                        ManSectionEntity sectionEntity = manSectionService.selectOne(
                                new EntityWrapper<ManSectionEntity>().eq("manId",entity.getManid()).eq("title",videoName));
                        if (sectionEntity == null) {
                            sectionEntity = new ManSectionEntity();
                            sectionEntity.setManid(entity.getManid());
                            sectionEntity.setSectionid(UuidUtil.getShortUUID());
                            sectionEntity.setTitle(videoName);
                            sectionEntity.setVideoUrl(videoUrl);
                            manSectionService.insert(sectionEntity);
                        } else {
                            break;
                        }
                    }
                    break;
                }
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
