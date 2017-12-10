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
public class Caninos extends Pokemon{
    
    public Caninos(){
        super();
        this.name = "Caninos";
        this.hp = 80;
        this.type="POKEMON";
        this.energy_type="fire";
        this.attacks=setAttacksCaninos();
        this.description="Un pokemon très loyal. Il restera immobile jusqu'à que son dresseur lui donne un ordre.";
        this.weakness="water";
        this.resistance="none";
        this.retreat_cost=2;
    }
    private ArrayList setAttacksCaninos(){

        ArrayList<String> need1=new ArrayList(); 
        need1.add("fire");
        need1.add("colorless");
        Attack attack1=new Attack("Fournaise", 10, need1);

        ArrayList attacks=new ArrayList();
        attacks.add(attack1);
        return attacks;
    }
}