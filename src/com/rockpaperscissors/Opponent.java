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
    private String opponentOutcome;

    public Opponent(){
    }

    public void setOpponentOutcome(String opponentOutcome) {
        this.opponentOutcome = opponentOutcome;
    }

    public String getOpponentOutcome() {
        return this.opponentOutcome;
    }
}