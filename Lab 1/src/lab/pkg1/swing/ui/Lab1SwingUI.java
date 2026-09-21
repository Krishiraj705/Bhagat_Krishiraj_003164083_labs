package lab.pkg1.swing.ui;

import javax.swing.*;
import java.awt.*;

public class Lab1SwingUI extends JFrame {

    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField availNumField;
    private JTextField priceField;

    private JTextField manufactureStreetField;
    private JTextField manufactureUnitField;
    private JTextField manufactureCityField;
    private JTextField manufactureZipField;

    private JTextField shippingStreetField;
    private JTextField shippingUnitField;
    private JTextField shippingCityField;
    private JTextField shippingZipField;

    private JTextArea displayArea;

    public Lab1SwingUI() {

        setTitle("Product Application");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // LEFT SIDE - FORM
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 2, 5, 5));

        nameField = new JTextField();
        descriptionField = new JTextField();
        availNumField = new JTextField();
        priceField = new JTextField();

        manufactureStreetField = new JTextField();
        manufactureUnitField = new JTextField();
        manufactureCityField = new JTextField();
        manufactureZipField = new JTextField();

        shippingStreetField = new JTextField();
        shippingUnitField = new JTextField();
        shippingCityField = new JTextField();
        shippingZipField = new JTextField();

        formPanel.add(new JLabel("Product Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Description:"));
        formPanel.add(descriptionField);

        formPanel.add(new JLabel("Available Number:"));
        formPanel.add(availNumField);

        formPanel.add(new JLabel("Price:"));
        formPanel.add(priceField);

        formPanel.add(new JLabel("Manufacture Street:"));
        formPanel.add(manufactureStreetField);

        formPanel.add(new JLabel("Manufacture Unit:"));
        formPanel.add(manufactureUnitField);

        formPanel.add(new JLabel("Manufacture City:"));
        formPanel.add(manufactureCityField);

        formPanel.add(new JLabel("Manufacture Zip Code:"));
        formPanel.add(manufactureZipField);

        formPanel.add(new JLabel("Shipping Street:"));
        formPanel.add(shippingStreetField);

        formPanel.add(new JLabel("Shipping Unit:"));
        formPanel.add(shippingUnitField);

        formPanel.add(new JLabel("Shipping City:"));
        formPanel.add(shippingCityField);

        formPanel.add(new JLabel("Shipping Zip Code:"));
        formPanel.add(shippingZipField);

        JButton displayButton = new JButton("Display Product");

        formPanel.add(displayButton);
        formPanel.add(new JLabel(""));

        // RIGHT SIDE - DISPLAY
        JPanel displayPanel = new JPanel(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Arial", Font.PLAIN, 14));

        displayPanel.add(new JLabel("Product Details"), BorderLayout.NORTH);
        displayPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // JSPLITPANE
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(formPanel),
                displayPanel
        );

        splitPane.setDividerLocation(400);

        add(splitPane);

        // BUTTON
        displayButton.addActionListener(e -> displayProduct());
    }

    private void displayProduct() {

        String name = nameField.getText();
        String description = descriptionField.getText();
        String availText = availNumField.getText();
        String priceText = priceField.getText();

        String manufactureStreet = manufactureStreetField.getText();
        String manufactureUnit = manufactureUnitField.getText();
        String manufactureCity = manufactureCityField.getText();
        String manufactureZip = manufactureZipField.getText();

        String shippingStreet = shippingStreetField.getText();
        String shippingUnit = shippingUnitField.getText();
        String shippingCity = shippingCityField.getText();
        String shippingZip = shippingZipField.getText();

        // IF-ELSE VALIDATION
        if (name.isEmpty() || description.isEmpty()
                || availText.isEmpty() || priceText.isEmpty()
                || manufactureStreet.isEmpty()
                || manufactureUnit.isEmpty()
                || manufactureCity.isEmpty()
                || manufactureZip.isEmpty()
                || shippingStreet.isEmpty()
                || shippingUnit.isEmpty()
                || shippingCity.isEmpty()
                || shippingZip.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        try {

            int availNum = Integer.parseInt(availText);
            double price = Double.parseDouble(priceText);

            Address manufactureAddress = new Address(
                    manufactureStreet,
                    manufactureUnit,
                    manufactureCity,
                    manufactureZip
            );

            Address shippingAddress = new Address(
                    shippingStreet,
                    shippingUnit,
                    shippingCity,
                    shippingZip
            );

            Product product = new Product(
                    name,
                    description,
                    availNum,
                    price,
                    manufactureAddress,
                    shippingAddress
            );

            displayArea.setText(product.toString());

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Available Number must be an integer and Price must be a number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Lab1SwingUI application = new Lab1SwingUI();
            application.setVisible(true);
        });
    }
}