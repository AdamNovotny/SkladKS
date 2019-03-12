/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sklad;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Adam
 */
 class rklikmenu extends JPopupMenu {
    private final JMenuItem anItem;
    private final int sloupec;
    private final int id;
    private final List<String> nazvy_materialu;
    private final  prepis n;
    private final Connection conn;
    
    public rklikmenu(int c, int id, List<String> nazvy_materialu, Connection conn){
        super();
        this.sloupec = c;
        this.id = id;
        this.nazvy_materialu = nazvy_materialu;
        this.conn = conn;
        anItem = new JMenuItem("Prepsat"); 
        this.n = new prepis(this.sloupec,this.id, this.nazvy_materialu, this.conn);
        listener();
        add(anItem);
    }
    
     private void listener() {
       //anItem.addActionListener((java.awt.event.ActionEvent evt) -> {
 //           java.awt.EventQueue.invokeLater(() -> {
//               n.setVisible(true); });        
         anItem.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e)  {       

            EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                n.setVisible(true);
            }
        }); 
            }}); 
    }
     
     
     JFrame getn(){
         return this.n;
     }

}  
