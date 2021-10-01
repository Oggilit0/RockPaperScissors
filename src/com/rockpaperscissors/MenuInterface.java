package com.rockpaperscissors;

/**
 * Class that handles menus.
 * @author Oskar
 */
public class MenuInterface {
        private final Game currentGame;

    /**
     * Constructor to add the current game program for access
     * @param currentGame input game to access its variables
     */
    public MenuInterface(Game currentGame ){
        this.currentGame = currentGame;
    }

    /**
     * The first menu the player encounters, gives multiple
     * decisions to advance
     */
    public void mainMenu(){

        int menuChoice = GameUtils.menuBuilder( "Main menu", this.currentGame.getCurrentPlayer(), "New Game","Choose player","Match history" );

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

        int menuChoice = GameUtils.menuBuilder( "No player selected", this.currentGame.getCurrentPlayer(), "Main menu","Choose existing player","Create new player" );

        switch (menuChoice) {
            case 1 -> mainMenu();
            case 2 -> {
                this.currentGame.createNewPlayer();
                this.currentGame.newMatch();
            }
            case 3 -> playerSelectionMenu();
        }
    }

    /**
     * Give the player an option to choose between the 3 outcomes
     * of rock paper & scissors
     */
    public void playerChoiceMenu(){

        int menuChoice = GameUtils.menuBuilder( "Rock, paper & Scissors", this.currentGame.getCurrentPlayer(), "Rock","Paper","Scissors" );

        switch (menuChoice) {
            case 1 -> this.currentGame.getCurrentPlayer().setPlayerOutcome("Rock");
            case 2 -> this.currentGame.getCurrentPlayer().setPlayerOutcome("Paper");
            case 3 -> this.currentGame.getCurrentPlayer().setPlayerOutcome("Scissors");
        }
    }

    /**
     * Dynamic menu with 3 static options.
     * Writes out all available players as different menu items for the user to choose from
     */
    public void playerSelectionMenu(){

        int menuChoice = GameUtils.menuBuilder( "Choose player", this.currentGame.getCurrentPlayer(),"Main menu","Create and choose new character",  "Delete player", GameUtils.printPlayerListAsMenuIndex( this.currentGame ) );

        switch (menuChoice) {
            case 1 -> mainMenu();
            case 2 -> this.currentGame.createNewPlayer();
            case 3 -> {
                if (this.currentGame.getAllPlayers().size() == 0) {
                    playerSelectionMenu();
                }
                System.out.println("Which player to remove?");
                this.currentGame.deletePlayer(this.currentGame.getAllPlayers().size() + 3);
                playerSelectionMenu();
            }
            default -> this.currentGame.setCurrentPlayer(this.currentGame.getAllPlayers().get(menuChoice - 4));
        }
        mainMenu();
    }

    /**
     * Menu for showing match history
     */
    public void matchHistoryMenu(){

        int menuChoice = GameUtils.menuBuilder( "Match History", this.currentGame.getCurrentPlayer(), GameUtils.matchHistoryPrinter( currentGame ) );

        if ( menuChoice == 1 ) {
            mainMenu();
        }
    }

    /**
     * Menu to show up after a match is complete, gives options to
     * replay, go back to main menu or to quick the game.
     */
    public void afterMatchMenu(){

        int menuChoice = GameUtils.menuBuilder( GameUtils.afterMatchMenuScoreBorder( this.currentGame ), this.currentGame.getCurrentPlayer(), "Main Menu","New Match","Quit" );

        switch (menuChoice) {
            case 1 -> mainMenu();
            case 2 -> this.currentGame.newMatch();
            case 3 -> this.currentGame.gameOver();
        }
    }
}
