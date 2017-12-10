/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upmc.pcg.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import upmc.pcg.ui.FightUI;
import upmc.pcg.ui.PrintCardUI;

/**
 *
 * @author Laura
 */
class Match {
    private Game game;
    private FightUI fightUI;
    private ArrayList<Player> fighters=new ArrayList();
    private boolean goOn=true;
    private final int VICTORY=1;
    
    public Match(Game game){
        this.game=game;
        this.fightUI=new FightUI();
        this.fighters=choseFighters();
        
        start();
        
    }
    private ArrayList choseFighters(){
        ArrayList<Player> fighters=new ArrayList(); 
        fighters.add(this.game.getPlayer());
        if(this.game.getPlayersNumber()>2){
            fighters.add(fightUI.askOpponent(this.game.getAllPlayers(), this.game.getPlayer()));
        }else{
            fighters.add(this.game.nextPlayer());
        }
        return fighters;
    }

    private void start() {
        fightUI.start(fighters);
        prepareArena();
        /*while(goOn){
                menuFight();
        }
        fightUI.endFight();*/
    }

    private void prepareArena() {
       playersGetHand();
       //playersChoosePokemons();
    }
    
    private void playersGetHand(){
        for(int i=0; i<2; i++){
            Player p=fighters.get(i);
            boolean isPokemon=false;
            while(!isPokemon){
                p.getDeck().shuffleCards();
                isPokemon=p.drawHand(7); // 7 is the numbers of cards you have to draw to initialize your deck
                if(!isPokemon){ // If there's no pokemon, try again
                    p.cancelDrawHand();
                    fightUI.noPokemon(p);
                }
            }
            
            fightUI.prepareArenaReport(p);
       }
    }

    private void playersChoosePokemons() {
        for(int i=0; i<2; i++){
            Player p=fighters.get(i);
            Card activePokemon=p.activePokemon( fightUI.chooseCard(p, "activate", "pokemon", "hand"), "hand", fightUI);
            fightUI.announceActivation(activePokemon, p);
            
            p.pokemonsOnBench( fightUI , false);
            
        }
    }
    private void playersChoosePokemons(Player p) {
        Card activePokemon=p.activePokemon( fightUI.chooseCard(p, "activate", "pokemon", "hand"), "hand", fightUI);
        fightUI.announceActivation(activePokemon, p);

        p.pokemonsOnBench( fightUI , false);
    }

    private void menuFight() {
        Player activePlayer=game.getPlayer();
        boolean hasAddEnergy=false;
        
        int action=fightUI.menuFight(activePlayer);
        switch(action){
            case 1: // SEE HAND
                Card cardToPrint=fightUI.chooseCard(activePlayer, "see", "card", "hand");
                if(cardToPrint!=null){
                    PrintCardUI.printCard(cardToPrint.getMapCard());
                }
                break;
            case 2: // SEE_ZONES
                menuZones();
                break;
            case 3: // ADD_ENERGY
                if(!hasAddEnergy){
                    Card energy=askEnergy();
                    if(energy!=null){
                        Card pokemon=askPokemon();
                        if(!pokemon.equals(null)){
                            activePlayer.combineEnergy(energy, pokemon);
                        }
                    }
                }else{
                    fightUI.actionAllreadyDone();
                }
                
                break;
            case 4://ADD POKEMON TO THE BENCH
                activePlayer.pokemonsOnBench( fightUI , true);
                break;
            case 5://CHange ACTIVE POKEMON
                changeActivePokemon();
                break;
            case 6: // ATTACK;
                boolean exec=attack();
                if(exec){ nextRound();}
                break;
            case 7: // DO NOTHING;
                nextRound();
                break;
            case 8://GIVE UP
                fightUI.giveUp(activePlayer);
                win(game.nextPlayer());
               
            default :
                System.out.println("ERROR Match.menuFight()");
            
        }
    }

    private void menuZones() {
        Player activePlayer=game.getPlayer();
        int toPrint=fightUI.menuZones();
        switch(toPrint){
            case 1: //Your active pokemon
                PrintCardUI.printCard(activePlayer.getActivePokemon().getMapCard());
                break;
            case 2: //Pokemon enemy
                Player adversary=getAdversary();
                
                PrintCardUI.printCard(adversary.getActivePokemon().getMapCard());
                break;
            case 3: // your bench
                Card cardToPrint=fightUI.chooseCard(activePlayer, "see", "card", "bench");
                if(cardToPrint!=null){
                    PrintCardUI.printCard(cardToPrint.getMapCard());
                }
                break;
            default:
                System.out.println("Error Match.menuZones()");
        }
    }

    private Card askEnergy() {
        Player activePlayer=game.getPlayer();
        Card c=fightUI.chooseCard(activePlayer, "combine", "energy", "hand");
        if(c!=null){
            if(c.getTypeCard().equals("ENERGY")){
                return c;
            }else{
                fightUI.errorTypeCard("energy");
            }
        }
        return null;
    }
    private Card askPokemon() {
        Player activePlayer=game.getPlayer();
        Card c=fightUI.askPokemonToAddEnergyTo(activePlayer);
        if(c!=null){
            if(c.getTypeCard().equals("POKEMON")){
                return c;
            }else{
                fightUI.errorTypeCard("Pokemon");
            }
        }
        return null;
    }

    private void changeActivePokemon() {
        Player activePlayer=game.getPlayer();
        if(activePlayer.getBench().size()>0){
            boolean canRetreat=activePlayer.getActivePokemon().retreat();
            if(canRetreat){
                activePlayer.changeActivePokemon( fightUI.chooseCard(activePlayer, "be your active pokemon", "pokemon", "bench") );
                fightUI.announceActivation(activePlayer.getActivePokemon(), activePlayer);
            }else{
                fightUI.noEnoughEnergy();
            }

        }else{
            fightUI.noPokemonOnBench();
        }
    }

    private Player getAdversary() {
        Player adversary=null;

        for(int i=0; i<2 ; i++){
            if(!fighters.get(i).equals(game.getPlayer())){
                adversary=fighters.get(i);
            }
        }
        return adversary;
    }

    private boolean attack() {
        Player activePlayer=game.getPlayer();
        Card pokemon =activePlayer.getActivePokemon() ;
        Attack attack = fightUI.selectAttack( pokemon );
        boolean canAttack = pokemon.canAttack( attack );
        boolean hasWin=false;
        if(canAttack){
            Card pokemonAdv = getAdversary().getActivePokemon();
            int dammage = pokemonAdv.isAttacked( attack.getStrenght(), pokemon.getEnergyType());
            boolean isDead=false;
            if(pokemonAdv.getHP()<=0){
                isDead=true;
                getAdversary().activePokemonDie();
                hasWin=activePlayer.winRound(VICTORY);
            }
            fightUI.attack(pokemon, pokemonAdv, dammage, isDead);
            if(hasWin){ win(activePlayer);}
            return true;
        }else{
            fightUI.noEnoughEnergy();
            return false;
        }
    }

    private void nextRound() {
        Player activePlayer=game.nextPlayer();
        if(activePlayer.getActivePokemon()==null){
            if(activePlayer.getBench().isEmpty()){
                win(getAdversary());
            }else{
                activePlayer.changeActivePokemon( fightUI.chooseCard(activePlayer, "be your active pokemon", "pokemon", "bench") );
            } 
        }
        fightUI.newCard(activePlayer, activePlayer.getCard());
        
    }

    private void win(Player winner) {
        fightUI.andTheWinnerIs(winner);
        goOn=false;
    }

}
