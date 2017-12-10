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
public class Magmar extends Pokemon{
    
    public Magmar(){
        super();
        this.name = "Magmar";
        this.hp = 80;
        this.type="POKEMON";
        this.energy_type="fire";
        this.attacks=setAttacksMagmar();
        this.description="On trouve ce pokemon près des bouches de volcan. Son corps incandescent atteint 1 200 degres";
        this.weakness="water";
        this.resistance="none";
        this.retreat_cost=2;
    }
    private ArrayList setAttacksMagmar(){
  
        ArrayList<String> need1=new ArrayList(); 
        need1.add("fire");
        Attack attack1=new Attack("Collision", 10, need1);
        
        ArrayList<String> need2=new ArrayList(); 
        need2.add("fire");
        need2.add("colorless");
        Attack attack2=new Attack("Flammèche", 30, need2);
        
        ArrayList attacks=new ArrayList();
        attacks.add(attack1);
        attacks.add( attack2);
        return attacks;
    }
}
