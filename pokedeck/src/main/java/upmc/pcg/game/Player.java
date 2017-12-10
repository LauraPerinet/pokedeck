package upmc.pcg.game;

import java.util.ArrayList;
import upmc.pcg.ui.FightUI;
import upmc.pcg.ui.GameGUI;

/**
 * @author Laura
 */

public class Player {
	
    private String name;
    private boolean isPlaying, isReady, hasAttacked, hasPutEnergy, hasBench = false;
    private Deck deck = new Deck();
    private ArrayList<Card> hand, bench;
    private Card activePokemon, selectedCard;
    private int win=0;

    

    
    public Player() {
        this.name = "Player 1";
        this.hand=new ArrayList();
        this.isPlaying = true;
    }
    
    public Player( String name ) {
    	
        this.name = name;
        this.isPlaying = true;
        this.hand=new ArrayList();
        this.bench = new ArrayList();
    }
    
    public void add_card( String card_type ) {
        this.deck.createCard( card_type );
    }
    
    public boolean drawHand(int numCardsToAdd) {
        boolean isPokemon=false;
        for(int i=0; i<numCardsToAdd; i++){
            Card c=this.deck.getOneCard(0);
            if(c.getTypeCard().equals("POKEMON")){
                isPokemon=true;
            }
            this.deck.removeCard(0);
            this.hand.add(c);
        }
        return isPokemon;
    }
    public void cancelDrawHand(){
        for(int i=0; i<this.hand.size(); i++){
            this.deck.getCards().add(this.hand.get(0));
            this.hand.remove(0);
        }
    }
    
    public Deck getDeck() {
        return this.deck;
    }
    
    public ArrayList<Card> getHand(){
        return this.hand;
    }
    public Card activePokemon(Card pokemon, String from, FightUI fightUI){
        while(!pokemon.getTypeCard().equals("POKEMON")){
            pokemon= fightUI.errorTypeCard("POKEMON", this.hand);
        }
        this.activePokemon=pokemon;
        switch(from){
            case "hand":
                this.hand.remove(this.hand.indexOf(pokemon));
                break;
            case "bench":
                this.bench.remove(this.bench.indexOf(pokemon));
                break;
            default :
                throw new UnsupportedOperationException("Problem : the source is wrong"); 
               
        }
        return this.activePokemon;
    }
    
    public void activePokemon(Card pokemon, String from, GameGUI fightUI){
        this.activePokemon=pokemon;
        switch(from){
            case "hand":
                this.hand.remove(this.hand.indexOf(pokemon));
                this.selectedCard=null;
                break;
            case "bench":
                this.bench.remove(this.bench.indexOf(pokemon));
                break;
            default :
                throw new UnsupportedOperationException("Problem : the source is wrong"); 
               
        }
        //return this.activePokemon;
    }
    
    public void pokemonsOnBench( FightUI fightUI , boolean onlyOnce){
        boolean addPokemon=true;
        while(addPokemon && this.bench.size()<=5){
            if(!onlyOnce){
                addPokemon = fightUI.addPokemonToBench();
            }
            
            if(addPokemon){
                Card pokemonToBench=fightUI.chooseCard(this, "add to your bench", "pokemon", "hand");
                
                if(pokemonToBench!=null){
                    if(!pokemonToBench.getTypeCard().equals("POKEMON")){
                        fightUI.errorTypeCard("POKEMON");
                    }else{
                        this.bench.add(pokemonToBench);
                        this.hand.remove(this.hand.indexOf(pokemonToBench));
                        fightUI.isOnTheBench(pokemonToBench);
  
                    }
                }
            }
            if(onlyOnce){
                addPokemon=false;
            }
        }
    }
    public void pokemonsOnBench( Card c, GameGUI fightUI ){
        Card pokemonToBench=c;
        if(pokemonToBench!=null){
            this.bench.add(pokemonToBench);
            this.hand.remove(this.hand.indexOf(pokemonToBench));
            this.selectedCard=null;
        }
    }
    
    public void setDeck( Deck up ) {
    	this.deck = up;
    }

    public String toString() {
        return this.name;
    }
    
    public void play( boolean inOut ) {
        this.isPlaying = inOut;
    }
    
    public boolean getIsPlaying() {
        return this.isPlaying;
    }

    public void changeActivePokemon(Card p) {
        int index=this.bench.indexOf(p);
        if(this.activePokemon!=null){
            this.bench.set(index, this.activePokemon);
        }else{
            this.bench.remove(index);
        }
        this.activePokemon=p;
    }

    public Card getActivePokemon() {
        return this.activePokemon;
    }

    public void combineEnergy(Card e, Card p) {
        Card pokemon=p;
        for(int i=0; i<this.bench.size(); i++){
            if(this.bench.get(i).equals(p)) pokemon=this.bench.get(i);
        }
        pokemon.addEnergy(e);
        this.hand.remove(this.hand.indexOf(e));
        this.selectedCard=null;
    }
    
    public ArrayList getBench(){
        return this.bench;
    }

    public boolean winRound(int victory) {
        this.win++;
        if(this.win>=victory){
        
            return true;
        }else{
   
            return false;
        }
      
    }

    public void activePokemonDie() {
        this.activePokemon=null;
    }
    public Card getCard(){
        Card c=this.deck.getOneCard(0);
        this.hand.add(c);
        this.deck.removeCard(0);
        return c;
    }
    public Card getSelectedCard(){
        return this.selectedCard;
    }

    public void setSelectedCard(Card card) {
        this.selectedCard=card;
    }
    public void isReady(){
        this.isReady=true;
    }
    public boolean getReady(){
        return this.isReady;
    }
    public void hasAttacked( Boolean change){
        this.hasAttacked=change;
    }
    public boolean getHasAttacked(){
        return this.hasAttacked;
    }
    public void hasBench( Boolean change){
        this.hasBench=change;
    }
    public boolean getHasBench(){
        return this.hasBench;
    }
    public void hasPutEnergy( Boolean change){
        this.hasPutEnergy=change;
    }
    public boolean getHasPutEnergy(){
        return this.hasPutEnergy;
    }



    
}