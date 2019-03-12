/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sklad;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adam
 */
public class tabulkacelkem extends PdfPTable {
    private final int pocet;
    private final Font font;
    
    public tabulkacelkem(int i, Font f){
        super(2);
        this.pocet = i;  
        this.font = f;
    }
    
   public void naplndata(){
        try {
            this.setWidths(new float[]{60f, 10f});
            this.setWidthPercentage(95);
        } catch (DocumentException ex) {
            Logger.getLogger(tabulkacelkem.class.getName()).log(Level.SEVERE, null, ex);
        }
            PdfPCell cell1 = new PdfPCell(new Phrase("Celkem",font));
            cell1.setBorder(Rectangle.BOTTOM );
            cell1.setVerticalAlignment(Element.ALIGN_CENTER);
            cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell1.setPaddingBottom(5);
            cell1.setRightIndent(30);
            this.addCell(cell1);
                      
            PdfPCell cell2 = new PdfPCell(new Phrase(String.format("%d ks", this.pocet),font));
            cell2.setBorder(Rectangle.BOTTOM );
            cell2.setVerticalAlignment(Element.ALIGN_CENTER);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setPaddingBottom(5);
            this.addCell(cell2);    
       
       
       
   }
    
    
}
