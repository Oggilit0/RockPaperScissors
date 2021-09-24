package com.rockpaperscissors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 */

public class Match {
    private String matchId;
    private String result;
    private Opponent currentOpponent;


    /**
     * Constructor of the class Match
     * @param result stores the outcome of match for later use
     */
    public Match(String result){
        this.result = result;
        matchId();
    }

    /**
     * Set matchId to current time as unique ID for each game
     */
    public void matchId(){
        LocalDateTime matchIdTime = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yy-MM-dd:HH.mm.ss");
        this.matchId = matchIdTime.format(timeFormat);
    }

    /**
     * Creates a new opponent
     */
    public void createNewOpponent(){
        this.currentOpponent = new Opponent();
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return this.result;
    }

    public String getMatchId() {
        return this.matchId;
    }

    public Opponent getCurrentOpponent() {
        return currentOpponent;
    }
}

