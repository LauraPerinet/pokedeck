package upmc.pcg.game;

/**
 * @author Laura
 */

public class Trainer extends Card {
	
	public Trainer() {}
	
	public void settings() {
		
        this.map_card.put( "card_type", this.type = "TRAINER" );
        this.name = setName();
        this.description = setDescription();
        report();
	}
	
    public String toString() {
        return "TRAINER : " + this.name;
    }
    
    public void setArgument( String argument ) {
    	
        switch ( argument ) {
            case "name" :
                this.name = setName();
                break;
            case "description" :
                this.description = setDescription();
                break; 
        }
    }
}