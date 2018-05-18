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

        moveRight(myMap);

        myMap.printMap();
    }


    public static void main(String[] args) {

        MazeRunner mazeRunner = new MazeRunner();

    }

    public void whereTo (){
        System.out.print("Where do you want to go today? \t");
        char onward = scanny.next().charAt(0);

        while (onward != 'U' && onward !='D' && onward != 'L' && onward != 'R'){
            System.out.println("That direction if not legal, please try again! \t");
            onward = scanny.next().charAt(0);
        }

        if (onward=='U');  //todo conmtinue
    }


    public static void moveRight(Maze myMap){
        boolean mayI = myMap.canIMoveRight();
        if (mayI==true){
            myMap.moveRight();
        }else {
            System.out.println("Sorry, you cannot go through. There is a hugeass wall blocking your way.");
        }


    }

}
