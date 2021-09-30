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
 * All logical decision-making and menu handling of the game.
 *
 * @author Oskar
 */

public class Game {
    private Match currentMatch;
    private Player currentPlayer;
    private final ArrayList<Player> allPlayers;
    private final UserInterface currentInterface;

    public Game(){
        this.allPlayers = new ArrayList<>();
        this.currentInterface = new UserInterface( this );
        randomGeneratedPlayers();
    }

    public void startUp (){
        this.currentInterface.mainMenu();
    }

    /**
     * Core method to call methods in subsequence to fulfill a game of Rock, paper & scissors
     */
    public void newMatch(){
        this.currentMatch = new Match( "n/a" );
        this.currentMatch.createNewOpponent();
        this.currentMatch.setCurrentPlayer( this.currentPlayer );
        this.currentInterface.playerChoiceMenu();
        this.currentMatch.OpponentOutcome();
        this.currentMatch.outcome();
        this.currentInterface.afterMatchMenu();
    }

    /**
     * Generates 3 predetermined players with a random name from array.
     */
    // gör om så den inte använder scanner
    public void randomGeneratedPlayers(){
        ArrayList<String> rndPlayers = new ArrayList<>();
        try {
            Scanner s = new Scanner( new File( "playerNames.txt" ) );
            while ( s.hasNext() ){
                rndPlayers.add( s.nextLine() );
            }
        } catch ( Exception e ) {
        }
        for( int i = 0; i < 3; i++ ){
            int randNr = ( int ) ( Math.random() * rndPlayers.size() );
            this.allPlayers.add( new Player( rndPlayers.get( randNr ) ) );
            rndPlayers.remove( randNr );
        }
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
