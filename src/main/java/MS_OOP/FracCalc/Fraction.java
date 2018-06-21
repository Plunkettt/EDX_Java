package MS_OOP.FracCalc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ja on 07/06/2018.
 * @project EDX,
 *
 * BE AWARE:
 * Prime factorization method here is used intentionally, even if it is not optimal algorithm for this problem.
 * Who knows I might make it into an educational app.
 * Cheers!
 */
public class Fraction {

    private int numerator;
    private int denominator;

    Fraction(int numerator, int denominator) {

        if (denominator==0){
            throw new IllegalArgumentException("Hold it right there cowboy! Division by 0 is illegal in this part of multiverse.");
        }
        if ((numerator<0 && denominator<0)||(denominator<0)){
            numerator=changeSign(numerator);
            denominator=changeSign(denominator);
        }

        this.numerator=numerator;
        this.denominator=denominator;
    }

    Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator=1;
    }

    Fraction() {
        this.numerator=0;
        this.denominator=1;
    }

    /** Inner Class */
    //This class turns a composite number into it's prima factors
    private class PrimeFactorizer {
        List<Integer> primes = new ArrayList<>();
        Map <Integer, Integer> primesInPowers = new HashMap<>();
        final int i=2;
        int x;

        PrimeFactorizer(int x) {
            this.x = x;

            recursionToPrime(x, i);

            for (Integer i : primes){
                primesInPowers.putIfAbsent(i, 0);
                primesInPowers.computeIfPresent(i, (k,v) -> v+1);
            }
        }

        // Methods of the Inner Class.

        private int recursionToPrime (int x, int i){
            if (x==1) return 0;
            if (x%i==0) {primes.add(i);return recursionToPrime(x/i, i);}
            return recursionToPrime(x, i+1);
        }

        List<Integer> getPrimes() {
            return primes;
        }
    }

    /** Private Methods */

    private int changeSign(int num){
        return num*(-1);
    }

    /** Public Methods */

    double toDouble (){
        return (double) this.numerator/this.denominator;
    }

    Fraction add (Fraction fraction){
        int LCM = leastCommonMultiple(fraction.denominator);

        int thisMultiplier = LCM/this.denominator;
        int otherMultiplier = LCM/fraction.denominator;

        int sumNumerator = (this.numerator*thisMultiplier)+(fraction.numerator*otherMultiplier);

        return new Fraction(sumNumerator, LCM);
    }

    Fraction subtract(Fraction fraction){
        int LCM = leastCommonMultiple(fraction.denominator);

        int thisMultiplier = LCM/this.denominator;
        int otherMultiplier = LCM/fraction.denominator;

        int differenceNumerator = (this.numerator*thisMultiplier)-(fraction.numerator*otherMultiplier);

        return new Fraction(differenceNumerator, LCM);
    }

    Fraction multiply(Fraction fraction){
        int outputNum=this.numerator*fraction.numerator;
        int outputDen=this.denominator*fraction.denominator;
        return new Fraction(outputNum,outputDen);
    }

    Fraction divide(Fraction fraction){
        int outputNum=this.numerator*fraction.denominator;
        int outputDen=this.denominator*fraction.numerator;
        return new Fraction(outputNum, outputDen);
    }

    public boolean equals (Object object){
        Fraction fraction = (Fraction) object;
        return ((this.numerator==fraction.numerator)&&(this.denominator==fraction.denominator));
    }

    void toLowestTerms(){
        int divisor = greatestCommonDivisor(this.numerator);

        this.numerator=this.numerator/divisor;
        this.denominator=this.denominator/divisor;
    }

    //This method finds GCD using primes contained in an ArrayList.

    int greatestCommonDivisor(int ownNumeratorOrOtherDenominator){
        List <Integer> ownDeno = new ArrayList<>(new PrimeFactorizer(this.denominator).getPrimes());
        List <Integer> otherDenoNum = new ArrayList<>(new PrimeFactorizer(ownNumeratorOrOtherDenominator).getPrimes());

        int outputGCD=1;

        for (Integer thisDeno : ownDeno){
            if (otherDenoNum.contains(thisDeno)){
                outputGCD*=thisDeno;
                otherDenoNum.remove(thisDeno);
            }
        }return outputGCD;
    }

    //This method finds LCM of a number using prime factors. Keys in Hashmap are numbers, values are powers in which those numbers are present. This solution is similar to wordcount.
    //The first loop finds numbers and determines which of them are in higher power. The second one picks up any numbers which haven't been present in the first number primes by using the excluded ArrayList.

    int leastCommonMultiple(int otherDenominator){
        Map <Integer, Integer> thisDenoPrimes = new PrimeFactorizer(this.denominator).primesInPowers;
        Map <Integer, Integer> otherDenoPrimes = new PrimeFactorizer(otherDenominator).primesInPowers;
        List <Integer> excluded = new ArrayList<>();

        int outputLCM = 1;

        for (Integer keyThis : thisDenoPrimes.keySet() ){
            if (otherDenoPrimes.containsKey(keyThis)){
                int maxPower;

                if (thisDenoPrimes.get(keyThis)>otherDenoPrimes.get(keyThis)){
                    maxPower=thisDenoPrimes.get(keyThis);
                    excluded.add(keyThis);
                }else {
                    maxPower=otherDenoPrimes.get(keyThis);
                    excluded.add(keyThis);
                }

                outputLCM*=Math.pow(keyThis, maxPower);

            }else outputLCM*=Math.pow(keyThis, thisDenoPrimes.get(keyThis));
        }

        for (Integer keyOther : otherDenoPrimes.keySet()){

                if (!excluded.contains(keyOther)){
                    outputLCM*=Math.pow(keyOther, otherDenoPrimes.get(keyOther));
                }

        }return outputLCM;
    }

    @Override
    public String toString() {
        if (denominator==1) return String.valueOf(numerator);

        return numerator+"/"+denominator;
    }

    int getNumerator() {
        return numerator;
    }

    int getDenominator() {
        return denominator;
    }
}
