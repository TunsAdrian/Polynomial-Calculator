package model;

/**
 * Class representing a monomial, having a coefficient and a degree.
 * Is the parent class for the IntegerMonomial and RealMonomial classes.
 *
 * @see IntegerMonomial
 * @see RealMonomial
 */
public abstract class Monomial implements Comparable<Monomial> {

    protected Number coefficient;
    private int degree;

    public abstract Number getCoefficient();

    public abstract void setCoefficient(Number coefficient);

    public IntegerMonomial toIntegerMonomial() {
        return new IntegerMonomial(this.coefficient.intValue(), this.getDegree());
    }

    public RealMonomial toRealMonomial() {
        return new RealMonomial(this.getCoefficient().doubleValue(), this.getDegree());
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int compareTo(Monomial other) {
        return Integer.compare(other.getDegree(), this.getDegree());
        /*
        if (this.getDegree() < other.getDegree()) {
            return 1;
        } else if (this.getDegree() > other.getDegree()) {
            return -1;
        } else {
            return 0;
        }
        */
    }

    public Monomial add(Monomial addend) {
        if (this instanceof IntegerMonomial && addend instanceof IntegerMonomial) {
            return this.toIntegerMonomial().addInt(addend.toIntegerMonomial());
        }

        //At least one is RealMonomial
        return this.toRealMonomial().addReal(addend.toRealMonomial());
    }

    public Monomial subtract(Monomial minuend) {
        if (this instanceof IntegerMonomial && minuend instanceof IntegerMonomial) {
            return this.toIntegerMonomial().subtractInt(minuend.toIntegerMonomial());
        }

        //At least one is RealMonomial
        return this.toRealMonomial().subtractReal(minuend.toRealMonomial());
    }

    /**
     * Checks if the Monomials are of type IntegerMonomial or RealMonomial.
     * If both are IntegerMonomials, then the multiplyInt method in the IntegerMonomial class is called.
     * Otherwise, the multiplyReal method from the RealMonomial class is called.
     *
     * @param multiplicand
     * @return
     */
    public Monomial multiply(Monomial multiplicand) {
        // If both are IntegerMonomials
        if (this instanceof IntegerMonomial && multiplicand instanceof IntegerMonomial) {
            return this.toIntegerMonomial().multiplyInt(multiplicand.toIntegerMonomial());
        }

        // At least one is RealMonomial
        return this.toRealMonomial().multiplyReal(multiplicand.toRealMonomial());
    }

    public Monomial divide(Monomial divisor) {
        if (this instanceof IntegerMonomial && divisor instanceof IntegerMonomial) {
            return this.toIntegerMonomial().divideInt(divisor.toIntegerMonomial());
        }

        //At least one is RealMonomial
        return this.toRealMonomial().divideReal(divisor.toRealMonomial());
    }

    @Override
    public String toString() {

        if (coefficient.doubleValue() == 0) {
            return "0";
        } else if (coefficient.doubleValue() == 1 && degree == 1) {
            return "x";
        } else if (coefficient.doubleValue() == -1 && degree == 1) {
            return "-x";
        } else if (coefficient.doubleValue() == 1 && degree != 0) {
            return "x^" + degree;
        } else if (degree == 1) {
            return coefficient + "x";
        } else if (degree == 0) {
            return "" + coefficient;
        } else if (coefficient.doubleValue() == -1) {
            return "-x^" + degree;
        } else {
            return coefficient + "x^" + degree;
        }
    }
}