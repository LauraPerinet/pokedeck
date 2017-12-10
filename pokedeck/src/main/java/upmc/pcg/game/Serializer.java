package upmc.pcg.game;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface Serializer {
	
	public static void saveDeck( Player p ) {
		
		dataExist();
		String uri = "data/" + p.toString() + ".json";
		Deck toSave = p.getDeck();
		
		try ( Writer writer = new FileWriter( uri ) ) {
			
			Gson gson = new GsonBuilder().create();
			gson.toJson( toSave, writer );
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean uploadDeck( Player p ) {
		
		String uri = "data/" + p.toString() + ".json";
		return up(p, uri);	
		
	}
	
    public static void uploadDeck( Player p , String deck ) {
		
		String uri = "data/" + deck + ".json";
		up(p, uri);
		
	}
    
    public static boolean up( Player p, String uri ) {
    	
    	dataExist();
    	
		try( Reader reader = new FileReader( uri ) ) {
			
			Gson gson = new GsonBuilder().create();
			Deck toUp = gson.fromJson( reader, Deck.class );
			ArrayList<Card> cards = toUp.getCards();
			cards = changeClassCard(cards);
			toUp.setCards(cards);
			p.setDeck( toUp );
			System.out.println(  p + "'s deck is loaded!" );
			return true;
			
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
    }
    
    public static ArrayList<Card> changeClassCard( ArrayList<Card> cards ) {
    	
    	for ( int i = 0, n = cards.size() ; i < n ; i++ ) {
    		
    		Gson gson = new GsonBuilder().create();
    		String s = gson.toJson(cards.get(i));
			switch ( cards.get(i).type ) {
				case "ENERGY":
					Energy e = gson.fromJson(s, Energy.class);
					cards.set( i, e );
					break;
				case "POKEMON":
					Pokemon p = gson.fromJson(s, Pokemon.class);
					cards.set( i, p );
					break;
				case "TRAINER":
					Trainer t = gson.fromJson(s, Trainer.class);
					cards.set(i, t);
					break;
			}
		}
    	
    	return cards;
	}

	public static void dataExist() {
    	File folder = new File (System.getProperty( "user.dir" ) + "/data/");
    	if (!folder.exists()){
    		folder.mkdir();
    	}
    }
    
}