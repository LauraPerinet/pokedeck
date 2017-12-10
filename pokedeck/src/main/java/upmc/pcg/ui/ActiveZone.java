/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upmc.pcg.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.util.ArrayList;
import javax.swing.JPanel;
import upmc.pcg.game.Attack;
import upmc.pcg.game.Card;
import upmc.pcg.game.Player;

/**
 *
 * @author Laura
 */
public class ActiveZone extends JPanel {
    private GameGUI gameGUI;
    private String info;
    private Boolean selectPokemon;
    
    public ActiveZone(GameGUI g) {
        super();
        this.setLayout(new BorderLayout());
        gameGUI =g;
    }
    
    public void setZone(Player p, String info, Boolean selectPokemon){
        this.selectPokemon=selectPokemon;
        this.removeAll();
        this.info=info;
        JPanel titre = new EmptyZone(info, true);
        JPanel actions = actionZone( p );
        
        titre.setPreferredSize(new Dimension(1050,60));
        actions.setPreferredSize(new Dimension(1050,430));
                
        this.add(titre, BorderLayout.NORTH);
        this.add(actions);
 
    }

    private JPanel actionZone( Player p) {
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout());
        
        JPanel pokemon =new JPanel();
        pokemon.setLayout(new GridLayout(1,1));
        pokemon.setMaximumSize(new Dimension(250, 430));
        JPanel titre =new EmptyZone("Pokemon actif");
        titre.setPreferredSize(new Dimension(250,30));
        pokemon.add(titre);
        
        JPanel card;
        JPanel actions;
        
        if( p.getActivePokemon()!=null){
            card = new CardGUI(p.getActivePokemon(), selectPokemon, "energy",gameGUI);
            actions=setBtns(p);
        }else{
            card = new EmptyZone();
            actions=new JPanel();
        }
        
        card.setPreferredSize(new Dimension(250, 430));
        pokemon.add(card);
        actions.setPreferredSize(new Dimension(800, 430));
        
        jp.add(pokemon);
        jp.add(actions);
        
        return jp;
    }

    private JPanel setBtns(Player p) {
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(3,1));
        ArrayList<Attack> attacks = p.getActivePokemon().getAttacks();
        
        if(p.getReady()){
            if(p.getHasAttacked()){
                Btn btn=new Btn(null, "Finir le tour", "doNothing", gameGUI);
                jp.add(btn);
            }else{
                for(Attack attack : attacks){
                    Btn btn=new Btn(p.getActivePokemon(), "Attaquer avec "+attack.getName(), "attack", attacks.indexOf(attack), gameGUI);
                    jp.add(btn);
                }

                Btn btn2=new Btn(p.getActivePokemon(), "Retraite", "retreat", gameGUI);
                Btn btn3=new Btn(p.getActivePokemon(), "Ne rien faire", "doNothing", gameGUI);
               
                jp.add(btn2);
                jp.add(btn3);
            }
        }else{
            Btn btn=new Btn(null, "Je suis prêt !", "isReady", gameGUI);
            jp.add(btn);  
        }
        
        return jp;
    }

    private JPanel info() {
        JPanel txt=new EmptyZone(info);
        txt.setPreferredSize(new Dimension(800, 30));
        return txt;
    }
    
}
