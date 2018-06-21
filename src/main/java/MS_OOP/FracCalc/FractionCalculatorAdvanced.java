package MS_OOP.FracCalc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ja on 18/06/2018.
 * @project EDX,
 * Refactoring, YAY!
 */

public class FractionCalculatorAdvanced extends FractionCalculator{

    FractionCalculatorAdvanced(){
        super(true);

        greeting();
        advancedCalc();
    }

    private void greeting(){
        System.out.println("This program is a fraction calculator" +
                "\nIt will add, subtract, multiply and divide fractions unqil you type Q to quit." +
                "\nValid operations are of form \"[FRAC] [OPRTATION] [FRAC]\".");
    }

    private String unifiedEntry(){
        Scanner scanny = new Scanner(System.in);

        System.out.print("Enter an operation (q to quit): ");
        String entry = scanny.next();

        if (!verifyEntry(entry)){
            System.out.println("Invalid operation. Must be \"[FRAC] [OPERATION] [FRAC]\".");
            unifiedEntry();
        }
        return entry;
    }

    private boolean verifyEntry(String str){
        List <Pattern> entryPatterns = new ArrayList<>();

        entryPatterns.add(Pattern.compile("\\d+[+\\-*/=]\\d+"));  //eg. 45/22
        entryPatterns.add(Pattern.compile("\\d+[+\\-*/=]\\d+/\\d+")); //eg. 11+48/11
        entryPatterns.add(Pattern.compile("\\d+/\\d+[+\\-*/=]\\d+")); //eg. 48/11+11
        entryPatterns.add(Pattern.compile("\\d+/\\d+[+\\-*/=]\\d+/\\d+")); //eg. 48/11+48/11
        entryPatterns.add(Pattern.compile("[qQ]")); //toQuit

        for (Pattern patty : entryPatterns){
            Matcher matcher = patty.matcher(str);
            if (matcher.matches()) return true;
        }
        return false;
    }

    private String[] cleanArray (final String [] in){
        List<String> list = new ArrayList<>(Arrays.asList(in));
        list.remove("");
        return list.toArray(new String [list.size()]);
    }

    //adapted

    private Fraction getFraction(String in) {
        Scanner scanny = new Scanner(System.in);
        int max = Integer.MAX_VALUE / 2;

        if (validFraction(in) == 3) {
            BigInteger numeratorIn = new BigInteger(acquireNumerator(in));
            BigInteger denominatorIn = new BigInteger(acquireDenominator(in));

            while (numeratorIn.compareTo(BigInteger.valueOf(max)) == 1) {
                try {
                    System.out.println("Sorry, integers only, this means the → numerator ← entered has to be lower than: " + ((Integer.MAX_VALUE / 2) - 1) + ".\nNumerator only, please: ");
                    String emergencyInputNume = scanny.next();
                    numeratorIn = new BigInteger(emergencyInputNume);
                } catch (NumberFormatException e) {
                }
            }

            while (denominatorIn.compareTo(BigInteger.valueOf(max)) == 1) {
                try {
                    System.out.println("Sorry, integers only, this means the → denominator ← entered has to be lower than: " + ((Integer.MAX_VALUE / 2) - 1) + ".\nDenominator only, please: ");
                    String emergencyInputDeno = scanny.next();
                    denominatorIn = new BigInteger(emergencyInputDeno);
                } catch (NumberFormatException e) {
                }
            }

            int numerator = Integer.parseInt(String.valueOf(numeratorIn));
            int denominator = Integer.parseInt(String.valueOf(denominatorIn));

            return new Fraction(numerator, denominator);
        }
        if (validFraction(in) == 2) {
            BigInteger value = new BigInteger(in);

            while (value.compareTo(BigInteger.valueOf(max)) == 1) {
                try {
                    System.out.println("Again, integers only, this means the number entered has to enter has to be lower than: " + ((Integer.MAX_VALUE / 2) - 1) + ".");
                    String emergencyInput = scanny.next();
                    value = new BigInteger(emergencyInput);
                } catch (NumberFormatException e) {
                    //intentionally
                }
            }

            int output = Integer.parseInt(String.valueOf(value));

            return new Fraction(output);
        }
        if (validFraction(in) == 1) {

            return new Fraction();
        }else {
            System.out.print("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
            getFraction();
        }
        return new Fraction();
    }

    //adapted

    private void calculatorItself(Fraction fraction1, Fraction fraction2, String operation) {

        if (operation.equals("+")) {

            Fraction sum = fraction1.add(fraction2);
            Fraction suminLowest = new Fraction(sum.getNumerator(), sum.getDenominator());
            suminLowest.toLowestTerms();

            System.out.println(fraction1 + " + " + fraction2 + " = " + sum + " In lowest terms it is: " + suminLowest);

        }
        if (operation.equals("-")) {

            Fraction difference = fraction1.subtract(fraction2);
            Fraction differenceInLowest = new Fraction(difference.getNumerator(), difference.getDenominator());
            differenceInLowest.toLowestTerms();

            System.out.println(fraction1 + " - " + fraction2 + " = " + difference + " In lowest terms it is: " + differenceInLowest);

        }
        if (operation.equals("*")) {

            Fraction product = fraction1.multiply(fraction2);
            Fraction productInLowest = new Fraction(product.getNumerator(), product.getDenominator());
            productInLowest.toLowestTerms();

            System.out.println(fraction1 + " * " + fraction2 + " = " + product + " In lowest terms it is: " + productInLowest);

        }
        if (operation.equals("/")) {

            Fraction quotient = new Fraction(1, 1);

            if (fraction2.getNumerator() != 0) {
                quotient = fraction1.divide(fraction2);
            }

            while (fraction2.getNumerator() == 0) {
                try {
                    quotient = fraction1.divide(fraction2);
                } catch (IllegalArgumentException e) {
                    System.out.println("\n" + e.getMessage());
                    System.out.println("You will have to enter 2nd fraction again. This time no zero-gunslinging, please.");
                    fraction2 = getFraction();
                    if (fraction2.getNumerator() != 0) {
                        quotient = fraction1.divide(fraction2);
                    }
                }
            }

            Fraction quotientInLowest = new Fraction(quotient.getNumerator(), quotient.getDenominator());
            quotientInLowest.toLowestTerms();

            System.out.println(fraction1 + " / " + fraction2 + " = " + quotient + " In lowest terms it is: " + quotientInLowest);

        }
        if (operation.equals("=")) {

            fraction1.toLowestTerms();
            fraction2.toLowestTerms();

            System.out.println("\nIn order to find out if the fractions are even we need to get them to lowest terms. This way they become comparable.");

            boolean equals = (fraction1.equals(fraction2));

            if (equals) {
                System.out.println("\nThey are equal! Who would have thought! In lowest terms it is:");
                System.out.println(fraction1 + " = " + fraction2);
            } else {
                System.out.println("\nSadly they aren't equal. There also is no consolation prize. In lowest terms they are: ");
                System.out.println(fraction1 + " != " + fraction2);
            }

        }
        if (operation.equals("Q") || operation.equals("q")) {
            System.out.println("\nHope you enjoyed the ADVANCED version of calculator! Byebye! <^_^>");
            System.exit(69);
        }
    }

    private void advancedCalc(){

        String str = unifiedEntry();

        String [] numbers = str.split("[+\\-*/=qQ]", 0);
        String [] signs = cleanArray(str.split("\\d+"));

        if (signs.length==1 && !(signs[0].equals("q") && !(signs[0].equals("Q")))){
            Fraction fraction1 = getFraction(numbers[0]);
            Fraction fraction2 = getFraction(numbers[1]);

            calculatorItself(fraction1, fraction2, signs[0]);
            advancedCalc();
        }
        if (signs.length==2 && signs[0].equals("/") && signs[1].equals("/")){
            Fraction fraction1 = getFraction(numbers[0]+"/"+ numbers[1]);
            Fraction fraction2 = getFraction(numbers[2]);

            calculatorItself(fraction1, fraction2, signs[1]);
            advancedCalc();
        }
        if (signs.length==2 && signs[1].equals("/") && !(signs[0].equals("/"))){
            Fraction fraction1 = getFraction(numbers[0]);
            Fraction fraction2 = getFraction(numbers[1]+"/"+numbers[2]);

            calculatorItself(fraction1, fraction2, signs[0]);
            advancedCalc();
        }
        if (signs.length==2 && signs[0].equals("/") && !(signs[1].equals("/"))){
            Fraction fraction1 = getFraction(numbers[0]+"/"+numbers[1]);
            Fraction fraction2 = getFraction(numbers[2]);

            calculatorItself(fraction1, fraction2, signs[1]);
            advancedCalc();
        }
        if (signs.length==3){
            Fraction fraction1 = getFraction(numbers[0]+"/"+numbers[1]);
            Fraction fraction2 = getFraction(numbers[2]+"/"+numbers[3]);

            calculatorItself(fraction1, fraction2, signs[1]);
            advancedCalc();
        }
        if (Arrays.asList(signs).contains("q") || Arrays.asList(signs).contains("Q")){
            Fraction fraction1 = new Fraction();
            Fraction fraction2 = new Fraction();

            calculatorItself(fraction1, fraction2, "q");
            advancedCalc();
        }
    }

    public static void main(String[] args) {
        new FractionCalculatorAdvanced();
    }
}
