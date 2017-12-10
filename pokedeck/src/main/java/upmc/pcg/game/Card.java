package upmc.pcg.game;

import java.util.ArrayList;
import java.util.HashMap;
import upmc.pcg.ui.CreationCardUI;
import upmc.pcg.ui.GameUI;


/**
 * @author Laura
 */

public class Card {
	
    protected HashMap map_card = setMapCard();
    protected String type, name, energy_type, description;

    
    private static HashMap<Integer,String> energies = new HashMap<Integer, String>() {
    	{
	        put( 1, "grass" );
	        put( 2, "fire" );
	        put( 3, "water" );
	        put( 4, "lightning" );
	        put( 5, "psychic" );
	        put( 6, "fighting" );
	        put( 7, "darkness" );
	        put( 8, "metal" );
	        put( 9, "fairy" );
	        put( 10, "dragon" );
	        put( 11, "colorless" );
    	}
    };
    
    public String toString() {
    	
    	String res = this.getTypeCard().toUpperCase() + " : ";
    	
    	if ( this.name != null ) {
    		res += this.name;
    		
    	} else {
    		res += this.energy_type;
    	}
    	
    	return res;
    }
    
    protected String setEnergy() {
    	
        String energy = CreationCardUI.ask_energy_type( energies, this.type );
        this.map_card.put( "energy_type", energy );
        return energy;
    }
    
    protected String setName() {
    	
        String name = CreationCardUI.ask_name( this.type );
        this.map_card.put( "name", name );
        return name;
    }
    
    protected ArrayList<Attack> setAttacks() {
    	
        ArrayList<Attack> attacks = new ArrayList<>();
        boolean add_attack = true;
        int i = 1;
        
        do {
        	
            String name = CreationCardUI.askAttackName();
            int strenght = CreationCardUI.askAttackStrenght();
            ArrayList energy_needed = CreationCardUI.askAttackEnergy( energies );
            Attack a = new Attack( name, strenght, energy_needed );
            attacks.add( a );
            
            if( i == 1 ) {
            	
                this.map_card.put( "attack_number", 2 );
                add_attack = CreationCardUI.askIfAddAttack();
                
            } else {
                add_attack = false;
            }
            
            this.map_card.put( "attack" + i + "_name", a.getName() );
            this.map_card.put( "attack" + i + "_strenght", a.getStrenght() );
            this.map_card.put( "attack" + i + "_energy_needed", a.get_energy_needed() );
            i++;
            
        } while( add_attack );
        
        return attacks;
    }
    
    protected int setHP() {
    	
        int hp = CreationCardUI.askHP();
        this.map_card.put( "HP", hp + "" );
        return hp;
    }
    
    protected String setWeakness() {
    	
        String weakness = CreationCardUI.askWeakness( energies );
        this.map_card.put( "weakness", weakness );
        return weakness;
    }
    
    protected String setResistance() {
    	
        String resistance = CreationCardUI.askResistance( energies );
        this.map_card.put( "resistance", resistance );
        return resistance;
    }
    
    protected int setRetreat() {
    	
        int retreat = CreationCardUI.askRetreat( energies );
        this.map_card.put( "retreat_cost", retreat + " colorless" );
        return retreat;
    }
    
    protected String setDescription() {
    	
        String description = CreationCardUI.askDescription( this.type );
        this.map_card.put( "description", description );
        return description;
    }
    
    protected void report() {
        GameUI.reportCreationCard( this.toString() );
    }
    
    private HashMap<String, String> setMapCard() {
    	
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put( "card_type", "" );
        hm.put( "energy_type", "" );
        hm.put( "name", "" );
        hm.put( "HP", "" );
        hm.put( "attack1_name", "" );
        hm.put( "attack1_strenght", "" );
        hm.put( "attack1_energy_needed", "" );
        hm.put( "attack2_name", "" );
        hm.put( "attack2_strenght", "" );
        hm.put( "attack2_energy_needed", "" );
        hm.put( "weakness", "" );
        hm.put( "resistance", "" );
        hm.put( "retreat_cost", "" );
        hm.put( "description", "" );
        return hm;
    }
    
    public HashMap getMapCard() {
        return this.map_card;
    }
    
    public String getTypeCard() {
    	return this.type;
    }

    public void setArgument(String string) {}

    public void settings() {}
        
    public void addEnergy(Card e){}
    
    public ArrayList getEnergyAssociated(){ return null;}

    public boolean retreat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList getAttacks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String getEnergyType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    public boolean canAttack(Attack attack) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int isAttacked(int strenght, String energyType){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getHP() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getWeakness() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getResistance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getRetreat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}