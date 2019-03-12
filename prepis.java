/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sklad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Adam
 */

public class prepis extends JFrame {

    private final prepispanel panel;
    private final int kprepisu;
    private final int id;
    private final List<String> list;
    private final Connection conn;
    String akt;
    
     prepis(int c, int id, List<String> l, Connection conn){
      super();
      this.list = l;
      this.kprepisu = c;
      this.id = id;
      this.setSize(410, 210);
      this.panel = new prepispanel();
      this.add(panel);
      this.conn = conn;
      init();
      
     }

    private void init() {
        switch (this.kprepisu){
            case 0:
                this.panel.popisek("Zadej novy nazev:");
                this.panel.naplncombobox(this.list);
                akt = "UPDATE APP.SKLAD SET NAZEV = ? WHERE ID = ?"; 
                break;
            case 1:
                this.panel.popisek("Zadej nove SN:");
                this.panel.nulcombobox();
                akt = "UPDATE APP.SKLAD SET SN = ? WHERE ID = ?"; 
                break;
        }
        
//        this.panel.getklik().addActionListener((java.awt.event.ActionEvent evt) -> {
//            aktualizace();
//            konec();
//        });
        this.panel.getklik().addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e)  {
                aktualizace();
                konec(); }
            });
        
    }
    
    private void aktualizace(){

        try {           
            PreparedStatement ps = conn.prepareStatement(akt);
            ps.setString(1,panel.dataout());
            ps.setInt(2, this.id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Prijem.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    
    
    private void konec(){
        this.dispose();
    }


}
