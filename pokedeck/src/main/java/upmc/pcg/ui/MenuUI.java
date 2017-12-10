package upmc.pcg.ui;
import java.util.ArrayList;
import upmc.pcg.game.Game;
import upmc.pcg.game.Player;

public class MenuUI implements TestsUI  {
	protected final Game game = new Game();
	private int choice;
    
    protected static void print( String str ){
        System.out.println( str );
    }
    
    protected boolean newPlayer() {
    	
    	print( "Do you want to add a player? Y/N" );
        char[] ok = { 'Y', 'N' };
        char choice = TestsUI.testChar( ok );  
        if( choice=='N' ) return false;
        return true;
    }
    
    protected int mainMenu() {
    	int maxChoice=2;
    	print( "1- Add a card to your deck" );
        print( "2- See your deck" );
       if(game.getPlayersNumber()>1){
           print( "3- Change player" );
           if(game.getPlayer().getDeck().getCards().size()>=30){
               print("4- Start a fight !!");
           }
           maxChoice=4;
       }
        print( "0- Leave the game" );
        int choice = TestsUI.testInt( -1, 0, maxChoice );
        return choice;
    }
    
    protected void menuDeck() {
    	
    	print( "Give the cards number to print the entire cards or :" );
        print( "Q to quit the deck menu" );
        print( "S to select by name" );
        print( "T to sort by type" );
        print( "" );
    }
    
    protected int menuCard() {
    	
    	print( "" );
        print( "Do you want to :" );
        print( "1- Remove the card" );
        print( "2- Modify the card" );
        print( "3- Return to the deck" );
        choice = TestsUI.testInt( -1, 1, 3 );
        return choice;
    }
    
    protected int testMenuCard( int deckSize ) {
    	
    	int index;
        String choice=TestsUI.testString(2);
        if ( choice.charAt(0) == 'S' ) {
        	index = -3;
        }else if ( choice.charAt(0) == 'T' ) {
        	index = -4;
        }else if ( choice.charAt(0) != 'Q' ) {
        	
            try{
               index = Integer.parseInt( choice ); 
               if( index > deckSize || index < 1 ){
            	   
                    index = -1;
                    print( "Please enter a good value" );
               }
               
            } catch( NumberFormatException e ){
            	
                index = -1;
                print("Please enter a good value");
            }
        } else {
            index = -2;
        }
        
        return index;
    }
    
    protected int menuSortCard() {
    	
    	print( "What kind of cards should we display?" );
    	print( "1 : Energy" );
    	print( "2 : Pokemon" );
    	print( "3 : Trainer" );
    	int choice = TestsUI.testInt( -1, 1, 3 );
    	return choice;
    }   
    
    protected static int menuStarterDeck(){
        print("Which stater deck will you choose ?");
        print("1- Fire and fighting");
        print("2- Water and grass");
        print("3- Finally, none. I want to create my deck from scratch.");
        int choice = TestsUI.testInt( -1, 1, 3 );
    	return choice;
    }
    
}