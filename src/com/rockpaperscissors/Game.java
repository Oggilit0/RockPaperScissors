package com.rockpaperscissors;


// this på allt också
// Kolla om break behövs till varje switch
// Fixa system outs på en linje
// Fixa mellanrum ( )
// debugga hela programmet
// Kolla efter onödiga metoder, variablar, duplicates etc
//Ändra ordning på menyer
// Kolla så att kommentarer är konsistent : Tex Current active player = du som spelare,
// Random oponent namn?
// e.printStackTrace


// Skapa player selection och lagring

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * All logical decision-making
 * @author Oskar
 */

public class Game {
    private Match currentMatch;
    private Player currentPlayer;
    private Opponent currentOpponent;
    private final ArrayList<Player> allPlayers;
    private final UserInterface currentInterface;

    /**
     * Constructor of the class Game.
     * Initialize player list for storing all players within class.
     * Initialize class handling menus.
     * Randomly generates an opponent and set amount of players
     *
     */
    public Game(){
        this.allPlayers = new ArrayList<>();
        this.currentInterface = new UserInterface( this );
        randomGeneratedPlayers(GameUtils.DynamicVariables.playerAmount);
        createNewOpponent();
    }

    /**
     * Run the Main menu
     */
    public void startUp (){
        this.currentInterface.mainMenu();
    }

    /**
     * Core method to call methods in subsequence to fulfill a game of Rock, paper & scissors
     */
    public void newMatch(){
        this.currentMatch = new Match(this.currentPlayer,this.currentOpponent);
        this.currentInterface.playerChoiceMenu();
        this.currentOpponent.RollOpponentOutcome();
        this.currentMatch.matchOutcome();
        this.currentInterface.afterMatchMenu();
    }

    /**
     * Generates set amount predetermined players with a random name from text file.
     * @param startingPlayerAmount input how many new players you want to start with
     */
    // gör om så den inte använder scanner
    public void randomGeneratedPlayers(int startingPlayerAmount){
        ArrayList<String> rndPlayers = new ArrayList<>();
        try {
            Scanner s = new Scanner( new File( "playerNames.txt" ) );
            while ( s.hasNext() ){
                rndPlayers.add( s.nextLine() );
            }
        } catch ( Exception e ) {
        }
        for( int i = 0; i < startingPlayerAmount; i++ ){
            int randNr = ( int ) ( Math.random() * rndPlayers.size() );
            this.allPlayers.add( new Player( rndPlayers.get( randNr ) ) );
            rndPlayers.remove( randNr );
        }
    }

    /**
     * Creates a new Opponent med a set name
     */
    public void createNewOpponent(){
        this.currentOpponent = new Opponent("Opponent");
    }

    /**
     * Terminate the program
     */
    public void gameOver(){
        System.out.println( "GAME OVER" );
        System.exit(0);
    }

    public Match getCurrentMatch() {
        return this.currentMatch;
    }

    public ArrayList<Player> getAllPlayers() {
        return this.allPlayers;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
}
