package io.renren.common.pdfUtils;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.util.ListIterator;

public class SplitPdf {
    public static String splitPdf(int pageNum, String filePath, String fileName, String outPath) {
        File indexFile = new File(filePath);// 这是对应文件名
        PDDocument document = null;
        try {
            document = PDDocument.load(indexFile);
            Splitter splitter = new Splitter();
            splitter.setStartPage(pageNum);
            splitter.setEndPage(pageNum);
            java.util.List<PDDocument> pages = splitter.split(document);
            ListIterator<PDDocument> iterator = pages.listIterator();
            while (iterator.hasNext()) {
                File file = new File(outPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                PDDocument pd = iterator.next();
                File newFile = new File(outPath + fileName);
                if (newFile.exists()) {
                    newFile.delete();
                }
                pd.save(outPath + fileName);
                pd.close();
                if (newFile.exists()) {
                    return newFile.getPath();
                }
            }
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
