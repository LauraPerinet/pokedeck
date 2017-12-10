package upmc.pcg.game;

import java.util.ArrayList;
import static java.util.Collections.shuffle;
import java.util.HashMap;

/**
 * @author Laura
 */

public class Deck {
	
    protected ArrayList<Card> cards = new ArrayList<Card>();
   
    public void createCard( String card_type ) {
    	
        Card c=null;
        
        switch( card_type ){
            case "ENERGY":
                c = new Energy();
                break;
            case "POKEMON":
                c = new Pokemon();
                break;
            case "TRAINER":
                c = new Trainer();
                break;
        }
        c.settings();
        
        cards.add( c );
    }
    public void shuffleCards(){
        shuffle(this.cards);
    }
    public ArrayList<Card> getCards() {
        return this.cards;
    }
    public Card getOneCard(int index) {
        return this.cards.get(index);
    }
    public void removeCard(int index){
        this.cards.remove(index);
    }

    public void setCards(ArrayList<Card> setCards) {
    	this.cards = setCards;
    }

    void starterDeck(String type) {
        if(type.equals("fire")){
            for(int i=0; i<4 ; i++){
                Card p=new Salameche();
                Card p2=new Pikachu();
                Card p3=new Caninos();
                Card p4=new Magmar();
                Card p5=new Machoc();
                this.cards.add(p);
                this.cards.add(p2);
                this.cards.add(p3);
                this.cards.add(p4);
                this.cards.add(p5);
                
                Card e=new Energy("fighting");
                Card e2=new Energy("lightning");
   
                this.cards.add(e);
                this.cards.add(e2);
            }
            for(int i=0; i<8; i++){
                Card e=new Energy("fire");
                this.cards.add(e);
            }
            
        }else{
            for(int i=0; i<4 ; i++){
                Card p=new Carapuce();
                Card p2=new Otaria();
                Card p3=new Bulbizzare();
                Card p4=new Taupiqueur();
                Card p5=new Scarabrute();
                this.cards.add(p);
                this.cards.add(p2);
                this.cards.add(p3);
                this.cards.add(p4);
                this.cards.add(p5);
                
                Card e=new Energy("fighting");
                Card e2=new Energy("water");
   
                this.cards.add(e);
                this.cards.add(e2);
            }
            for(int i=0; i<8; i++){
                Card e=new Energy("grass");
                this.cards.add(e);
            }
        }
    }
}