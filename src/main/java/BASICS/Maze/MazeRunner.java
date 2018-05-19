package BASICS.Maze;

import java.util.Scanner;

/**
 * @author Ja on 17/05/2018.
 * @project EDX,
 *
 * Maze.java has been provided by EDX.
 */
public class MazeRunner {

    private int counter =100;

    private Scanner scanny = new Scanner(System.in);

    private MazeRunner(){


        Maze myMap = new Maze();

        myMap.printMap();

        intro();

        whereTo(myMap);


    }


    public static void main(String[] args) {

        MazeRunner mazeRunner = new MazeRunner();

    }

    //solved by manual recusion :D
    private void whereTo (Maze myMap){
        System.out.print("Where do you want to go today? \t");
        char onward = scanny.next().charAt(0);

        while (onward != 'U' && onward !='D' && onward != 'L' && onward != 'R'){
            System.out.println("That direction if not legal, please try again! \t");
            onward = scanny.next().charAt(0);
        }

        if (myMap.didIWin()){
            System.out.println("WooHoo You WIN!");
            System.exit(0);
        }

        warnings(counter);

        if (onward=='U') {
            moveUp(myMap);
            myMap.printMap();
            whereTo(myMap);
        }
        if (onward=='D') {
            moveDown(myMap);
            myMap.printMap();
            whereTo(myMap);
        }
        if (onward=='L') {
            moveLeft(myMap);
            myMap.printMap();
            whereTo(myMap);
        }
        if (onward=='R') {
            moveRight(myMap);
            myMap.printMap();
            whereTo(myMap);
        }
    }

    // 4 movement methods

    private void moveUp(Maze myMap){
        boolean mayI = myMap.canIMoveUp();
        boolean pit = myMap.isThereAPit("U");

        if (pit){
            navigatePit(myMap, "U");
        }
        if (mayI){
            myMap.moveUp();
            counter--;
        }
        else wall();
    }

    private void moveDown(Maze myMap){
        boolean mayI = myMap.canIMoveDown();
        boolean pit = myMap.isThereAPit("D");

        if (pit){
            navigatePit(myMap, "D");
        }
        if (mayI){
            myMap.moveDown();
            counter--;
        }
        else wall();
    }

    private void moveLeft(Maze myMap){
        boolean mayI = myMap.canIMoveLeft();
        boolean pit = myMap.isThereAPit("L");

        if (pit){
            navigatePit(myMap, "L");
        }
        if (mayI){
            myMap.moveLeft();
            counter--;
        }
        else wall();
    }

    private void moveRight(Maze myMap){
        boolean mayI = myMap.canIMoveRight();
        boolean pit = myMap.isThereAPit("R");

        if (pit){
            navigatePit(myMap, "R");
        }
        if (mayI){
            myMap.moveRight();
            counter--;
        }
        else wall();
    }

    private void wall(){
        System.out.println("YOU SHALL NOT PASS! (Wall ahead, sorry)");
    }

    private void navigatePit(Maze myMap, String direction){
        System.out.println("A PIT! What to do? JUMP JUMP, Kris Kross will make ya! Jump? Y for Yes, N for No or M for Maybe?");
        char choice = scanny.next().charAt(0);
        while (choice!= 'Y' && choice!= 'N'){
            System.out.println("Just kidding, maybe is not an option. Y for Yes and N for No, please?");
            choice=scanny.next().charAt(0);
        }
        if (choice=='Y') {
            System.out.println("YAY! You live to fight another day! Kris Kross would be proud!");
            myMap.jumpOverPit(direction);
        }
        else{
            System.out.println("SPLAT!");
            System.exit(0);
        }
    }

    private void warnings(int counter){
        if (counter==50){
            System.out.println("Warning: You have made 50 moves, you have "+counter+" moves remaining before the maze exit closes.");
        }
        if (counter==25){
            System.out.println("Alert! You have made 75 moves, you only have "+counter+" moves to escape.");
        }
        if (counter==10){
            System.out.println("DANGER! You have made 90 moves, you only have "+counter+" moves left to escape!");
        }
        if (counter==0){
            System.out.println("Oh no! You took too long to escape and now the maze exit is closed FOREVER >:[");
            System.out.println("Sorry, but you didn't escape in time- you lose!");
            System.exit(0);
        }
    }

    private void intro(){
        System.out.println("Welcome to the Maze Runner game!");
    }
}
