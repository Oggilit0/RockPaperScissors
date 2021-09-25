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
 * All logical decision-making and menu handling of the game.
 *
 */

public class Game {
    private Scanner console;
    //private String playerChoice;
    private Match currentMatch;
    private Player currentPlayer;
    private final ArrayList<Player> allPlayers;
    private Interface currentInterface;

    public Game(){
        this.allPlayers = new ArrayList<>();
        currentInterface = new Interface(this);
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
        this.currentMatch.setCurrentPlayer(currentPlayer);
        this.currentInterface.playerChoiceMenu();
        this.currentMatch.OpponentOutcome();
        this.currentMatch.outcome();
        this.currentInterface.afterMatchMenu();
    }

    /**
     * Custom character creation and adding it to playerbase
     * Restricted name length to handle menu geometry easier.
     */
    public void createNewPlayer(){
        String playerName;
        do {
            System.out.print("Name your character: ");
            playerName = this.console.nextLine();
            if (playerName.length() > 15 || playerName.length() < 1) {
                System.out.println("Sorry, your name need to be 1-15 characters");
            }
            System.out.println(playerName.length());
        }while (playerName.length() > 15 || playerName.length() < 1) ;
        allPlayers.add(new Player(playerName));
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

//    /**
//     * Method to handling user input and to get rid of wrongfully inputs that the user might do
//     * @param maxCase get input from menu to know max case to handle exceptions
//     * @return int value to be handled in menus
//     */
//    public int tryCatchMenus(int maxCase){
//        int menuChoice = 0;
//        do {
//            try {
//                this.console = new Scanner( System.in );
//                menuChoice = Integer.parseInt( this.console.nextLine() );
//            }
//            catch( Exception e ){
//                System.out.println( "Invalid input" );
//            }
//            if(menuChoice < 0 || menuChoice > maxCase){
//                System.out.println( "invalid input" );
//            }
//        }while( menuChoice == 0 || menuChoice < 0 || menuChoice > maxCase);
//        return menuChoice;
//    }

//    /**
//     * Design for all menus to look identical with semi-hard coded geometry
//     * Menu name length is dynamic but shouldn't be too long
//     * @param menu input string to display menu name in top right corner of menu
//     */
//    public void menuDesign( String menu ){
//        if ( this.currentPlayer != null ){
//            System.out.println( geometryBuilder( "─",0,50 ) + "\n" + menu + geometryBuilder(" ",this.currentPlayer.getName().length(),50-menu.length()) + this.currentPlayer.getName() + "\n" + geometryBuilder( "─",0,50 ) );
//        }else{
//            System.out.println( geometryBuilder( "─",0,50 ) + "\n" + menu + "\n" + geometryBuilder( "─",0,50 ) );
//        }
//    }

    public Scanner getConsole() {
        return console;
    }

    public void setConsole(Scanner console) {
        this.console = console;
    }

    /**
     * The first running method of the program. Gives the user options to advance
     */


    public Match getCurrentMatch() {
        return currentMatch;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public void setCurrentMatch(Match currentMatch) {
        this.currentMatch = currentMatch;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setCurrentInterface(Interface currentInterface) {
        this.currentInterface = currentInterface;
    }
/**
     *
     */
//    public void mainMenu(){
//        menuDesign( "Main Menu" );
//        System.out.println( "1.  New Game\n2.  Choose player\n3.  Match history\n4.  Quit" );
//        int menuChoice = tryCatchMenus(4);
//
//        switch( menuChoice ){
//            case 1:
//                if( this.currentPlayer == null ){
//                    noPlayerSelectMenu();
//                }else{
//                    newMatch();
//                }
//                break;
//            case 2:
//                playerSelectionMenu();
//                break;
//            case 3:
//                matchHistoryMenu();
//                break;
//            case 4:
//                gameOver();
//                break;
//        }
//    }

    // FIXA HELA DEN HÄR JÄVLA MENYN
//    public void playerSelectionMenu(){
//        menuDesign( "Choose Player" );
//        int playerSelectCounter = 0;
//
//        for( Player p : this.allPlayers ){
//            System.out.println( ( playerSelectCounter+1 ) + ".   " + p.getName() );
//            playerSelectCounter++;
//        }
//        System.out.println( playerSelectCounter + 1 + ".   Create new character\n" + ( playerSelectCounter + 2 ) + ".   Return to main menu\n" + ( playerSelectCounter + 3 ) + ".   Delete player" );
//        int menuChoice = tryCatchMenus(100); /// FIXA DEN HÄR
//
//        if( menuChoice == (this.allPlayers.size() + 1 ) ){
//
//            createNewPlayer();
//
//        }else if ( menuChoice == ( this.allPlayers.size() + 2 ) ){
//
//            mainMenu();
//
//        }else if( menuChoice == ( this.allPlayers.size() ) + 3 ){
//
//            System.out.println( "Which player to remove?" );
//            menuChoice = tryCatchMenus(100);
//
//
//            /// BEHÖVS EN IFSATS FÖR ATT kunna ta bort en spelare även om man inte valt nån
//            if( menuChoice <= 0 || menuChoice > this.allPlayers.size() ){
////                System.out.println( "Invalid input" );
////            if (currentPlayer != null){
////
////            }
//            }else if (currentPlayer!= null && this.allPlayers.get(menuChoice - 1).getName().equals(this.currentPlayer.getName())) {
//                System.out.println("Can't delete existing player!");
//
//            }else{
//                this.allPlayers.remove( menuChoice - 1 );
//            }
//
//        }
//        if( menuChoice > this.allPlayers.size() ){
//            System.out.println( "Invalid input" ); // Gör do while loopar istället kanske ?
//
//        }else{
//            this.currentPlayer = this.allPlayers.get( menuChoice - 1 );
//            System.out.println( this.currentPlayer.getName() );
//            mainMenu();
//
//        }
//        playerSelectionMenu();
//    }

//    public void noPlayerSelectMenu(){
//        menuDesign( "no player selected" );
//        System.out.println( "1.  Create new player \n2.  Choose existing player\n3.  Main menu" );
//        int menuChoice = tryCatchMenus(3);
//
//        switch ( menuChoice ){
//            case 1:
//                createNewPlayer();
//                this.currentPlayer = this.allPlayers.get( this.allPlayers.size() - 1 );
//                newMatch();
//                break;
//            case 2:
//                playerSelectionMenu();
//                break;
//            case 3:
//                mainMenu();
//                break;
//        }
//    }
//
//    /**
//     * Method to handle geometry, making longer strings of characters to use in menus.
//     * Max length: 50
//     *
//     * @param symbol The character you want to print out
//     * @param offset Offset from left side of console
//     * @param length total length of desired part
//     * @return
//     */
//    public String geometryBuilder(String symbol, int offset,int length){
//        String geometry = "";
//        for( int i = offset; i < length ; i++ ){
//            geometry += symbol;
//        }
//        return geometry;
//    }
//
//
//    public void playerChoiceMenu(){
//        menuDesign( "Rock, paper & scissors" );
//        System.out.println( "1.  Rock\n2.  Paper\n3.  Scissors" );
//        int menuChoice = tryCatchMenus(3);
//
//        switch( menuChoice ){
//            case 1:
//                this.currentPlayer.setPlayerOutcome("Rock");
//                break;
//            case 2:
//                this.currentPlayer.setPlayerOutcome("Paper");
//                break;
//            case 3:
//                this.currentPlayer.setPlayerOutcome("Scissors");
//                break;
//        }
//    }
//    public void matchHistoryMenu(){
//        menuDesign( "Match History" );
//        System.out.println( "1   Match history\n2   Main Menu");
//        int menuChoice = tryCatchMenus(2);
//
//        switch(menuChoice){
//            case 1:
//                if( this.currentPlayer == null ){
//                    System.out.println( "No active player" );
//                    matchHistoryMenu();
//                }
//
//                if( !this.currentPlayer.getMatchHistory().isEmpty() ){
//                    System.out.println( " Player: " + this.currentPlayer.getName() );
//                    System.out.println( "┌"+geometryBuilder("─",0,48)+"┐\n" + "│ Match id " + geometryBuilder(" ", 0, 31) + "Result │\n" + "├" +geometryBuilder("─",0,48)+"┤" );
//
//                    if( !this.currentPlayer.getMatchHistory().isEmpty() ){
//                        for( Match m : this.currentPlayer.getMatchHistory() ){
//                            System.out.println( "│ [" + m.getMatchId() + "]" +geometryBuilder(" ",this.currentPlayer.getMatchHistory().get(0).getResult().length(),28)+ m.getResult() + "│" );
//                        }
//                    }
//                    System.out.println( "└────────────────────────────────────────────────┘" );
//                }else{
//                    System.out.println("no record to show");
//                }
//                break;
//            case 2:
//                mainMenu();
//                break;
//        }
//        afterMatchHistoryMenu();
//    }

//    public void afterMatchHistoryMenu(){
//        System.out.println( "1.  Main Menu\n2.  Quit" );
//        int menuChoice = tryCatchMenus(2);
//
//        switch( menuChoice ){
//            case 1:
//                mainMenu();
//                break;
//            case 2:
//                gameOver();
//                break;
//        }
//    }

//    /// FIXA GEOMETRI HÄR MED MELLANRUM
//    public void afterMatchMenu(){
//        String[] space = { "","","" };
//        for( int i = ( this.currentPlayer.getName().length() + currentPlayer.getPlayerOutcome().length()) + 1 ; i < 24 ; i++ ){
//            space[0] += " ";
//        }
//        for( int i = this.currentMatch.getCurrentOpponent().getOpponentOutcome().length() + 9; i < 24 ; i++ ){
//            space[1] += " ";
//        }
//        for( int i = this.currentMatch.getResult().length() ; i <= 26 ; i++ ){
//            space[2] += " ";
//        }
//
//        String result = "Opponent:" + space[1] + this.currentMatch.getCurrentOpponent().getOpponentOutcome() + "││" + currentPlayer.getPlayerOutcome() +space[0] + ":";
//
//        menuDesign( result );
//        System.out.println(space[2] + this.currentMatch.getResult() );
//        System.out.println( "1.  Main Menu\n2.  New Match\n3.  Quit" );
//        int menuChoice = tryCatchMenus(3);
//
//        switch( menuChoice ){
//            case 1:
//                mainMenu();
//                break;
//            case 2:
//                newMatch();
//                break;
//            case 3:
//                gameOver();
//                break;
//        }
//    }

    /**
     * Terminate the program
     */
    public void gameOver(){
        System.out.println( "GAME OVER" );
    }
}
