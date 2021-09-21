package com.rockpaperscissors;

/*
 * Möjligtvis ändra namn på denna klass till Computer / ai eller nåt
 * Skapa fler opponents?
 * Human opponent?
 *
 */

//TODO Javadoc här

public class Opponent {
    private String opponentOutcome;


    public Opponent(){
        System.out.println("Test github");
    }

    /**
     * Method to return one of following elements with equal chance
     * @return Rock, Paper or Scissors
     */
    public void OpponentOutcome(){
        int randNr = ( int ) (Math.random() * 3 ) + 1;

        switch( randNr ){
            case 1:
                opponentOutcome = "Rock";
                break;
            case 2:
                opponentOutcome = "Paper";
                break;
            case 3:
                opponentOutcome = "Scissors";
                break;
        }
    }

    public String getOpponentOutcome() {
        return opponentOutcome;
    }
}