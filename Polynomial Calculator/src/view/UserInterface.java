package view;

import model.Polynomial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserInterface implements Runnable {

    private JFrame frame;
    private JPanel inputPanel = new JPanel();
    private JPanel operationsPanel = new JPanel();
    private JPanel outputPanel = new JPanel();
    private JLabel polynomialLabel1 = new JLabel("Polynomial 1:");
    private JLabel polynomialLabel2 = new JLabel("Polynomial 2:");
    private JLabel resultLabel = new JLabel("Result:");
    private JLabel remainderLabel = new JLabel("Remainder:");
    private JTextField polynomialField1 = new JTextField(30);
    private JTextField polynomialField2 = new JTextField(30);
    private JTextField resultField = new JTextField(30);
    private JTextField remainderField = new JTextField("Used for division", 30);
    private JButton addButton = new JButton("Add");
    private JButton subtractButton = new JButton("Subtract");
    private JButton multiplyButton = new JButton("Multiply");
    private JButton divideButton = new JButton("Divide");
    private JButton differentiateButton = new JButton("Differentiate");
    private JButton integrateButton = new JButton("Integrate");

    public UserInterface() {
    }

    private void createComponents(Container container) {

        container.setLayout(new GridLayout(3, 1));

        inputPanel.setLayout(new GridLayout(2, 2));
        operationsPanel.setLayout(new GridLayout(3, 2));
        outputPanel.setLayout(new GridLayout(2, 2));

        inputPanel.add(polynomialLabel1);
        inputPanel.add(polynomialField1);
        inputPanel.add(polynomialLabel2);
        inputPanel.add(polynomialField2);

        operationsPanel.add(addButton);
        operationsPanel.add(subtractButton);
        operationsPanel.add(multiplyButton);
        operationsPanel.add(divideButton);
        operationsPanel.add(differentiateButton);
        operationsPanel.add(integrateButton);

        outputPanel.add(resultLabel);
        outputPanel.add(resultField);
        outputPanel.add(remainderLabel);
        outputPanel.add(remainderField);

        container.add(inputPanel);
        container.add(operationsPanel);
        container.add(outputPanel);

        resultField.setEditable(false);
        remainderField.setEditable(false);
    }

    public String getPolynomial1() {
        return polynomialField1.getText();
    }

    public String getPolynomial2() {
        return polynomialField2.getText();
    }

    public void setResult(Polynomial polynomial) {
        resultField.setText(polynomial.toString());
    }

    public void setRemainderField() {
        remainderField.setText("Used for division");
    }

    public void setRemainderField(Polynomial polynomial) {
        remainderField.setText(polynomial.toString());
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void addListeners(ActionListener addListener, ActionListener subtractListener, ActionListener multiplyListener,
                             ActionListener divideListener, ActionListener differentiateListener,
                             ActionListener integrateListener) {

        addButton.addActionListener(addListener);
        subtractButton.addActionListener(subtractListener);
        multiplyButton.addActionListener(multiplyListener);
        divideButton.addActionListener(divideListener);
        differentiateButton.addActionListener(differentiateListener);
        integrateButton.addActionListener(integrateListener);
    }

    public void run() {
        frame = new JFrame("Polynomial Calculator");
        frame.setPreferredSize(new Dimension(600, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
