import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

public class PassengersUI {
    private DefaultTableModel tableModel;
    private JTable table;
    private String csvFilePath = "C:\\Users\\Eduar\\Desktop\\passengers.txt";

    public void showUI() {
        // Create the main JFrame
        JFrame frame = new JFrame("Passengers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create a table to display passenger information
        String[] columnNames = {"Name", "Surname", "Passport ID"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 12));

        // Load data from CSV file
        loadPassengers();

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Create a button for "Go Back"
        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Go back to the menu page
                MenuUI menuUI = new MenuUI();
                menuUI.showUI();
                frame.dispose(); // Close the current passengers page
            }
        });

        // Create buttons for adding and deleting passengers
        JButton addButton = new JButton("Add Passenger");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open a dialog or perform action to add a new passenger
                addPassenger();
            }
        });

        JButton deleteButton = new JButton("Delete Passenger");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Delete the selected passenger from the table and CSV file
                deletePassenger();
            }
        });

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(goBackButton);

        // Add the scroll pane and button panel to the frame
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Set the frame visible
        frame.setVisible(true);
    }

    private void loadPassengers() {
        // Read data from the CSV file and populate the table
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addPassenger() {
        // Open a dialog or perform action to add a new passenger
        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField passportIdField = new JTextField();

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Surname:"));
        inputPanel.add(surnameField);
        inputPanel.add(new JLabel("Passport ID:"));
        inputPanel.add(passportIdField);

        int result = JOptionPane.showConfirmDialog(
                table,
                inputPanel,
                "Add Passenger",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String surname = surnameField.getText().trim();
            String passportId = passportIdField.getText().trim();

            // Validate name and surname (only letters)
            if (!name.matches("[a-zA-Z]+") || !surname.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(
                        table,
                        "Invalid name or surname. Only letters are allowed.",
                        "Add Passenger",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Validate passport ID (exactly 6 characters)
            if (passportId.length() != 6) {
                JOptionPane.showMessageDialog(
                        table,
                        "Invalid passport ID. It should contain exactly 6 characters.",
                        "Add Passenger",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Add the new passenger to the table and CSV file
            Vector<String> passengerData = new Vector<>();
            passengerData.add(name);
            passengerData.add(surname);
            passengerData.add(passportId);
            tableModel.addRow(passengerData);

            // Append the new passenger data to the CSV file
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true));
                writer.write(name + "," + surname + "," + passportId);
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deletePassenger() {
        // Get the selected row
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(table, "Please select a passenger to delete.", "Delete Passenger", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirm the deletion
        int confirm = JOptionPane.showConfirmDialog(table, "Are you sure you want to delete this passenger?", "Delete Passenger", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Delete the selected passenger from the table and CSV file
            tableModel.removeRow(selectedRow);

            // Rewrite the updated data to the CSV file
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, false));
                for (int row = 0; row < tableModel.getRowCount(); row++) {
                    for (int col = 0; col < tableModel.getColumnCount(); col++) {
                        writer.write((String) tableModel.getValueAt(row, col));
                        if (col < tableModel.getColumnCount() - 1) {
                            writer.write(",");
                        }
                    }
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PassengersUI().showUI();
            }
        });
    }
}
