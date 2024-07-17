import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JTextField displayField;
    private JButton[] digitButtons;
    private JButton[] operatorButtons;
    private JButton clearButton;
    private JButton equalsButton;
    private JPanel buttonPanel;
    private String operator;
    private double num1, num2, result;

    public Main() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display field
        displayField = new JTextField();
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        // Button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        // Digit buttons (0-9)
        digitButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            digitButtons[i] = new JButton(String.valueOf(i));
            digitButtons[i].addActionListener(this);
            buttonPanel.add(digitButtons[i]);
        }

        // Operator buttons (+, -, *, /)
        operatorButtons = new JButton[4];
        operatorButtons[0] = new JButton("+");
        operatorButtons[1] = new JButton("-");
        operatorButtons[2] = new JButton("*");
        operatorButtons[3] = new JButton("/");
        for (int i = 0; i < 4; i++) {
            operatorButtons[i].addActionListener(this);
            buttonPanel.add(operatorButtons[i]);
        }

        // Clear and equals buttons
        clearButton = new JButton("C");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        equalsButton = new JButton("=");
        equalsButton.addActionListener(this);
        buttonPanel.add(equalsButton);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Handle digit button clicks
        if (command.matches("\\d")) {
            String currentText = displayField.getText();
            displayField.setText(currentText + command);
        }

        // Handle operator button clicks
        else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            operator = command;
            String currentText = displayField.getText();
            num1 = Double.parseDouble(currentText);
            displayField.setText("");
        }

        // Handle clear button click
        else if (command.equals("C")) {
            displayField.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
        }

        // Handle equals button click
        else if (command.equals("=")) {
            String currentText = displayField.getText();
            num2 = Double.parseDouble(currentText);

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(this, "Error: Division by zero");
                    }
                    break;
            }

            displayField.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
