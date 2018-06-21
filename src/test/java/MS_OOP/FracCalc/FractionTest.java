package MS_OOP.FracCalc;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Ja on 16/06/2018.
 * @project EDX,
 *
 * A set of basic tests for every method in Fraction class once.
 */

class FractionTest {

    static int counter = 0;

    private Fraction fraction;
    private Fraction fractionOther;

    @BeforeAll
    static void before(){
        System.out.println("This is a set of simple tests for the Fraction class. Please stand by.");
    }

    @BeforeEach
    void setup(){
        fraction = new Fraction(20, 30);
        fractionOther = new Fraction(40, 80);
    }

    @AfterEach
    void afterEach(){
        counter++;
    }

    @AfterAll
    static void afterAll(){
        System.out.println("\n"+counter+" tests were performed.");
    }

    @Test
    void toDouble() {
        assertEquals(0.66666, fraction.toDouble(), 0.00001);
    }

    @Test
    void add() {
        Fraction expectedSum = new Fraction(280, 240);
        assertEquals(expectedSum, fraction.add(fractionOther));
    }

    @Test
    void subtract() {
        Fraction expectedDifference = new Fraction(40, 240);
        assertEquals(expectedDifference, fraction.subtract(fractionOther));
    }

    @Test
    void multiply() {
        Fraction expectedProduct = new Fraction(800, 2400);
        assertEquals(expectedProduct, fraction.multiply(fractionOther));
    }

    @Test
    void divide() {
        Fraction expectedQuotient = new Fraction(1600, 1200);
        assertEquals(expectedQuotient, fraction.divide(fractionOther));
    }

    @Test
    void equals() {
        assertFalse(fraction.equals(fractionOther));
    }

    @Test
    void toLowestTerms() {
        Fraction expectedLowestTerms = new Fraction(2,3);
        fraction.toLowestTerms();
        assertEquals(expectedLowestTerms, fraction);
    }

    @Test
    void greatestCommonDivisor() {
        assertEquals(10, fraction.greatestCommonDivisor(fraction.getNumerator()));
    }

    @Test
    void leastCommonMultiple() {
        assertEquals(240, fraction.leastCommonMultiple(fractionOther.getDenominator()));
    }

    @Test
    void getNumerator() {
        assertEquals(20, fraction.getNumerator());
    }

    @Test
    void getDenominator() {
        assertEquals(30, fraction.getDenominator());
    }
}