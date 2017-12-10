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
public class Salameche extends Pokemon{
    
    public Salameche(){
        super();
        this.name = "Salameche";
        this.hp = 50;
        this.type="POKEMON";
        this.energy_type="fire";
        this.attacks=setAttacksSalameche();
        this.description="Salameche, un des pokemons les plus celebre  !";
        this.weakness="water";
        this.resistance="none";
        this.retreat_cost=1;
    }
    private ArrayList setAttacksSalameche(){
        ArrayList<String> need1=new ArrayList(); 
        need1.add("colorless");
        Attack attack1=new Attack("Represailles", 10, need1);
        
        ArrayList<String> need2=new ArrayList(); 
        need2.add("colorless");
        need2.add("fire");
        Attack attack2=new Attack("Queue de Flamme", 20, need2);
        
        ArrayList attacks=new ArrayList();
        attacks.add(attack1);
        attacks.add( attack2);
        return attacks;
    }
}
