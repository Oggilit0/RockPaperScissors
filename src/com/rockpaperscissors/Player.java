package com.rockpaperscissors;

import java.util.ArrayList;

/**
 * Handling and storing player name, outcome and match history for later usage.
 * @author Oskar
 */

public class Player {
    private final String name;
    private final ArrayList<Match> matchHistory;
    private String playerOutcome;

    /**
     * Constructor of the class Player.
     * Initialize name from parameter and initializing
     * an empty arraylist
     * @param name input name of player
     */
    public Player( String name ){
        this.name = name;
        this.matchHistory = new ArrayList<>();
    }

    /**
     * Return the name of the player
     * @return Player objects name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Uses data from parameter to add to players matchHistory list
     * @param matchHistory Input match to be stored locally in player
     */
    public void setMatchHistory( Match matchHistory ) {
        this.matchHistory.add(matchHistory);
    }

    /**
     * Fetch arraylist with stored matches
     * @return Arraylist with stored Match objects
     */
    public ArrayList<Match> getMatchHistory() {
        return this.matchHistory;
    }

    /**
     * Uses data from parameter to add player outcome
     * @param playerOutcome Input string to be stored locally in player
     */
    public void setPlayerOutcome( String playerOutcome ) {
        this.playerOutcome = playerOutcome;
    }

    /**
     * Fetch String whit store outcome
     * @return String with stored outcome
     */
    public String getPlayerOutcome() {
        return this.playerOutcome;
    }
}