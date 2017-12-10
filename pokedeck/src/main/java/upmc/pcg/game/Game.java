package upmc.pcg.game;
import upmc.pcg.ui.GameUI;

import java.util.*;

public class Game implements Serializer {

	private ArrayList<Player> players;
        private Match match;
        
	public Game() {
		players = new ArrayList<>();
	}

	public void initialize( ArrayList<String> players_name ) {
           
            for( int i=0; i<2 ; i++ ) {
                Player user = new Player( players_name.get(i) );
                if(i==0){
                    user.getDeck().starterDeck("water");
                }else{
                    user.getDeck().starterDeck("fire");
                }
                /*if (! Serializer.uploadDeck( user )) {

                    String starterDeck = GameUI.askIfStarterDeck( user );
                    if(!starterDeck.equals("")) Serializer.uploadDeck( user, starterDeck );
                }*/

                this.players.add( user );
            }
	}
	
        public int getPlayersNumber(){
            return players.size();
        }
        public ArrayList getAllPlayers(){
            return players;
        }
	public Player getPlayer() {

		Player p = null;
		
		for( Player cursor : this.players ) {
			
			if ( cursor.getIsPlaying() ) {
				p=cursor;
			}
		}
		
		if( p != null ) {
			return p;
		} else {
			throw new UnsupportedOperationException( "Not supported yet." );
		}
	}
	
	public Player nextPlayer() {
		
		Player current = getPlayer();
 
		Player nextPlayer = getAdversePlayer( current );
		current.play( false );
		nextPlayer.play( true );
		return nextPlayer;
	}

	public ArrayList<Card> getActualDeck() {
		return this.getPlayer().getDeck().getCards();
	}

	public void setDecks() {
		
		for( Player p : this.players ){
			Serializer.saveDeck( p );
		}
	}

        public void startMatch() {
            match=new Match(this);   
        }

    public Player getAdversePlayer(Player current) {
        int next = this.players.indexOf( current ) + 1;
		
        if( next >= this.players.size() ) {
                next = 0;
        }

        Player nextPlayer = this.players.get( next );
        return nextPlayer;
    }

}