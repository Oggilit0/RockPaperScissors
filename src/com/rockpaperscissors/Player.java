package com.rockpaperscissors;

import java.util.ArrayList;

/**
 * Handling and storing player data, such as name and match history for later usage.
 */
public class Player {
    private final String name;
    private final ArrayList<Match> matchHistory;
    private String playerOutcome;

    public Player(String name){
        this.name = name;
        matchHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setMatchHistory(Match matchHistory) {
        this.matchHistory.add(matchHistory);
    }

    public ArrayList<Match> getMatchHistory() {
        return matchHistory;
    }

    public void setPlayerOutcome(String playerOutcome) {
        this.playerOutcome = playerOutcome;
    }

    public String getPlayerOutcome() {
        return playerOutcome;
    }
}