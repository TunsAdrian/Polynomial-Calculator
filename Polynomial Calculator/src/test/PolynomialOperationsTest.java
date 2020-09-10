package test;

import controller.Controller;
import model.IntegerMonomial;
import model.Monomial;
import model.Polynomial;
import model.PolynomialUtilities;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import view.UserInterface;

public class PolynomialOperationsTest {

    private Polynomial p1;
    private Polynomial p2;
    private UserInterface view = new UserInterface();
    private Controller controller = new Controller(view);

    /**
     * Creates two polynomials for testing.
     */
    @Before
    public void createTestPolynomials() {
        p1 = controller.toPolynomial("3x^4 + 2x^3 + x");
        p2 = controller.toPolynomial("5x^2 + 2x + 4");
    }

    /**
     * Tests the string-to-polynomial parsing.
     */
    @Test
    public void parsePolynomial() {
        Polynomial expectedPolynomial = new Polynomial();
        Monomial m = new IntegerMonomial(3, 4);
        Monomial p = new IntegerMonomial(2, 3);
        Monomial q = new IntegerMonomial(1, 1);

        PolynomialUtilities.introduceMonomial(expectedPolynomial, m);
        PolynomialUtilities.introduceMonomial(expectedPolynomial, p);
        PolynomialUtilities.introduceMonomial(expectedPolynomial, q);

        String actual = p1.toString();
        String expected = expectedPolynomial.toString();
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests the addition between two polynomials.
     */
    @Test
    public void additionTest() {

        Polynomial expectedSum = controller.toPolynomial("3x^4 + 2x^3 + 5x^2 + 3x + 4");

        String actual = (p1.add(p2)).toString();
        String expected = expectedSum.toString();
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests the difference between two polynomials.
     */
    @Test
    public void differenceTest() {

        Polynomial expectedDifference = controller.toPolynomial("3x^4 + 2x^3 - 5x^2 - x - 4");

        String actual = (p1.subtract(p2)).toString();
        String expected = expectedDifference.toString();
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests the multiplication between two polynomials.
     */
    @Test
    public void multiplicationTest() {

        Polynomial expectedProduct = controller.toPolynomial("15x^6 + 16x^5 + 16x^4 + 13x^3 + 2x^2 + 4x");

        String actual = (p1.multiply(p2)).toString();
        String expected = expectedProduct.toString();
        Assert.assertEquals(expected, actual);
    }
}
