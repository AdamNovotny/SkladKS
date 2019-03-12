/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sklad;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.GrayFilter;

/**
 *
 * @author Adam
 */
public class hlavicka extends PdfPTable{
    Image image;
    private final Font font;
    
    public hlavicka(Font f) throws DocumentException{
        super(2);
        this.font = f;
        this.font.setStyle("italic");
        try {
            BufferedImage img = ImageIO.read(new File(".\\Data/logo3.png"));
            this.image = makeBlackAndWhitePng(img);
        } catch (IOException ex) {
            Logger.getLogger(hlavicka.class.getName()).log(Level.SEVERE, null, ex);
        }
            image.setColorTransform(0);
            
            PdfPCell cell1 = new PdfPCell(image,true);
            Paragraph p = new Paragraph("Kardiostimulační středisko",this.font);
            PdfPCell cell2 = new PdfPCell(p);
            cell1.setBorder(Rectangle.BOTTOM);
            cell1.setBorderWidthBottom(2);
            cell1.setPaddingBottom(10);
            cell2.setBorder(Rectangle.BOTTOM);
            cell2.setBorderWidthBottom(2);
            cell2.setPaddingBottom(10);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT); 
            this.addCell(cell1);
            this.addCell(cell2);
            float[] columnWidths = new float[] {15f, 20f};
            this.setWidths(columnWidths);
    }
    
    public static Image makeBlackAndWhitePng(java.awt.image.BufferedImage image) throws IOException, DocumentException {
        ImageFilter filter = new GrayFilter(true, 1);  
        ImageProducer producer = new FilteredImageSource(image.getSource(), filter);  
        java.awt.Image mage = Toolkit.getDefaultToolkit().createImage(producer); 
        com.itextpdf.text.Image img1 = com.itextpdf.text.Image.getInstance(mage, null);
        return img1;
    }    
    
    
}
