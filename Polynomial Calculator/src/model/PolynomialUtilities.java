package model;

import java.util.Collections;

public final class PolynomialUtilities {

    /**
     * introduce a new monomial in the monomials list of the polynomial.
     *
     * @param polynomial the polynomial where we want to introduce a new monomial
     * @param monomial   the monomial which is introduced
     */
    public static void introduceMonomial(Polynomial polynomial, Monomial monomial) {
        if (monomial.getCoefficient().doubleValue() != 0)
            polynomial.getMonomials().add(monomial);
    }

    /**
     * Sorts the monomials inside a polynomial, descending by degree.
     *
     * @param polynomial the polynomial to be sorted
     */
    public static void sortPolynomial(Polynomial polynomial) {
        Collections.sort(polynomial.getMonomials());
        Monomial empty = new IntegerMonomial(0, -9999);
        polynomial.getMonomials().add(empty);
    }

    /**
     * Simplifies a polynomial (adds the terms with the same coefficient together).
     * Example: Given as parameter the polynomial x^2 + 3x + 2x, after the method is run, the polynomial will look like this: x^2 + 5x
     *
     * @param polynomial the polynomial to be simplified
     */
    public static void simplify(Polynomial polynomial) {
        sortPolynomial(polynomial);
        int n = polynomial.getMonomials().size();
        for (int i = 0; i < n - 1; i++) {
            if (polynomial.getMonomials().get(i).getDegree() == polynomial.getMonomials().get(i + 1).getDegree()) {
                polynomial.getMonomials().set(i, polynomial.getMonomials().get(i).add(polynomial.getMonomials().get(i + 1)));
                polynomial.getMonomials().remove(polynomial.getMonomials().get(i + 1));

                i--;
                n--;
            }
        }
    }
}
