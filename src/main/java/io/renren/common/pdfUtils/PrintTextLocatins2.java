package io.renren.common.pdfUtils;


import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.IOException;

public class PrintTextLocatins2 extends PDFTextStripper {

    private static int BOLD_F_NUM = 2;
    private static String[] BOLD_FLAGS = {"Bold", "CAJ FNT04"};
    private static int ITALIC_F_NUM = 2;
    private static String[] ITALIC_FLAGS = {"Italic", "CAJ FNT03"};

    private static boolean IsBold(String font)
    {
        int i;
        for (i = 0; i < BOLD_F_NUM; i++)
            if (font.contains(BOLD_FLAGS[i]))
                return true;
        return false;
    }

    private static boolean IsItalic(String font)
    {
        int i;
        for (i = 0; i < ITALIC_F_NUM; i++)
            if (font.contains(ITALIC_FLAGS[i]))
                return true;
        return false;
    }

    public PrintTextLocatins2() throws IOException
    {
        super.setSortByPosition( false );
    }

    protected void processTextPosition( TextPosition text )
    {
        //PDFontDescriptor fd = text.getFont().getFontDescriptor();

        System.out.println( "String[" +
                text.getXDirAdj() + "," +
                text.getYDirAdj() +
                " fs=" + text.getFontSize() +
                " xscale=" + text.getXScale() +
                " height=" + text.getHeightDir() +
                " space=" + text.getWidthOfSpace() +
                " width=" + text.getWidthDirAdj() +
                " subfont=" + text.getFont().getSubType() +
                "]" +
                text.getCharacterCodes() );
    }

    /**
     * This will print the usage for this document.
     */
    private static void usage()
    {
        System.err.println( "Usage: java org.apache.pdfbox.examples.pdmodel.PrintTextLocations <input-pdf>" );
    }
}