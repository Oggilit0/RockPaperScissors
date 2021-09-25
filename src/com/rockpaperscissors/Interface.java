package com.rockpaperscissors;

import java.util.Scanner;

public class Interface {
        private Game currentGame;

    public Interface(Game currentGame){
        this.currentGame = currentGame;
    }

    public void mainMenu(){
        menuDesign( "Main Menu" );
        System.out.println( "1.  New Game\n2.  Choose player\n3.  Match history\n4.  Quit" );
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

    public void noPlayerSelectMenu(){
        menuDesign( "no player selected" );
        System.out.println( "1.  Create new player \n2.  Choose existing player\n3.  Main menu" );
        int menuChoice = tryCatchMenus(3);

        switch ( menuChoice ){
            case 1:
                this.currentGame.createNewPlayer();
                this.currentGame.setCurrentPlayer(this.currentGame.getAllPlayers().get( this.currentGame.getAllPlayers().size() - 1 ));
                this.currentGame.newMatch();
                break;
            case 2:
                playerSelectionMenu();
                break;
            case 3:
                mainMenu();
                break;
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
    public String geometryBuilder(String symbol, int offset,int length){
        String geometry = "";
        for( int i = offset; i < length ; i++ ){
            geometry += symbol;
        }
        return geometry;
    }


    public void playerChoiceMenu(){
        menuDesign( "Rock, paper & scissors" );
        System.out.println( "1.  Rock\n2.  Paper\n3.  Scissors" );
        int menuChoice = tryCatchMenus(3);

        switch( menuChoice ){
            case 1:
                this.currentGame.getCurrentPlayer().setPlayerOutcome("Rock");
                break;
            case 2:
                this.currentGame.getCurrentPlayer().setPlayerOutcome("Paper");
                break;
            case 3:
                this.currentGame.getCurrentPlayer().setPlayerOutcome("Scissors");
                break;
        }
    }

    public void playerSelectionMenu(){
        menuDesign( "Choose Player" );
        int playerSelectCounter = 0;

        for( Player p : this.currentGame.getAllPlayers() ){
            System.out.println( ( playerSelectCounter+1 ) + ".   " + p.getName() );
            playerSelectCounter++;
        }
        System.out.println( playerSelectCounter + 1 + ".   Create new character\n" + ( playerSelectCounter + 2 ) + ".   Return to main menu\n" + ( playerSelectCounter + 3 ) + ".   Delete player" );
        int menuChoice = tryCatchMenus(100); /// FIXA DEN HÄR

        if( menuChoice == (this.currentGame.getAllPlayers().size() + 1 ) ){

            this.currentGame.createNewPlayer();

        }else if ( menuChoice == ( this.currentGame.getAllPlayers().size() + 2 ) ){

            mainMenu();

        }else if( menuChoice == ( this.currentGame.getAllPlayers().size() ) + 3 ){

            System.out.println( "Which player to remove?" );
            menuChoice = tryCatchMenus(100);


            /// BEHÖVS EN IFSATS FÖR ATT kunna ta bort en spelare även om man inte valt nån
            if( menuChoice <= 0 || menuChoice > this.currentGame.getAllPlayers().size() ){
//                System.out.println( "Invalid input" );
//            if (currentPlayer != null){
//
//            }
            }else if (this.currentGame.getCurrentPlayer()!= null && this.currentGame.getAllPlayers().get(menuChoice - 1).getName().equals(this.currentGame.getCurrentPlayer().getName())) {
                System.out.println("Can't delete existing player!");

            }else{
                this.currentGame.getAllPlayers().remove( menuChoice - 1 );
            }

        }
        if( menuChoice > this.currentGame.getAllPlayers().size() ){
            System.out.println( "Invalid input" ); // Gör do while loopar istället kanske ?

        }else{
            this.currentGame.setCurrentPlayer(this.currentGame.getAllPlayers().get( menuChoice - 1 ));
            System.out.println( this.currentGame.getCurrentPlayer().getName() );
            mainMenu();

        }
        playerSelectionMenu();
    }

    public void matchHistoryMenu(){
        menuDesign( "Match History" );
        System.out.println( "1   Match history\n2   Main Menu");
        int menuChoice = tryCatchMenus(2);

        switch(menuChoice){
            case 1:
                if( this.currentGame.getCurrentPlayer() == null ){
                    System.out.println( "No active player" );
                    matchHistoryMenu();
                }

                if( !this.currentGame.getCurrentPlayer().getMatchHistory().isEmpty() ){
                    System.out.println( " Player: " + this.currentGame.getCurrentPlayer().getName() );
                    System.out.println( "┌"+geometryBuilder("─",0,48)+"┐\n" + "│ Match id " + geometryBuilder(" ", 0, 31) + "Result │\n" + "├" +geometryBuilder("─",0,48)+"┤" );

                    if( !this.currentGame.getCurrentPlayer().getMatchHistory().isEmpty() ){
                        for( Match m : this.currentGame.getCurrentPlayer().getMatchHistory() ){
                            System.out.println( "│ [" + m.getMatchId() + "]" +geometryBuilder(" ",this.currentGame.getCurrentPlayer().getMatchHistory().get(0).getResult().length(),28)+ m.getResult() + "│" );
                        }
                    }
                    System.out.println( "└────────────────────────────────────────────────┘" );
                }else{
                    System.out.println("no record to show");
                }
                break;
            case 2:
                mainMenu();
                break;
        }
        afterMatchHistoryMenu();
    }

    public void afterMatchHistoryMenu(){
        System.out.println( "1.  Main Menu\n2.  Quit" );
        int menuChoice = tryCatchMenus(2);

        switch( menuChoice ){
            case 1:
                mainMenu();
                break;
            case 2:
                this.currentGame.gameOver();
                break;
        }
    }

    /// FIXA GEOMETRI HÄR MED MELLANRUM
    public void afterMatchMenu(){
        String[] space = { "","","" };
        for( int i = ( this.currentGame.getCurrentPlayer().getName().length() + this.currentGame.getCurrentPlayer().getPlayerOutcome().length()) + 1 ; i < 24 ; i++ ){
            space[0] += " ";
        }
        for( int i = this.currentGame.getCurrentMatch().getCurrentOpponent().getOpponentOutcome().length() + 9; i < 24 ; i++ ){
            space[1] += " ";
        }
        for( int i = this.currentGame.getCurrentMatch().getResult().length() ; i <= 26 ; i++ ){
            space[2] += " ";
        }

        String result = "Opponent:" + space[1] + this.currentGame.getCurrentMatch().getCurrentOpponent().getOpponentOutcome() + "││" + this.currentGame.getCurrentPlayer().getPlayerOutcome() +space[0] + ":";

        menuDesign( result );
        System.out.println(space[2] + this.currentGame.getCurrentMatch().getResult() );
        System.out.println( "1.  Main Menu\n2.  New Match\n3.  Quit" );
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
     * Method to handling user input and to get rid of wrongfully inputs that the user might do
     * @param maxCase get input from menu to know max case to handle exceptions
     * @return int value to be handled in menus
     */
    public int tryCatchMenus(int maxCase){
        int menuChoice = 0;
        do {
            try {
                this.currentGame.setConsole(new Scanner( System.in ));
                menuChoice = Integer.parseInt( this.currentGame.getConsole().nextLine() );
            }
            catch( Exception e ){
                System.out.println( "Invalid input" );
            }
            if(menuChoice < 0 || menuChoice > maxCase){
                System.out.println( "invalid input" );
            }
        }while( menuChoice == 0 || menuChoice < 0 || menuChoice > maxCase);
        return menuChoice;
    }

    /**
     * Design for all menus to look identical with semi-hard coded geometry
     * Menu name length is dynamic but shouldn't be too long
     * @param menu input string to display menu name in top right corner of menu
     */
    public void menuDesign( String menu ){
        if ( this.currentGame.getCurrentPlayer() != null ){
            System.out.println( geometryBuilder( "─",0,50 ) + "\n" + menu + geometryBuilder(" ",this.currentGame.getCurrentPlayer().getName().length(),50-menu.length()) + this.currentGame.getCurrentPlayer().getName() + "\n" + geometryBuilder( "─",0,50 ) );
        }else{
            System.out.println( geometryBuilder( "─",0,50 ) + "\n" + menu + "\n" + geometryBuilder( "─",0,50 ) );
        }
    }

}
