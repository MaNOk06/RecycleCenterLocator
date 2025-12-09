package main;

import service.RecyclingLocator;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SimpleDialogGUI {
    private static RecyclingLocator locator;

    public static void main(String[] args) {
        // Initialize the system once
        locator = new RecyclingLocator();

        // Try to load data
        SwingUtilities.invokeLater(() -> {
            showLoadingDialog();
        });
    }

    private static void showLoadingDialog() {
        int choice = JOptionPane.showOptionDialog(null,
                "Welcome to EcoFind Recycling Centre Locator!\n\n" +
                        "This system helps you find recycling centres\n" +
                        "that accept specific materials near you.",
                "EcoFind - Startup",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Load Data", "Exit"},
                "Load Data");

        if (choice == 0) {
            loadDataAndShowMainMenu();
        } else {
            System.exit(0);
        }
    }

    private static void loadDataAndShowMainMenu() {
        // Show loading message
        JOptionPane.showMessageDialog(null,
                "Loading recycling centre data from file...",
                "Loading",
                JOptionPane.INFORMATION_MESSAGE);

        // Initialize with your data file
        locator.initialize("src/centres.txt");

        // Show success message
        int centreCount = locator.getCentreCount();
        JOptionPane.showMessageDialog(null,
                "Successfully loaded " + centreCount + " recycling centres!",
                "Data Loaded",
                JOptionPane.INFORMATION_MESSAGE);

        // Show main menu
        showMainMenu();
    }

    private static void showMainMenu() {
        while (true) {
            String[] options = {
                    "🔍 Search by Material",
                    "📍 Find Nearest Centre",
                    "📋 List All Centres",
                    "❌ Exit"
            };

            int choice = JOptionPane.showOptionDialog(null,
                    "EcoFind Main Menu\n\n" +
                            "Select an option:",
                    "Main Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (choice == 0) {
                searchByMaterial();
            } else if (choice == 1) {
                findNearestCentre();
            } else if (choice == 2) {
                listAllCentres();
            } else {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null,
                            "Thank you for using EcoFind!\n" +
                                    "♻️ Help make our planet greener! ♻️",
                            "Goodbye",
                            JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
        }
    }

    private static void searchByMaterial() {
        // Create dropdown for materials
        String[] materials = {
                "PLASTIC", "PAPER", "GLASS", "METAL",
                "ORGANIC", "ELECTRONIC", "TEXTILE"
        };

        String selectedMaterial = (String) JOptionPane.showInputDialog(null,
                "Select the material you want to recycle:",
                "Search by Material",
                JOptionPane.QUESTION_MESSAGE,
                null,
                materials,
                materials[0]);

        if (selectedMaterial != null) {
            try {
                Material material = Material.valueOf(selectedMaterial);
                List<RecyclingCentre> centres = locator.findCentresByMaterial(material);

                if (centres.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "No centres found accepting " + selectedMaterial + ".\n" +
                                    "Try another material.",
                            "No Results",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    // Build results message
                    StringBuilder message = new StringBuilder();
                    message.append("Found ").append(centres.size())
                            .append(" centres accepting ").append(selectedMaterial).append(":\n\n");

                    for (int i = 0; i < centres.size(); i++) {
                        RecyclingCentre centre = centres.get(i);
                        message.append(i + 1).append(". ").append(centre.getName()).append("\n");
                        message.append("   Location: ").append(centre.getLocation().getPlaceName()).append("\n");

                        // Check if it's an eco centre
                        if (centre instanceof EcoRecyclingCentre) {
                            message.append("   Type: ♻️ Eco Centre\n");
                        }

                        message.append("   Materials: ").append(centre.getMaterials()).append("\n\n");
                    }

                    // Show results in scrollable dialog
                    JTextArea textArea = new JTextArea(message.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(500, 300));

                    JOptionPane.showMessageDialog(null,
                            scrollPane,
                            "Search Results",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Error: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void findNearestCentre() {
        // Get user location
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Your X coordinate:"));
        JTextField xField = new JTextField("100");
        panel.add(xField);
        panel.add(new JLabel("Your Y coordinate:"));
        JTextField yField = new JTextField("200");
        panel.add(yField);

        int result = JOptionPane.showConfirmDialog(null,
                panel,
                "Enter Your Location",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int x = Integer.parseInt(xField.getText());
                int y = Integer.parseInt(yField.getText());

                Location userLocation = new Location("Your Location", x, y);
                RecyclingCentre nearest = locator.findNearestCentre(userLocation);

                if (nearest != null) {
                    double distance = nearest.getLocation().computeDistance(userLocation);

                    String message = "📍 Nearest Recycling Centre:\n\n" +
                            "Name: " + nearest.getName() + "\n" +
                            "Location: " + nearest.getLocation().getPlaceName() + "\n" +
                            "Distance: " + String.format("%.1f", distance) + " units\n" +
                            "Materials: " + nearest.getMaterials() + "\n\n";

                    if (nearest instanceof EcoRecyclingCentre) {
                        message += "♻️ This is an Eco Recycling Centre!";
                    }

                    JOptionPane.showMessageDialog(null,
                            message,
                            "Nearest Centre Found",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "No recycling centres found in the system.",
                            "No Centres",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Please enter valid numbers for coordinates.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void listAllCentres() {
        List<RecyclingCentre> allCentres = locator.getServiceLogic().getCentres();

        if (allCentres.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No centres loaded in the system.",
                    "Empty System",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder message = new StringBuilder();
        message.append("Total Recycling Centres: ").append(allCentres.size()).append("\n\n");

        for (int i = 0; i < allCentres.size(); i++) {
            RecyclingCentre centre = allCentres.get(i);
            message.append("🏢 ").append(centre.getName()).append("\n");
            message.append("   📍 ").append(centre.getLocation().getPlaceName()).append("\n");
            message.append("   📊 Materials: ").append(centre.getMaterials()).append("\n");

            if (centre instanceof EcoRecyclingCentre) {
                message.append("   ♻️ Type: Eco Recycling Centre\n");
            }

            message.append("\n");
        }

        // Show in scrollable dialog
        JTextArea textArea = new JTextArea(message.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(null,
                scrollPane,
                "All Recycling Centres",
                JOptionPane.INFORMATION_MESSAGE);
    }
}