package com.rockpaperscissors;

import java.util.Scanner;

/**
 * Class that handles repeated processes and complicated outputs throughout the program
 * @author Oskar
 */
public class GameUtils {

    private static boolean firstMenuAlreadyExecuted = false;

    /**
     * inner Class handling repeated used variables throughout the program
     */
    public static class DynamicVariables {

        static int playerAmount = 5;
        static int menuLength = 50;
        static String[] menuBorderSymbols = {"─","│","┌","┐","├","┤","└","┘"};
    }

    /**
     * Method to handling user input and to get rid of wrongfully inputs that the user might do
     * @param maxCase get input from menu to know max amount of menu indexes to handle exceptions
     * @return int value to be handled in menus
     */
    public static int tryCatchMenuInput( int maxCase ){
        int menuChoice = 0;
        System.out.print( "\nUser input: " );
        Scanner console = new Scanner( System.in );

        do {
            try {
                menuChoice = Integer.parseInt( console.nextLine() );
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
     * Method to handle geometry, making longer strings of characters to use in menus.
     * @param symbol The character you want to print out
     * @param offset Offset from left to right
     * @param length total length of desired part
     * @return String containing parameters character with desired length
     */
    public static String geometryBuilder( String symbol, int offset,int length ){
        String geometry = "";
        for( int i = offset; i < length ; i++ ){
            geometry += symbol;
        }
        return geometry;
    }

    /**
     * Design for all menus to look identical and to print out a menu border, containing menu name
     * and current player name
     *
     * @param menuName Name of created menu
     * @param player player to be printed in menu border
     * @param options name and amount of menu indexes
     * @return int for menu choices
     */
    public static int menuBuilder ( String menuName, Player player, String ...options ){

        if(firstMenuAlreadyExecuted){
            System.out.println( "\n".repeat( 50 ) );
        }
        firstMenuAlreadyExecuted = true;

        int counter = 1;

        if ( player != null ){
            System.out.println( geometryBuilder( DynamicVariables.menuBorderSymbols[0],0, DynamicVariables.menuLength ) + "\n" + menuName + geometryBuilder(" ",player.getName().length(), DynamicVariables.menuLength - menuName.length()) + player.getName() + "\n" + geometryBuilder( DynamicVariables.menuBorderSymbols[0],0, DynamicVariables.menuLength ) );
        }else{
            System.out.println( geometryBuilder( DynamicVariables.menuBorderSymbols[0],0, DynamicVariables.menuLength ) + "\n" + menuName + "\n" + geometryBuilder( DynamicVariables.menuBorderSymbols[0],0, DynamicVariables.menuLength ) );
        }

        String maxMenuIndex = "";
        for (String option : options ){

            if ( option.equals( "" ) ) {
                // prints no value if string is empty
            }else if ( options.length == 1 ){
                System.out.println( option );
                maxMenuIndex += option;
            }else{
                System.out.println( counter + ".\t" + option );
                counter++;
                maxMenuIndex += option+"\n";
            }
        }
        return tryCatchMenuInput( ( int ) maxMenuIndex.lines().count() );
    }

    /**
     * Menu for making the match history design and outputs
     * @param currentGame Needs current game to fetch variables
     * @return String of text that will display borders and statistics of chosen player
     */
    public static String matchHistoryPrinter ( Game currentGame ){ // Disclaimer!: The readability of this method may disturb readers

        String matchHistoryTopBorder = "";

        if( currentGame.getCurrentPlayer() == null || currentGame.getCurrentPlayer().getMatchHistory().isEmpty() ){
            return "1.\tMain menu";
        }
        int borderLength = GameUtils.DynamicVariables.menuLength-2;

        if( !currentGame.getCurrentPlayer().getMatchHistory().isEmpty() ){

            // Shortened the string to be more readable
            String matchHistoryTBPartOne = ( GameUtils.DynamicVariables.menuBorderSymbols[2] ) + GameUtils.geometryBuilder(GameUtils.DynamicVariables.menuBorderSymbols[0],0,borderLength )+ GameUtils.DynamicVariables.menuBorderSymbols[3] + "\n│ Match id ";
            String matchHistoryTBPartTwo = GameUtils.geometryBuilder(" ", 0, GameUtils.DynamicVariables.menuLength-19);
            String matchHistoryTBPartThree = "Result "+ GameUtils.DynamicVariables.menuBorderSymbols[1]+ "\n" + GameUtils.DynamicVariables.menuBorderSymbols[4] +GameUtils.geometryBuilder(GameUtils.DynamicVariables.menuBorderSymbols[0],0,borderLength) + GameUtils.DynamicVariables.menuBorderSymbols[5];

            matchHistoryTopBorder = matchHistoryTBPartOne + matchHistoryTBPartTwo + matchHistoryTBPartThree;

            for( Match m : currentGame.getCurrentPlayer().getMatchHistory() ){
                matchHistoryTopBorder += ( "\n"+GameUtils.DynamicVariables.menuBorderSymbols[1]+" [" + m.getMatchId() + "]" +GameUtils.geometryBuilder(" ",currentGame.getCurrentPlayer().getMatchHistory().get(0).getResult().length(), GameUtils.DynamicVariables.menuLength -22)+ m.getResult() + GameUtils.DynamicVariables.menuBorderSymbols[1] );
            }
            matchHistoryTopBorder += "\n";
        }
        String matchHistoryBottomBorder = ( GameUtils.DynamicVariables.menuBorderSymbols[6]+GameUtils.geometryBuilder( GameUtils.DynamicVariables.menuBorderSymbols[0],0,borderLength )+GameUtils.DynamicVariables.menuBorderSymbols[7]+"\n" );


        return matchHistoryTopBorder + matchHistoryBottomBorder +"1.\tMain menu";
    }

    /**
     * Method to create the scoreboard after match ended and integrate
     * it to the current menu design.
     * Displays opponents name, players name and result.
     * @return opponent name, match result and player name in a string
     */
    public static String afterMatchMenuScoreBorder(Game currentGame){

        String[] space = new String[2];
        int opponentOffset = currentGame.getCurrentMatch().getCurrentOpponent().getOpponentOutcome().length() + currentGame.getCurrentMatch().getCurrentOpponent().getOpponentName().length() + 1;
        int playerOffset = currentGame.getCurrentMatch().getCurrentPlayer().getName().length() + currentGame.getCurrentPlayer().getPlayerOutcome().length() + 1;
        int outcomeSpaceLength = ( GameUtils.DynamicVariables.menuLength / 2 ) - ( currentGame.getCurrentMatch().getResult().length() / 2 ) - 1;

        space[0] = GameUtils.geometryBuilder( " ",opponentOffset, outcomeSpaceLength );
        space[1] = GameUtils.geometryBuilder( " ",playerOffset, outcomeSpaceLength );

        String printOpponentOutcome ="Opponent: " + currentGame.getCurrentMatch().getCurrentOpponent().getOpponentOutcome() + space[0];
        String printPlayerOutcome =  space [1] + currentGame.getCurrentPlayer().getPlayerOutcome() + " :";

        return printOpponentOutcome + currentGame.getCurrentMatch().getResult() + printPlayerOutcome;
    }

    /**
     * Method to take a dynamic list and add to the menu as menu indexes
     * @return String with player names and indexes
     */
    public static String printPlayerListAsMenuIndex ( Game currentGame ){
        int playerSelectCounter = 0;
        String playerListBuilder = "";
        for( Player p : currentGame.getAllPlayers() ){
            if( playerSelectCounter == 0 ){
                playerListBuilder +=( p.getName()+"\n" );
            }else{
                playerListBuilder +=( playerSelectCounter + 4 ) + ".\t" + p.getName() + "\n";
            }
            playerSelectCounter++;
        }
        return playerListBuilder;
    }
}
