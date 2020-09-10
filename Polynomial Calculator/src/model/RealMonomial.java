package model;

public class RealMonomial extends Monomial {

    public RealMonomial() {
    }

    public RealMonomial(Double coefficient, int degree) {
        this.setDegree(degree);
        this.coefficient = coefficient;
    }

    public Double getCoefficient() {
        return (Double) this.coefficient;
    }

    public void setCoefficient(Number coefficient) {
        this.coefficient = (Double) coefficient;
    }

    public RealMonomial multiplyReal(Monomial multiplicand) {
        RealMonomial result = new RealMonomial(); //the method will return this object.
        result.setCoefficient(this.getCoefficient() * multiplicand.getCoefficient().doubleValue());
        result.setDegree(this.getDegree() + multiplicand.getDegree());

        return result;
    }

    public RealMonomial addReal(Monomial addend) {
        RealMonomial result = new RealMonomial(); //the method will return this object.
        result.setDegree(this.getDegree());
        result.setCoefficient(this.getCoefficient() + addend.getCoefficient().doubleValue());

        return result;
    }

    public RealMonomial subtractReal(Monomial minuend) {
        RealMonomial result = new RealMonomial(); //the method will return this object.
        result.setDegree(this.getDegree());
        result.setCoefficient(this.getCoefficient() - minuend.getCoefficient().doubleValue());

        return result;
    }

    public RealMonomial divideReal(Monomial divisor) {
        RealMonomial result = new RealMonomial(); //the method will return this object.
        result.setCoefficient(this.getCoefficient() / divisor.getCoefficient().doubleValue());
        result.setDegree(this.getDegree() - divisor.getDegree());

        return result;
    }
}
