package com.rockpaperscissors;

import java.util.ArrayList;

//TODO Javadoc här
public class Player {
    private final String name;
    private final ArrayList<Match> matchHistory;

    public Player(String name){
        this.name = name;
        matchHistory = new ArrayList<>();
    }

    public ArrayList<Match> getMatchHistory() {
        return matchHistory;
    }

    public void setMatchHistory(Match matchHistory) {
        this.matchHistory.add(matchHistory);
    }

    public String getName() {
        return name;
    }
}