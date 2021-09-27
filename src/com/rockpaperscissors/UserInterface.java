package com.rockpaperscissors;

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
        menuDesign( "Main Menu" );
        System.out.println( "1.\tNew Game\n2.\tChoose player\n3.\tMatch history\n4.\tQuit" );
        int menuChoice = tryCatchMenus(4);
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
        menuDesign( "no player selected" );
        System.out.println( "1.\tCreate new player \n2.\tChoose existing player\n3.\tMain menu" );
        int menuChoice = tryCatchMenus(3);

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
        menuDesign( "Rock, paper & scissors" );
        System.out.println( "1.\tRock\n2.\tPaper\n3.\tScissors" );
        int menuChoice = tryCatchMenus(3);

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
        menuDesign( "Choose Player" );
        int playerSelectCounter = 0;

        System.out.println( "1.\tCreate and choose new character\n" +  "2.\tReturn to main menu\n" + "3.\tDelete player" );

        for( Player p : this.currentGame.getAllPlayers() ){
            System.out.println( ( playerSelectCounter+4 ) + ".\t" + p.getName() );
            playerSelectCounter++;
        }
        int menuChoice = tryCatchMenus(playerSelectCounter+3);


        switch(menuChoice){
            case 1:
                createNewPlayer();
                break;
            case 2:
                mainMenu();
                break;
            case 3:
                System.out.println( "Which player to remove?" );
                deletePlayer(playerSelectCounter+3);
                break;
            default:
                this.currentGame.setCurrentPlayer(this.currentGame.getAllPlayers().get( menuChoice - 4 ));
        }
        mainMenu();
    }


    /**
     * Method to delete a player from the playerSelectionMenu.
     * @param player takes the player and remove it from arraylist in game class.
     */
    public void deletePlayer(int player) {
        int menuChoice = tryCatchMenus(player);

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
        menuDesign( "Match History" );
        try{
            matchHistoryPrinter();
        }catch ( Exception e ){
            System.out.println( "no record to show" );
        }
        System.out.println( "\n1\tMain Menu\n");
        int menuChoice = tryCatchMenus(1);

        switch( menuChoice ){
            case 1:
                mainMenu();
                break;
        }
    }

    /**
     * Method to print out the match history of current active player
     * with semi-hard coded data based on menu size
     */
    public void matchHistoryPrinter (){
        if( !this.currentGame.getCurrentPlayer().getMatchHistory().isEmpty() ){
            System.out.println( "┌"+geometryBuilder("─",0,48)+"┐\n" + "│ Match id " + geometryBuilder(" ", 0, 31) + "Result │\n" + "├" +geometryBuilder("─",0,48)+"┤" );

            if( !this.currentGame.getCurrentPlayer().getMatchHistory().isEmpty() ){
                for( Match m : this.currentGame.getCurrentPlayer().getMatchHistory() ){
                    System.out.println( "│ [" + m.getMatchId() + "]" +geometryBuilder(" ",this.currentGame.getCurrentPlayer().getMatchHistory().get(0).getResult().length(),28)+ m.getResult() + "│" );
                }
            }
            System.out.println( "└"+geometryBuilder("─",0,48)+"┘" );
        }
    }

    /**
     * Custom character creation and adding it to playerbase
     * Restricted name length to handle menu geometry easier.
     */
    public void createNewPlayer(){
        String playerName;
        do {
            System.out.print( "Name your character: " );
            playerName = this.console.nextLine();
            if ( playerName.length() > 15 || playerName.length() < 1 ) {
                System.out.println("Sorry, your name need to be 1-15 characters");
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
        int menuChoice = tryCatchMenus(3);

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
        menuDesign( result );
        System.out.println( space[2] + this.currentGame.getCurrentMatch().getResult() );
    }

    /**
     * Method to handling user input and to get rid of wrongfully inputs that the user might do
     * @param maxCase get input from menu to know max case to handle exceptions
     * @return int value to be handled in menus
     */
    public int tryCatchMenus( int maxCase ){
        int menuChoice = 0;
        System.out.print( "\nUser input: " );
        do {
            try {
                this.console = new Scanner( System.in);
                menuChoice = Integer.parseInt( this.console.nextLine() );
            }
            catch( Exception e ){
                System.out.println( "Invalid input" );
            }
            if( menuChoice < 0 || menuChoice > maxCase ){
                System.out.println( "invalid input" );
            }
        }while( menuChoice == 0 || menuChoice < 0 || menuChoice > maxCase );
        return menuChoice;
    }

    /**
     * Design for all menus to look identical with semi-hard coded geometry
     * Menu name length is dynamic but shouldn't be too long
     * @param menu input string to display menu name in top right corner of menu
     */
    public void menuDesign( String menu ){
        if ( this.currentGame.getCurrentPlayer() != null ){
            System.out.println( geometryBuilder( "─",0,50 ) + "\n" + menu + geometryBuilder(" ",this.currentGame.getCurrentPlayer().getName().length(),50 - menu.length()) + this.currentGame.getCurrentPlayer().getName() + "\n" + geometryBuilder( "─",0,50 ) );
        }else{
            System.out.println( geometryBuilder( "─",0,50 ) + "\n" + menu + "\n" + geometryBuilder( "─",0,50 ) );
        }
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
