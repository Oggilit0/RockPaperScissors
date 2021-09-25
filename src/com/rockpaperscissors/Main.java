package com.rockpaperscissors;

/* Java naming conventions
 *
 *
 *
 *  Class:
 *  Start with uppercase
 * (Substantiv)
 *  Riktiga ord inga påhitt
 *
 *
 *  Method:
 *  Start with lowercase
 * (Verb)
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

/**
 *
 * @author Oskar
 */

public class Main {
    public static void main(String[] args) {

        // För extra bling bling lagras spelare tills nästa gång programmet körs
        Game newGame = new Game();
        newGame.startUp();

    }
}
