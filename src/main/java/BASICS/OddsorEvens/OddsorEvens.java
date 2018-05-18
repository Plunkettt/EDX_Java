package BASICS.OddsorEvens;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Ja on 11/05/2018.
 * @project EDX,
 */


class Main{
    Greeter greeter = new Greeter();
    int playerHand = greeter.getFingersOfPlayer();
    String playerNickname = greeter.getNickname();
    char playerSide = greeter.getSide();


    RandomAIMachineOMG randomAIMachineOMG = new RandomAIMachineOMG();
    int aiHand = randomAIMachineOMG.getFinalAIHand();

    //Constructor below takes in order: Player nickname, Player Odds or Evens, Player amount of fingers, AI amount of fingers.
    BigShowdownInLittleChina bigShowdownInLittleChina = new BigShowdownInLittleChina(playerNickname, playerSide, playerHand, aiHand);

    Main() throws InterruptedException {
    }
}

class Greeter extends Scanny{

    String nickname;
    char side;
    int fingersOfPlayer=0;

    Greeter(){
        System.out.println("Let's play a round of Odds or Evens, shall we?!");
        System.out.print("Please enter the nickname you go by:   ");
        nickname=acquire();
        System.out.print("Now please pick either Odds or Evens by typing in O or E:   ");
        side=acquire().charAt(0);

        while (side != 'O' && side !='E'){
            System.out.print("Wrong character, DUH! Try again please, O for Odds and E for Evens:   ");
            side=acquire().charAt(0);
        }
        System.out.println(nickname+" you are a proud warrior who has chosen the path of "+ sideExtender(side)+". The AI player will follow the path of "+sideExtenderAI(side)+".");
        System.out.println("\nd------------------------------------------------b\n");
        System.out.print("Now only one thing remains. Please reveal to US with how many fingers do you intend to come forth?");
        fingersOfPlayer=Integer.parseInt(acquire());
        while (fingersOfPlayer<0 || fingersOfPlayer>5){
            System.out.println("Legendary warrior, surely you must be able to put forth from 0 to 5 fingers, not more not less.");
            fingersOfPlayer=Integer.parseInt(acquire());
        }
    }

    public String sideExtender(char size){
        if (size == 'E')return "Evens";
        if (size == 'O') return "Odds";
        else return "error?";
    }

    public String sideExtenderAI (char size){
        if (size == 'E')return "Odds";
        if (size == 'O')return "Evens";
        else return "error!";
    }

    public String getNickname() {
        return nickname;
    }

    public char getSide() {
        return side;
    }

    public int getFingersOfPlayer() {
        return fingersOfPlayer;
    }
}

class FingersOfAI{
    int fingers;

    @Override
    public String toString() {
        return String.valueOf(fingers);
    }

    FingersOfAI(){
        Random random = new Random(new Date().getTime());
        fingers=random.nextInt(6);
    }

    public int getFingers() {
        return fingers;
    }
}

class RandomAIMachineOMG {
    int finalAIHand = 0;

    RandomAIMachineOMG() throws InterruptedException {
        System.out.println("The superb AI Random Machine Of DOOM (AIRMOD) starts turning it's gears and sprockets, drawing 3 possible AI hands!!!");
        System.out.println("------------drumdoll------------");
        TimeUnit.SECONDS.sleep(3);
        FingersOfAI fingers1=new FingersOfAI();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("First is: "+fingers1);
        FingersOfAI fingers2=new FingersOfAI();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Second is: "+fingers2);
        FingersOfAI fingers3=new FingersOfAI();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("And the final one is: "+fingers3);

        Random random = new Random(new Date().getTime());


        switch (random.nextInt(3)){
            default:
            case 0: finalAIHand=fingers1.getFingers();break;
            case 1: finalAIHand=fingers2.getFingers();break;
            case 2: finalAIHand=fingers3.getFingers();break;
        }

        System.out.println("------------drumdoll------------");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("(AIRMOD) has finished it's effort, the ultimate AI hand is: "+finalAIHand);
        System.out.println("\nd------------------------------------------------b\n");
    }

    public int getFinalAIHand() {
        return finalAIHand;
    }
}

class BigShowdownInLittleChina {


    BigShowdownInLittleChina(String nickname, char playerSide, int playerHand, int aiHand) throws InterruptedException {

        int sum = playerHand+aiHand;

        System.out.println("Our channelnger "+nickname+" has chosen "+whatSideText(playerSide)+" and has put forth "+playerHand+" fingers.");
        System.out.println("\nThe time has come..");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("\n\t"+playerHand+" + "+aiHand+" = "+sum+". Without doubt in anyones heart this number is an "+oddEvenText(sum)+" number.\n");
        System.out.println("And it must follow, as the night the day, that it is "+whoWonText(nickname, playerSide, sum)+" who was victorious today!");



    }

    public String oddEvenText(int summed){
        if (summed%2==0) return "even";
        else return "odd";
    }

    public String whoWonText(String name, char side, int summed){
        String ai = "AI Player";

        if ((side=='E') && (summed%2==0)) return name;
        if ((side=='O') && (summed%2!=0)) return name;
        else return ai;
    }

    public String whatSideText(char side){
        if (side=='O') return "Odds";
        else return "Evens";
    }
}

class Scanny{

    public String acquire (){
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        return str;
    }

}

public class OddsorEvens {
    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
    }
}
