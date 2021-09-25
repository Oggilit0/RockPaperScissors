package com.rockpaperscissors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 */

public class Match {
    private String matchId;
    private String result;
    private Player currentPlayer;
    private Opponent currentOpponent;

    /**
     * Constructor of the class Match
     * @param result stores the outcome of match for later use
     */
    public Match( String result ){
        this.result = result;
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
     * Method to randomize Opponent outcome
     */
    public void OpponentOutcome(){
        int randNr = ( int ) ( Math.random() * 3 ) + 1;

        switch( randNr ){
            case 1:
                this.currentOpponent.setOpponentOutcome( "Rock" );
                break;
            case 2:
                this.currentOpponent.setOpponentOutcome( "Paper" );
                break;
            case 3:
                this.currentOpponent.setOpponentOutcome( "Scissors" );
                break;
        }
    }

    /**
     * Handling outcome of each individual match and stores the result in Match Class.
     * The match and result is then stored separately in Player class for the current player.
     */
    public void outcome(){
        if ( currentPlayer.getPlayerOutcome().equals( this.getCurrentOpponent().getOpponentOutcome() ) ){
            this.setResult( "Draw" );
        } else{
            switch( currentPlayer.getPlayerOutcome() ){
                case "Rock":
                    if( this.getCurrentOpponent().getOpponentOutcome().equals( "Scissors" ) ){
                        this.setResult( "Won!" );
                    }else{
                        this.setResult( "Lost" );
                    }
                    break;

                case "Paper":
                    if( this.getCurrentOpponent().getOpponentOutcome().equals( "Rock" ) ){
                        this.setResult( "Won!" );
                    }else{
                        this.setResult( "Lost" );
                    }
                    break;

                case "Scissors":
                    if(this.getCurrentOpponent().getOpponentOutcome().equals( "Paper" )){
                        this.setResult( "Won!" );
                    }else{
                        this.setResult( "Lost" );
                    }
                    break;
            }
        }
        this.currentPlayer.setMatchHistory( this );
    }

    /**
     * Creates a new opponent
     */
    public void createNewOpponent(){
        this.currentOpponent = new Opponent();
    }

    public void setResult( String result ) {
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

    public void setCurrentPlayer( Player currentPlayer ) {
        this.currentPlayer = currentPlayer;
    }

}

