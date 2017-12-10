package upmc.pcg;

import upmc.pcg.ui.GameGUI;
import upmc.pcg.ui.GameUI;

public class Pokedeck {
	
    public static void main( String[] args ) {
        GameGUI gameGUI = new GameGUI();
        gameGUI.fastMatch();
        gameGUI.setVisible(true);
        //game_ui.start();
    }
    
}