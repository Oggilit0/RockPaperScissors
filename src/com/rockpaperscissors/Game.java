package com.rockpaperscissors;


// Döp om till nåt mer passande för att ha med menyer i classen ? typ program ? Döp om till game
// this på allt också


// Skapa player selection och lagring

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Scanner console;
    private String playerChoice;
    private Player currentPlayer;
    private Opponent currentOpponent;
    private Match currentMatch;
    private ArrayList<Player> allPlayers = new ArrayList<>();

    public Game(){
        this.console = new Scanner(System.in);
    }

    public void newMatch(){
        currentMatch = new Match("n/a");
        playerChoiceMenu();
        currentOpponent.OpponentOutcome();
        System.out.println(currentPlayer.getName()+ ": "+playerChoice);
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
        allPlayers.add(currentPlayer);
    }

    public void playerSelectionMenu(){
        System.out.println("================\n    MainMenu\n================\n1.  Rocky\n2.  Papena\n3.  Scilla\n4.  Create new player"); // skapa dynamisk system out för att lägga till spelare


        int menuChoice=0;

        try {
            menuChoice = Integer.parseInt(console.nextLine());
        }
        catch(Exception e){
        }

        switch(menuChoice){
            case 1:
                currentPlayer = new Player("Rocky");
                break;
            case 2:
                currentPlayer = new Player("Papena");
                break;
            case 3:
                currentPlayer = new Player("Scilla");
                break;
            case 4:
                break;
            default:
                System.out.println("Där bidde det fel, försök igen");
                playerSelectionMenu();
        }
        allPlayers.add(currentPlayer);
        mainMenu();
    }

    public void createNewOpponent(){
        this.currentOpponent = new Opponent();
    }

    public void gameOver(){
        System.out.println("GAME OVER");
    }

    public void mainMenu(){

        System.out.println("================\n    MainMenu\n================\n1.  New Game\n2.  Choose player\n3.  Match histoy\n4.  Quit");


        int menuChoice=0;

        try {
            menuChoice = Integer.parseInt(console.nextLine());
        }
        catch(Exception e){
        }

        switch(menuChoice){
            case 1:
                if(currentPlayer == null){ // Sätta den här nån annanstans =?
                    createNewPlayer();
                    newMatch();
                }else{
                    newMatch();
                }
                break;
            case 2:
                playerSelectionMenu();
                break;
            case 3:
                matchHistoryMenu();
                break;
            case 4:
                gameOver();
                break;
            default:
                System.out.println("Där bidde det fel, försök igen");
                mainMenu();
        }
    }

    public void playerChoiceMenu(){
        System.out.println("================\n"+ currentPlayer.getName() +"'s Choice\n================\n1.  Rock\n2.  Paper\n3.  Scissors");
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
        System.out.println("================\n     " + currentMatch.getResult()+ "\n================\n1.  Main Menu\n2.  New Match\n3.  Quit");
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

    public void matchHistoryMenu(){

        if(currentPlayer == null){

        }else{
            int i =0;
            for(Player p : allPlayers){
                System.out.println("Namn: "+p.getName());
                for(Match m : allPlayers.get(i).getMatchHistory()){
                    System.out.println("<Match id> [" + m.getMatchId() + "] : " + m.getResult());
                }
                System.out.println("----------------");
                i++;
            }

//            for(Match a : currentPlayer.getMatchHistory()){
//                System.out.println("<Match id> [" + a.getMatchId() + "] : " + a.getResult());
//            }
//            System.out.println("================\n     " + currentMatch.getResult()+ "\n================\n");
        }

        System.out.println("1.  Main Menu\n2.  Quit");
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
                gameOver();
                break;
            default:
                System.out.println("Där bidde det fel, försök igen");
                afterMatchMenu();
        }

    }



}
