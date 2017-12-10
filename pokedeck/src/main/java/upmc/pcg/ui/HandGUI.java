/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upmc.pcg.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import upmc.pcg.game.Card;
import upmc.pcg.game.Deck;
import upmc.pcg.game.Game;
import upmc.pcg.game.Player;


/**
 *
 * @author Laura
 */
public class HandGUI extends JPanel {
    private ArrayList<Card> hand;
    private ArrayList<Btn> btns;
    private HashMap<String, Color> colors=new HashMap();
    private GameGUI gameGUI;
    
    public HandGUI(GameGUI g){
        this.gameGUI=g;
        btns=new ArrayList();
        setColors();
    }
    
    public void printHand(ArrayList<Card> hand){
        this.removeAll();
        this.hand=hand;
        this.setLayout(new GridLayout(this.hand.size(),1));
        
        if(!this.hand.isEmpty()){
            Card selected=this.hand.get(0);
            for(int i=0; i<this.hand.size(); i++){
                Btn b=new Btn(this.hand.get(i), this.hand.get(i).toString(), "setHand", gameGUI);
                this.add(b);
                this.btns.add(b);
            }
        }else{
            JPanel noCard=new EmptyZone("Plus de cartes !");
            noCard.setPreferredSize(new Dimension(250,30));
            this.add(noCard);
        }
    }      
    
    private void setColors() {
        colors.put("fire", Color.ORANGE);
        colors.put("water", Color.CYAN);
        colors.put("lightning", Color.YELLOW);
        colors.put("psychic", Color.MAGENTA);
        colors.put("fighting", Color.RED);
        colors.put("darkness", Color.DARK_GRAY);
        colors.put("metal", Color.LIGHT_GRAY);
        colors.put("fairy", Color.PINK);
        colors.put("dragon", Color.BLACK);
        colors.put("grass", Color.GREEN);
        
    }
    
    public ArrayList<Btn> getBtn(){
        return this.btns;
    }
    
}
