/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sklad;

import java.awt.EventQueue;


/**
 *
 * @author Adam
 */
public class Sklad {

    private static final String url = "jdbc:derby:.\\Data";    
    
    public static void main(String[] args) {

//        java.awt.EventQueue.invokeLater(() -> {
//            new HlavniOkno(url).setVisible(true);
//        });  
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HlavniOkno(url).setVisible(true);
            }
        });        
    
    }
    
}
