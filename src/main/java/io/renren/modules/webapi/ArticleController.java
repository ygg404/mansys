package io.renren.modules.webapi;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import io.renren.common.pdfUtils.SplitPdf;
import io.renren.common.utils.R;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionGoTo;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.ListIterator;

@RestController
@RequestMapping("webapi/article")
public class ArticleController {

    @RequestMapping("/pa")
    public R pa() {
        File file = new File("F:\\BaiduNetdiskDownload\\01. 18供配电 精讲班 (视频课程+电子资料)\\03 负荷计算及负荷分级\\精讲班-负荷计算 ( 讲义 )\\2018年供配电专业[精讲班〕负荷计算第一讲(1).pdf");
        if (!file.exists()) { // 文件不存在，则 从FTP下载文件到本地
            return R.error();
        }

        //创建PdfDocument实例
        PdfDocument doc= new PdfDocument();
        //加载PDF文件
        doc.loadFromFile("F:\\软件\\cv\\OpenCV官方教程中文版（For Python）.pdf");

        PdfPageBase page;
        //遍历PDF页面，获取文本
        PdfDocument newPdf= new PdfDocument();
        StringBuilder sb= new StringBuilder();
        for(int i=0;i<doc.getPages().getCount();i++){
            //添加新页面到新文档
            page=doc.getPages().get(i);
            sb.append(page.extractText(true));
            String pageText = page.extractText(true);
        }
        FileWriter writer;

//        doc.saveToFile("ToHTML.html", FileFormat.HTML);

//        StringBuilder sb= new StringBuilder();
//
//        PdfPageBase page;
//        //遍历PDF页面，获取文本
//        for(int i=0;i<doc.getPages().getCount();i++){
//            page=doc.getPages().get(i);
//            sb.append(page.extractText(true));
//        }
//        FileWriter writer;
//
        try {
            //将文本写入文本文件
            writer = new FileWriter("ExtractText.txt");
            writer.write(sb.toString().replaceAll("Evaluation Warning : The document was created with Spire.PDF for Java.", "")
            .replaceAll("www.linuxidc.com" , ""));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        doc.close();
        //读取pdf文件内容-代码实现
        try {
//            SplitPdf.splitPdf(18,"F:\\BaiduNetdiskDownload\\OpenCV3.pdf", "e.txt", "e:\\");
//            printOnline("F:\\BaiduNetdiskDownload\\OpenCV3.pdf");
//            System.out.println(getText(new File("F:\\BaiduNetdiskDownload\\OpenCV3.pdf") ));

        }catch (Exception e) {
            return R.error();
        }
        return R.ok();
    }

    public static void printOnline(String path_test) throws InvalidPasswordException, IOException {
        PDDocument document = PDDocument.load(new File(path_test));
        PDDocumentOutline bookmarks1 = document.getDocumentCatalog().getDocumentOutline();

        if (bookmarks1 != null) {
            printBookmark(document, bookmarks1, "");
        } else {
            System.out.println("This document does not contain any bookmarks");
        }
    }

    /**
     * <p>
     * 打印BOOKMARKS
     * </p>
     *
     * @author wanghuihui
     * @date 2017年10月23日下午1:38:26
     * @param document
     * @param bookmark
     * @param indentation
     * @throws IOException
     */
    public static void printBookmark(PDDocument document, PDOutlineNode bookmark, String indentation) throws IOException {
        PDOutlineItem current = bookmark.getFirstChild();
        while (current != null) {
            System.out.println(indentation + current.getTitle());
            if (current.getDestination() instanceof PDPageDestination) {
                PDPageDestination pd = (PDPageDestination) current.getDestination();
                System.out.println(" number = "+pd.getPageNumber());
                System.out.println(" cosobject = "+pd.getCOSObject());
                System.out.println(indentation + "Destination page: " + (pd.retrievePageNumber() + 1));
                COSArray array = pd.getCOSObject();
                array.size();
                array.get(1);
                for(int i=0;i<array.size();i++){
                    COSBase obj = array.get(i);
                    System.out.println(obj);
                }
            }
            if (current.getAction() instanceof PDActionGoTo) {
                PDActionGoTo gta = (PDActionGoTo) current.getAction();
                if (gta.getDestination() instanceof PDPageDestination) {
                    PDPageDestination pd = (PDPageDestination) gta.getDestination();
                    //System.out.println(indentation + "Destination page: " + (pd.retrievePageNumber() + 1));
                }
            }
            System.out.println("------------------------"+current.getOpenCount()+"   ---------------------- ");


            //Iterable<PDOutlineItem>  children = current.children();
            //System.out.println(JSONObject.fromObject(children).toString());
            printBookmark(document, current, indentation + "    ");
            current = current.getNextSibling();
        }
    }

    public static String getText(File file)throws Exception {
        boolean sort = false;
        int startPage = 1;
        int endPage = 10;
        PDDocument document = null;
        try {
            try {
                document = PDDocument.load(file);
            } catch (MalformedURLException e) {

            }
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(sort);
            stripper.setStartPage(startPage);
            stripper.setEndPage(endPage);
            return stripper.getText(document);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }
}
