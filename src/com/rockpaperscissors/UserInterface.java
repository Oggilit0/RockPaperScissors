package com.rockpaperscissors;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;


/**
 * Class that handles menu choices & graphical geometry of the program.
 * @author Oskar
 */
public class UserInterface {
        private final Game currentGame;
        private Scanner console;

    /**
     * Constructor to add the current game program for access
     * @param currentGame
     */
    public UserInterface(Game currentGame ){
        this.currentGame = currentGame;
    }

    /**
     * The first menu the player encounters, gives multiple
     * decisions to advance
     */
    public void mainMenu(){

        int menuChoice = GameUtils.menuBuilder("Main menu", currentGame.getCurrentPlayer(), "New Game","Choose player","Match history");

        switch( menuChoice ){
            case 1:
                if( this.currentGame.getCurrentPlayer() == null ){
                    noPlayerSelectMenu();
                }else{
                    this.currentGame.newMatch();
                }
                break;
            case 2:
                playerSelectionMenu();
                break;
            case 3:
                matchHistoryMenu();
                break;
            case 4:
                this.currentGame.gameOver();
                break;
        }
    }

    /**
     * Menu which only shows if trying to play a game without
     * an active player.
     */
    public void noPlayerSelectMenu(){

        int menuChoice = GameUtils.menuBuilder("No player selected", currentGame.getCurrentPlayer(), "Create new player","Choose existing player","Main menu");

        switch ( menuChoice ){
            case 1:
                createNewPlayer();
                this.currentGame.newMatch();
                break;
            case 2:
                playerSelectionMenu();
                this.currentGame.newMatch();//////////////// FIXA
                break;
            case 3:
                mainMenu();
                break;
        }
    }

    /**
     * Give the player an option to choose between the 3 outcomes
     * of rock paper & scissors
     */
    public void playerChoiceMenu(){

        int menuChoice = GameUtils.menuBuilder("Rock, paper & Scissors", currentGame.getCurrentPlayer(), "Rock","Paper","Scissors");

        switch( menuChoice ){
            case 1:
                this.currentGame.getCurrentPlayer().setPlayerOutcome( "Rock" );
                break;
            case 2:
                this.currentGame.getCurrentPlayer().setPlayerOutcome( "Paper" );
                break;
            case 3:
                this.currentGame.getCurrentPlayer().setPlayerOutcome( "Scissors" );
                break;
        }
    }

    //Fixa den här menyn!
    /**
     * Dynamic menu with 3 static options.
     * Writes out all available players as different menu items for the user to choose from
     */
    public void playerSelectionMenu(){
        GameUtils.menuDesign( "Choose Player" , currentGame.getCurrentPlayer() );


        //System.out.println( "1.\tCreate and choose new character\n" +  "2.\tReturn to main menu\n" + "3.\tDelete player" );


        //int menuChoice = GameUtils.tryCatchMenus(playerSelectCounter+3);


        int menuChoice = GameUtils.menuBuilder("Choose player", currentGame.getCurrentPlayer(),"Create and choose new character", "Return to main menu", "Delete player", test());


        switch(menuChoice){
            case 1:
                createNewPlayer();
                break;
            case 2:
                mainMenu();
                break;
            case 3:
                System.out.println( "Which player to remove?" );
                deletePlayer(this.currentGame.getAllPlayers().size()+3);
                break;
            default:
                this.currentGame.setCurrentPlayer(this.currentGame.getAllPlayers().get( menuChoice - 4 ));
        }
        mainMenu();
    }

    public String test (){
        int playerSelectCounter = 0;
        String test ="";
        for( Player p : this.currentGame.getAllPlayers() ){
            if(playerSelectCounter == 0){
                test +=(p.getName()+"\n");
            }else{
                test +=( playerSelectCounter+4 ) + ".\t" + p.getName()+"\n";
            }

            playerSelectCounter++;
        }
        return test;
    }


    /**
     * Method to delete a player from the playerSelectionMenu.
     * @param playerIndexToRemove takes the player and remove it from arraylist in game class.
     */
    public void deletePlayer(int playerIndexToRemove) {
        int menuChoice = GameUtils.tryCatchMenus(playerIndexToRemove);

        try{
            if (this.currentGame.getCurrentPlayer() != null && this.currentGame.getAllPlayers().get(menuChoice - 4).getName().equals(this.currentGame.getCurrentPlayer().getName())) {
                System.out.println("Can't delete existing player!");
            }else{
                this.currentGame.getAllPlayers().remove( menuChoice -4 );
            }
        }catch(Exception e){
            System.out.println( "Invalid input" );
        }

        playerSelectionMenu();
    }

    /**
     * Writes out current active players match history.
     * doesn't print it out if no active player.
     * Gives an option to go back to main menu
     */
    public void matchHistoryMenu(){

        int menuChoice = GameUtils.menuBuilder("Match Historu", currentGame.getCurrentPlayer(), matchHistoryPrinter());

        if (menuChoice == 1) {
            mainMenu();
        }
    }

    /**
     * Method to print out the match history of current active player
     * with semi-hard coded data based on menu size
     */
    public String matchHistoryPrinter (){

        String matchHistoryTopBorder = "";
        String matchHistoryBottomBorder = "";

        if(this.currentGame.getCurrentPlayer() == null){
            return "1.\tMain menu";
        }

        if( !this.currentGame.getCurrentPlayer().getMatchHistory().isEmpty() ){
            matchHistoryTopBorder = ( "┌"+geometryBuilder("─",0,48)+"┐\n" + "│ Match id " + geometryBuilder(" ", 0, 31) + "Result │\n" + "├" +geometryBuilder("─",0,48)+"┤" );

            if( !this.currentGame.getCurrentPlayer().getMatchHistory().isEmpty() ){
                for( Match m : this.currentGame.getCurrentPlayer().getMatchHistory() ){
                    matchHistoryTopBorder += ( "\n│ [" + m.getMatchId() + "]" +geometryBuilder(" ",this.currentGame.getCurrentPlayer().getMatchHistory().get(0).getResult().length(),28)+ m.getResult() + "│" );
                }
                matchHistoryTopBorder += "\n";
            }
            matchHistoryBottomBorder = ( "└"+geometryBuilder("─",0,48)+"┘\n" );
        }

        return matchHistoryTopBorder + matchHistoryBottomBorder +"1.\tMain menu";
    }

    /**
     * Custom character creation and adding it to playerbase
     * Restricted name length to handle menu geometry easier.
     */
    public void createNewPlayer(){
        String playerName;
        console = new Scanner(System.in);
        do {
            System.out.print( "Name your character: " );
            playerName = this.console.nextLine();
            if ( playerName.length() > 15 || playerName.length() < 2 ) {
                System.out.println("Sorry, your name need to be 2-15 characters");
            }
            System.out.println(playerName.length());
        }while ( playerName.length() > 15 || playerName.length() < 1 ) ;
        this.currentGame.getAllPlayers().add( new Player( playerName ) );
        this.currentGame.setCurrentPlayer( this.currentGame.getAllPlayers().get( this.currentGame.getAllPlayers().size() - 1 ) );
    }

    /**
     * Menu to show up after a match is complete, gives options to
     * replay, go back to main menu or to quick the game.
     */
    public void afterMatchMenu(){
        afterMatchMenuScoreBorder();
        System.out.println( "1.\tMain Menu\n2.\tNew Match\n3.\tQuit" );
        int menuChoice = GameUtils.tryCatchMenus(3);

        switch( menuChoice ){
            case 1:
                mainMenu();
                break;
            case 2:
                this.currentGame.newMatch();
                break;
            case 3:
                this.currentGame.gameOver();
                break;
        }
    }


    /**
     * Displays match data on the after match screen
     */
    public void afterMatchMenuScoreBorder(){
        String[] space = new String[3];
        space[0] = geometryBuilder(" ",( this.currentGame.getCurrentMatch().getCurrentOpponent().getOpponentOutcome().length() + 9 ),24 );
        space[1] = geometryBuilder(" ",( ( this.currentGame.getCurrentPlayer().getName().length() + this.currentGame.getCurrentPlayer().getPlayerOutcome().length()) + 1 ),24 );
        space[2] = geometryBuilder(" ",this.currentGame.getCurrentMatch().getResult().length(),27 );

        geometryBuilder(" ",( ( this.currentGame.getCurrentPlayer().getName().length() + this.currentGame.getCurrentPlayer().getPlayerOutcome().length() ) + 1 ),24 );
        String result = "Opponent:" + space[0] + this.currentGame.getCurrentMatch().getCurrentOpponent().getOpponentOutcome() + "││" + this.currentGame.getCurrentPlayer().getPlayerOutcome() +space[1] + ":";
        GameUtils.menuDesign( result , currentGame.getCurrentPlayer() );
        System.out.println( space[2] + this.currentGame.getCurrentMatch().getResult() );
    }


    /**
     * Method to handle geometry, making longer strings of characters to use in menus.
     * Max length: 50
     *
     * @param symbol The character you want to print out
     * @param offset Offset from left side of console
     * @param length total length of desired part
     * @return
     */

    public String geometryBuilder( String symbol, int offset,int length ){
        String geometry = "";
        for( int i = offset; i < length ; i++ ){
            geometry += symbol;
        }
        return geometry;
    }

}
