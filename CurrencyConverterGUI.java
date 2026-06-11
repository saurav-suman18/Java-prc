import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverterGUI extends JFrame implements ActionListener {

    JLabel titleLabel, amountLabel, currencyLabel, resultLabel;
    JTextField amountField;
    JComboBox<String> currencyBox;
    JButton convertButton;

    public CurrencyConverterGUI() {

        setTitle("Global Currency Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(5, 2, 10, 10));

        titleLabel = new JLabel("GLOBAL CURRENCY CONVERTER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        amountLabel = new JLabel("Enter Amount:");
        amountField = new JTextField();

        currencyLabel = new JLabel("Select Currency:");
        String[] currencies = {"USD", "EUR", "GBP"};
        currencyBox = new JComboBox<>(currencies);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

        resultLabel = new JLabel("Converted Amount: ");

        add(titleLabel);
        add(new JLabel(""));

        add(amountLabel);
        add(amountField);

        add(currencyLabel);
        add(currencyBox);

        add(convertButton);
        add(new JLabel(""));

        add(resultLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            double amount = Double.parseDouble(amountField.getText());
            String currency = currencyBox.getSelectedItem().toString();

            double result = ConverterEngine.convert(amount, currency);

            resultLabel.setText("Converted Amount: ₹ " + result);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid amount!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new CurrencyConverterGUI();
    }
}

class ConverterEngine {

    public static final double USD_TO_INR = 83.0;
    public static final double EUR_TO_INR = 90.0;
    public static final double GBP_TO_INR = 105.0;

    public static double convert(double amount, String currencyType) {

        switch (currencyType.toUpperCase()) {

            case "USD":
                return amount * USD_TO_INR;

            case "EUR":
                return amount * EUR_TO_INR;

            case "GBP":
                return amount * GBP_TO_INR;

            default:
                return 0;
        }
    }
}