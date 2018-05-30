package MS_OOP.BattleShips;

import java.util.*;

/**
 * @author Ja on 20/05/2018.
 * @project EDX,
 * This project is one listing due to EDX requirements (You paste projects into a forum post).
 */
/*
* Please keep in mind that since in a cartesian coordinates system x is horizontal and y is vertical and in a 2D array
* i is vertical and j is horizontal. Therefore every time user is presented with coordinates they are switched.
* */
//todo check out if this approach makes sense, playing whole thing, what i mean if whether random turning those won't have a problem


class Main{



    Main(int numberOfPlayerShips, int numberofAIShips) {

        //todo this is only needed for final, remove it as soon as full game is ready
        ViewMethods viewMethods = new ViewMethods();

        Intro intro = new Intro();

        OceanMapPlayer playerVisibleOceanMap = new OceanMapPlayer();
        
        SetUpPlayerShips setUpPlayerShips = new SetUpPlayerShips(numberOfPlayerShips, playerVisibleOceanMap.getOceanMapPlayer());

        OceanMapMain mainOceanMap = new OceanMapMain(playerVisibleOceanMap);

        SetUpAIShips setUpAIShips = new SetUpAIShips(numberofAIShips, mainOceanMap.getOceanMapMain());

        GameItself gameItself = new GameItself(playerVisibleOceanMap, mainOceanMap, numberOfPlayerShips, numberofAIShips);



        /*System.out.println("FINAL--------------------ALDJFIOHJFWOIEJFIOPEWJFIOJIO#WEJOIEWJFIOEW:");
        viewMethods.viewMapAsList(mainOceanMap);
        System.out.println("\nmain\nplayer visible\n");
        viewMethods.viewMapAsList(playerVisibleOceanMap);*/

    }
}





class CommonMethods{

    public CommonMethods() {
    }

    int scannyOfInt(){
        Scanner scanny = new Scanner(System.in);
        int scanned = scanny.nextInt();
        scanny.skip("\\s|[A-Z]|[a-z]|\\`|\\-|\\=|\\[|\\]|\\\\|\\;|\\'|\\,|\\.|\\/|\\*|\\+]");
        return scanned;
    }
    //This method switches x and y to make it cartessian. todo check if it's right, remember AI must have same transition and shots must have it too
    char[][] addShip(int x, int y, char[][] map, char shipSymbol){
        map[y][x]=shipSymbol;
        return map;
    }

    //This method is used for AI
    public char [] mapTo1D (char[][] input){

        char [] output = new char[input.length*input[0].length];

        for (int i=0; i<input.length;i++) {
            for (int j = 0; j < input[0].length; j++) {
                output[j+i*input[0].length] = input[i][j];

            }
        }return output;
    }
}





class ViewMethods{

    public ViewMethods() {
    }

    //overloaded mthod, takes map from object
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
        for (int i=0; i<objectMap.length;i++) {
            lists.add(Arrays.asList(objectMap[i]));
        }

        //left and right
        for (int i=0; i<lists.size(); i++) {
            List temp = new ArrayList(lists.get(i));
            temp.add(i);
            temp.add(0, i);
            lists.set(i, temp);
        }

        //top and bottom
        int [] mapLength = new int[map.length];

        for (int i=0; i<mapLength.length; i++){
            mapLength[i]=i;
        }

        List <Object> mapLengthList = new ArrayList();

        for (int i : mapLength){
            mapLengthList.add(i);
        }

        lists.add(0, mapLengthList);
        lists.add(mapLengthList);

        for (int i=0;i<lists.size();i+=11) {
            List temp = new ArrayList(lists.get(i));
            temp.add(" ");
            temp.add(0, " ");
            lists.set(i, temp);
        }

        // displaying the ocean map
        for (List<Object> r : lists){
            System.out.println(r);
        }
    }

    //overloaded method, useful in testing
    void viewMapAsList(char [][] map){

        List<List<Object>> lists =new ArrayList<>();
        Character [][] objectMap = new Character[map.length][map[0].length];

        //boxing to object contructing 'lists'
        for (int i=0; i<map.length;i++){
            for (int j=0; j<map[0].length;j++){
                objectMap [i][j] = map[i][j];
            }
        }
        for (int i=0; i<objectMap.length;i++) {
            lists.add(Arrays.asList(objectMap[i]));
        }

        //left and right
        for (int i=0; i<lists.size(); i++) {
            List temp = new ArrayList(lists.get(i));
            temp.add(i);
            temp.add(0, i);
            lists.set(i, temp);
        }

        //top and bottom
        int [] mapLength = new int[map.length];

        for (int i=0; i<mapLength.length; i++){
            mapLength[i]=i;
        }

        List <Object> mapLengthList = new ArrayList();

        for (int i : mapLength){
            mapLengthList.add(i);
        }

        lists.add(0, mapLengthList);
        lists.add(mapLengthList);

        for (int i=0;i<lists.size();i+=11) {
            List temp = new ArrayList(lists.get(i));
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
    Intro(){
        System.out.println("Welcome to the Game!");
    }
}





class OceanMap {
    char oceanMap[][] = new char[10][10];

    OceanMap() {

        for (int i = 0; i < oceanMap.length; i++) {
            Arrays.fill(oceanMap[i], ' ');
        }
    }

    public char[][] getOceanMap() {
        return oceanMap;
    }

}

class OceanMapPlayer extends OceanMap{
    char[][] oceanMapPlayer;

    public OceanMapPlayer() {
        oceanMapPlayer=oceanMap;
    }

    public char[][] getOceanMapPlayer() {
        return oceanMapPlayer;
    }
}

class OceanMapMain extends OceanMap{
    char[][] oceanMapMain;

    public OceanMapMain(OceanMapPlayer oceanMapPLayer) {
        oceanMapMain=oceanMap;

        for (int i=0; i<oceanMapMain.length;i++){
            for (int j=0; j<oceanMapMain[0].length;j++){
                oceanMapMain[i][j]=oceanMapPLayer.getOceanMapPlayer()[i][j];
            }
        }
    }
    public char[][] getOceanMapMain() {
        return oceanMapMain;
    }
}





class SetUpPlayerShips {
    ViewMethods viewMethods = new ViewMethods();
    CommonMethods commonMethods = new CommonMethods();

    SetUpPlayerShips(int numberOfPlayerShips, char [][] oceanMapPlayerDeploying) {

        System.out.println("Place your ships on the map using cartesian coordinate system rules. 'x' for horizontal and 'y' for vertical.");

        int counterOfPlayerSHipDeployment = numberOfPlayerShips;

        while (counterOfPlayerSHipDeployment > 0) {


            System.out.print("Please enter 'x': \t");
            int x = commonMethods.scannyOfInt();
            System.out.print("Please enter 'y': \t");
            int y = commonMethods.scannyOfInt();

            KeeperOfCoordinatesInRange keeperOfCoordinatesInRange0 = new KeeperOfCoordinatesInRange(x, y);

            x = keeperOfCoordinatesInRange0.getX();
            y = keeperOfCoordinatesInRange0.getY();

            if (oceanMapPlayerDeploying[x][y] == '@') {

                System.out.println("Sadly you are trying to place a ship in area where there already is one. Please try again.");

                System.out.print("Please enter x, again: \t");
                x = commonMethods.scannyOfInt();
                System.out.print("Please enter y, again: \t");
                y = commonMethods.scannyOfInt();

                KeeperOfCoordinatesInRange keeperOfCoordinatesInRange1 = new KeeperOfCoordinatesInRange(x, y);

                x = keeperOfCoordinatesInRange1.getX();
                y = keeperOfCoordinatesInRange1.getY();

                while (oceanMapPlayerDeploying[x][y] == '@') {
                    System.out.println("Right, yet again you are trying to place your ship on another one of your ships. Sorry, no double-deckers.");
                    System.out.print("Yet again, please enter x: \t");
                    x = commonMethods.scannyOfInt();
                    System.out.print("Yet again, please enter y: \t");
                    y = commonMethods.scannyOfInt();

                    KeeperOfCoordinatesInRange keeperOfCoordinatesInRange2 = new KeeperOfCoordinatesInRange(x, y);

                    x = keeperOfCoordinatesInRange2.getX();
                    y = keeperOfCoordinatesInRange2.getY();
                }
            }

            commonMethods.addShip(x, y, oceanMapPlayerDeploying, '@');

            counterOfPlayerSHipDeployment--;
            viewMethods.viewMapAsList(oceanMapPlayerDeploying);
            System.out.println("Number of ships we can deploy: " + counterOfPlayerSHipDeployment);
        }
    }
}




//todo keeper doesnt have the entry turned CARTESIAN
class KeeperOfCoordinatesInRange {

    CommonMethods commonMethods = new CommonMethods();

    int x;
    int y;

    KeeperOfCoordinatesInRange(int x, int y){
        this.x=x;
        this.y=y;

        while (!coordinateInRange(x) || !coordinateInRange(y)) {
            System.out.println("It seems that your coordinates were out of range, please try again.");
            System.out.print("Please enter'x': \t");
            x = commonMethods.scannyOfInt();
            System.out.print("Please enter 'y': \t");
            y = commonMethods.scannyOfInt();

            this.x=x;
            this.y=y;
        }
    }
    private boolean coordinateInRange(int coordinate){
        if (coordinate>=0 && coordinate<=9) return true;
        else return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}





class SetUpAIShips{
    ViewMethods viewMethods = new ViewMethods();
    CommonMethods commonMethods = new CommonMethods();

    char [][] oceanMap;

    SetUpAIShips(int numberOfAIShips, char [][] oceanMap){
        this.oceanMap=oceanMap;

        List <Integer> rangeForPlacingAIShips = new ArrayList<>(Arrays.asList(markingValuesWithInt1D(oceanMap)));

        List <Integer> shipsAlreadyPlacedByPlayerList = excludeFromRange('@', commonMethods.mapTo1D(oceanMap));

        for (Integer i : shipsAlreadyPlacedByPlayerList){
            rangeForPlacingAIShips.remove(i);
        }

        for (int i=0; i<numberOfAIShips; i++) {
            Integer x = generateRandomIntFromIntList(rangeForPlacingAIShips);
            rangeForPlacingAIShips.remove(x);
            //keep in mind addShip method has x and y switched to make it cartesian! For further info check common methods.
            //'#' is the symbol of AI ships
            commonMethods.addShip(x % 10, x / 10, oceanMap, '#');
        }
    }

    public Integer [] markingValuesWithInt1D (char[][] input){

        Integer [] output = new Integer[input.length*input[0].length];

        for (int i=0; i<output.length; i++){
            output[i]=i;
        }return output;
    }

    public List <Integer> excludeFromRange(char valueToExclude, char [] map1D){

        List<Integer> output = new ArrayList<>();

        for (int i=0; i<map1D.length; i++){
            if (map1D[i]==valueToExclude){
                output.add(i);
            }
        }return output;
    }

    public int generateRandomIntFromIntList(List <Integer> input){
        Random random = new Random(new Date().getTime());

        return input.get(random.nextInt(input.size()));
    }
}





class GameItself{
    CommonMethods commonMethods = new CommonMethods();
    ViewMethods viewMethods = new ViewMethods();

    OceanMap playerVisibleMap;
    OceanMap mainMap;
    int numberOfPlayerShips;
    int numberOfAIShips;


    List<Character> range;

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

        //todo dummy
        System.out.println(range);

        if (playerShips==0){
            System.out.println("AI won");
        }
        if (AIShips==0){
            System.out.println("Player won");
        }else {
            System.out.println("player turn");
            playerTurn();
            System.out.println("AI turn");
            AITurn();
            theGame();
        }
    }

    private void playerTurn(){
        //todo casrtesize
        //todo BLOODY RANGE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        //todo major issue here with cartesius, too tired for this :(
        System.out.print("enter x: ");
        int x=commonMethods.scannyOfInt();
        System.out.print("enter y: ");
        int y=commonMethods.scannyOfInt();

        KeeperOfCoordinatesInRange keeperOfCoordinatesInRange = new KeeperOfCoordinatesInRange(x, y);

        x=keeperOfCoordinatesInRange.getX();
        y=keeperOfCoordinatesInRange.getY();

        if (mainMap.getOceanMap()[x][y]=='#'){
            System.out.println("Player HIT");
            playerVisibleMap.getOceanMap()[x][y]='!';
            viewMethods.viewMapAsList(playerVisibleMap);
            numberOfAIShips--;
            System.out.println("ai ships left "+numberOfAIShips);
        }
        if (mainMap.getOceanMap()[x][y]==' '){
            System.out.println("Player miss");
            playerVisibleMap.getOceanMap()[x][y]='-';
            viewMethods.viewMapAsList(playerVisibleMap);
        }
        if (mainMap.getOceanMap()[x][y]=='@'){
            System.out.println("Players own hit");
            playerVisibleMap.getOceanMap()[x][y]='x';
            viewMethods.viewMapAsList(playerVisibleMap);
            numberOfPlayerShips--;
            System.out.println("player ships left "+numberOfPlayerShips);
        }
    }

    private void AITurn(){
        Random random = new Random(new Date().getTime());

        int xy = random.nextInt(range.size());

        System.out.println(xy);

        int x = xy/10;
        int y = xy%10;

        System.out.println(x+" y  x"+y);  //todo cartesized DONE

        if (mainMap.getOceanMap()[x][y]=='@'){
            System.out.println("AI HIT");
            playerVisibleMap.getOceanMap()[x][y]='x';
            viewMethods.viewMapAsList(playerVisibleMap);
            numberOfPlayerShips--;
            System.out.println("player ships left "+numberOfPlayerShips);
            range.remove(xy);
        }
        if (mainMap.getOceanMap()[x][y]==' '){
            System.out.println("AI MISS at x:"+y+"y:"+x);  //todo cartesized
            viewMethods.viewMapAsList(playerVisibleMap);
            range.remove(xy);
        }
        if (mainMap.getOceanMap()[x][y]=='#'){
            System.out.println("AI SELF HIT!");
            playerVisibleMap.getOceanMap()[x][y]='!';
            viewMethods.viewMapAsList(playerVisibleMap);
            numberOfAIShips--;
            System.out.println("AI ships left "+numberOfAIShips);
            range.remove(xy);
        }
    }
}





public class Battleships {
    public static void main(String[] args) {
        Main main = new Main(5, 5);
    }
}
