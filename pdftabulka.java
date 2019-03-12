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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Adam
 */
public class pdftabulka extends PdfPTable {
    private final String hlavicka;
    private final Font font;
    private final ResultSet rs;
    private int ks=0;
    
    public pdftabulka(String b, Font f, ResultSet rs){
        //super(5);
        super(4);
        this.hlavicka = b;
        this.font = f;
        this.rs = rs;
        
    }
    
    public void naplndata(){
        try {
            this.setWidths(new float[]{30f, 15f, 15f, 10f});
        } catch (DocumentException ex) {
            Logger.getLogger(pdftabulka.class.getName()).log(Level.SEVERE, null, ex);
        }
        Font f2 = new Font(this.font.getBaseFont(),14);
        f2.setStyle("italic");
          try {
    if ( rs.next()  ) {
     //Phrase[] hlavicky = {new Phrase(this.hlavicka,f2), new Phrase("Výrobní číslo",this.font), new Phrase("Dodací list",this.font),new Phrase("Výdej",this.font),new Phrase("Počet",this.font)};
     Phrase[] hlavicky = {new Phrase(this.hlavicka,f2), new Phrase("Výrobní číslo",this.font), new Phrase("Dodací list",this.font),new Phrase("Počet",this.font)};
     for (int i=0;i<4;i++){
            PdfPCell cell = new PdfPCell(hlavicky[i]);
            cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPaddingBottom(5);
            this.addCell(cell); } 
            
            this.cyklus();

            while(rs.next()){
                this.cyklus();
                }
            }   } catch (SQLException ex) {
            Logger.getLogger(pdftabulka.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    private void cyklus() throws SQLException{
                this.ks++;
                for (int i=0;i<4;i++){
                   if(i<3) {
                    Phrase p = new Phrase(rs.getObject(i+1).toString(),this.font);
                    PdfPCell cell = new PdfPCell(p);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setVerticalAlignment(Element.ALIGN_CENTER);
                    if (i==0) {
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);} else {
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);    
                    }
                    this.addCell(cell);}
                   else {
                    Phrase p = new Phrase(rs.getObject(i+2).toString(),this.font);
                    p = new Phrase(rs.getObject(i+1).toString(),this.font);
                    PdfPCell cell = new PdfPCell(p);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.setVerticalAlignment(Element.ALIGN_CENTER);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    this.addCell(cell);          
        
        
    } } }

public int getkusu(){
    return this.ks;
}  

public void setkusu (int i){
    this.ks = i;
} 
    
    
}