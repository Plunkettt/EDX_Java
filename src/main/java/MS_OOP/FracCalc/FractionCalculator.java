package MS_OOP.FracCalc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ja on 07/06/2018.
 * @project EDX,
 */


public class FractionCalculator {

    FractionCalculator() {

        calculatorMain();

    }

    FractionCalculator(boolean advancced){

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

    /**This method is a syntax qualifier, returning int value depending on what kind of input has been provided.
     * It is used to direct output to proper constructor.
     * @param string String input to be classified.
     * @return An int value. 3 for fraction, 2 for scalar, 1 for zero. 0 return left as failsafe.
     * */
    int validFraction(String string){
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

    Fraction getFraction(){
        Scanner scanny = new Scanner(System.in);
        int max = Integer.MAX_VALUE/2;


        System.out.print("Please enter a fraction (a/b) or an integer (a): ");
        String in = scanny.next();

        if (validFraction(in)==3){
            BigInteger numeratorIn = new BigInteger(acquireNumerator(in));
            BigInteger denominatorIn = new BigInteger(acquireDenominator(in));

            while (numeratorIn.compareTo(BigInteger.valueOf(max))==1){
                try {
                    System.out.println("Sorry, integers only, this means the → numerator ← entered has to be lower than: "+((Integer.MAX_VALUE/2)-1)+".\nNumerator only, please: ");
                    String emergencyInputNume = scanny.next();
                    numeratorIn=new BigInteger(emergencyInputNume);
                }catch (NumberFormatException e){
                }
            }

            while (denominatorIn.compareTo(BigInteger.valueOf(max))==1){
                try {
                    System.out.println("Sorry, integers only, this means the → denominator ← entered has to be lower than: " + ((Integer.MAX_VALUE / 2) - 1) + ".\nDenominator only, please: ");
                    String emergencyInputDeno = scanny.next();
                    denominatorIn = new BigInteger(emergencyInputDeno);
                }catch (NumberFormatException e){
                    //intentionally empty
                }
            }

            int numerator = Integer.parseInt(String.valueOf(numeratorIn));
            int denominator = Integer.parseInt(String.valueOf(denominatorIn));

            return new Fraction(numerator, denominator);

        } if (validFraction(in)==2) {
            BigInteger value = new BigInteger(in);

            while (value.compareTo(BigInteger.valueOf(max)) == 1) {
                try {
                    System.out.println("Again, integers only, this means the number entered has to enter has to be lower than: " + ((Integer.MAX_VALUE / 2) - 1) + ".");
                    String emergencyInput = scanny.next();
                    value = new BigInteger(emergencyInput);
                }catch (NumberFormatException e){
                }
            }

            int output = Integer.parseInt(String.valueOf(value));

            return new Fraction(output);

        } if (validFraction(in)==1){

            return new Fraction();

        } else {
            System.out.print("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
            getFraction();
        }
        return new Fraction();
    }

    String acquireNumerator(String in){
        if (in.charAt(0)=='/') return "";

        return (in.charAt(0))+ acquireNumerator(in.substring(1));
    }

    String acquireDenominator(String in){
        if (in.length()==0) return "";

        if (in.charAt(0)=='/') return in.substring(1);

        return acquireDenominator(in.substring(1));
    }

    private void calculatorMain(){
        String operation = getOperation();
        if (operation.equals("+")){
            Fraction fraction1 = getFraction();
            Fraction fraction2 = getFraction();

            Fraction sum = fraction1.add(fraction2);
            Fraction suminLowest = new Fraction(sum.getNumerator(), sum.getDenominator());
            suminLowest.toLowestTerms();

            System.out.println(fraction1+" + "+fraction2+" = "+sum+" In lowest terms it is: "+suminLowest);
            calculatorMain();
        }
        if (operation.equals("-")){
            Fraction fraction1 = getFraction();
            Fraction fraction2 = getFraction();

            Fraction difference = fraction1.subtract(fraction2);
            Fraction differenceInLowest = new Fraction(difference.getNumerator(), difference.getDenominator());
            differenceInLowest.toLowestTerms();

            System.out.println(fraction1+" - "+fraction2+" = "+difference+" In lowest terms it is: "+differenceInLowest);
            calculatorMain();
        }
        if (operation.equals("*")){
            Fraction fraction1 = getFraction();
            Fraction fraction2 = getFraction();

            Fraction product = fraction1.multiply(fraction2);
            Fraction productInLowest = new Fraction(product.getNumerator(), product.getDenominator());
            productInLowest.toLowestTerms();

            System.out.println(fraction1+" * "+fraction2+" = "+product+" In lowest terms it is: "+productInLowest);
            calculatorMain();
        }
        if (operation.equals("/")){
            Fraction fraction1 = getFraction();
            Fraction fraction2 = getFraction();

            Fraction quotient = new Fraction(1,1);

            if (fraction2.getNumerator()!=0) {
                quotient=fraction1.divide(fraction2);
            }

            while (fraction2.getNumerator()==0) {
                try {
                    quotient=fraction1.divide(fraction2);
                } catch (IllegalArgumentException e) {
                    System.out.println("\n" + e.getMessage());
                    System.out.println("You will have to enter 2nd fraction again. This time no zero-gunslinging, please.");
                    fraction2 = getFraction();
                    if (fraction2.getNumerator()!=0){
                        quotient=fraction1.divide(fraction2);
                    }
                }
            }

            Fraction quotientInLowest = new Fraction(quotient.getNumerator(), quotient.getDenominator());
            quotientInLowest.toLowestTerms();

            System.out.println(fraction1+" / "+fraction2+" = "+quotient+" In lowest terms it is: "+quotientInLowest);

            calculatorMain();
        }
        if (operation.equals("=")){
            Fraction fraction1 = getFraction();
            Fraction fraction2 = getFraction();

            fraction1.toLowestTerms();
            fraction2.toLowestTerms();

            System.out.println("\nIn order to find out if fractions the are even we need to get them to lowest terms. This way they become comparable.");

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
            System.exit(69);
        }
        else {
            System.out.println("Something went horribly wrong. You are not supposed to be here. getOperation error.");
        }
    }

    public static void main(String[] args) {
        new FractionCalculator();

    }
}