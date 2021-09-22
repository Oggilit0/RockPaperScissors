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

    }
    /**
     * Method to randomize Opponent outcome
     */
    public void OpponentOutcome(){
        int randNr = ( int ) (Math.random() * 3 ) + 1;

        switch( randNr ){
            case 1:
                this.opponentOutcome = "Rock";
                break;
            case 2:
                this.opponentOutcome = "Paper";
                break;
            case 3:
                this.opponentOutcome = "Scissors";
                break;
        }
    }

    public String getOpponentOutcome() {
        return this.opponentOutcome;
    }
}