package BASICS.Maze;

import java.util.Scanner;

/**
 * @author Ja on 17/05/2018.
 * @project EDX,
 */
public class MazeRunner {

    Scanner scanny = new Scanner(System.in);

    MazeRunner(){


        Maze myMap = new Maze();

        myMap.printMap();

        //intro()

        //whereTo(myMap);

        myMap.moveRight();
        myMap.printMap();
        System.out.println(myMap.canIMoveRight());
        System.out.println(myMap.isThereAPit("R"));
        myMap.printMap();
    }


    public static void main(String[] args) {

        MazeRunner mazeRunner = new MazeRunner();

    }

    public void whereTo (Maze myMap){
        System.out.print("Where do you want to go today? \t");
        char onward = scanny.next().charAt(0);

        while (onward != 'U' && onward !='D' && onward != 'L' && onward != 'R'){
            System.out.println("That direction if not legal, please try again! \t");
            onward = scanny.next().charAt(0);
        }

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

    //movement

    public static void moveUp(Maze myMap){
        boolean mayI = myMap.canIMoveUp();
        if (mayI==true){
            myMap.moveUp();
        }else {
            System.out.println("Sorry, you cannot go through. There is a huge ass wall blocking your way.");
        }
    }

    public static void moveDown(Maze myMap){
        boolean mayI = myMap.canIMoveDown();
        if (mayI==true){
            myMap.moveDown();
        }else {
            System.out.println("Sorry, you cannot go through. There is a huge ass wall blocking your way.");
        }
    }

    public static void moveLeft(Maze myMap){
        boolean mayI = myMap.canIMoveLeft();
        if (mayI==true){
            myMap.moveLeft();
        }else {
            System.out.println("Sorry, you cannot go through. There is a huge ass wall blocking your way.");
        }
    }

    public static void moveRight(Maze myMap){
        boolean mayI = myMap.canIMoveRight();
        if (mayI==true){
            myMap.moveRight();
        }else {
            System.out.println("Sorry, you cannot go through. There is a huge ass wall blocking your way.");
        }
    }
}
