package io.renren.modules.webapi;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.spider.ContentUitls;
import io.renren.common.utils.FileUtil;
import io.renren.common.utils.R;
import io.renren.common.utils.UuidUtil;
import io.renren.modules.man.entity.ManNovelEntity;
import io.renren.modules.man.entity.ManSectionEntity;
import io.renren.modules.man.service.ManImgService;
import io.renren.modules.man.service.ManNovelService;
import io.renren.modules.man.service.ManSectionService;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("webapi/man")
public class ManController {
    @Value("${spring.file.downImgFolder}")
    private String downImgFolder;

    @Autowired
    private ManImgService manImgService;
    @Autowired
    private ManNovelService manNovelService;
    @Autowired
    private ManSectionService manSectionService;


    @RequestMapping("/pa")
    public R pa(){
        try {
            for(int page = 1; page <138; page++) {
                String baseUrl = "http://www.imomoe.in";
                String pageUrl = "http://www.imomoe.in/so.asp?page=" + String.valueOf(page) + "&fl=0&gj=%B8%E3%D0%A6&pl=hit";
                // 漫画分页主页面
                Document document = ContentUitls.getContent(pageUrl);
                Elements manList = document.select("div[class=pics]").first().select("li");
                for ( Element man : manList) {
                    String manViewUrl = man.select("a").first().attr("href");
                    String manName = man.select("h2").select("a").first().attr("title");

                    String viewUrl = baseUrl + manViewUrl;
                    paContent(viewUrl, manName);
                }
            }
        } catch (Exception e) {
            return R.error();
        }
        return R.ok();
    }


    public R paContent(String Url,String manName){
        try{
            String baseUrl = "http://www.imomoe.in";
//            String Url = "http://www.imomoe.in/view/290.html";
            // 漫画主链接主页面
            Document document = ContentUitls.getContent(Url);
            String key = document.select("title").eq(0).text();
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
                entity.setKeys(key + " " + "免费动漫观看");

                String imgurl = document.select("div[class=tpic l]").select("img").first().attr("src");
                if(entity.getImgUrl() == null) {
                    String nameNameuuid = manName + UuidUtil.getShortUUID();
                    FileUtil.download(imgurl,nameNameuuid +".jpg", downImgFolder);
                    entity.setImgUrl(nameNameuuid + ".jpg");
                }
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
                        String videoName = urlJson.substring(urlJson.indexOf("\\u") == -1? 0: urlJson.indexOf("\\u") , urlJson.indexOf("$"));
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
            return R.error();
        }
        return R.ok();
    }

}
