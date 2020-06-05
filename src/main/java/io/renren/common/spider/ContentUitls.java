package io.renren.common.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ContentUitls {

    public static Document getContent(String url){
        Document doc;
        while (true){
            try {
                doc = Jsoup.connect(url)
                        .timeout(10000)
                        .ignoreContentType(true)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                        .get();
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return doc;
    }
}
