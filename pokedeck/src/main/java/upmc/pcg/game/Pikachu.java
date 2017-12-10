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
public class Pikachu extends Pokemon{
    
    public Pikachu(){
        super();
        this.name = "Pikachu";
        this.hp = 60;
        this.type="POKEMON";
        this.energy_type="lightning";
        this.attacks=setAttacksPikachu();
        this.description="Pikachu a de petites poches d'electricite dans ses joues. S'il se sent menacé, il envoie de petites décharges electriques.";
        this.weakness="fighting";
        this.resistance="metal";
        this.retreat_cost=1;
    }
    private ArrayList setAttacksPikachu(){

        ArrayList<String> need1=new ArrayList(); 
        need1.add("lightning");
        need1.add("lightning");
        Attack attack1=new Attack("Ronge", 10, need1);
        
        ArrayList<String> need2=new ArrayList(); 
        need2.add("colorless");
        need2.add("colorless");
        Attack attack2=new Attack("Hate", 20, need2);
        
        ArrayList attacks=new ArrayList();
        attacks.add(attack1);
        attacks.add( attack2);
        return attacks;
    }
}