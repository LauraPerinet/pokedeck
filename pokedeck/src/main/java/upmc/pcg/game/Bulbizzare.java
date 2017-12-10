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
public class Bulbizzare extends Pokemon{
    
    public Bulbizzare(){
        super();
        this.name = "Bulbizzare";
        this.hp = 80;
        this.type="POKEMON";
        this.energy_type="grass";
        this.attacks=setAttacksBulbizzare();
        this.description="Il a une étrange graine plantée derrière son dos. Elle grandit avec lui depuis sa naissance.";
        this.weakness="fire";
        this.resistance="water";
        this.retreat_cost=2;
    }
    private ArrayList setAttacksBulbizzare(){
        ArrayList<String> need1=new ArrayList(); 
        need1.add("grass");
        Attack attack1=new Attack("Vampigraine", 20, need1);
        
        ArrayList<String> need2=new ArrayList(); 
        need2.add("grass");
        need2.add("colorless");
        Attack attack2=new Attack("Tranch'Herbe", 40, need2);
        
        ArrayList attacks=new ArrayList();
        attacks.add(attack1);
        attacks.add( attack2);
        return attacks;
    }
}