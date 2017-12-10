/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upmc.pcg.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import upmc.pcg.game.Attack;
import upmc.pcg.game.Card;
import upmc.pcg.game.Deck;
import upmc.pcg.game.Game;
import upmc.pcg.game.Player;
import upmc.pcg.game.Salameche;

/**
 *
 * @author Laura
 */
public class GameGUI extends JFrame {
    private Game game=new Game();
    private JPanel main;
    private JPanel north;
    private benchZone benchZone;
    private HandGUI handZone;
    private AdversePlayer adversePlayer;
    private ActiveZone activeZone;
    private Player player;
    private final int VICTORY=4;
    private Boolean winner=false;
    
    public GameGUI(){
        this.setTitle("PCG");
        this.setSize(1550,1070);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);  
        //this.setVisible(true);
    }
    
    public void fastMatch() {
        ArrayList<String> names = new ArrayList();
        names.add("Player 1");
        names.add("Player 2");
        game.initialize(names);
        game.startMatch();
        start();
    }
    
    public void start(){
        this.player = game.getPlayer();
        printZones();
        this.handZone.printHand(player.getHand());
        benchZone.setZones(player);
        activeZone.setZone(player, player+", préparez votre terrain. Choisissez votre pokemon actif.", false);
        adversePlayer.setZones(game.getAdversePlayer(player));
    }

    private void printZones() {
        main=new JPanel();
        main.setLayout(new BorderLayout());
        handZone=new HandGUI( this );
        benchZone = new benchZone(this);
        adversePlayer = new AdversePlayer();
        activeZone = new ActiveZone( this );
        

        benchZone.setPreferredSize(new Dimension(1550, 510));
        handZone.setPreferredSize(new Dimension(250, 460));
        adversePlayer.setPreferredSize(new Dimension(250, 460));
        
        
        main.add(handZone, BorderLayout.WEST);
        main.add(benchZone, BorderLayout.SOUTH);
        main.add(adversePlayer, BorderLayout.EAST);
        main.add(activeZone, BorderLayout.CENTER);
        this.getContentPane().add(main);
    }

    public Boolean selectCard(Card card) {
        player.setSelectedCard(card);
        benchZone.setZones(player);
        this.setContentPane(this.main);
        return true;
    }

    public void activatePokemon(Card card) {
        player.activePokemon(card, "hand", this);
        handZone.printHand(player.getHand());
        benchZone.setZones(player);
        activeZone.setZone(player, "Vous pouvez maintenant assigner des pokemons sur le banc.", false);

        this.setContentPane(main);
    }

    public void toBench(Card card) {
        if(player.getReady()){
            player.hasBench(true);
        }
        player.pokemonsOnBench(card, this );
        handZone.printHand(player.getHand());
        benchZone.setZones(player);
        this.setContentPane(main);
    }

    public void associateEnergy() {
        player.hasPutEnergy(true);
        benchZone.setZones(player, true, "energy");
        activeZone.setZone(player, "A quel pokemon voulez vous associer cette energie ?", true);
        this.setContentPane(main);
    }

    public void associateTo(Card card) {
        player.combineEnergy(player.getSelectedCard(), card);
        player.hasPutEnergy(true);
        benchZone.setZones(player);
        activeZone.setZone(player, "Est-ce le moment d'attaquer ?", false);
        handZone.printHand(player.getHand());
        this.setContentPane(main);
    }

    public void retreat() {
        if(player.getBench().size()>0){
            boolean canRetreat=player.getActivePokemon().retreat();
            if(canRetreat){
                activeZone.setZone(player, "Quel pokemon du banc souhaitez vous activer ? ", false);
                benchZone.setZones(player, true, "activation");
                
            }else{
                activeZone.setZone(player, player.getActivePokemon()+" n'a pas assez d'energie ! ", false);
            }

        }else{
            activeZone.setZone(player, "Vous devez avoir un pokemon sur le banc pour effectuer une retraite !", false);
        }
        this.setContentPane(main);
    }

    public void changePlayer() {
        Boolean isOver = testWinner();
        if(isOver){
            main.removeAll();
            Victory v=new Victory(player, this);
            main.add(v);
        }else{
            player.isReady();
            player = game.nextPlayer();
            player.setSelectedCard(null);
            player.hasAttacked(false);
            player.hasBench(false);
            player.hasPutEnergy(false);
            String str =player+", à vous de jouer !";
            if(player.getReady()){
                Card c=player.getCard();
                str +=" Vous avez tiré une carte "+c;
            }
            if(player.getActivePokemon()==null && player.getReady()){
                str+= " Quel pokemon souhaitez vous activer ?";
                benchZone.setZones(player, true, "activation");
                activeZone.setZone(player, str, false);
            }else{
                benchZone.setZones(player);
                activeZone.setZone(player, str, false);
            }
            
            handZone.printHand(player.getHand());
            adversePlayer.setZones(game.getAdversePlayer(player));
        }
        
        this.setContentPane(main);
        
    }

    public void attack(int index) {
        Card pokemon = player.getActivePokemon();
        ArrayList<Attack> attacks=pokemon.getAttacks();
        boolean canAttack = pokemon.canAttack( attacks.get(index) );
        System.out.println("can attack  = "+canAttack);
        if(canAttack){
            Card pokemonAdv = game.getAdversePlayer(player).getActivePokemon();
            int dammage = pokemonAdv.isAttacked( attacks.get(index).getStrenght(), pokemon.getEnergyType());
            boolean isDead=false;
            if(pokemonAdv.getHP()<=0){
                isDead=true;
                game.getAdversePlayer(player).activePokemonDie();
                winner = player.winRound(VICTORY);
            }
            String str = pokemon.getName()+" attaque ! "+pokemonAdv+" perd "+dammage+" points de vie...";
            if(isDead){
                str += pokemonAdv+" est hors jeu !";
            }
            player.hasAttacked(true);
            activeZone.setZone(player, str, false);
            adversePlayer.setZones(game.getAdversePlayer(player));
        }else{
            activeZone.setZone(player, pokemon.getName()+" n'a pas assez d'énergie !", false);
            benchZone.setZones(player);
        }
        this.setContentPane(main);
    }



    void activate(Card card) {
        player.changeActivePokemon( card );
        activeZone.setZone(player, player.getActivePokemon().getName()+" est activé !", false);
        benchZone.setZones(player);
        this.setContentPane(main);
    }

    private Boolean testWinner() {
        if(winner){
            System.out.println(player +"4 rounds");
            return true;
        }
        if(game.getAdversePlayer(player).getReady() && game.getAdversePlayer(player).getActivePokemon()==null && game.getAdversePlayer(player).getBench().size()==0){
            System.out.println(player +"plus de pokemon");
            return true;
        }else{
            return false;
        }
    }
}
