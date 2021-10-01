package com.rockpaperscissors;


import java.util.Scanner;


/**
 * Class that handles menu choices & graphical geometry of the program.
 * @author Oskar
 */
public class UserInterface {
        private final Game currentGame;

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

        int menuChoice = GameUtils.menuBuilder("Main menu", this.currentGame.getCurrentPlayer(), "New Game","Choose player","Match history");

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

        int menuChoice = GameUtils.menuBuilder("No player selected", this.currentGame.getCurrentPlayer(), "Create new player","Choose existing player","Main menu");

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

        int menuChoice = GameUtils.menuBuilder("Rock, paper & Scissors", this.currentGame.getCurrentPlayer(), "Rock","Paper","Scissors");

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

    /**
     * Dynamic menu with 3 static options.
     * Writes out all available players as different menu items for the user to choose from
     */
    public void playerSelectionMenu(){

        int menuChoice = GameUtils.menuBuilder("Choose player", this.currentGame.getCurrentPlayer(),"Create and choose new character", "Return to main menu", "Delete player", printPlayerListAsMenuIndex());


//        antal options = 4
//                men options måste vara 3 + size

        switch(menuChoice){
            case 1:
                createNewPlayer();
                break;
            case 2:
                mainMenu();
                break;
            case 3:
                if(this.currentGame.getAllPlayers().size() == 0){
                    System.out.println("Test"); /// FIXA
                    playerSelectionMenu();
                }
                System.out.println( "Which player to remove?" );
                deletePlayer(this.currentGame.getAllPlayers().size() + 3);
                playerSelectionMenu();
                break;
            default:
                System.out.println(menuChoice);
                this.currentGame.setCurrentPlayer(this.currentGame.getAllPlayers().get( menuChoice - 4 ));
        }
        mainMenu();
    }

    public String printPlayerListAsMenuIndex (){
        int playerSelectCounter = 0;
        String playerListBuilder ="";
        for( Player p : this.currentGame.getAllPlayers() ){
            if(playerSelectCounter == 0){

                playerListBuilder +=(p.getName()+"\n");
            }else{
                playerListBuilder +=( playerSelectCounter + 4 ) + ".\t" + p.getName()+"\n";
            }
            playerSelectCounter++;
        }
        return playerListBuilder;
    }


    /**
     * Method to delete a player from the playerSelectionMenu.
     * @param playerIndexToRemove takes the player and remove it from arraylist in game class.
     * @return
     */
    public String deletePlayer(int playerIndexToRemove) {

            int menuChoice = 0;

            do{
                menuChoice = GameUtils.tryCatchMenuInput(playerIndexToRemove);
                if(menuChoice < 4){
                    System.out.println("Invalid input");
                }
            }while(menuChoice < 4);
            if (this.currentGame.getCurrentPlayer() != null && this.currentGame.getAllPlayers().get(menuChoice - 4).getName().equals(this.currentGame.getCurrentPlayer().getName())) {
                return "Can't delete existing player!";
            }else{
                this.currentGame.getAllPlayers().remove( menuChoice -4 );
                return  this.currentGame.getAllPlayers()+"removed";
            }
    }

    /**
     * Writes out current active players match history.
     * doesn't print it out if no active player.
     * Gives an option to go back to main menu
     */
    public void matchHistoryMenu(){

        int menuChoice = GameUtils.menuBuilder("Match Historu", this.currentGame.getCurrentPlayer(), matchHistoryPrinter());

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
        int borderLength = GameUtils.DynamicVariables.menuLength-2;
        int spaceLength = GameUtils.DynamicVariables.menuLength - 2;

        if( !this.currentGame.getCurrentPlayer().getMatchHistory().isEmpty() ){
            matchHistoryTopBorder = (GameUtils.DynamicVariables.menuBorderSymbols[2]) + GameUtils.geometryBuilder(GameUtils.DynamicVariables.menuBorderSymbols[0],0,borderLength)+ GameUtils.DynamicVariables.menuBorderSymbols[3] + "\n│ Match id " + GameUtils.geometryBuilder(" ", 0, 31) + "Result "+ GameUtils.DynamicVariables.menuBorderSymbols[1]+ "\n" + GameUtils.DynamicVariables.menuBorderSymbols[4] +GameUtils.geometryBuilder(GameUtils.DynamicVariables.menuBorderSymbols[0],0,borderLength) + GameUtils.DynamicVariables.menuBorderSymbols[5] ;

            if( !this.currentGame.getCurrentPlayer().getMatchHistory().isEmpty() ){
                for( Match m : this.currentGame.getCurrentPlayer().getMatchHistory() ){
                    matchHistoryTopBorder += ( "\n│ [" + m.getMatchId() + "]" +GameUtils.geometryBuilder(" ",this.currentGame.getCurrentPlayer().getMatchHistory().get(0).getResult().length(),28)+ m.getResult() + "│" );
                }
                matchHistoryTopBorder += "\n";
            }
            matchHistoryBottomBorder = ( "└"+GameUtils.geometryBuilder( "─",0,48 )+"┘\n" );
        }

        return matchHistoryTopBorder + matchHistoryBottomBorder +"1.\tMain menu";
    }

    /**
     * Custom character creation and adding it to playerbase
     * Restricted name length to handle menu geometry easier.
     */
    public void createNewPlayer(){
        String playerName;
        Scanner console = new Scanner(System.in);
        do {
            System.out.print( "Name your character: " );
            playerName = console.nextLine();
            if ( playerName.length() > 10 || playerName.length() < 2 ) {
                System.out.println("Sorry, your name need to be 2-10 characters");
            }
        }while ( playerName.length() > 10 || playerName.length() < 2 ) ;
        this.currentGame.getAllPlayers().add( new Player( playerName ) );
        this.currentGame.setCurrentPlayer( this.currentGame.getAllPlayers().get( this.currentGame.getAllPlayers().size() - 1 ) );
    }

    /**
     * Menu to show up after a match is complete, gives options to
     * replay, go back to main menu or to quick the game.
     */
    public void afterMatchMenu(){

        int menuChoice = GameUtils.menuBuilder( afterMatchMenuScoreBorder(), this.currentGame.getCurrentPlayer(), "Main Menu","New Match","Quit" );

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
     * Method to create the scoreboard after match ended and integrate
     * it to the current menu design.
     * Displays opponents name, players name and result.
     * @return opponent name, match result and player name in a string
     */
    public String afterMatchMenuScoreBorder(){

        String[] space = new String[2];
        int opponentOffset = this.currentGame.getCurrentMatch().getCurrentOpponent().getOpponentOutcome().length() + this.currentGame.getCurrentMatch().getCurrentOpponent().getOpponentName().length() + 1;
        int playerOffset = this.currentGame.getCurrentMatch().getCurrentPlayer().getName().length() + this.currentGame.getCurrentPlayer().getPlayerOutcome().length() + 1;
        int outcomeSpaceLength = ( GameUtils.DynamicVariables.menuLength /2 ) - ( this.currentGame.getCurrentMatch().getResult().length()/2 )-1;

        space[0] = GameUtils.geometryBuilder( " ",opponentOffset, outcomeSpaceLength );
        space[1] = GameUtils.geometryBuilder( " ",playerOffset, outcomeSpaceLength );

        String printOpponentOutcome ="Opponent: " + this.currentGame.getCurrentMatch().getCurrentOpponent().getOpponentOutcome()+space[0];
        String printPlayerOutcome =  space [1] + this.currentGame.getCurrentPlayer().getPlayerOutcome() + " :";

        return printOpponentOutcome + this.currentGame.getCurrentMatch().getResult() + printPlayerOutcome;
    }
}
