package com.rockpaperscissors;


// Döp om till nåt mer passande för att ha med menyer i classen ? typ program ? Döp om till game
// this på allt också

import java.util.Scanner;

public class Game {
    private Scanner console;
    private String playerChoice;
    private Player currentPlayer;
    private Opponent currentOpponent;
    private Match currentMatch;

    public Game(){
        this.console = new Scanner(System.in);
    }

    public void newMatch(){
        currentMatch = new Match("n/a");
        playerChoiceMenu();
        System.out.println("Player: "+playerChoice);
        currentOpponent.OpponentOutcome();
        System.out.println("Opponent : "+currentOpponent.getOpponentOutcome());
        outcome();
        afterMatchMenu();
    }


    public void outcome(){
        if (playerChoice.equals(currentOpponent.getOpponentOutcome())){
            currentMatch.setResult("Draw!");
        } else{
            switch(playerChoice){
                case "Rock":
                    if(currentOpponent.getOpponentOutcome().equals("Scissors")){
                        currentMatch.setResult("Won!");
                    }else{
                        currentMatch.setResult("Lost!");
                    }
                    break;

                case "Paper":
                    if(currentOpponent.getOpponentOutcome().equals("Rock")){
                        currentMatch.setResult("Won!");
                    }else{
                        currentMatch.setResult("Lost!");
                    }
                    break;

                case "Scissors":
                    if(currentOpponent.getOpponentOutcome().equals("Paper")){
                        currentMatch.setResult("Won!");
                    }else{
                        currentMatch.setResult("Lost!");
                    }
                    break;
            }
        }

        currentPlayer.setMatchHistory(this.currentMatch);
    }

    public void createNewPlayer(){
        System.out.println("Skriv in ditt namn: ");
        String playerName = console.nextLine();
        currentPlayer = new Player(playerName);
    }

    public void createNewOpponent(){
        this.currentOpponent = new Opponent();
    }

    public void gameOver(){
        System.out.println("GAME OVER");
    }

    public void mainMenu(){

        System.out.println("MainMenu\n1.  New Game\n2.  Match histoy\n3.  Quit");


        int menuChoice=0;

        try {
            menuChoice = Integer.parseInt(console.nextLine());
        }
        catch(Exception e){
        }

        switch(menuChoice){
            case 1:
                if(currentPlayer == null){
                    createNewPlayer();
                    newMatch();
                }else{
                    newMatch();
                }
                break;
            case 2:
                for(Match a : currentPlayer.getMatchHistory()){
                    System.out.println("<Match id> [" + a.getMatchId() + "] : " + a.getResult());
                }
                break;
            case 3:
                gameOver();
                break;
            default:
                System.out.println("Där bidde det fel, försök igen");
                mainMenu();
        }
    }

    public void playerChoiceMenu(){
        System.out.println("Vad väljer du?");
        System.out.println("1. Rock     2. Paper     3. Scissors\n"); // kom ihåg <---------\n
        int menuChoice = 0;

        try {
            menuChoice = Integer.parseInt(console.nextLine());
        }
        catch(Exception e){
        }

        switch(menuChoice){
            case 1:
                playerChoice = "Rock";
                break;
            case 2:
                playerChoice = "Paper";
                break;
            case 3:
                playerChoice = "Scissors";
                break;
            default:
                System.out.println("Där bidde det fel, försök igen");
                playerChoiceMenu();
        }
    }

    public void afterMatchMenu(){
        System.out.println("Resultat: " + currentMatch.getResult());
        System.out.println("1. Huvudmeny     2. Kör igen     3. Avsluta spelet\n"); // kom ihåg <---------\n
        int menuChoice = 0;

        try {
            menuChoice = Integer.parseInt(console.nextLine());
        }
        catch(Exception e){
        }

        switch(menuChoice){
            case 1:
                mainMenu();
                break;
            case 2:
                newMatch();
                break;
            case 3:
                gameOver();
                break;
            default:
                System.out.println("Där bidde det fel, försök igen");
                afterMatchMenu();
        }

    }

}
