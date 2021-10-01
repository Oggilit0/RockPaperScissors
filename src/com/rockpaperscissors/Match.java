package com.rockpaperscissors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * This is the Match class where we store ID and result of the match..
 * We also roll our opponent's outcome.
 * @author Oskar
 */

public class Match {
    private String matchId;
    private String result;
    private final Player currentPlayer;
    private final Opponent currentOpponent;

    /**
     * Constructor of the Class Match, creates and store and ID for each match object
     * @param currentPlayer Needs an active player
     * @param currentOpponent Needs an active Opponent
     */
    public Match( Player currentPlayer, Opponent currentOpponent ){
        this.currentPlayer = currentPlayer;
        this.currentOpponent = currentOpponent;
        matchId();
    }

    /**
     * Set matchId to current time as unique ID for each game
     */
    public void matchId(){
        LocalDateTime matchIdTime = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern( "yy-MM-dd:HH.mm.ss" );
        this.matchId = matchIdTime.format( timeFormat );
    }

    /**
     * Handling outcome of each individual match and stores the result in current match object.
     * Stores the match in active Player's match history.
     */
    public void matchOutcome(){
        if ( this.currentPlayer.getPlayerOutcome().equals( this.currentOpponent.getOpponentOutcome() ) ){
            this.result = "Draw";
        } else{
            switch( this.currentPlayer.getPlayerOutcome() ){
                case "Rock":
                    if( this.currentOpponent.getOpponentOutcome().equals( "Scissors" ) ){
                        this.result = "Won!";
                    }else{
                        this.result = "Lost";
                    }
                    break;

                case "Paper":
                    if( this.currentOpponent.getOpponentOutcome().equals( "Rock" ) ){
                        this.result = "Won!";
                    }else{
                        this.result = "Lost";
                    }
                    break;

                case "Scissors":
                    if(this.currentOpponent.getOpponentOutcome().equals( "Paper" )){
                        this.result = "Won!";
                    }else{
                        this.result = "Lost";
                    }
                    break;
            }
        }
        this.currentPlayer.setMatchHistory( this );
    }

    /**
     * Return the match result
     * @return result
     */
    public String getResult() {
        return this.result;
    }

    /**
     * Return the match ID
     * @return matchId
     */
    public String getMatchId() {
        return this.matchId;
    }

    public Opponent getCurrentOpponent() {
        return currentOpponent;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}

