package com.rockpaperscissors;

/* Java naming conventions
 *
 *
 *
 *  Class:
 *  Start with uppercase (Substantiv)
 *  Riktiga ord inga påhitt
 *
 *
 *  Method:
 *  Start with lowercase (Verb)
 *  Camelcase
 *
 *
 *  Variable:
 *  Start with lowercase
 *  Camelcase
 *  avoid one character variable (x, y, z)
 *
 *
 *  Package:
 *  Starts with lowercase
 *  multiple words should be seperated by dots
 *
 */

public class Main {
    public static void main(String[] args) {

        // Behöver en input för att ta user data

        // Spelare som skall finnas från början i G
        // För vg skapar men spelare själv
        // För extra bling bling lagras spelare tills nästa gång programmet körs


        Game newGame = new Game();
        newGame.createNewOpponent();
        newGame.mainMenu();


    }
}
