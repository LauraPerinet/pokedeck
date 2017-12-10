package upmc.pcg.game;

import java.util.ArrayList;

/**
 * @author Laura
 */

public class Attack {
	
    private String name;
    private int strenght;
    private ArrayList<String> energy_needed;
    
    public Attack( String name, int strenght, ArrayList<String> energy_needed ) {
    	
        this.name = name;
        this.strenght = strenght;
        this.energy_needed = energy_needed;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getStrenght() {
        return this.strenght;
    }
    public ArrayList<String> getEnergies(){
        return this.energy_needed;
    }
    public String get_energy_needed() {
    	
        String str = "";
        
        for( int i = 0; i < this.energy_needed.size(); i++ ) {
            str += this.energy_needed.get(i) + " ";
        }
        
        return str;
    }   
}