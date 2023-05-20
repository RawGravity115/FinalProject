import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUI {
    private JFrame frame;

    public void showUI() {
        // Create the main JFrame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a panel for the menu options
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setBackground(Color.WHITE);

        // Create a panel for the app name
        JPanel appNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        appNamePanel.setBackground(Color.WHITE);

        // Create a label for the app name
        JLabel appNameLabel = new JLabel("Airport Management System");
        appNameLabel.setForeground(Color.RED);
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Add the app name label to the app name panel
        appNamePanel.add(appNameLabel);

        // Create a panel for the access button
        JPanel accessPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        accessPanel.setBackground(Color.WHITE);

        // Create an "Access" button to enter the system
        JButton accessButton = new JButton("Access");
        accessButton.setFont(new Font("Arial", Font.PLAIN, 16));
        accessButton.setBackground(Color.RED);
        accessButton.setForeground(Color.WHITE);
        accessButton.setFocusPainted(false);
        accessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show the main menu options
                showMenuOptions();
            }
        });

        // Add the "Access" button to the access panel
        accessPanel.add(accessButton);

        // Add the app name panel and access panel to the menu panel
        menuPanel.add(appNamePanel, BorderLayout.CENTER);
        menuPanel.add(accessPanel, BorderLayout.SOUTH);

        // Add the menu panel to the frame
        frame.getContentPane().add(menuPanel, BorderLayout.CENTER);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Set the frame visible
        frame.setVisible(true);
    }

    private void showMenuOptions() {
        // Clear the frame content
        frame.getContentPane().removeAll();

        // Create a panel for the menu options
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2, 1));

        // Create buttons for the menu options
        JButton flightsButton = new JButton("Flights");
        flightsButton.setFont(new Font("Arial", Font.BOLD, 18));
        flightsButton.setFocusPainted(false);
        flightsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the FlightsUI page
                FlightsUI flightsUI = new FlightsUI();
                flightsUI.showUI();
                frame.dispose(); // Close the current menu page
            }
        });

        JButton passengersButton = new JButton("Passengers");
        passengersButton.setFont(new Font("Arial", Font.BOLD, 18));
        passengersButton.setFocusPainted(false);
        passengersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the PassengersUI page
                PassengersUI passengersUI = new PassengersUI();
                passengersUI.showUI();
                frame.dispose(); // Close the current menu page
            }
        });

        // Add the buttons to the menu panel
        menuPanel.add(flightsButton);
        menuPanel.add(passengersButton);

        // Add the menu panel to the frame
        frame.getContentPane().add(menuPanel, BorderLayout.CENTER);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Validate the frame to update its content
        frame.revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MenuUI menuUI = new MenuUI();
                menuUI.showUI();
            }
        });
    }
}
