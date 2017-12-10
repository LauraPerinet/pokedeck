/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upmc.pcg.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import upmc.pcg.game.Card;
import upmc.pcg.game.Player;

/**
 *
 * @author Laura
 */
public class benchZone extends JPanel { 
    private GameGUI gameGUI;
    private Boolean selectPokemon=false;
    private String action="";
    
    public benchZone(GameGUI g) {
        this.setLayout(new GridLayout(1, 7));
        this.gameGUI =g;
    }

    public void setBench(ArrayList<Card> bench) {
        JPanel card;
        for(int i=0; i<5; i++){
            if(!bench.isEmpty()){
                if(bench.size()>i && bench.get(i)!=null){
                    card=new CardGUI(bench.get(i), selectPokemon, action, gameGUI);
                }else{
                    card=new EmptyZone();
                }
            }else{
                card=new EmptyZone();
            }
            this.add(card);
        }
        
    }

    public void setZoneSelectedCard(Player p) {
        Card c=p.getSelectedCard();
        JPanel selectedCard=new JPanel();
        selectedCard.setLayout(new FlowLayout());
        
        
        JPanel titre=new EmptyZone("Carte selectionnée");
        JPanel zoneCard;
        JButton btn=null;
        
        if(c!=null){
            zoneCard=new CardGUI(c);
            btn=newButton( c, p);
            
        }else{
            zoneCard=new EmptyZone();
        }
        
        titre.setPreferredSize(new Dimension(250,30));
        zoneCard.setPreferredSize(new Dimension(250,400));  
        
        selectedCard.add(titre);
        selectedCard.add(zoneCard);
        if(btn!=null){
            selectedCard.add(btn);
        }
        this.add(selectedCard);
    }

    public void setZones(Player p, Boolean selectPokemon, String action) {
        this.selectPokemon=selectPokemon;
        this.action=action;
        this.removeAll();
        this.setZoneSelectedCard(p);
        this.setBench(p.getBench());
    }
    public void setZones(Player p) {
        this.removeAll();
        this.setZoneSelectedCard(p);
        this.setBench(p.getBench());
    }

    private JButton newButton(Card c, Player p) {
        JButton btn=null;
        
        if(c.getTypeCard().equals("POKEMON")){
            if(p.getActivePokemon()==null){
                btn=new Btn(c, "Activer ce pokemon", "activation", gameGUI);
            }else{
                System.out.println(p.getBench().size());
                if(p.getBench().size()<5 && !p.getHasBench()){
                    btn=new Btn(c, "Mettre sur le banc", "toBench", gameGUI);
                }
            }   
        }else{
            if(p.getActivePokemon()!=null && p.getReady() && !p.getHasPutEnergy()){
                btn=new Btn(c, "Associer a un pokemon", "associate", gameGUI);
            }
        }
        return btn;
    }

    
    
    
}
