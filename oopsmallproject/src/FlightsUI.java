import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

public class FlightsUI {
    private DefaultTableModel tableModel;
    private JTable table;
    private JFrame frame;
    private String csvFilePath = "C:\\Users\\Eduar\\Desktop\\flights.txt";

    public static void main(String[] args) {
        FlightsUI flightsUI = new FlightsUI();
        flightsUI.showUI();
    }

    public void showUI() {
        // Create the main JFrame
        frame = new JFrame("Flights");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create a table to display flight information
        String[] columnNames = {"Flight Number", "Airline", "Departure", "Arrival"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 12));

        // Load data from CSV file
        loadFlights();

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Create a button for "Go Back"
        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Go back to the menu page
                MenuUI menuUI = new MenuUI();
                menuUI.showUI();
                frame.dispose(); // Close the current flights page
            }
        });

        // Create buttons for adding and deleting flights
        JButton addButton = new JButton("Add Flight");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open a dialog or perform action to add a new flight
                addFlight();
            }
        });

        JButton deleteButton = new JButton("Delete Flight");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Delete the selected flight from the table
                deleteFlight();
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

    private void loadFlights() {
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

    private void addFlight() {
        // Open a dialog or perform action to add a new flight
        JTextField flightNumberField = new JTextField();
        JTextField airlineField = new JTextField();
        JTextField departureField = new JTextField();
        JTextField arrivalField = new JTextField();

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Flight Number:"));
        inputPanel.add(flightNumberField);
        inputPanel.add(new JLabel("Airline:"));
        inputPanel.add(airlineField);
        inputPanel.add(new JLabel("Departure Airport:"));
        inputPanel.add(departureField);
        inputPanel.add(new JLabel("Arrival Airport:"));
        inputPanel.add(arrivalField);

        int result = JOptionPane.showConfirmDialog(
                frame,
                inputPanel,
                "Add Flight",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String flightNumber = flightNumberField.getText().trim();
            String airline = airlineField.getText().trim();
            String departure = departureField.getText().trim();
            String arrival = arrivalField.getText().trim();

            // Validate flight number format
            if (!isValidFlightNumber(flightNumber)) {
                JOptionPane.showMessageDialog(frame, "Flight number must be 5 characters long and contain only capital letters and numbers.", "Invalid Flight Number", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validate airline format
            if (containsNumbers(airline)) {
                JOptionPane.showMessageDialog(frame, "Airline name cannot contain numbers.", "Invalid Airline", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validate departure format
            if (containsNumbers(departure)) {
                JOptionPane.showMessageDialog(frame, "Departure airport name cannot contain numbers.", "Invalid Departure Airport", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validate arrival format
            if (containsNumbers(arrival)) {
                JOptionPane.showMessageDialog(frame, "Arrival airport name cannot contain numbers.", "Invalid Arrival Airport", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Add the new flight to the table
            Vector<String> flightData = new Vector<>();
            flightData.add(flightNumber);
            flightData.add(airline);
            flightData.add(departure);
            flightData.add(arrival);
            tableModel.addRow(flightData);

            // Append the new flight data to the CSV file
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true));
                writer.write(flightNumber + "," + airline + "," + departure + "," + arrival);
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isValidFlightNumber(String flightNumber) {
        return flightNumber.matches("[A-Z0-9]{5}");
    }

    private boolean containsNumbers(String text) {
        return text.matches(".*\\d.*");
    }


    private void deleteFlight() {
        // Get the selected row
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a flight to delete.", "Delete Flight", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirm the deletion
        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this flight?", "Delete Flight", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Delete the selected flight from the table
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
}
