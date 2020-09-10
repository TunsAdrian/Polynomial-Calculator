package controller;

import model.IntegerMonomial;
import model.Polynomial;
import model.PolynomialUtilities;
import view.UserInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Controller part of a MVC Design.
 * Links the Model (Polynomial) with the View (UserInterface), processing the input and setting the action of each button.
 *
 * @see Polynomial
 * @see UserInterface
 */
public class Controller {

    private UserInterface view;

    public Controller(UserInterface view) {
        this.view = view;
        this.setListeners();
    }

    /**
     * Converts the input String to a Polynomial object.
     *
     * @param polynomialString the input String (read from the UI fields).
     * @return a Polynomial object.
     * @see Polynomial
     */
    public Polynomial toPolynomial(String polynomialString) {

        polynomialString = polynomialString.replaceAll("\\s+", "");
        Polynomial result = new Polynomial();

        Pattern p = Pattern.compile("((-?\\d+(?=x))?(-?[xX])(\\^(-?\\d+))?)|(-?\\d+)");
        Matcher matcher = p.matcher(polynomialString);
        while (matcher.find()) {

            IntegerMonomial newMonomial;
            int coefficient = 0;
            int degree = 0;

            /*
            The pattern returns 6 groups with details about the read String.
             group 1: existence of a valid term
             group 2: existence of digit + a future x
             group 3: existence of x
             group 4: existence of an exponent
             group 5: exponent value
             group 6: a single number (no x)
             */

            // Cx^D
            if (matcher.group(2) != null && matcher.group(6) == null && matcher.group(5) != null) {

                coefficient = Integer.parseInt(matcher.group(2));
                degree = Integer.parseInt(matcher.group(5));

            }

            // x^D
            else if (matcher.group(1) != null && matcher.group(3) != null && matcher.group(4) != null && matcher.group(5) != null) {

                if (matcher.group(0).charAt(0) == '-') {
                    coefficient = -1;

                } else {
                    coefficient = 1;
                }

                degree = Integer.parseInt(matcher.group(5));

            }

            // Cx
            else if (matcher.group(2) != null && matcher.group(4) == null && matcher.group(6) == null) {

                coefficient = Integer.parseInt(matcher.group(2));
                degree = 1;

            }

            // +/- x
            else if (matcher.group(2) == null && matcher.group(5) == null && matcher.group(6) == null) {

                if (matcher.group(0).charAt(0) == '-') {
                    coefficient = -1;

                } else {
                    coefficient = 1;
                }

                degree = 1;
            }

            // C
            else if (matcher.group(6) != null) {

                coefficient = Integer.parseInt(matcher.group(6));
                degree = 0;
            }

            newMonomial = new IntegerMonomial(coefficient, degree);

            PolynomialUtilities.introduceMonomial(result, newMonomial);

        }
        return result;
    }

    private void setListeners() {

        view.addListeners(e -> {
            // Addition
            String firstPolynomialString = view.getPolynomial1();
            String secondPolynomialString = view.getPolynomial2();
            Polynomial firstPolynomial = toPolynomial(firstPolynomialString);
            Polynomial secondPolynomial = toPolynomial(secondPolynomialString);
            view.setResult(firstPolynomial.add(secondPolynomial));
            view.setRemainderField();

        }, e -> {
            // Difference
            String firstPolynomialString = view.getPolynomial1();
            String secondPolynomialString = view.getPolynomial2();
            Polynomial firstPolynomial = toPolynomial(firstPolynomialString);
            Polynomial secondPolynomial = toPolynomial(secondPolynomialString);
            view.setResult(firstPolynomial.subtract(secondPolynomial));
            view.setRemainderField();

        }, e -> {
            // Multiplication
            String firstPolynomialString = view.getPolynomial1();
            String secondPolynomialString = view.getPolynomial2();
            Polynomial firstPolynomial = toPolynomial(firstPolynomialString);
            Polynomial secondPolynomial = toPolynomial(secondPolynomialString);

            view.setResult(firstPolynomial.multiply(secondPolynomial));
            view.setRemainderField();

        }, e -> {
            // Division
            String firstPolynomialString = view.getPolynomial1();
            String secondPolynomialString = view.getPolynomial2();
            Polynomial firstPolynomial = toPolynomial(firstPolynomialString);
            Polynomial secondPolynomial = toPolynomial(secondPolynomialString);

            if (secondPolynomialString.equals("0") || secondPolynomialString.equals("")) {
                view.displayErrorMessage("Division by 0 not allowed!");
            } else {

                view.setResult(firstPolynomial.divide(secondPolynomial));
                view.setRemainderField(firstPolynomial);
            }

        }, e -> {
            // Differentiation
            String firstPolynomialString = view.getPolynomial1();
            Polynomial firstPolynomial = toPolynomial(firstPolynomialString);

            view.setResult(firstPolynomial.differentiate());
            view.setRemainderField();
        }, e -> {
            // Integration
            String firstPolynomialString = view.getPolynomial1();
            Polynomial firstPolynomial = toPolynomial(firstPolynomialString);

            view.setResult(firstPolynomial.integrate());
            view.setRemainderField();
        });
    }
}
