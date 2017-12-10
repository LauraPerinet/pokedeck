/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upmc.pcg.game;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Laura
 */
public class Scarabrute extends Pokemon{
    
    public Scarabrute(){
        super();
        this.name = "Scarabrute";
        this.hp = 80;
        this.type="POKEMON";
        this.energy_type="grass";
        this.attacks=setAttacksScarabrute();
        this.description="Il sert les proies dans ses pinces pour les trancher en deux. S'il n'y arrive pas, il les jette au loin.";
        this.weakness="fire";
        this.resistance="none";
        this.retreat_cost=1;
    }
    private ArrayList setAttacksScarabrute(){
        ArrayList<String> need1=new ArrayList(); 
        need1.add("grass");
        Attack attack1=new Attack("Empoigner et presser", 20, need1);
        
        ArrayList<String> need2=new ArrayList(); 
        need2.add("grass");
        need2.add("colorless");
        need2.add("colorless");
        Attack attack2=new Attack("Guillotine", 40, need2);
        
        ArrayList attacks=new ArrayList();
        attacks.add(attack1);
        attacks.add( attack2);
        return attacks;
    }
}