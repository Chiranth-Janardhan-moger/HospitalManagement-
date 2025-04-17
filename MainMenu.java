import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.*;

// Class representing a hospital
class Hospital {
    String name;
    double rating;
    double price;
    String city;
    String imagePath; // Path to hospital image

    public Hospital(String name, double rating, double price, String city, String imagePath) {
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.city = city;
        this.imagePath = imagePath;
    }
}

public class MainMenu {
    private static ArrayList<Hospital> hospitals = new ArrayList<>();
    private static JFrame mainFrame;

    public static void main(String[] args) {
        // Add sample hospitals with images
        hospitals.add(new Hospital("Apollo", 4.5, 300.0, "Bangalore", "./image/apollo.png"));
        hospitals.add(new Hospital("Fortis", 4.2, 250.0, "Bangalore", "./image/fortis_hospital.png"));
        hospitals.add(new Hospital("Manipal", 4.7, 200.0, "Chennai", "./image/manipal_hospital.png"));
        hospitals.add(new Hospital("SIMS", 4.3, 180.0, "Chennai", "./image/sims_hospital.png"));
        hospitals.add(new Hospital("KMC", 4.8, 280.0, "Mangalore", "kmc_hospital.png"));
        hospitals.add(new Hospital("A.J Hospital", 4.4, 220.0, "Mangalore", "./image/aj_hospital.png"));
        hospitals.add(new Hospital("BGS Global", 4.6, 350.0, "Bangalore", "./image/global.png"));
        


        showLogin();
    }

    // Login screen
    private static void showLogin() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 300);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField();
        JButton loginButton = new JButton("Login");

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passLabel);
        panel.add(passText);
        panel.add(new JLabel()); // Empty space
        panel.add(loginButton);

        loginFrame.add(panel);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

        loginButton.addActionListener(e -> {
            // Simplified login check
            if ("a".equals(userText.getText()) && "123".equals(new String(passText.getPassword()))) {
                loginFrame.dispose();
                showMainMenu();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Main Menu
    private static void showMainMenu() {
        mainFrame = new JFrame("Main Menu");
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        JButton sortByPlaceButton = new JButton("Sort by Place");
        JButton exitButton = new JButton("Exit");

        panel.add(sortByPlaceButton);
        panel.add(exitButton);

        mainFrame.add(panel);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        sortByPlaceButton.addActionListener(e -> showCityOptions());
        exitButton.addActionListener(e -> System.exit(0));
    }

    // City Options
    private static void showCityOptions() {
        JFrame cityFrame = new JFrame("Select City");
        cityFrame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        JButton bangaloreButton = new JButton("Bangalore");
        JButton chennaiButton = new JButton("Chennai");
        JButton mangaloreButton = new JButton("Mangalore");
        JButton backButton = new JButton("Go to Main Menu");

        panel.add(bangaloreButton);
        panel.add(chennaiButton);
        panel.add(mangaloreButton);
        panel.add(backButton);

        cityFrame.add(panel);
        cityFrame.setLocationRelativeTo(null);
        cityFrame.setVisible(true);

        bangaloreButton.addActionListener(e -> showHospitalOptions("Bangalore"));
        chennaiButton.addActionListener(e -> showHospitalOptions("Chennai"));
        mangaloreButton.addActionListener(e -> showHospitalOptions("Mangalore"));
        backButton.addActionListener(e -> {
            cityFrame.dispose();
            mainFrame.setVisible(true);
        });
    }

    // Hospital Options
    private static void showHospitalOptions(String city) {
        JFrame hospitalFrame = new JFrame(city + " Hospitals");
        hospitalFrame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        JButton allHospitalsButton = new JButton("All Hospital List");
        JButton sortByRatingButton = new JButton("Sort Hospitals by Rating");
        JButton sortByPriceButton = new JButton("Sort Hospitals by Price");
        JButton backButton = new JButton("Go to City Menu");

        panel.add(allHospitalsButton);
        panel.add(sortByRatingButton);
        panel.add(sortByPriceButton);
        panel.add(backButton);

        hospitalFrame.add(panel);
        hospitalFrame.setLocationRelativeTo(null);
        hospitalFrame.setVisible(true);

        allHospitalsButton.addActionListener(e -> displayHospitals(city, "ALL"));
        sortByRatingButton.addActionListener(e -> displayHospitals(city, "RATING"));
        sortByPriceButton.addActionListener(e -> displayHospitals(city, "PRICE"));
        backButton.addActionListener(e -> hospitalFrame.dispose());
    }

    // Display Hospitals
    private static void displayHospitals(String city, String sortType) {
        ArrayList<Hospital> cityHospitals = new ArrayList<>();
        for (Hospital h : hospitals) {
            if (h.city.equalsIgnoreCase(city)) {
                cityHospitals.add(h);
            }
        }

        // Sort based on type
        switch (sortType) {
            case "RATING":
                cityHospitals.sort(Comparator.comparingDouble(h -> -h.rating));
                break;
            case "PRICE":
                cityHospitals.sort(Comparator.comparingDouble(h -> h.price));
                break;
        }

        JFrame displayFrame = new JFrame(city + " Hospitals");
        displayFrame.setSize(500, 400);

        JPanel panel = new JPanel(new GridLayout(cityHospitals.size(), 1, 10, 10));
        for (Hospital h : cityHospitals) {
            String displayText = h.name;
            if ("RATING".equals(sortType)) {
                displayText += " - Rating: " + h.rating;
            } else if ("PRICE".equals(sortType)) {
                displayText += " - Price: $" + h.price;
            }

            JLabel hospitalLabel = new JLabel(displayText);
            if (h.imagePath != null) {
                hospitalLabel.setIcon(new ImageIcon(h.imagePath));
            }
            panel.add(hospitalLabel);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        displayFrame.add(scrollPane);
        displayFrame.setLocationRelativeTo(null);
        displayFrame.setVisible(true);
    }
}
