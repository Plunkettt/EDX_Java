package MS_OOP.BattleShips;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Ja on 20/05/2018.
 * @project EDX,
 * This project is one listing due to EDX requirements (projects are to be pasted into a forum post).
 */
/*
* Please keep in mind that since in a cartesian coordinates system x is horizontal and y is vertical and in a 2D array
* i is vertical and j is horizontal. Therefore every time user is presented with coordinates they are switched.
* */


class Main{



    Main(int numberOfPlayerShips, int numberofAIShips) {

        new Intro();

        OceanMapPlayer playerVisibleOceanMap = new OceanMapPlayer();
        
        new SetUpPlayerShips(numberOfPlayerShips, playerVisibleOceanMap.getOceanMapPlayer());

        OceanMapMain mainOceanMap = new OceanMapMain(playerVisibleOceanMap);

        new SetUpAIShips(numberofAIShips, mainOceanMap.getOceanMapMain());

        new GameItself(playerVisibleOceanMap, mainOceanMap, numberOfPlayerShips, numberofAIShips);

    }
}





class CommonMethods{

    CommonMethods() {
    }

    int scannyOfInt(){
        Scanner scanny = new Scanner(System.in);
        int scanned = scanny.nextInt();
        scanny.skip("\\s|[A-Z]|[a-z]|\\`|\\-|\\=|\\[|\\]|\\|\\;|\\'|\\,|\\.|\\/|\\*|\\+]");
        return scanned;
    }
    //This method switches x and y to make it cartessian. If you're lucky it might even add a ship ;)
    void addShip(int x, int y, char[][] map, char shipSymbol){
        map[y][x]=shipSymbol;
    }

    //This method is used for AI
    char [] mapTo1D (char[][] input){

        char [] output = new char[input.length*input[0].length];

        for (int i=0; i<input.length;i++) {
            for (int j = 0; j < input[0].length; j++) {
                output[j+i*input[0].length] = input[i][j];

            }
        }return output;
    }
}





class ViewMethods{

    ViewMethods() {
    }

    //overloaded mthod, map from Oceanmap object
    void viewMapAsList(OceanMap oceanMap){
        char[][] map = oceanMap.getOceanMap();

        List<List<Object>> lists =new ArrayList<>();
        Character [][] objectMap = new Character[map.length][map[0].length];

        //boxing to object contructing 'lists'
        for (int i=0; i<map.length;i++){
            for (int j=0; j<map[0].length;j++){
                objectMap [i][j] = map[i][j];
            }
        }

        for (Character [] c : objectMap){
            lists.add(Arrays.asList(c));
        }

        //left and right
        for (int i=0; i<lists.size(); i++) {
            List<Object> temp = new ArrayList<>(lists.get(i));
            temp.add(i);
            temp.add(0, i);
            lists.set(i, temp);
        }

        //top and bottom
        int [] mapLength = new int[map.length];

        for (int i=0; i<mapLength.length; i++){
            mapLength[i]=i;
        }

        List <Object> mapLengthList = new ArrayList<>();

        for (int i : mapLength){
            mapLengthList.add(i);
        }

        lists.add(0, mapLengthList);
        lists.add(mapLengthList);

        for (int i=0;i<lists.size();i+=11) {
            List <Object> temp = new ArrayList<>(lists.get(i));
            temp.add(" ");
            temp.add(0, " ");
            lists.set(i, temp);
        }

        // displaying the ocean map
        for (List<Object> r : lists){
            System.out.println(r);
        }
    }

    //overloaded method,  useful in testing
    void viewMapAsList(char [][] map){

        List<List<Object>> lists =new ArrayList<>();
        Character [][] objectMap = new Character[map.length][map[0].length];

        //boxing to object contructing 'lists'
        for (int i=0; i<map.length;i++){
            for (int j=0; j<map[0].length;j++){
                objectMap [i][j] = map[i][j];
            }
        }

        for (Character [] c : objectMap){
            lists.add(Arrays.asList(c));
        }

        //left and right
        for (int i=0; i<lists.size(); i++) {
            List <Object>temp = new ArrayList<>(lists.get(i));
            temp.add(i);
            temp.add(0, i);
            lists.set(i, temp);
        }

        //top and bottom
        int [] mapLength = new int[map.length];

        for (int i=0; i<mapLength.length; i++){
            mapLength[i]=i;
        }

        List <Object> mapLengthList = new ArrayList<>();

        for (int i : mapLength){
            mapLengthList.add(i);
        }

        lists.add(0, mapLengthList);
        lists.add(mapLengthList);

        for (int i=0;i<lists.size();i+=11) {
            List <Object> temp = new ArrayList<>(lists.get(i));
            temp.add(" ");
            temp.add(0, " ");
            lists.set(i, temp);
        }

        // displaying the ocean map
        for (List<Object> r : lists){
            System.out.println(r);
        }
    }

    void viewMapAsArray(char [][] map){
        System.out.print("  ");
        for (int i=0; i<map.length;i++){
            System.out.print(" "+i);
        }
        System.out.println();

        for (int i=0; i<map.length;i++){
            System.out.print(i+"|");
            for (int j=0; j<map[0].length;j++){
                System.out.print(" "+map[i][j]);
            }
            System.out.println("|"+i);
        }
        System.out.print("  ");
        for (int i=0; i<map.length;i++){
            System.out.print(" "+i);
        }
    }
}





class Intro{
    //Made using:   http://patorjk.com/software/taag/

    Intro(){

        System.out.println("");
        System.out.println("           .---.            ,--,                                ____                      ___                         ___      ,---,               ");
        System.out.println("          /. ./|          ,--.'|                              ,'  , `.                  ,--.'|_                     ,--.'|_  ,--.' |               ");
        System.out.println("      .--'.  ' ;          |  | :               ,---.       ,-+-,.' _ |                  |  | :,'   ,---.            |  | :,' |  |  :               ");
        System.out.println("     /__./ \\ : |          :  : '              '   ,'\\   ,-+-. ;   , ||                  :  : ' :  '   ,'\\           :  : ' : :  :  :               ");
        System.out.println(" .--'.  '   \\' .   ,---.  |  ' |      ,---.  /   /   | ,--.'|'   |  || ,---.          .;__,'  /  /   /   |        .;__,'  /  :  |  |,--.   ,---.   ");
        System.out.println("/___/ \\ |    ' '  /     \\ '  | |     /     \\.   ; ,. :|   |  ,', |  |,/     \\         |  |   |  .   ; ,. :        |  |   |   |  :  '   |  /     \\  ");
        System.out.println(";   \\  \\;      : /    /  ||  | :    /    / ''   | |: :|   | /  | |--'/    /  |        :__,'| :  '   | |: :        :__,'| :   |  |   /' : /    /  | ");
        System.out.println(" \\   ;  `      |.    ' / |'  : |__ .    ' / '   | .; :|   : |  | ,  .    ' / |          '  : |__'   | .; :          '  : |__ '  :  | | |.    ' / | ");
        System.out.println("  .   \\    .\\  ;'   ;   /||  | '.'|'   ; :__|   :    ||   : |  |/   '   ;   /|          |  | '.'|   :    |          |  | '.'||  |  ' | :'   ;   /| ");
        System.out.println("   \\   \\   ' \\ |'   |  / |;  :    ;'   | '.'|\\   \\  / |   | |`-'    '   |  / |          ;  :    ;\\   \\  /           ;  :    ;|  :  :_:,''   |  / | ");
        System.out.println("    :   '  |--\" |   :    ||  ,   / |   :    : `----'  |   ;/        |   :    |          |  ,   /  `----'            |  ,   / |  | ,'    |   :    | ");
        System.out.println("     \\   \\ ;     \\   \\  /  ---`-'   \\   \\  /          '---'          \\   \\  /            ---`-'                      ---`-'  `--''       \\   \\  /  ");
        System.out.println("      '---\"       `----'             `----'                           `----'                                                              `----'   ");
        System.out.println("                                 ,----,       ,----,   ,--,                                                                                        ");
        System.out.println("                               ,/   .`|     ,/   .`|,---.'|                                 ,--,        ,-.----.                                   ");
        System.out.println("    ,---,.    ,---,          ,`   .'  :   ,`   .'  :|   | :       ,---,.  .--.--.         ,--.'|   ,---,\\    /  \\    .--.--.                       ");
        System.out.println("  ,'  .'  \\  '  .' \\       ;    ;     / ;    ;     /:   : |     ,'  .' | /  /    '.    ,--,  | :,`--.' ||   :    \\  /  /    '.                     ");
        System.out.println(",---.' .' | /  ;    '.   .'___,/    ,'.'___,/    ,' |   ' :   ,---.'   ||  :  /`. / ,---.'|  : '|   :  :|   |  .\\ :|  :  /`. /                     ");
        System.out.println("|   |  |: |:  :       \\  |    :     | |    :     |  ;   ; '   |   |   .';  |  |--`  |   | : _' |:   |  '.   :  |: |;  |  |--`                      ");
        System.out.println(":   :  :  /:  |   /\\   \\ ;    |.';  ; ;    |.';  ;  '   | |__ :   :  |-,|  :  ;_    :   : |.'  ||   :  ||   |   \\ :|  :  ;_                        ");
        System.out.println(":   |    ; |  :  ' ;.   :`----'  |  | `----'  |  |  |   | :.'|:   |  ;/| \\  \\    `. |   ' '  ; :'   '  ;|   : .   / \\  \\    `.                     ");
        System.out.println("|   :     \\|  |  ;/  \\   \\   '   :  ;     '   :  ;  '   :    ;|   :   .'  `----.   \\'   |  .'. ||   |  |;   | |`-'   `----.   \\                    ");
        System.out.println("|   |   . |'  :  | \\  \\ ,'   |   |  '     |   |  '  |   |  ./ |   |  |-,  __ \\  \\  ||   | :  | ''   :  ;|   | ;      __ \\  \\  |                    ");
        System.out.println("'   :  '; ||  |  '  '--'     '   :  |     '   :  |  ;   : ;   '   :  ;/| /  /`--'  /'   : |  : ;|   |  ':   ' |     /  /`--'  /                    ");
        System.out.println("|   |  | ; |  :  :           ;   |.'      ;   |.'   |   ,/    |   |    \\'--'.     / |   | '  ,/ '   :  |:   : :    '--'.     /                     ");
        System.out.println("|   :   /  |  | ,'           '---'        '---'     '---'     |   :   .'  `--'---'  ;   : ;--'  ;   |.' |   | :      `--'---'                      ");
        System.out.println("|   | ,'   `--''                                              |   | ,'              |   ,/      '---'   `---'.|                                    ");
        System.out.println("`----'                                                        `----'                '---'                 `---`                                    ");
        System.out.println("");

    }
}





class Victory{

    Victory(){
        System.out.println("");
        System.out.println("        (                   )  (       )  ____ ");
        System.out.println("        )\\ )  (    *   ) ( /(  )\\ ) ( /( |   / ");
        System.out.println(" (   ( (()/(  )\\ ` )  /( )\\())(()/( )\\())|  /  ");
        System.out.println(" )\\  )\\ /(_)|((_) ( )(_)|(_)\\  /(_)|(_)\\ | /   ");
        System.out.println("((_)((_|_)) )\\___(_(_())  ((_)(_))__ ((_)|/    ");
        System.out.println("\\ \\ / /|_ _((/ __|_   _| / _ \\| _ \\ \\ / (      ");
        System.out.println(" \\ V /  | | | (__  | |  | (_) |   /\\ V /)\\     ");
        System.out.println("  \\_/  |___| \\___| |_|   \\___/|_|_\\ |_|((_)    ");
        System.out.println("");
    }
}





class OceanMap {
    char oceanMap[][] = new char[10][10];

    OceanMap() {

        for (char [] c : oceanMap){
            Arrays.fill(c, ' ');
        }
    }

    char[][] getOceanMap() {
        return oceanMap;
    }

}

class OceanMapPlayer extends OceanMap{
    private char[][] oceanMapPlayer;

    OceanMapPlayer() {
        oceanMapPlayer=oceanMap;
    }

    char[][] getOceanMapPlayer() {
        return oceanMapPlayer;
    }
}

class OceanMapMain extends OceanMap{
    private char[][] oceanMapMain;

    OceanMapMain(OceanMapPlayer oceanMapPLayer) {
        oceanMapMain=oceanMap;

        for (int i=0; i<oceanMapMain.length;i++){
            System.arraycopy(oceanMapPLayer.getOceanMapPlayer()[i], 0, oceanMapMain[i], 0, oceanMapMain[0].length);
        }
    }
    char[][] getOceanMapMain() {
        return oceanMapMain;
    }
}





class SetUpPlayerShips {


    SetUpPlayerShips(int numberOfPlayerShips, char [][] oceanMapPlayerDeploying) {

        ViewMethods viewMethods = new ViewMethods();
        CommonMethods commonMethods = new CommonMethods();

        System.out.println("Captain, place your ships on the map. Cartesian coordinate system rules apply, 'x' for horizontal and 'y' for vertical.");

        int counterOfPlayerSHipDeployment = numberOfPlayerShips;

        while (counterOfPlayerSHipDeployment > 0) {


            System.out.print("Enter horizontal value (x): \t");
            int x = commonMethods.scannyOfInt();
            System.out.print("Enter vertical value (y): \t");
            int y = commonMethods.scannyOfInt();

            KeeperOfCoordinatesInRange keeperOfCoordinatesInRange0 = new KeeperOfCoordinatesInRange(x, y);

            x = keeperOfCoordinatesInRange0.getX();
            y = keeperOfCoordinatesInRange0.getY();

            if (oceanMapPlayerDeploying[x][y] == '@') {

                System.out.println("Sadly you are trying to place a ship in area where there already is one. Please try again.");

                System.out.print("Enter horizontal value (x): \t");
                x = commonMethods.scannyOfInt();
                System.out.print("Enter vertical value (y): \t");
                y = commonMethods.scannyOfInt();

                KeeperOfCoordinatesInRange keeperOfCoordinatesInRange1 = new KeeperOfCoordinatesInRange(x, y);

                x = keeperOfCoordinatesInRange1.getX();
                y = keeperOfCoordinatesInRange1.getY();

                while (oceanMapPlayerDeploying[x][y] == '@') {
                    System.out.println("Right, yet again you are trying to place your ship on another one of your ships. Sorry, no double-deckers.");
                    System.out.print("Enter horizontal value (x): \t");
                    x = commonMethods.scannyOfInt();
                    System.out.print("Enter vertical value (y): \t");
                    y = commonMethods.scannyOfInt();

                    KeeperOfCoordinatesInRange keeperOfCoordinatesInRange2 = new KeeperOfCoordinatesInRange(x, y);

                    x = keeperOfCoordinatesInRange2.getX();
                    y = keeperOfCoordinatesInRange2.getY();
                }
            }

            commonMethods.addShip(x, y, oceanMapPlayerDeploying, '@');

            counterOfPlayerSHipDeployment--;
            viewMethods.viewMapAsList(oceanMapPlayerDeploying);
            System.out.println("Captain, we still can deploy: " + counterOfPlayerSHipDeployment+" ships.");
        }
    }
}





class KeeperOfCoordinatesInRange {

    private int x;
    private int y;

    KeeperOfCoordinatesInRange(int x, int y){
        CommonMethods commonMethods = new CommonMethods();

        this.x=x;
        this.y=y;

        while (!coordinateInRange(x) || !coordinateInRange(y)) {
            System.out.println("It seems that your coordinates were out of range, please try again.");
            System.out.print("Enter horizontal value (x): \t");
            x = commonMethods.scannyOfInt();
            System.out.print("Enter vertical value (y): \t");
            y = commonMethods.scannyOfInt();

            this.x=x;
            this.y=y;
        }
    }
    private boolean coordinateInRange(int coordinate){
        return  (coordinate>=0 && coordinate<=9);
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}





class SetUpAIShips{

    SetUpAIShips(int numberOfAIShips, char [][] oceanMap){
        CommonMethods commonMethods = new CommonMethods();

        List <Integer> rangeForPlacingAIShips = new ArrayList<>(Arrays.asList(markingValuesWithInt1D(oceanMap)));

        List <Integer> shipsAlreadyPlacedByPlayerList = findShipsLocation('@', commonMethods.mapTo1D(oceanMap));


        rangeForPlacingAIShips.removeAll(shipsAlreadyPlacedByPlayerList);


        for (int i=0; i<numberOfAIShips; i++) {
            Integer x = generateRandomIntFromIntList(rangeForPlacingAIShips);
            rangeForPlacingAIShips.remove(x);
            //keep in mind addShip method has x and y switched to make it cartesian! For further info check common methods.
            //'#' is the symbol of AI ships
            commonMethods.addShip(x % 10, x / 10, oceanMap, '#');
            System.out.println("Enemy ship number "+(i+1)+ " has been deployed!");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Integer [] markingValuesWithInt1D (char[][] input){

        Integer [] output = new Integer[input.length*input[0].length];

        for (int i=0; i<output.length; i++){
            output[i]=i;
        }return output;
    }

    private List <Integer> findShipsLocation(char shipMarkCharacter, char [] map1D){

        List<Integer> output = new ArrayList<>();

        for (int i=0; i<map1D.length; i++){
            if (map1D[i]==shipMarkCharacter){
                output.add(i);
            }
        }return output;
    }

    private int generateRandomIntFromIntList(List <Integer> input){
        Random random = new Random(new Date().getTime());

        return input.get(random.nextInt(input.size()));
    }
}





class GameItself{
    private CommonMethods commonMethods = new CommonMethods();
    private ViewMethods viewMethods = new ViewMethods();

    private OceanMap playerVisibleMap;
    private OceanMap mainMap;
    private int numberOfPlayerShips;
    private int numberOfAIShips;


    private List<Character> range;

    GameItself(OceanMap playerVisibleMap, OceanMap mainMap, int numberOfPlayerShips, int numberOfAISHips){
        this.playerVisibleMap=playerVisibleMap;
        this.mainMap=mainMap;
        this.numberOfPlayerShips=numberOfPlayerShips;
        this.numberOfAIShips=numberOfAISHips;

        range=charArrayToList(commonMethods.mapTo1D(mainMap.getOceanMap()));

        theGame();
    }

    private List<Character> charArrayToList (char [] input){
        List<Character> output = new ArrayList<>();

        for (char c : input){
            output.add(c);
        }
        return output;
    }

    private void theGame(){
        int playerShips = numberOfPlayerShips;
        int AIShips = numberOfAIShips;

        if (playerShips==0){
            System.out.println("\n Sadly we have been bested by your opponents. Better luck next time!");
        }
        if (AIShips==0){
            new Victory();
        }else {
            System.out.println("Your turn.");
            playerTurn();
            System.out.println("Opponents turn.");
            AITurn();
            theGame();
        }
    }

    private void playerTurn(){
        //This method had been adjusted for the cartesian way!
        System.out.println("Captain, please provide us with coordinates for our next salvo!");
        System.out.print("Enter horizontal value (x): \t");
        int x=commonMethods.scannyOfInt();
        System.out.print("Enter vertical value (y): \t");
        int y=commonMethods.scannyOfInt();

        KeeperOfCoordinatesInRange keeperOfCoordinatesInRange = new KeeperOfCoordinatesInRange(x, y);

        x=keeperOfCoordinatesInRange.getX();
        y=keeperOfCoordinatesInRange.getY();

        if (mainMap.getOceanMap()[y][x]=='#'){
            System.out.println("Captain, we have SUNK one of opponents ships!");
            playerVisibleMap.getOceanMap()[y][x]='!';
            viewMethods.viewMapAsList(playerVisibleMap);
            numberOfAIShips--;
            System.out.println("Our opponents have "+numberOfAIShips+" ships remaining.");
        }
        if (mainMap.getOceanMap()[y][x]==' '){
            System.out.println("Captain, we have missed our target.");
            playerVisibleMap.getOceanMap()[y][x]='-';
            viewMethods.viewMapAsList(playerVisibleMap);
        }
        if (mainMap.getOceanMap()[y][x]=='@'){
            System.out.println("Captain, we have sunk OUR OWN ship!");
            playerVisibleMap.getOceanMap()[y][x]='x';
            viewMethods.viewMapAsList(playerVisibleMap);
            numberOfPlayerShips--;
            System.out.println("Captain, we have only "+numberOfPlayerShips+" ships remaining.");
        }
    }

    private void AITurn(){
        Random random = new Random(new Date().getTime());

        int xy = random.nextInt(range.size());

        System.out.println(xy);

        int x = xy/10;
        int y = xy%10;

        System.out.println("Captain, our opponents shot at horizontal (x): "+y+", and vertical (y): "+x+".");

        if (mainMap.getOceanMap()[x][y]=='@'){
            System.out.println("Captain, our opponents have sunk one of our ships.");
            playerVisibleMap.getOceanMap()[x][y]='x';
            viewMethods.viewMapAsList(playerVisibleMap);
            numberOfPlayerShips--;
            System.out.println("Captain, we have only "+numberOfPlayerShips+" ships remaining.");
            range.remove(xy);
        }
        if (mainMap.getOceanMap()[x][y]==' '){
            System.out.println("Captain, our opponents have missed!");
            viewMethods.viewMapAsList(playerVisibleMap);
            range.remove(xy);
        }
        //Documentation clearly states that AI should be able to shoot own ships, hence it's possible.
        if (mainMap.getOceanMap()[x][y]=='#'){
            System.out.println("Captain, our opponents have sunk one of their own ships...");
            playerVisibleMap.getOceanMap()[x][y]='!';
            viewMethods.viewMapAsList(playerVisibleMap);
            numberOfAIShips--;
            System.out.println("Our opponents have "+numberOfAIShips+" ships remaining.");
            range.remove(xy);
        }
    }
}





public class Battleships {
    public static void main(String[] args) {
        new Main(5, 5);
    }
}
