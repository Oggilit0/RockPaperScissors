package com.rockpaperscissors;

/*
 * Möjligtvis ändra namn på denna klass till Computer / ai eller nåt
 * Skapa fler opponents?
 * Human opponent?
 *
 */

/**
 * Class for handling and storing the outcome of the opponent for each match
 * @author Oskar
 */
public class Opponent {
    private String opponentName;
    private String opponentOutcome;

    /**
     * Constructor for the class Opponent, Initialize Opponents name
     * @param opponentName Give the opponent a name
     */
    public Opponent(String opponentName){
        this.opponentName = opponentName;
    }

    /**
     * Randomize the opponent's outcome, choosing randomly, with
     * 1/3 probability, from rock, paper or scissors
     */
    public void RollOpponentOutcome(){
        int randNr = ( int ) ( Math.random() * 3 ) + 1;

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

    public String getOpponentName() {
        return opponentName;
    }
}