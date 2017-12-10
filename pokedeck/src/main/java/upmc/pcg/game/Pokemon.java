package upmc.pcg.game;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author licence
 */

public class Pokemon extends Card {
    protected String weakness, resistance;
    protected ArrayList<Attack> attacks;
    protected int hp, retreat_cost;
    

    private ArrayList<Card> energy_associated;
    
    public Pokemon() {
        this.type = "POKEMON";
        this.energy_associated=new ArrayList();
        
    }
    
    public void settings() {
    	
        this.map_card.put( "card_type", this.type = "POKEMON" );
        this.name = setName();
        this.energy_type = setEnergy();
        this.hp = setHP();
        this.attacks = setAttacks();
        this.weakness = setWeakness();
        this.resistance = setResistance();
        this.retreat_cost = setRetreat();
        this.description = setDescription();
        report();
    }
    
    public String toString() {
        return "POKEMON : "+this.name;
    }
    
    public void setArgument( String argument ) {
    	System.out.println(argument);
        switch ( argument ){
            case "energy" :
                this.energy_type = setEnergy();
                break;
            case "name" :
                this.name = setName();
                break;
            case "description" :
                this.description = setDescription();
                break;
            case "HP" :
                this.hp = setHP();
                break;
            case "attacks" :
                this.attacks = setAttacks();
                break;
            case "weakness" :
                this.weakness = setWeakness();
                break;
            case "resistance" :
                this.resistance = setResistance();
                break;
            case "retreat" :
                this.retreat_cost = setRetreat();
                break;
        }
    }
    
    public void addEnergy(Card e){
        this.energy_associated.add(e);
    }
    
    public ArrayList getEnergyAssociated(){
        return this.energy_associated;
    }
    
    public boolean retreat(){
        int energy_available=this.energy_associated.size();
        if(energy_available < this.retreat_cost){
            return false;
        }else{
            return true;
        }
        
    }

   
    private void getHurt(int strenght) {
        this.hp-=strenght;
    }
    
    public boolean canAttack(Attack attack) {
        System.out.println("on y est");
        ArrayList<Card> used=new ArrayList();
        int hasEnergy=0;
        int colorless=0;
        
        for(int i=0; i<attack.getEnergies().size(); i++ ){
            if(!this.energy_associated.isEmpty()){
                if(!attack.getEnergies().get(i).equals("colorless")){
                    for(int j=0; j<this.energy_associated.size(); j++){
                        if(this.energy_associated.get(j).getEnergyType().equals(attack.getEnergies().get(i))){
                            used.add(this.energy_associated.get(j));
                            this.energy_associated.remove(j);
                            
                            hasEnergy++;
                            break;
                        }
                    }
                    if(hasEnergy==0){return false;}
                }else{colorless++;}
            }
        }
        
        if(colorless >= this.energy_associated.size()){
            hasEnergy+=colorless;
        }
        for(int i=0; i<used.size();i++){
            this.energy_associated.add(used.get(i));
            used.remove(i);
        }

        if(hasEnergy>=attack.getEnergies().size()){
            return true;
        }else{
            return false;
        }
    }
    
    public int isAttacked(int strenght, String energyType) {
        int strong=strenght;
        if(energyType.equals(this.weakness)){
            strong*=2;
        }
        if(energyType.equals(this.resistance)){
            strong/=3;
        }
        getHurt(strong);
        return strong;
    }
    
     public int getHP(){
        return this.hp;
    }
    
    public ArrayList<Attack> getAttacks(){
        return this.attacks;
    }
    public String getEnergyType(){
        return this.energy_type;
    }
     public String getName(){
        return this.name;
    }
    
    public String getDescription(){
        return this.description;
    }
    public String getWeakness(){
        return this.weakness;
    }
    public String getResistance(){
        return this.resistance;
    }
    public int getRetreat(){
        return this.retreat_cost;
    }
}