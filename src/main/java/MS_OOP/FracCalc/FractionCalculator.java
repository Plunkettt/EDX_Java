package MS_OOP.FracCalc;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ja on 07/06/2018.
 * @project EDX,
 */


public class FractionCalculator {

    public FractionCalculator() {
        calculatorMain();

    }

    private String getOperation(){
        Scanner scanny = new Scanner(System.in);
        System.out.print("Please enter an operation (+, -, /, *, = or Q to quit): ");

        String out=scanny.next();

        Pattern pattern = Pattern.compile("[+\\-*/=qQ]");
        Matcher matcher = pattern.matcher(out);

        while (!(matcher.matches())){
            System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");
            out=scanny.next();
            matcher = pattern.matcher(out);
        }
        return out;
    }

    //This method is a logical syntax text, returning int value depending on what kind of input has been provided.
    //Output of this method is used to choose proper constructor in the Fraction class.
    private int validFraction(String string){
        List<Pattern> listOfFractions = new ArrayList<>();
        listOfFractions.add(Pattern.compile("\\d+/[1-9]\\d*$")); //positive fraction
        listOfFractions.add(Pattern.compile("-\\d+/[1-9]\\d*$")); //negative fraction

        List<Pattern> listOfScalars = new ArrayList<>();
        listOfScalars.add(Pattern.compile("^[1-9]\\d*$")); //positive scalar
        listOfScalars.add(Pattern.compile("^-[1-9]\\d*$")); //negative scalar


        for (Pattern patty : listOfFractions){
            Matcher matcher = patty.matcher(string);
            if (matcher.matches())return 3;
        }

        for (Pattern patty : listOfScalars){
            Matcher matcher = patty.matcher(string);
            if (matcher.matches())return 2;
        }

        if (string.equals("0")) return 1;

        return 0;
    }

    private Fraction getFraction(){
        Scanner scanny = new Scanner(System.in);

        System.out.print("Please enter a fraction (a/b) or an integer (a): ");
        String in = scanny.next();

        if (validFraction(in)==3){
            int numerator = Integer.parseInt(acquireNumerator(in));
            int denominator = Integer.parseInt(acquireDenominator(in));

            return new Fraction(numerator, denominator);

        } if (validFraction(in)==2){
            int value = Integer.parseInt(in);

            return new Fraction(value);

        } if (validFraction(in)==1){

            return new Fraction();

        } else {
            System.out.print("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
            getFraction();
        }
        return new Fraction();
    }

    private String acquireNumerator(String in){
        if (in.charAt(0)=='/') return "";

        return (in.charAt(0))+ acquireNumerator(in.substring(1));
    }

    private String acquireDenominator(String in){
        if (in.length()==0) return "";

        if (in.charAt(0)=='/') return in.substring(1);

        return acquireDenominator(in.substring(1));
    }

    private void calculatorMain(){
        String operation = getOperation();
        if (operation.equals("+")){
            Fraction fraction1 = getFraction();
            Fraction fraction2 = getFraction();

            System.out.println(fraction1+" + "+fraction2+" = "+fraction1.add(fraction2));
            calculatorMain();
        }
        if (operation.equals("-")){
            Fraction fraction1 = getFraction();
            Fraction fraction2 = getFraction();

            System.out.println(fraction1+" - "+fraction2+" = "+fraction1.subtract(fraction2));
            calculatorMain();
        }
        if (operation.equals("*")){
            Fraction fraction1 = getFraction();
            Fraction fraction2 = getFraction();

            System.out.println(fraction1+" * "+fraction2+" = "+fraction1.multiply(fraction2));
            calculatorMain();
        }
        if (operation.equals("/")){
            Fraction fraction1 = getFraction();
            Fraction fraction2 = getFraction();

            if (fraction2.getNumerator()!=0) {
                System.out.println(fraction1 + " / " + fraction2 + " = " + fraction1.divide(fraction2));
            }

            while (fraction2.getNumerator()==0) {
                try {
                    System.out.println(fraction1 + " / " + fraction2 + " = " + fraction1.divide(fraction2));
                } catch (IllegalArgumentException e) {
                    System.out.println("\n" + e.getMessage());
                    System.out.println("You will have to enter 2nd fraction again. This time no zero-gunslinging, please.");
                    fraction2 = getFraction();
                    if (fraction2.getNumerator()!=0){
                        System.out.println(fraction1 + " / " + fraction2 + " = " + fraction1.divide(fraction2));
                    }
                }
            }
            calculatorMain();
        }
        if (operation.equals("=")){
            Fraction fraction1 = getFraction();
            Fraction fraction2 = getFraction();

            fraction1.toLowestTerms();
            fraction2.toLowestTerms();

            System.out.println("\nIn order to find out if fractions are even we need to get them to lowest terms. This way they become comparable.");

            boolean equals =(fraction1.equals(fraction2));

            if (equals){
                System.out.println("\nThey are equal! Who would have thought! In lowest terms it is:");
                System.out.println(fraction1+" = "+ fraction2);
            }else {
                System.out.println("\nSadly they aren't equal. There also is no consolation prize. In lowest terms they are: ");
                System.out.println(fraction1+" != "+fraction2);
            }

            calculatorMain();
        }
        if (operation.equals("Q")||operation.equals("q")){
            System.out.println("\nHope you enjoyed! Byebye! <^_^>");
            System.exit(0);
        }
        else {
            System.out.println("Something went horribly wrong. You are not supposed to be here. getOperation error.");
        }
    }

    public static void main(String[] args) {
        FractionCalculator fractionCalculator = new FractionCalculator();

    }
}