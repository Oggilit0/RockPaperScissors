package com.rockpaperscissors;

import java.util.Scanner;

public class GameUtils {

    private static boolean firstMenuAlreadyExecuted = false;

    /**
     * inner Class handling geometry of the program
     *<p> Border symbols:
     * <p>[0] "─" <p> [1] "│" <p>[2] "┌" <p>[3] "┐" <p>[4] "├" <p>[5] "┤"
     */
    public class DynamicVariables {
        static int playerAmount = 3;
        static int menuLength = 50;
        static String[] menuBorderSymbols = {"─","│","┌","┐","├","┤"};
    }

    /**
     * Clears the console by creating many empty lines
     */
    //Behövs inte mer än ett st'älle just nu ?
    public static void clearConsole(){
        System.out.println( "\n".repeat( 20 ) );
    }

    /**
     * Method to handling user input and to get rid of wrongfully inputs that the user might do
     * @param maxCase get input from menu to know max case to handle exceptions
     * @return int value to be handled in menus
     */
    public static int tryCatchMenuInput(int maxCase ){
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
     * Keep Max length: 50
     *
     * @param symbol The character you want to print out
     * @param offset Offset from left side of console
     * @param length total length of desired part
     * @return
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
     * @param options name and amount of menu index's
     * @return int for menu choices
     */
    public static int menuBuilder ( String menuName, Player player, String ...options ){

        if(firstMenuAlreadyExecuted){
            GameUtils.clearConsole();
        }
        firstMenuAlreadyExecuted = true;

        int counter = 1;

        if ( player != null ){
            System.out.println( geometryBuilder( DynamicVariables.menuBorderSymbols[0],0, DynamicVariables.menuLength ) + "\n" + menuName + geometryBuilder(" ",player.getName().length(), DynamicVariables.menuLength - menuName.length()) + player.getName() + "\n" + geometryBuilder( DynamicVariables.menuBorderSymbols[0],0, DynamicVariables.menuLength ) );
        }else{
            System.out.println( geometryBuilder( DynamicVariables.menuBorderSymbols[0],0, DynamicVariables.menuLength ) + "\n" + menuName + "\n" + geometryBuilder( DynamicVariables.menuBorderSymbols[0],0, DynamicVariables.menuLength ) );
        }

        for (String option : options ){

            if ( option.equals( "" ) ) {
                // prints no value if string is empty
            }else if ( options.length == 1 ){
                System.out.println( option );
            }else{
                System.out.println( counter + ".\t" + option );
                counter++;
            }
        }
        return tryCatchMenuInput( options.length );
    }
}
