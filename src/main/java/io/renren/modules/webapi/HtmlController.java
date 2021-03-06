package io.renren.modules.webapi;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.spider.ContentUitls;
import io.renren.common.utils.FileUtil;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.UuidUtil;
import io.renren.modules.html.entity.HtmlPartEntity;
import io.renren.modules.html.service.HtmlPartService;
import io.renren.modules.man.entity.ManNovelEntity;
import io.renren.modules.man.entity.ManSectionEntity;
import io.renren.modules.man.service.ManImgService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("webapi/html")
public class HtmlController {
    @Value("${spring.file.downImgFolder}")
    private String downImgFolder;

    @Autowired
    private HtmlPartService htmlPartService;

    @RequestMapping("/h5page")
    public R page(@RequestParam Map<String, Object> params){
        PageUtils page = htmlPartService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        HtmlPartEntity htmlPart = htmlPartService.selectById(id);

        return R.ok().put("htmlPart", htmlPart);
    }

    @RequestMapping("/pa")
    public R pa(){
        try {
            List<String> files = new ArrayList<String>();
            File file = new File("C:\\Users\\Administrator\\Downloads\\1");
            File[] tempList = file.listFiles();

            List<HtmlPartEntity> htmlList = new ArrayList<>();
            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isFile()) {
                    HtmlPartEntity entity = new HtmlPartEntity();

                    entity.setTitle(tempList[i].getName().replace(".zip","").replace("【推荐】", ""));
//                    entity.setImgUrl();
                    entity.setDownloadUrl("uploadFile/gamezip/" + tempList[i].getName());
                    entity.setSize(((float)tempList[i].length())/1024/2014);
                    entity.setIsMobile(1L);
                    entity.setKeys(entity.getTitle() + ",H5特效源代码");
                    entity.setCate("特效");
                    htmlList.add(entity);
                    //文件名，不包含路径
                    //String fileName = tempList[i].getName();
                }
                if (tempList[i].isDirectory()) {
                    //这里就不递归了，
                }
            }
            htmlPartService.insertBatch(htmlList);
        } catch (Exception e) {
            return R.error();
        }
        return R.ok();
    }

}
