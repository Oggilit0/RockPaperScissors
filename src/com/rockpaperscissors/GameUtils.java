package com.rockpaperscissors;

import java.util.Scanner;

public class GameUtils {

    private static Scanner console;

    public static void clearConsole(){
        System.out.println("\n".repeat(20));
    }

    /**
     * Method to handling user input and to get rid of wrongfully inputs that the user might do
     * @param maxCase get input from menu to know max case to handle exceptions
     * @return int value to be handled in menus
     */
    public static int tryCatchMenus( int maxCase ){
        int menuChoice = 0;
        System.out.print( "\nUser input: " );
        console = new Scanner( System.in);

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
     * Design for all menus to look identical with semi-hard coded geometry
     * Menu name length is dynamic but shouldn't be too long
     * @param menu input string to display menu name in top right corner of menu
     */
    public static void menuDesign( String menu , Player player ){
        GameUtils.clearConsole();
        int menuLength = 50;
        String menuBorder = "─";
        if ( player != null ){
            System.out.println( geometryBuilder( menuBorder,0,menuLength ) + "\n" + menu + geometryBuilder(" ",player.getName().length(),menuLength - menu.length()) + player.getName() + "\n" + geometryBuilder( menuBorder,0,menuLength ) );
        }else{
            System.out.println( geometryBuilder( menuBorder,0,menuLength ) + "\n" + menu + "\n" + geometryBuilder( menuBorder,0,menuLength ) );
        }
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


    public static int menuBuilder ( String menuName, Player player, String ...options ){
        GameUtils.clearConsole();
        int menuLength = 50;
        String menuBorder = "─";
        if ( player != null ){
            System.out.println( geometryBuilder( menuBorder,0,menuLength ) + "\n" + menuName + geometryBuilder(" ",player.getName().length(),menuLength - menuName.length()) + player.getName() + "\n" + geometryBuilder( menuBorder,0,menuLength ) );
        }else{
            System.out.println( geometryBuilder( menuBorder,0,menuLength ) + "\n" + menuName + "\n" + geometryBuilder( menuBorder,0,menuLength ) );
        }

        int counter = 1;
        for (String option : options ){

            if ( option.equals( "" ) ) {
                // outprints no value if string is empty
            }else if ( options.length == 1 ){
                System.out.println( option );
            }else{
                System.out.println( counter + ".\t" + option );
                counter++;
            }

        }
        return tryCatchMenus( options.length );
    }


}
