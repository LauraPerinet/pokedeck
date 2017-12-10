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
public class Otaria extends Pokemon{
    
    public Otaria(){
        super();
        this.name = "Otaria";
        this.hp = 60;
        this.type="POKEMON";
        this.energy_type="water";
        this.attacks=setAttacksOtaria();
        this.description="Un habitant des icebergs. En mer, il utilise sa corne pour briser les icebergs.";
        this.weakness="metal";
        this.resistance="none";
        this.retreat_cost=2;
    }
    private ArrayList setAttacksOtaria(){

        ArrayList<String> need1=new ArrayList(); 
        need1.add("colorless");
        Attack attack1=new Attack("Coup d'keu", 10, need1);
        
        ArrayList<String> need2=new ArrayList(); 
        need2.add("water");
        need2.add("colorless");
        Attack attack2=new Attack("Vent glacé", 20, need2);
        
        ArrayList attacks=new ArrayList();
        attacks.add(attack1);
        attacks.add( attack2);
        return attacks;
    }
}