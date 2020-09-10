package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The model of the project design.
 * Represents a mathematical polynomial with real or integer coefficients and integer exponents.
 */
public class Polynomial {
    private List<Monomial> monomials;

    public Polynomial() {
        monomials = new ArrayList<Monomial>();
    }

    public List<Monomial> getMonomials() {
        return monomials;
    }

    public int getDegree() {
        PolynomialUtilities.sortPolynomial(this);
        return this.monomials.get(0).getDegree();
    }

    public Polynomial add(Polynomial addend) {
        Polynomial result = new Polynomial();

        PolynomialUtilities.simplify(this);
        PolynomialUtilities.simplify(addend);

        Iterator<Monomial> iter1 = this.getMonomials().iterator();
        Iterator<Monomial> iter2 = addend.getMonomials().iterator();
        Monomial m1 = iter1.next();
        Monomial m2 = iter2.next();

        while (iter1.hasNext() || iter2.hasNext()) {

            if (!iter1.hasNext()) {
                PolynomialUtilities.introduceMonomial(result, m2.toIntegerMonomial());
                m2 = iter2.next();

            } else if (!iter2.hasNext()) {
                PolynomialUtilities.introduceMonomial(result, m1.toIntegerMonomial());
                m1 = iter1.next();

            } else {
                if (m1.compareTo(m2) == 0) {
                    PolynomialUtilities.introduceMonomial(result, m1.toIntegerMonomial().addInt(m2.toIntegerMonomial()));
                    m1 = iter1.next();
                    m2 = iter2.next();

                } else if (m1.compareTo(m2) < 0) {
                    PolynomialUtilities.introduceMonomial(result, m1.toIntegerMonomial());
                    m1 = iter1.next();

                } else {
                    PolynomialUtilities.introduceMonomial(result, m2.toIntegerMonomial());
                    m2 = iter2.next();
                }
            }
        }

        return result;
    }

    public Polynomial subtract(Polynomial minuend) {
        Polynomial result = new Polynomial();

        PolynomialUtilities.simplify(this);
        PolynomialUtilities.simplify(minuend);

        Iterator<Monomial> iter1 = this.getMonomials().iterator();
        Iterator<Monomial> iter2 = minuend.getMonomials().iterator();
        Monomial m1 = iter1.next();
        Monomial m2 = iter2.next();

        while (iter1.hasNext() || iter2.hasNext()) {

            if (!iter1.hasNext()) {
                PolynomialUtilities.introduceMonomial(result, new IntegerMonomial(-m2.getCoefficient().intValue(), m2.getDegree()));
                m2 = iter2.next();

            } else if (!iter2.hasNext()) {
                PolynomialUtilities.introduceMonomial(result, m1);
                m1 = iter1.next();

            } else {
                if (m1.compareTo(m2) == 0) {
                    if (m1 instanceof IntegerMonomial && m2 instanceof IntegerMonomial) {
                        PolynomialUtilities.introduceMonomial(result, m1.toIntegerMonomial().subtractInt(m2.toIntegerMonomial()));
                    } else {
                        PolynomialUtilities.introduceMonomial(result, m1.toRealMonomial().subtractReal(m2));
                    }
                    m1 = iter1.next();
                    m2 = iter2.next();

                } else if (m1.compareTo(m2) < 0) {
                    PolynomialUtilities.introduceMonomial(result, m1);
                    m1 = iter1.next();

                } else {
                    PolynomialUtilities.introduceMonomial(result, new IntegerMonomial(-m2.getCoefficient().intValue(), m2.getDegree()));
                    m2 = iter2.next();
                }
            }
        }

        return result;
    }

    public Polynomial multiply(Polynomial multiplicand) {
        Polynomial result = new Polynomial();

        PolynomialUtilities.simplify(this);
        PolynomialUtilities.simplify(multiplicand);

        for (Monomial m1 : this.getMonomials()) {
            for (Monomial m2 : multiplicand.getMonomials()) {

                Monomial product = m1.multiply(m2);
                PolynomialUtilities.introduceMonomial(result, product);
            }

        }
        PolynomialUtilities.simplify(result);
        return result;
    }

    public Polynomial divide(Polynomial divisor) {

        PolynomialUtilities.simplify(this);
        PolynomialUtilities.simplify(divisor);

        Polynomial dividend = this;
        Polynomial quotient = new Polynomial();
        Polynomial auxiliaryPolynomial; // used in calculations
        Polynomial singleMonomialPolynomial = new Polynomial(); // used to hold a single Monomial in order to be multiplied with another polynomial.

        if (dividend.getDegree() < divisor.getDegree()) {
            return quotient;
        }

        while (dividend.getDegree() >= divisor.getDegree()) {

            Monomial auxiliaryMonomial = dividend.getMonomials().get(0).divide(divisor.getMonomials().get(0));

            PolynomialUtilities.introduceMonomial(quotient, auxiliaryMonomial);

            singleMonomialPolynomial.getMonomials().add(auxiliaryMonomial);
            auxiliaryPolynomial = singleMonomialPolynomial.multiply(divisor);
            singleMonomialPolynomial.getMonomials().remove(auxiliaryMonomial);

            dividend = dividend.subtract(auxiliaryPolynomial);
        }

        this.getMonomials().clear();
        for (Monomial m : dividend.getMonomials()) {
            PolynomialUtilities.introduceMonomial(this, m);
        }

        return quotient;
    }

    public Polynomial differentiate() {

        PolynomialUtilities.simplify(this);

        Polynomial result = new Polynomial();

        for (Monomial m : this.getMonomials()) {
            PolynomialUtilities.introduceMonomial(result, m.toIntegerMonomial().differentiate());
        }

        return result;
    }

    public Polynomial integrate() {
        Polynomial result = new Polynomial();

        PolynomialUtilities.simplify(this);

        for (Monomial m : this.getMonomials()) {
            if (m.getDegree() != -1)
                PolynomialUtilities.introduceMonomial(result, m.toIntegerMonomial().integrate());
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (Monomial m : monomials) {

            if (m.getDegree() != -9999) {
                s.append(m).append(" + ");
            }
        }

        String result = s.toString().replaceAll("\\+\\s-", " - ");
        if (result.length() >= 1) {
            return result.substring(0, s.length() - 2);
        }
        return "0";
    }
}
