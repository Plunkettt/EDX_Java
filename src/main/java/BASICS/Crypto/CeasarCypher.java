package BASICS.Crypto;

import java.util.Scanner;

/**
 * @author Ja on 14/05/2018.
 * @project EDX,
 */



class Main {
    Scanner scanny = new Scanner(System.in);

    Main() {
        System.out.println("Hello there. Rumour has it that you are interested in keeping something SECRET! You came to the right place.");
        System.out.print("\nIf you would like to use our Encoding services please enter E or if Decoding is what you need, then please enter D: \t");
        char choiceOfFunction = scanny.next().charAt(0);

        while (choiceOfFunction!= 'E' && choiceOfFunction!= 'D'){
            System.out.print ("WHOOPS! You have entered wrong character, please try again: \t");
            choiceOfFunction=scanny.next().charAt(0);
        }

        if (choiceOfFunction=='E'){
            System.out.println("\nWelcome to the encoding system. Please enter your SUPER-AMAZINGLY-SECRET message.");
            System.out.print("Right here -> \t");
            scanny.nextLine();
            String toBeEncoded = scanny.nextLine();

            AcquireAndNormnalize acquireAndNormnalize = new AcquireAndNormnalize(toBeEncoded);

            String acquired = acquireAndNormnalize.getProcesed();
            System.out.print("\nAfter preliminary processing we have determined that the length of your text is "+ acquired.length()+
                    ". We advise to group your message into chunks of following length: ");
            System.out.println(findBestDivider(acquired.length())+".");

            System.out.print("\nPlease choose the length of chunks you would like your encoded text to be divided into: \t");
            int gap = scanny.nextInt();

            System.out.print("\nNow please choose the shift value between 1 and 26, this number will become your private key. Please keep it in a safe place. \t");
            int shift = scanny.nextInt();

            Encode encode = new Encode(acquired, shift, gap);

            System.out.println("\nYour encoded text can be found below, please remember to save it. Also don't forget your key for decryption, which is: "+shift+".\n\n\n");

            System.out.println(encode.getEncoded());

        }

        else {
            scanny.nextLine();
            System.out.println("\nWelcome to the decoding system, please enter your encoded text below.");
            System.out.print("Right here -> \t");
            String toBeDecoded = scanny.nextLine();

            System.out.print("\nNow please enter your private key: \t");
            int shift =scanny.nextInt();

            Decode decode = new Decode( toBeDecoded, shift);

            System.out.println("\nYour decoded text is displayed below. Thank you for using our encryption system!\n\n\n");
            System.out.println(decode.getDecoded().toString());
        }
    }

    protected String findBestDivider (int length){
        if (length%4==0) return "4";
        if (length%5==0) return "5";
        if (length%3==0) return "3";
        else return "Unfortunately your string doesn't divide evenly by 4, 5 nor 3. In this case we the standard chunk size of 4.";
    }
}

class AcquireAndNormnalize{

    String procesed;

    AcquireAndNormnalize(String text){
        String normalized = normalizeText(text);
        procesed = obify(normalized);

    }

    private String normalizeText(String str){
        String out = str.replaceAll("\\s|\\.|,|:|;|\"|\\!|\\?|\\(|\\)", "");
        String done =out.toUpperCase();
        return done;
    }

    private String obify (String str){
        StringBuilder outSB = new StringBuilder(str);
        String ob = "OB";
        char [] triggers = {'A', 'E', 'I', 'U', 'O', 'Y'};

        for (int i=0; i<outSB.length(); i++){
            for (int j=0; j<triggers.length; j++){

                if (outSB.charAt(i)==triggers[j]){
                    outSB.insert(i, ob);
                    i+=2;
                }
            }
        }

        return outSB.toString();
    }

    protected String getProcesed() {
        return procesed;
    }
}

class Cipher{

    Cipher(){

    }

    protected String normalAlphabet (){
        String out ="";

        for (char i = 'A'; out.length()<26; i++){
            out=out+ i;
        }
        return out;
    }

    //Method provided by EDX, method wraps alphabet by a set amount
    protected String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }

    protected String groupify(String str, int gap){
        StringBuilder outSB = new StringBuilder(str);

        for (int i=gap; i<outSB.length(); i+=gap){
          outSB.insert(i, " ");
          i++;
        }
        return outSB.toString();
    }

    protected String ungroupify(String inDireNeedOfSpaceRemovalForDecyphering){
        String out = inDireNeedOfSpaceRemovalForDecyphering.replaceAll("\\s", "");
        return out;
    }

    //TODO THIS SHOIULD BE SOLVED WITH NESTED RECURSION. IF OB HAS BEEN FOUND THEN THERE SHOULD BE ANOTHER RECURSIVE CALL INSIDE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    protected String unobify(String str){

        if (str.length()<2)return str.substring(0,1)+"";

        if (str.substring(0, 2).equals("OB"))return unobify(str.substring(2));

        else return str.charAt(0)+unobify(str.substring(1));
    }
}

class Encode extends Cipher{  //this class is referred to as ceasarify in docs

    String encoded ="";

    Encode(String forEncoding, int shift, int gap){
        String alphabet = normalAlphabet();
        String ceasarAlphabet = shiftAlphabet(shift);
        String encoding = "";

        for (int i=0; i<forEncoding.length();i++){
            char one = forEncoding.charAt(i);
            int index = alphabet.indexOf(one);
            char two = ceasarAlphabet.charAt(index);
            encoding=encoding+two;
        }
        encoded=groupify(encoding, gap);
    }

    public String getEncoded() {
        return encoded;
    }
}

class Decode extends Cipher {  //and this is unceasarify

    String decoded = "";

    Decode(String forDecoding, int shift){
        String withoutSpaces = ungroupify(forDecoding);

        String alphabet = normalAlphabet();
        String ceasarAlphabet = shiftAlphabet(shift);
        String decoding = "";

        for (int i=0; i<withoutSpaces.length();i++) {
            char one = withoutSpaces.charAt(i);
            int index = ceasarAlphabet.indexOf(one);
            char two = alphabet.charAt(index);
            decoding = decoding + two;
        }
        decoded=unobify(decoding);
    }

    public String getDecoded() {
        return decoded;
    }
}

public class CeasarCypher {
    public static void main(String[] args) {
        Main main = new Main();
    }
}
