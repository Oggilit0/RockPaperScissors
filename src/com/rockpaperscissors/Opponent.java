package com.rockpaperscissors;

/**
 * Class for handling and storing the outcome of the opponent for each match
 * @author Oskar
 */
public class Opponent {
    private final String opponentName;
    private String opponentOutcome;

    /**
     * Constructor for the class Opponent, Initialize Opponents name
     * @param opponentName Give the opponent a name
     */
    public Opponent(String opponentName){
        this.opponentName = opponentName;
    }

    /**
     * Randomize the opponent's outcome, choosing randomly with
     * 1/3 probability, from rock, paper or scissors
     */
    public void RollOpponentOutcome(){
        int randNr = ( int ) ( Math.random() * 3 ) + 1;

        switch (randNr) {
            case 1 -> this.opponentOutcome = "Rock";
            case 2 -> this.opponentOutcome = "Paper";
            case 3 -> this.opponentOutcome = "Scissors";
        }
    }

    /**
     * Returns the outcome of the Opponent
     * @return Opponent object's outcome as a string
     */
    public String getOpponentOutcome() {
        return this.opponentOutcome;
    }

    /**
     * Returns the name of the Opponent
     * @return Opponent object's name as a string
     */
    public String getOpponentName() {
        return this.opponentName;
    }
}