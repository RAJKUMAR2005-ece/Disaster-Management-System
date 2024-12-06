import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DisasterManagementSystem extends JFrame {
    private JTextField disasterNameField, locationField, severityField;
    private JTextArea displayArea;
    private ArrayList<String> disasterList;

    public DisasterManagementSystem() {
        // Window setup
        setTitle("Disaster Management System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initializing components
        disasterList = new ArrayList<>();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));

        // Labels and fields
        inputPanel.add(new JLabel("Disaster Name:"));
        disasterNameField = new JTextField();
        inputPanel.add(disasterNameField);

        inputPanel.add(new JLabel("Location:"));
        locationField = new JTextField();
        inputPanel.add(locationField);

        inputPanel.add(new JLabel("Severity (1-10):"));
        severityField = new JTextField();
        inputPanel.add(severityField);

        // Buttons
        JButton addButton = new JButton("Add Disaster");
        JButton viewButton = new JButton("View All Disasters");

        inputPanel.add(addButton);
        inputPanel.add(viewButton);

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Adding components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Event listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addDisaster();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayDisasters();
            }
        });
    }

    // Method to add disaster information
    private void addDisaster() {
        String name = disasterNameField.getText();
        String location = locationField.getText();
        String severity = severityField.getText();

        if (name.isEmpty() || location.isEmpty() || severity.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                int severityValue = Integer.parseInt(severity);
                if (severityValue < 1 || severityValue > 10) {
                    JOptionPane.showMessageDialog(this, "Severity must be between 1 and 10!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String disasterInfo = "Disaster: " + name + ", Location: " + location + ", Severity: " + severity;
                    disasterList.add(disasterInfo);
                    JOptionPane.showMessageDialog(this, "Disaster added successfully!");
                    clearFields();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Severity must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to display all disasters
    private void displayDisasters() {
        displayArea.setText("");
        if (disasterList.isEmpty()) {
            displayArea.append("No disaster records available.");
        } else {
            for (String disaster : disasterList) {
                displayArea.append(disaster + "\n");
            }
        }
    }

    // Method to clear input fields
    private void clearFields() {
        disasterNameField.setText("");
        locationField.setText("");
        severityField.setText("");
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DisasterManagementSystem frame = new DisasterManagementSystem();
            frame.setVisible(true);
        });
    }
}