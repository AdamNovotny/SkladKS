/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sklad;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Date;

/**
 *
 * @author Adam
 */
 class Footer extends PdfPageEventHelper {

 
        int pagenumber = 0;
        Phrase p;

        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            pagenumber++;
        }
 
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            Rectangle rect = writer.getBoxSize("art");
            Date today = new Date();
            String datum = String.format("Tisk %td. %tm. %tY %n", today,today,today);

            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_RIGHT, new Phrase(String.format("Strana %d", pagenumber)),
                    rect.getRight()-36, rect.getBottom() +40, 0);
            
            
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_LEFT, new Phrase(datum),
                    36, rect.getBottom()+40 , 0);
        PdfContentByte cb = writer.getDirectContent();
        cb.setLineWidth(2);
        cb.moveTo(rect.getLeft()+36, rect.getBottom()+60);
        cb.lineTo(rect.getRight()-36, rect.getBottom()+60);
        cb.stroke();
            
        
        }
    }
