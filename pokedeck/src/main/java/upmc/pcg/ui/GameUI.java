package upmc.pcg.ui;

import java.util.*;
import upmc.pcg.game.Card;
import upmc.pcg.game.Game;
import upmc.pcg.game.Player;

public class GameUI extends MenuUI {    

    private boolean goOn = true;
    
    private void fastMatch(){
        ArrayList<String> names = new ArrayList();
        names.add("Player 1");
        names.add("Player 2");
        game.initialize(names);
        game.startMatch();
    }
    private ArrayList<Card> search = new ArrayList<>();
  
    
    public void start() {
        fastMatch();
        /*printWelcomeMsg();
        
        ArrayList<String> names = askPlayersNames();
        game.initialize(names);
        
        while(goOn){
            menu();
        }*/
        printGoodbyeMsg();
    }

    private ArrayList<String> askPlayersNames() {
        boolean addPlayer=true;
        ArrayList<String> al = new ArrayList<>();
        while(addPlayer){
            String player_name = askPlayerName();
            al.add(player_name);
            addPlayer = newPlayer();
        }
        return al;
    }

    private void printWelcomeMsg() {
        print("Hi Trainer ! Welcome to the arena !");
    }
    private void welcome(Player p) {
        print("Hi "+p+" !");
    }

    public static String askIfStarterDeck(Player p) {
        print(p+", do you wanna start with a starter deck ? (Y/N)");
        char[] ok = {'Y', 'N'};
        char starterDeck=TestsUI.testChar(ok);
        String deck;
        
        if(starterDeck=='N'){
            deck="";
        }else{
            
            int choice = menuStarterDeck();
            switch(choice){
                case 1:
                    deck="Feu";
                    break;
                case 2:
                    deck="Eau";
                    break;
                default:
                    deck="";
            }
            
        }
        return deck;
        
    }
    private void menu() {
    	ArrayList<Card> deck = game.getActualDeck();
        int choice;
        
        print("Hey "+game.getPlayer()+"! Do you want to :");
        choice = mainMenu();
        
        switch(choice){
            case 1:
                askTypeCard();
                break;
            case 2:
                printDeck(deck);
                break;
            case 3:
                game.nextPlayer();
                welcome(game.getPlayer());
                break;
            case 4:
                game.startMatch();
                break;
            case 0:
                goOn = false;
                break;
        }
    }

    private void askTypeCard() {
        HashMap<Integer,String> type_card_tab=new HashMap<>();
        type_card_tab.put(1, "ENERGY");
        type_card_tab.put(2, "POKEMON");
        type_card_tab.put(3, "TRAINER");
        int choice;
        
        print("What kind of card do you want to create :");
        print("1- ENERGY        2- POKEMON        3-TRAINER");
        
        choice = TestsUI.testInt(-1, 1, type_card_tab.size());

        game.getPlayer().add_card( (String)type_card_tab.get(choice) );
        
    }
    
    public static void reportCreationCard(String c){
        print("Well done ! You've just created a new card " + c);
    }

    private void printGoodbyeMsg() {
    	print("...Save of deck(s) in progress ...");
    	game.setDecks();
        print("Bye " + game.getPlayer() + "! See you soon :D");
    }

    private void printDeck(ArrayList<Card> deck) {
        
        print("------------------------------YOUR DECK !-------------------------------");
        if(deck.size() == 0){
            print("Your deck is empty !!");
            
        }else{ 
        	menuDeck();
            for(int i = 0, n = deck.size() ; i < n ; i++){
                print( (i+1) + " - " + deck.get(i) );
            }
            printCard(deck);
        }
    }
    
    private void printCard(ArrayList<Card> deck) {
    	search.clear();
    	int deckSize = deck.size();
        int index=-1;
        do{
        	index = testMenuCard(deckSize);
        }while( index == -1 );
        if(index == -3) {
        	index = selectByName(deck);
        } else if (index == -4) {
        	index = sortByType(deck);
        }
        if (index == -5) {
        	printDeck(search);
        } else if (index == -2) {
        	return;
        } else {
            index--;
            print("You want to see the card "+deck.get(index));
            Card c = deck.get(index);
            HashMap<String, String> get_map_card = c.getMapCard();
            PrintCardUI.printCard(get_map_card);
            menu_card(index, deck);
        }
    }
    
    private int selectByName(ArrayList<Card> deck) {
    	int index = -3;
    	print("Which one do you want to see? (Write his name, max 15 char)");
    	String choice=TestsUI.testString(15);
    	for(int i = 0, n = deck.size() ; i < n ; i++){
            String cardList = deck.get(i) +"";
            String[] cardName = cardList.split(":");
            if (cardName[1].toLowerCase().indexOf(choice.toLowerCase()) > -1) {
            	index = -5;
            	search.add(deck.get(i));
            }
        }
    	if (index == -3) {
    		print("No card found with this name!");
    		index = -2;
    	}
    	return index;
    }
    
    private int sortByType(ArrayList<Card> deck) {
    	int index = -4;
    	int choiceType = menuSortCard() - 1;
    	String[] types = {"energy","pokemon","trainer"};
    	for(int i = 0, n = deck.size() ; i < n ; i++){
    		String cardList = deck.get(i) +"";
            String[] cardName = cardList.split(":");
            if (cardName[0].toLowerCase().indexOf(types[choiceType]) > -1) {
            	search.add(deck.get(i));
            	index = -5;
            }
    	}
    	if (index == -4) {
    		print("No card found with this type!");
    		index = -2;
    	}
    	return index;
    }
    
    private void menu_card(int index, ArrayList<Card> deck) {
        
        int choice = menuCard();
        
        switch(choice){
            case 1:
                print("Are you sure you want to removed "+ deck.get(index) +"? (Y/N)");
                char[] ok = {'Y', 'N'};
                char erase=TestsUI.testChar(ok);
                if( erase == 'Y'){
                    print(deck.get(index)+"...is removed");
                    deck.remove(index);
                }else{
                    print("Removing removal ...");
                }
                break;
            case 2:
                menuCardModification(deck.get(index));
                break;
            default:
            	printDeck(deck);
                break;
        }

    }

    private void menuCardModification(Card c) {
        HashMap c_map = c.getMapCard();
        HashMap<Integer, String> c_arguments = new HashMap();
        Set<String> c_keys = c_map.keySet();
        Iterator<String> it = c_keys.iterator();
        int i=1;
        
        print("What do you wanna change?");
 
        while(it.hasNext()){
            String next=it.next();
            if( !c_map.get( next ).equals("") && !next.equals("card_type") && (!next.contains("attack") || next.equals("attack1_name"))){
            if( next.equals("energy_type")) next = "energy";
                if( next.equals("attack1_name")) next = "attacks";
                if( next.equals("retreat_cost")) next = "retreat";
                
                print( i +" "+ next );
                c_arguments.put( i, next );
                i++;
            }
        }
        
        int choice =TestsUI.testInt(-1, 1, i);
        
        c.setArgument(c_arguments.get(choice));

    }

    private String askPlayerName() {
        String player_name="";
        print("What's your name ? (max 20 char)");
        do {
            player_name = TestsUI.testString( 20 );
            if (player_name == "Q") {
                print("Sorry you can't use this name. Retry please !");
            }
        } while (player_name == "Q");
        return player_name;
    }

}
