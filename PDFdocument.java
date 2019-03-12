/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sklad;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Adam
 */
public class PDFdocument {
        private BaseFont bf;
        private final Document doc;
        private final Font font; 
        private OutputStream file;
        private final Footer footer;
        private PdfWriter writer;
        private int pocet = 0;
        
        PDFdocument(){
         this.doc = new Document(PageSize.A4);
            try {
                this.bf = BaseFont.createFont(".\\Data/l_10646.ttf", "Cp1250", BaseFont.EMBEDDED);
                JFileChooser chooser =new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
                chooser.setCurrentDirectory(new File("."));
                chooser.setFileFilter(filter);
                chooser.showSaveDialog(null);  
                File f = chooser.getSelectedFile();
                    String filePath = f.getPath();
                    if(!filePath.toLowerCase().endsWith(".pdf"))
                    {
                       this.file = new FileOutputStream(filePath+".pdf");
                        }
                    else {
                        this.file = new FileOutputStream(filePath);
                    }

                this.writer = PdfWriter.getInstance(doc, file);
            } catch (    DocumentException | IOException ex) {
                Logger.getLogger(PDFdocument.class.getName()).log(Level.SEVERE, null, ex);
            }

        this.font = new Font(bf,20); 
        this.doc.setMargins(36,36,60,80);  
        this.footer = new Footer();
        this.writer.setBoxSize("art", doc.getPageSize());
        this.writer.setPageEvent(footer);           
        }
        
        public void opendoc(){
            this.doc.open();
        }
        
        public void closedoc(){
            this.doc.close();
            try {
                this.file.close();
            } catch (IOException ex) {
                Logger.getLogger(PDFdocument.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void hlavicka() throws DocumentException{
            PdfPTable t1 = new hlavicka(new Font(bf,15));
            t1.setWidthPercentage(100);
            this.doc.add(t1);  
        }
        
        public void vydejka(String s1, String s2) throws DocumentException{
            PdfPTable tb = new PdfPTable(2);
            tb.setWidthPercentage(100);
            tb.setSpacingBefore(20);
            tb.setSpacingAfter(15);
            Paragraph p1 = new Paragraph(s1+s2.substring(8),font);
            PdfPCell c1 = new PdfPCell(p1);
            c1.setBorder(Rectangle.NO_BORDER);
            Paragraph p2 = new Paragraph(s2,font);
            PdfPCell c2 = new PdfPCell(p2);
            c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c2.setBorder(Rectangle.NO_BORDER);
            tb.addCell(c1);
            tb.addCell(c2);
            doc.add(tb);                
        }
        
        public void adresa(String[] adresa) throws DocumentException{
            Paragraph a1 = new Paragraph(adresa[0],new Font(bf,15));
            Paragraph a2 = new Paragraph(adresa[1],new Font(bf,15));
            Paragraph a3 = new Paragraph(adresa[2],new Font(bf,15));
            a1.setAlignment(Element.ALIGN_LEFT); a2.setAlignment(Element.ALIGN_LEFT); a3.setAlignment(Element.ALIGN_LEFT);
            a1.setIndentationLeft(60); a2.setIndentationLeft(60); a3.setIndentationLeft(60);
            a3.setSpacingAfter(20);
            doc.add(a1); doc.add(a2); doc.add(a3);
            PdfPTable a = new PdfPTable(1);
            a.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell();
            cell.setBorder(Rectangle.BOTTOM);
            cell.setBorderWidthBottom(2);
            a.addCell(cell);
            a.setSpacingAfter(20);
            doc.add(a);             
        }
        
        public void datatabulky(String s,ResultSet rs) throws DocumentException {
            pdftabulka t = new pdftabulka(s,new Font(bf,12),rs);
            t.setkusu(this.pocet);
            t.naplndata();
            t.setWidthPercentage(95);
            t.setSpacingAfter(40);
            doc.add(t);
            this.pocet = t.getkusu();           
        }
        
        public void celkove() throws DocumentException{
            tabulkacelkem t = new tabulkacelkem(this.pocet,new Font(bf,12));
            t.naplndata();
            doc.add(t);            
        }
        
}
