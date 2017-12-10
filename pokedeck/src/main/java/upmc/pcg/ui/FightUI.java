/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upmc.pcg.ui;

import java.util.ArrayList;
import java.util.HashMap;
import upmc.pcg.game.Attack;
import upmc.pcg.game.Card;
import upmc.pcg.game.Player;
import static upmc.pcg.ui.MenuUI.print;

/**
 *
 * @author Laura
 */
public class FightUI {
    public Player askOpponent(ArrayList<Player> allPlayers, Player currentPlayer) {
        print("Who do you want to challenge ?");
        int i;
        for(i=0; i<allPlayers.size(); i++){
            if(!allPlayers.get(i).equals(currentPlayer)){
                print((i+1)+"- "+allPlayers.get(i));
            }
        }
        int choice = TestsUI.testInt(-1, 1, i);
        return allPlayers.get(choice-1); 
    }
    
    public void start(ArrayList<Player> p){
        print("*************************************** FIGHT *****************************************");
        print("---------------------------------------------------------------------------------------");
        print("                          "+p.get(0)+"  VS  "+p.get(1)+"                                ");
        print("---------------------------------------------------------------------------------------");
    }
    public void prepareArenaReport(Player p){
        print("");
        print(p+" shuffle his deck.");
        print(p+" draw his hand.");
    }
    
    public void noPokemon(Player p){
        print("");
        print(p+" has no pokemon in his hand !");
    }
    
    public Card chooseCard(Player p, String action, String type, String from){
        ArrayList<Card> toPrint=new ArrayList();
        switch(from){
            case "hand":
                toPrint=p.getHand();
                break;
            case "bench":
                toPrint=p.getBench();
                break;
            default :
                System.out.println("Error MathUI.chooseCard()");
        }
        print("");
        print(p+", which "+type+" do you want to "+action+"?");
        if(!action.equals("activate")){
            print("(Q to cancel)");
        }
        printHand(toPrint);
        
        char[] authorized_value=new char[toPrint.size()+1];
        for(int i=0; i<toPrint.size(); i++){
            String value=i+1+"";
            authorized_value[i]=value.charAt(0);
        }
        authorized_value[toPrint.size()]='Q';
       
        char choice = TestsUI.testChar(authorized_value);
        if(choice!='Q'){
            int index=choice-'0'-1;
            return toPrint.get(index);
        }else{
            return null;
        }
        
    }
    
    public void printHand(ArrayList<Card> hand){
        print("");
        for(int i=0; i<hand.size(); i++){
            print((i+1)+" - "+hand.get(i));
        }
    }

    public void announceActivation(Card activePokemon, Player p) {
        print("");
        print(activePokemon +" is activated !");
    }
    public void errorTypeCard(String type) {
        print("");
        print("That 's not a card "+type+"!");
    }
    
    public Card errorTypeCard(String type, ArrayList<Card> hand) {
        print("");
        print("You have to choose a card "+type+"!");
        int choice = TestsUI.testInt( -1, 1, hand.size() );
        return hand.get(choice-1);
    }
    
    public boolean addPokemonToBench(){
        print("");
        print("DO you wanna add a pokemon to your bench ? (Y/N)");
        char[] authorized_values={'Y', 'N'};
        char choice=TestsUI.testChar(authorized_values);
        return (choice=='Y') ? true:false;
    }
    public void isOnTheBench(Card pokemon){
        print("");
        print(pokemon +" is on the bench !");
    }

    public int menuFight(Player p) {
        print("");
        print(p+", do you want to :");
        print("1 - See yout hand");
        print("2 - See the fight zone");
        print("3 - Add an energy card to a pokemon");
        print("4 - Add a pokemon to your bench");
        print("5 - Change your active pokemon ?");
        print("6 - Attack");
        print("7 - Do nothing");
        print("8 - Give up the fight");
        int choice=TestsUI.testInt(-1, 1, 8);
        return choice;
    }

    public void giveUp(Player activePlayer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void endFight() {
        print("");
        print("Well done trainers ! That was a very good fight!");
        print("");
    }

    public int menuZones() {
        print("");
        print("Do you want to :");
        print("1 - See your active pokemon");
        print("2 - See the adverse pokemon");
        print("3 - See your bench");
        
        int choice=TestsUI.testInt(-1, 1, 3);
        return choice;
    }

    public void actionAllreadyDone() {
        print("");
        print("You can do that action only once by round");
    }

    public Card askPokemonToAddEnergyTo(Player p) {
        char[] authorized_value;
        ArrayList<Card> bench=p.getBench();
        
        print("");
        print("To which pokemon would you like to combine this energy ? (Q to back to menu)");
        print("YOUR ACTIVE POKEMON");
        print("A - "+p.getActivePokemon());
        if(!bench.isEmpty()){
            print("YOUR BENCH");
            printHand(bench);

            authorized_value=new char[bench.size()+2];
            for(int i=0; i<bench.size(); i++){
                String value=i+1+"";
                authorized_value[i]=value.charAt(0);
            }
            authorized_value[bench.size()]='Q';
            authorized_value[bench.size()+1]='A';
        }else{
            authorized_value=new char[2];
            authorized_value[0]='Q';
            authorized_value[1]='A';
        }
        
        char choice = TestsUI.testChar(authorized_value);
        if(choice!='Q'){
            if(choice=='A'){
                return p.getActivePokemon();
            }else{
               int index=choice-'0'-1;
               return bench.get(index); 
            }
        }else{
            return null;
        }
    }
    
    public Attack selectAttack( Card p ){
        ArrayList<Attack> attacks=p.getAttacks();
        print("");
        print("Which attack do you want to use ?");
        for(int i=0; i<attacks.size(); i++){

            print(i+1+" - "+attacks.get(i).getName()+" ---> "+attacks.get(i).getStrenght()+"  |  "+attacks.get(i).get_energy_needed() );
        }
        int choice=TestsUI.testInt(-1, 1, attacks.size());
        return attacks.get(choice-1);
    }
    
    public void noPokemonOnBench() {
        print("");
        print("You have no pokemon on the bench !");
    }

    public void noEnoughEnergy() {
        print("");
        print("You dont have enough energy !");
    }

    public void attack(Card pokemon, Card pokemonAdv, int dammage, boolean isDead) {
        print("");
        print(pokemon +" attack!");
        print(pokemonAdv+" take "+dammage+" dammage...");
        if(isDead){
            print(pokemonAdv+" is off of the game !");
        }
    }

    public void newCard(Player activePlayer, Card card) {
        print("");
        print(activePlayer+" draw "+card+"!");
    }

    public void andTheWinnerIs(Player winner) {
        print("");
        print("And the winner is............. "+winner+"!!!!!!");
    }

}
