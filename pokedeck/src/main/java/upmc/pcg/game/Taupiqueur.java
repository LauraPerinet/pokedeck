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
public class Taupiqueur extends Pokemon{
    
    public Taupiqueur(){
        super();
        this.name = "Taupiqueur";
        this.hp = 40;
        this.type="POKEMON";
        this.energy_type="fighting";
        this.attacks=setAttacksTaupiqueur();
        this.description="Il vit à un mètre sous terre et se nourrit de racines. Il apparait rarement à la surface";
        this.weakness="grass";
        this.resistance="lightning";
        this.retreat_cost=1;
    }
    private ArrayList setAttacksTaupiqueur(){
        
        ArrayList<String> need1=new ArrayList(); 
        need1.add("fighting");
        Attack attack1=new Attack("Tunnel", 10, need1);
        
        ArrayList<String> need2=new ArrayList(); 
        need2.add("fighting");
        need2.add("fighting");
        Attack attack2=new Attack("Jet de boue", 40, need2);
        
        ArrayList attacks=new ArrayList();
        attacks.add(attack1);
        attacks.add( attack2);
        return attacks;
    }
}