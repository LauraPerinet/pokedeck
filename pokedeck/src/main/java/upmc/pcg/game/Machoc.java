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
public class Machoc extends Pokemon{
    
    public Machoc(){
        super();
        this.name = "Machoc";
        this.hp = 70;
        this.type="POKEMON";
        this.energy_type="fighting";
        this.attacks=setAttacksMachoc();
        this.description="C'est un musclor de base : pas très malin et content de le montrer.";
        this.weakness="psychic";
        this.resistance="none";
        this.retreat_cost=2;
    }
    private ArrayList setAttacksMachoc(){

        ArrayList<String> need1=new ArrayList(); 
        need1.add("fighting");
        need1.add("fighting");
        Attack attack1=new Attack("Double Baffes", 20, need1);

        ArrayList attacks=new ArrayList();
        attacks.add(attack1);
        return attacks;
    }
}