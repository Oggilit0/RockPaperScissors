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



// Skapa player selection och lagring

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * All logical decisionmaking and menu handling of the game.
 *
 */

public class Game {
    private Scanner console;
    private String playerChoice;
    private Match currentMatch;
    private Player currentPlayer;
    private ArrayList<Player> allPlayers = new ArrayList<>();

    public Game(){
        randomGeneratedPlayers();
    }


    /**
     * Core method to call methods in subsequence to fulfill a game of Rock, paper & scissors
     */
    public void newMatch(){
        this.currentMatch = new Match( "n/a" );
        this.currentMatch.createNewOpponent();
        playerChoiceMenu();
        this.currentMatch.getCurrentOpponent().OpponentOutcome();
        outcome();
        afterMatchMenu();
    }


    /**
     * Handling outcome of each individual match and stores the result in Match Class.
     * The match and result is then stored separately in Player class for the current player.
     */
    public void outcome(){
        if ( this.playerChoice.equals( this.currentMatch.getCurrentOpponent().getOpponentOutcome() ) ){
            this.currentMatch.setResult( "Draw" );
        } else{
            switch( this.playerChoice ){
                case "Rock":
                    if( this.currentMatch.getCurrentOpponent().getOpponentOutcome().equals( "Scissors" ) ){
                        this.currentMatch.setResult( "Won!" );
                    }else{
                        this.currentMatch.setResult( "Lost" );
                    }
                    break;

                case "Paper":
                    if( this.currentMatch.getCurrentOpponent().getOpponentOutcome().equals( "Rock" ) ){
                        this.currentMatch.setResult( "Won!" );
                    }else{
                        this.currentMatch.setResult( "Lost" );
                    }
                    break;

                case "Scissors":
                    if(this.currentMatch.getCurrentOpponent().getOpponentOutcome().equals( "Paper" )){
                        this.currentMatch.setResult( "Won!" );
                    }else{
                        this.currentMatch.setResult( "Lost" );
                    }
                    break;
            }
        }
        this.currentPlayer.setMatchHistory( this.currentMatch );
    }

    /**
     * Custom character creation and adding it to playerbase
     * Restricted name length to handle menu geometry easier.
     */
    public void createNewPlayer(){
        System.out.print( "Name your character: " );
        String playerName = this.console.nextLine();
        if( playerName.length() > 15 ) {
            System.out.println( "Sorry, your name is to long, please enter a name of 15 characters or less" );
            createNewPlayer();
        }else if ( playerName.length() < 1 ) {
            System.out.println( "Sorry, your name is to short" );
            createNewPlayer();
        }else{
            allPlayers.add( new Player( playerName ) );
        }
    }

    /**
     * Generates 3 predetermined players with a random name from array.
     */
    public void randomGeneratedPlayers(){
        ArrayList<String> rndPlayers = new ArrayList<>();
        try {
            Scanner s = new Scanner( new File( "src/com/rockpaperscissors/playerNames.txt" ) );
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
     * Method to handling user input and to get rid of wrongfully inputs that the user might do
     * @return int value to be handled in menus
     */
    public int tryCatchMenus(){
        int menuChoice = 0;
        do {
            try {
                this.console = new Scanner( System.in );
                menuChoice = Integer.parseInt( this.console.nextLine() );
            }
            catch( Exception e ){
                System.out.println( "Invalid input" );
            }
            if(menuChoice < 0){
                System.out.println( "invalid input" );
            }
        }while( menuChoice == 0 || menuChoice < 0 );
        return menuChoice;
    }

    /**
     * Design for all menus to look identical with hard coded geometry
     * Menu name length is dynamic but shouldn't be too long
     * @param menu input string to display that name in top right corner of menu
     */
    public void menuDesign( String menu ){
        String space = "";

        if ( this.currentPlayer != null ){
            for( int i = this.currentPlayer.getName().length() ; i < ( 50 - menu.length() ) ; i++ ){
                space += " ";
            }
            System.out.println( "──────────────────────────────────────────────────\n" + menu + space + this.currentPlayer.getName() + "\n──────────────────────────────────────────────────" );
            // space = "";
        }else{
            for( int i = 0; i <( 50 - menu.length() ); i++ ){
                space += " ";
            }
            System.out.println( "──────────────────────────────────────────────────\n" + menu + "\n──────────────────────────────────────────────────" );
        }
    }

    /**
     * The first running method of the program. Gives the user options to advance
     */
    public void mainMenu(){
        menuDesign( "Main Menu" );
        System.out.println( "1.  New Game\n2.  Choose player\n3.  Match history\n4.  Quit" );
        int menuChoice = tryCatchMenus();

        switch( menuChoice ){
            case 1:
                if( this.currentPlayer == null ){
                    menuDesign( "no player selected" );
                    System.out.println( "1.  Create new player \n2.  Choose existing player\n3.  Main menu" );
                    menuChoice = tryCatchMenus();

                    switch ( menuChoice ){
                        case 1:
                            createNewPlayer();
                            this.currentPlayer = this.allPlayers.get( this.allPlayers.size() - 1 );
                            newMatch();
                            break;
                        case 2:
                            playerSelectionMenu();
                            break;
                        case 3:
                            mainMenu();
                            break;
                    }
                }else{
                    newMatch();
                }
                break;
            case 2:
                playerSelectionMenu();
                break;
            case 3:
                matchHistoryMenu();
                break;
            case 4:
                gameOver();
                break;
        }
    }
    public void playerSelectionMenu(){
        menuDesign( "Choose Player" );
        int playerSelectCounter = 0;

        for( Player p : this.allPlayers ){
            System.out.println( ( playerSelectCounter+1 ) + ".   " + p.getName() );
            playerSelectCounter++;
        }
        System.out.println( playerSelectCounter + 1 + ".   Create new character\n" + ( playerSelectCounter + 2 ) + ".   Return to main menu\n" + ( playerSelectCounter + 3 ) + ".   Delete player" );
        int menuChoice = tryCatchMenus();

        if( menuChoice == (this.allPlayers.size() + 1 ) ){

            createNewPlayer();

        }else if ( menuChoice == ( this.allPlayers.size() + 2 ) ){

            mainMenu();

        }else if( menuChoice == ( this.allPlayers.size() ) + 3 ){

            System.out.println( "Which player to remove?" );
            menuChoice = tryCatchMenus();


            /// BEHÖVS EN IFSATS FÖR ATT kunna ta bort en spelare även om man inte valt nån
            if( menuChoice <= 0 || menuChoice > this.allPlayers.size() ){
//                System.out.println( "Invalid input" );
//            if (currentPlayer != null){
//
//            }
            }else if (currentPlayer!= null && this.allPlayers.get(menuChoice - 1).getName().equals(this.currentPlayer.getName())) {
                System.out.println("Can't delete existing player!");

            }else{
                this.allPlayers.remove( menuChoice - 1 );
            }

        }
        if( menuChoice > this.allPlayers.size() ){
            System.out.println( "Invalid input" ); // Gör do while loopar istället kanske ?

        }else{
            this.currentPlayer = this.allPlayers.get( menuChoice - 1 );
            System.out.println( this.currentPlayer.getName() );
            mainMenu();

        }
        playerSelectionMenu();
    }

    public void playerChoiceMenu(){
        menuDesign( "Rock, paper & scissors" );
        System.out.println( "1.  Rock\n2.  Paper\n3.  Scissors" );
        int menuChoice = tryCatchMenus();

        switch( menuChoice ){
            case 1:
                this.playerChoice = "Rock";
                break;
            case 2:
                this.playerChoice = "Paper";
                break;
            case 3:
                this.playerChoice = "Scissors";
                break;
        }
    }
    public void matchHistoryMenu(){
        menuDesign( "Match History" );
        System.out.println( "1   Match history" );
        System.out.println( "2   Main Menu" );
        int menuChoice = tryCatchMenus();

        switch(menuChoice){
            case 1:
                if( this.currentPlayer == null ){
                    System.out.println( "No active player" );
                    matchHistoryMenu();
                }else if(currentPlayer.getMatchHistory().isEmpty()){
                    System.out.println("No record to show");
                    matchHistoryMenu();
                }

                String[] space = { "","" };
                for( int i = 0; i <= 30 ; i++ ){
                    space[0] += " ";
                }

                if( !this.currentPlayer.getMatchHistory().isEmpty() ){
                    System.out.println( " Player: " + this.currentPlayer.getName() );

                    System.out.println( "┌────────────────────────────────────────────────┐\n" + "│ Match id " + space[0] + "Result │\n" + "├────────────────────────────────────────────────┤" );

                    for( int u = this.currentPlayer.getMatchHistory().get( 0  ).getResult().length() ; u < 28 ; u++ ){
                        space[1] += " ";
                    }

                    if( !this.currentPlayer.getMatchHistory().isEmpty() ){
                        for( Match m : this.currentPlayer.getMatchHistory() ){
                            System.out.println( "│ [" + m.getMatchId() + "]" +space[1]+ m.getResult() + "│" );
                        }
                    }
                    System.out.println( "└────────────────────────────────────────────────┘" );
                }
                break;
            case 2:
                mainMenu();
                break;
        }
        System.out.println( "1.  Main Menu\n2.  Quit" );
        menuChoice = tryCatchMenus();

        switch( menuChoice ){
            case 1:
                mainMenu();
                break;
            case 2:
                gameOver();
                break;
        }
    }

    public void afterMatchMenu(){
        String[] space = { "","","" };
        for( int i = ( this.currentPlayer.getName().length() + this.playerChoice.length()) + 1 ; i < 24 ; i++ ){
            space[0] += " ";
        }
        for( int i = this.currentMatch.getCurrentOpponent().getOpponentOutcome().length() + 9; i < 24 ; i++ ){
            space[1] += " ";
        }
        for( int i = this.currentMatch.getResult().length() ; i <= 26 ; i++ ){
            space[2] += " ";
        }

        String result = "Opponent:" + space[1] + this.currentMatch.getCurrentOpponent().getOpponentOutcome() + "││" + this.playerChoice +space[0] + ":";

        menuDesign( result );
        System.out.println(space[2] + this.currentMatch.getResult() );
        System.out.println( "1.  Main Menu\n2.  New Match\n3.  Quit" );
        int menuChoice = tryCatchMenus();

        switch( menuChoice ){
            case 1:
                mainMenu();
                break;
            case 2:
                newMatch();
                break;
            case 3:
                gameOver();
                break;
        }
    }

    /**
     * Terminate the program
     */
    public void gameOver(){
        System.out.println( "GAME OVER" );
    }
}
