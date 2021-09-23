package com.rockpaperscissors;


// Döp om till nåt mer passande för att ha med menyer i classen ? typ program ? Döp om till game
// this på allt också
// Fixa score board så att inte oanvända spelare syns



// Skapa player selection och lagring

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Scanner console;
    private String playerChoice;
    //private Player currentPlayer;
    private Match currentMatch;
    private Player currentPlayer;
    private ArrayList<Player> allPlayers = new ArrayList<>();

    public Game(){
        this.console = new Scanner(System.in);
        randomGeneratedPlayers();
    }

    public void newMatch(){
        this.currentMatch = new Match("n/a");
        this.currentMatch.createNewOpponent();
        playerChoiceMenu();
        this.currentMatch.getCurrentOpponent().OpponentOutcome();
        System.out.println(this.currentPlayer.getName()+ ": "+this.playerChoice);
        System.out.println("Opponent : "+this.currentMatch.getCurrentOpponent().getOpponentOutcome());
        outcome();
        afterMatchMenu();
    }

    public void outcome(){
        if (this.playerChoice.equals(this.currentMatch.getCurrentOpponent().getOpponentOutcome())){
            this.currentMatch.setResult("Draw!");
        } else{
            switch(this.playerChoice){
                case "Rock":
                    if(this.currentMatch.getCurrentOpponent().getOpponentOutcome().equals("Scissors")){
                        this.currentMatch.setResult("Won!");
                    }else{
                        this.currentMatch.setResult("Lost!");
                    }
                    break;

                case "Paper":
                    if(this.currentMatch.getCurrentOpponent().getOpponentOutcome().equals("Rock")){
                        this.currentMatch.setResult("Won!");
                    }else{
                        this.currentMatch.setResult("Lost!");
                    }
                    break;

                case "Scissors":
                    if(this.currentMatch.getCurrentOpponent().getOpponentOutcome().equals("Paper")){
                        this.currentMatch.setResult("Won!");
                    }else{
                        this.currentMatch.setResult("Lost!");
                    }
                    break;
            }
        }
        this.currentPlayer.setMatchHistory(this.currentMatch);
    }

    public void createNewPlayer(){
        System.out.print("Name your character: ");
        String playerName = console.nextLine();
        if(playerName.length()> 25){
            System.out.println("Sorry, your name is to long, please enter a name of 25 characters or less");
            createNewPlayer();
        }else{
            allPlayers.add(new Player(playerName));
        }
    }

    /**
     * Generates 3 predetermined players with a random name from array.
     */
    public void randomGeneratedPlayers(){
        String[] cpuNames = {"Lisa","Riley","Keon","Uriel","Allan","Doyle","Veronica","Tiana","Aubree","Nathaniel","Robert","Abril","Sandra","Miranda","Fatima","Carter","Adam","Douglas","Taylor","Jonathan"};
        ArrayList<String> test = new ArrayList<>();
        for(int i = 0; i<3; i++){
            int randNr = (int) (Math.random() * cpuNames.length);
            this.allPlayers.add(new Player(cpuNames[randNr]));




        }
    }

    public void playerSelectionMenu(){  // Try catch för arraysen!
        menuDesign("Choose Player");
        int playerSelectCounter = 0;

        for(Player p : allPlayers){
            System.out.println((playerSelectCounter+1) + ".   " + p.getName());
            playerSelectCounter++;
        }
        System.out.println(playerSelectCounter+1 + ".   Create new character\n" +(playerSelectCounter+2) + ".   Return to main menu\n" + (playerSelectCounter+3) + ".   Delete player" );
        int menuChoice = tryCatchMenus();

        if(menuChoice == (allPlayers.size()+1)){

            createNewPlayer();

        }else if (menuChoice == (allPlayers.size()+2)){

            mainMenu();

        }else if(menuChoice == (allPlayers.size())+3){

            System.out.println("Which player to remove?");
            menuChoice = tryCatchMenus();

            if(menuChoice <= 0 || menuChoice > this.allPlayers.size()){
                System.out.println("Invalid input");

            }else{
                allPlayers.remove(menuChoice-1);
            }
            playerSelectionMenu();
        }
        if(menuChoice <= 0 || menuChoice > this.allPlayers.size()){
            System.out.println("Invalid input");

        }else{

            currentPlayer = this.allPlayers.get(menuChoice-1);
            System.out.println(currentPlayer.getName());

        }
        mainMenu();
    }

    public void gameOver(){
        System.out.println("GAME OVER");
    }

    public void menuDesign(String menu){
        String space = "";

        if (currentPlayer != null){
            for(int i = currentPlayer.getName().length(); i < (50-menu.length()); i++){
                space += " ";
            }
            System.out.println("──────────────────────────────────────────────────\n" + currentPlayer.getName()+ space + menu + "\n──────────────────────────────────────────────────");
           // space = "";
        }else{
            for(int i = 0; i <(50-menu.length()); i++){
                space += " ";
            }



            System.out.println("──────────────────────────────────────────────────\n"+ space + menu + "\n──────────────────────────────────────────────────");
            //space = "";
        }

    }

    public void mainMenu(){
        menuDesign("Main Menu");
        System.out.println("1.  New Game\n2.  Choose player\n3.  Match histoy\n4.  Quit");
        int menuChoice = tryCatchMenus();

        switch(menuChoice){
            case 1:
                if(currentPlayer == null){
                    System.out.println("Du har ingen spelare");
                    System.out.println("1.  Skapa ny spelare \n2. välj befintlig spelare\n3.  återgå till main menu");
                    menuChoice = tryCatchMenus();

                    switch (menuChoice){
                        case 1:
                            createNewPlayer();
                            break;
                        case 2:
                            playerSelectionMenu();
                            break;
                        case 3:
                            mainMenu();
                            break;
//                        default:
//                            System.out.println("Där bidde det fel!");
                    }
                    mainMenu();
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
//            default:
//                System.out.println("Där bidde det fel, försök igen");
//                mainMenu();
        }
    }

    public int tryCatchMenus(){
        int menuChoice = 0;

        try {
            menuChoice = Integer.parseInt(this.console.nextLine());
        }
        catch(Exception e){
        }
        return menuChoice;
    }

    public void playerChoiceMenu(){
        menuDesign("Game on");
        System.out.println("1.  Rock\n2.  Paper\n3.  Scissors");
        int menuChoice = tryCatchMenus();

        switch(menuChoice){
            case 1:
                this.playerChoice = "Rock";
                break;
            case 2:
                this.playerChoice = "Paper";
                break;
            case 3:
                this.playerChoice = "Scissors";
                break;
            default:
                System.out.println("Där bidde det fel, försök igen");
                playerChoiceMenu();
        }
    }

    public void afterMatchMenu(){
        System.out.println("================\n     " + this.currentMatch.getResult()+ "\n================\n1.  Main Menu\n2.  New Match\n3.  Quit");
        int menuChoice = tryCatchMenus();

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

        System.out.println("1   Show history for current player");
        System.out.println("2   Show history for all players");

//        switch(){
//
//        }

        if(this.currentPlayer == null){

        }else{
            int i =0;
            for(Player p : this.allPlayers){
                System.out.println("Namn: "+p.getName());
                for(Match m : this.allPlayers.get(i).getMatchHistory()){
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
        int menuChoice = tryCatchMenus();

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
