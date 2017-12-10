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
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import upmc.pcg.game.Attack;
import upmc.pcg.game.Card;

/**
 *
 * @author Laura
 */
public class CardGUI extends JPanel implements MouseListener{
    private GameGUI gameGUI;
    private Card card;
    private HashMap<String, Color> colors=new HashMap();
    private final int width=250;
    private final int height=400;
    private Boolean addBtn=false;
    private String action;

    
    public CardGUI(Card c){
        this.card=c;
        setColors();
    }
    
    public CardGUI(Card c, Boolean addBtn, String action, GameGUI g){
        this.gameGUI=g;
        this.card=c;
        this.action=action;
        this.addBtn=addBtn;
        if(addBtn){
            this.addMouseListener(this);
        }
        setColors();
    }
    
    public void paintComponent(Graphics g){
        String type = this.card.getTypeCard();
        g.setColor(colors.get(this.card.getEnergyType()));
        g.fillRoundRect(0, 0, width, height,10,10);
        Font font=new Font("Arial", Font.BOLD, 18);
        g.setFont(font);
        
        
        addScripts(g, type);
        drawIllustration(g, type);
        if(type.equals("POKEMON")){
            addFooter(g);
            addEnergies(g);
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

    private void addScripts(Graphics g, String type) {
        if(g.getColor()==Color.BLACK || g.getColor()==Color.DARK_GRAY){
            g.setColor(Color.WHITE);
        }else{
            g.setColor(Color.BLACK);
        }
        
        switch(type){
            case "POKEMON":
                g.drawString(this.card.getName(), 10, 20);
                g.drawString("HP "+this.card.getHP(),width-100,20);
                addAttacks(g);
                break;
            case "ENERGY":
                g.drawString(this.card.toString(), 10, 20);
                break;
            default:
                System.out.println("probleme CardGUI");
        }
    }

    private void drawIllustration(Graphics g, String type) {
        String illustration;
        switch(type){
            case "POKEMON":
                illustration = this.card.getName();
                break;
            case "ENERGY":
                illustration = this.card.getEnergyType()+"Big";
                break;
            default:
                illustration="none";
        }
        
        try{
            Image energy=ImageIO.read(new File("img/"+card.getEnergyType()+".png"));
            Image img=ImageIO.read(new File("img/"+illustration+".png"));
            g.drawImage(energy, width-30, 10, this);
            g.drawImage(img, 10, 40, this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void addAttacks(Graphics g) {
        ArrayList<Attack> attacks=this.card.getAttacks();
        
        for(int i=0; i<attacks.size(); i++){
            ArrayList<String> energies=attacks.get(i).getEnergies();
            for(int j=0; j<energies.size(); j++){
                try{
                    Image energy=ImageIO.read(new File("img/"+energies.get(j)+".png"));
                    g.drawImage(energy, 10+(j*25), 220+(i*50), this);
                    g.drawString(attacks.get(i).getName(), 10, 260+(i*50));
                    g.drawString(attacks.get(i).getStrenght()+"", width-40, 260+(i*50));
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        
    }

    private void addFooter(Graphics g) {
        Font font=new Font("Arial", Font.BOLD, 13);
        g.setFont(font);
        g.drawString("Faiblesse",10, height-60);
        g.drawString("Résistance", 90, height-60);
        g.drawString("Retraite", 190, height-60);
        
        try{
            Image weakness=ImageIO.read(new File("img/"+this.card.getWeakness()+".png"));
            g.drawImage( weakness, 30, height-50, this);
            Image resistance=ImageIO.read(new File("img/"+this.card.getResistance()+".png"));
            g.drawImage( resistance, 120, height-50, this);
            for(int i=0; i<this.card.getRetreat(); i++){
                Image retreat=ImageIO.read(new File("img/colorless.png"));
                g.drawImage( retreat, 200+(i*25), height-50, this);
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void addEnergies(Graphics g) {
        ArrayList<Card> energies = this.card.getEnergyAssociated();
        g.drawString("Energie associée :",10, height+10);
        try{
            for(int i=0; i<energies.size(); i++){
                Image energy=ImageIO.read(new File("img/"+energies.get(i).getEnergyType()+".png"));
                g.drawImage(energy, 10+(i*25), height+20, this);
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        switch(this.action){
            case "energy":
                gameGUI.associateTo(this.card);
                break;
            case "activation":
                System.out.println("ok");
                gameGUI.activate(this.card);
                break;
            default:
                break;
        }
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
