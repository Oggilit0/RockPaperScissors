package com.rockpaperscissors;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
    private final MenuInterface menuInterface;

    /**
     * Constructor of the class Game.
     * Initialize player list for storing all players within class.
     * Initialize class handling menus.
     * Randomly generates an opponent and set amount of players
     *
     */
    public Game(){
        this.allPlayers = new ArrayList<>();
        this.menuInterface = new MenuInterface( this );
        randomGeneratedPlayers(GameUtils.DynamicVariables.playerAmount);
        createNewOpponent();
    }

    /**
     * Run the Main menu
     */
    public void startUp (){
        this.menuInterface.mainMenu();
    }

    /**
     * Core method to call methods in subsequence to fulfill a game of Rock, paper & scissors
     */
    public void newMatch(){
        this.currentMatch = new Match( this.currentPlayer,this.currentOpponent );
        this.menuInterface.playerChoiceMenu();
        this.currentOpponent.RollOpponentOutcome();
        this.currentMatch.matchOutcome();
        this.menuInterface.afterMatchMenu();
    }

    /**
     * Generates set amount predetermined players with a random name from text file.
     * @param startingPlayerAmount input how many new players you want to start with
     */
    public void randomGeneratedPlayers( int startingPlayerAmount ){

        String filename = "playerNames.txt";

        try{
            List<String> rndPlayers = Files.readAllLines(Paths.get(filename));

            for( int i = 0; i < startingPlayerAmount; i++ ){
                int randNr = ( int ) ( Math.random() * rndPlayers.size() );
                this.allPlayers.add( new Player( rndPlayers.get( randNr ) ) );
                rndPlayers.remove( randNr );
            }
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    /**
     * Custom character creation and adding it to player list
     * Restricted name length to handle menu geometry easier.
     */
    public void createNewPlayer(){
        String playerName;
        Scanner console = new Scanner( System.in );
        do {
            System.out.print( "Name your character: " );
            playerName = console.nextLine();
            if ( playerName.length() > 10 || playerName.length() < 2 ) {
                System.out.println("Sorry, your name need to be 2-10 characters");
            }
        }while ( playerName.length() > 10 || playerName.length() < 2 ) ;
        this.getAllPlayers().add( new Player( playerName ) );
        this.setCurrentPlayer( this.getAllPlayers().get( this.getAllPlayers().size() - 1 ) );
    }

    /**
     * Method to delete a player from the playerSelectionMenu.
     * @param playerIndexToRemove player index to be removed from arraylist
     */
    public void deletePlayer( int playerIndexToRemove ) {
        int menuChoice;
        do{
            menuChoice = GameUtils.tryCatchMenuInput( playerIndexToRemove );
            if( menuChoice < 4 ){
                System.out.println( "Invalid input" );
            }
        }while( menuChoice < 4 );

        this.getAllPlayers().remove( menuChoice -4 );
    }

    /**
     * Creates a new Opponent med a set name
     */
    public void createNewOpponent(){
        this.currentOpponent = new Opponent( "Opponent" );
    }

    /**
     * Terminate the program
     */
    public void gameOver(){
        System.out.println( "GAME OVER" );
        System.exit( 0 );
    }

    /**
     * Returns the current match
     * @return current Match object
     */
    public Match getCurrentMatch() {
        return this.currentMatch;
    }

    /**
     * Return a list of stored players
     * @return ArrayList of Player objects
     */
    public ArrayList<Player> getAllPlayers() {
        return this.allPlayers;
    }

    /**
     * Input player to be stored locally
     * @param currentPlayer player object to be stored locally
     */
    public void setCurrentPlayer( Player currentPlayer ) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Get current active player
     * @return current Player object
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
}
