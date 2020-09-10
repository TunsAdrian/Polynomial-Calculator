package model;

/**
 * Class representing a monomials with integer coefficients.
 * It implements monomial operations, such as addition, difference, multiplication, division, differentiation and integration.
 *
 * @see Monomial
 */
public class IntegerMonomial extends Monomial {

    public IntegerMonomial() {
    }

    public IntegerMonomial(Integer coefficient, int degree) {
        this.setDegree(degree);
        this.coefficient = coefficient;
    }

    public Integer getCoefficient() {
        return (Integer) this.coefficient;
    }

    public void setCoefficient(Number coefficient) {
        this.coefficient = (Integer) coefficient;
    }

    /**
     * Adds to a monomial having the same degree and returns the sum as a monomial.
     *
     * @param addend the second term of the sum.
     * @return the sum as a monomial of the same degree.
     */
    public IntegerMonomial addInt(IntegerMonomial addend) {
        IntegerMonomial result = new IntegerMonomial(); //the method will return this object.
        result.setDegree(this.getDegree());
        result.setCoefficient(this.getCoefficient() + addend.getCoefficient());

        return result;
    }

    /**
     * Subtracts a monomial of the same degree and returns the difference.
     *
     * @param minuend the minuend.
     * @return the difference as a monomial of the same degree.
     */
    public IntegerMonomial subtractInt(IntegerMonomial minuend) {
        IntegerMonomial result = new IntegerMonomial(); //the method will return this object.
        result.setDegree(this.getDegree());
        result.setCoefficient(this.getCoefficient() - minuend.getCoefficient());

        return result;
    }

    /**
     * Multiplies with a monomial and returns the product as another monomial.
     *
     * @param multiplicand a Monomial object, the factor of multiplication.
     * @return the product as a Monomial object.
     */
    public IntegerMonomial multiplyInt(IntegerMonomial multiplicand) {
        IntegerMonomial result = new IntegerMonomial(); //the method will return this object.
        result.setCoefficient(this.getCoefficient() * multiplicand.getCoefficient());
        result.setDegree(this.getDegree() + multiplicand.getDegree());

        return result;
    }

    /**
     * Differentiates a monomial with respect to its single variable.
     *
     * @return the result as a Monomial object.
     */
    public IntegerMonomial differentiate() {
        IntegerMonomial result = new IntegerMonomial(); //the method will return this object.
        result.setCoefficient(this.getCoefficient() * this.getDegree());
        result.setDegree(this.getDegree() - 1);

        return result;
    }

    /**
     * Divides by a monomial and returns the quotient.
     *
     * @param divisor the divisor.
     * @return the quotient as a Monomial object.
     */
    public RealMonomial divideInt(Monomial divisor) {

        RealMonomial result = new RealMonomial(); //the method will return this object.
        result.setCoefficient(this.getCoefficient().doubleValue() / divisor.getCoefficient().doubleValue());
        result.setDegree(this.getDegree() - divisor.getDegree());

        return result;
    }

    /**
     * Integrates a real-coefficient monomial with respect to its single variable.
     *
     * @return the result as a RealMonomial object.
     */
    public RealMonomial integrate() {
        RealMonomial result = new RealMonomial(); //the method will return this object.

        if (this.getDegree() != -1) {
            result.setCoefficient(this.getCoefficient().doubleValue() / (this.getDegree() + 1));
        }

        result.setDegree(this.getDegree() + 1);


        return result;
    }
}
