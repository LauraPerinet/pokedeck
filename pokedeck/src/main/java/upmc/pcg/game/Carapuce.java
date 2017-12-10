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
public class Carapuce extends Pokemon{
    
    public Carapuce(){
        super();
        this.name = "Carapuce";
        this.hp = 60;
        this.type="POKEMON";
        this.energy_type="water";
        this.attacks=setAttacksCarapuce();
        this.description="Il se refugie dans sa carapace et réplique par un éclaboussement à la première occasion.";
        this.weakness="grass";
        this.resistance="none";
        this.retreat_cost=1;
    }
    private ArrayList setAttacksCarapuce(){

        ArrayList<String> need1=new ArrayList(); 
        need1.add("water");
        need1.add("colorless");
        Attack attack1=new Attack("Eclaboussure", 20, need1);

        ArrayList attacks=new ArrayList();
        attacks.add(attack1);
        return attacks;
    }
}