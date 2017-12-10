/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upmc.pcg.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Laura
 */
public class EmptyZone extends JPanel {
    String text="";
    private int width=250; 
    private int height=400;
    
    public EmptyZone(){
        
    }
    public EmptyZone(String text, Boolean infoGame) {
        this.text=text;
        this.height=30;
        this.width=800;
    }
    public EmptyZone(String text) {
        this.text=text;
        this.height=30;
    }
     public void paintComponent(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(0, 0, this.width, this.height,10,10);
        Font font=new Font("Arial", Font.BOLD, 18);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString(this.text+"", 20, 20);
    }               
    
}
