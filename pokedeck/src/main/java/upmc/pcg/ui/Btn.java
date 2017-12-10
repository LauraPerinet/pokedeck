/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upmc.pcg.ui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import upmc.pcg.game.Card;

/**
 *
 * @author Laura
 */
public class Btn extends JButton implements MouseListener{
    private static ArrayList<Btn> HAND_BTN=new ArrayList();
    private String action;
    private String value;
    private Card card;
    private GameGUI gameGUI;
    private int indexAttack;
    
    public Btn(Card c, String value, String action, GameGUI g){
        super(value);
        HAND_BTN.add(this);
        this.action=action;
        this.card=c;
        this.gameGUI=g;
        this.addMouseListener(this);
        this.setBackground(Color.LIGHT_GRAY);
    }
    public Btn(Card c, String value, String action, int index, GameGUI g){
        super(value);
        HAND_BTN.add(this);
        this.action=action;
        this.indexAttack=index;
        this.card=c;
        this.gameGUI=g;
        this.addMouseListener(this);
        this.setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        switch(this.action){
            
            case "setHand":
                setHand();
                break;
            case "isReady":
                gameGUI.changePlayer();
                break;
            case "activation":
                gameGUI.activatePokemon(this.card);
                break;
            case "toBench":
                gameGUI.toBench(this.card);
                break;
            case "associate":
                gameGUI.associateEnergy();
                break;
            case "attack":
                gameGUI.attack(this.indexAttack);
                break;
            case "retreat":
                gameGUI.retreat();
                break;
            case "doNothing":
                gameGUI.changePlayer();
                break;
        }
        
    }
    private void setHand() {
        neutral(HAND_BTN);
        this.setBackground(Color.CYAN);
        Boolean action = gameGUI.selectCard(this.card);

    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //this.setBackground(Color.BLUE);
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //this.setBackground(Color.LIGHT_GRAY);
    }

    private void neutral(ArrayList<Btn> allBtns) {
        for(Btn btn : allBtns){
            btn.setBackground(Color.LIGHT_GRAY);
        }
       
    }

    
}
