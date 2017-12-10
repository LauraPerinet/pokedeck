/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upmc.pcg.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import upmc.pcg.game.Game;
import upmc.pcg.game.Player;

/**
 *
 * @author Laura
 */
public class HeaderGUI extends JPanel {
    private Game game;
    
    public HeaderGUI(Game game) {
        this.game=game;
        
    }
    public void paintComponent(Graphics g){
        /*Font font=new Font("Arial", Font.BOLD, 18);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("TEST", 20, 20);*/
    }

    private String getFighters() {
        ArrayList<Player> fighters=game.getAllPlayers();
        return fighters.get(0)+" VS "+fighters.get(1);
    }
    
}
