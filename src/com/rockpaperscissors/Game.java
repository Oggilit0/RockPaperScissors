package com.rockpaperscissors;


// Döp om till nåt mer passande för att ha med menyer i classen ? typ program ? Döp om till game
// this på allt också
// Fixa score board så att inte oanvända spelare syns
// Kolla om break behövs till varje switch
// Fixa system outs på en linje
// Fixa mellanrum ( )
// Lägg in skriv: efter varje menyval
// Behöver jag quit i statistik?
// Geometry metod??
// debugga hela programmet
// Kolla efter onödiga metoder, variablar, duplicates etc
// Do while i player selection menu
//Ändra ordning på menyer
// Kolla så att kommentarer är konsistent : Tex Current active player = du som spelare,
// Random text från opponent =?
// Random oponent namn?
// Gör en hjälpklass static
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
    //private Scanner console;
    private final ArrayList<Player> allPlayers;
    private final UserInterface currentInterface;

    public Game(){
        this.allPlayers = new ArrayList<>();
        this.currentInterface = new UserInterface( this );
        randomGeneratedPlayers();
    }

    public void startUp (){
        currentInterface.mainMenu();
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

    public Match getCurrentMatch() {
        return currentMatch;
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Terminate the program
     */
    public void gameOver(){
        System.out.println( "GAME OVER" );
        System.exit(0);
    }
}
