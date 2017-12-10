/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upmc.pcg.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import upmc.pcg.game.Player;

/**
 *
 * @author Laura
 */
class Victory extends JPanel {
    Player player;
    GameGUI gameGUI;
    
    public Victory(Player p, GameGUI g){
        this.player=p;
        this.gameGUI=g;
    }
    
    public void paintComponent(Graphics g){
        try{
            Image img = ImageIO.read(new File("img/victory.jpg"));
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(),this);
        }catch(IOException e){
            e.printStackTrace();
        }
        Font font=new Font("Arial", Font.BOLD, 50);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString(player+" a gagné !", 550, 400);
        
    }

   
}
