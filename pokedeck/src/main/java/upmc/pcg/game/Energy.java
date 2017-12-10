package upmc.pcg.game;

/**
 * @author Laura
 */

public class Energy extends Card { 
    
    public Energy() {}
    public Energy(String energy) {
        this.type = "ENERGY";
        this.energy_type = energy;
    }
    
    public void settings() {
    	
        this.map_card.put( "card_type", this.type = "ENERGY" );
        this.energy_type = setEnergy();
        report();
    }
    
    @Override
    public String toString() {
        return "ENERGY : " + this.energy_type;
    }
    
    public void setArgument( String argument ) {
    	
        switch ( argument ) {
            case "energy" :
                this.energy_type = setEnergy();
                break;
        }
    }
    public String getEnergyType() {
        return this.energy_type;
    }
}