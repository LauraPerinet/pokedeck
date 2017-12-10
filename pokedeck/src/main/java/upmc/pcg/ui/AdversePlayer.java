/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upmc.pcg.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import upmc.pcg.game.Card;
import upmc.pcg.game.Player;

/**
 *
 * @author Laura
 */
public class AdversePlayer extends JPanel{
    
    public AdversePlayer() {
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(250,460)); 
        this.setBackground(Color.yellow);
    }
    
    public void setZones(Player p) {
        this.removeAll();
        Card c=p.getActivePokemon();
  
 
        JPanel titre=new EmptyZone("Pokemon adverse");
        JPanel bench=new EmptyZone("Pokemon sur le banc : "+p.getBench().size());
        JPanel zoneCard;
        
        if(c!=null){
            zoneCard=new CardGUI(c);
        }else{
            zoneCard=new EmptyZone();
        }
        
        titre.setPreferredSize(new Dimension(250,30));
        zoneCard.setPreferredSize(new Dimension(250,400)); 
        bench.setPreferredSize(new Dimension(250,30));
        
        this.add(titre);
        this.add(zoneCard);
        this.add(bench);
      
   
    }
    
}
