
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppointmentBooking {

    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Hospital Management - Appointment Booking");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        // UI Components
        JLabel patientIdLabel = new JLabel("Patient ID:");
        JTextField patientIdField = new JTextField();

        JLabel doctorIdLabel = new JLabel("Doctor ID:");
        JTextField doctorIdField = new JTextField();

        JLabel dateLabel = new JLabel("Appointment Date (YYYY-MM-DD):");
        JTextField dateField = new JTextField();

        JButton bookButton = new JButton("Book Appointment");
        JButton resetButton = new JButton("Reset");

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Adding components to the frame
        frame.add(patientIdLabel);
        frame.add(patientIdField);
        frame.add(doctorIdLabel);
        frame.add(doctorIdField);
        frame.add(dateLabel);
        frame.add(dateField);
        frame.add(bookButton);
        frame.add(resetButton);
        frame.add(new JLabel("Appointments:"));
        frame.add(scrollPane);

        // Button actions
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientId = patientIdField.getText();
                String doctorId = doctorIdField.getText();
                String date = dateField.getText();

                if (patientId.isEmpty() || doctorId.isEmpty() || date.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String appointmentInfo = "Patient ID: " + patientId + ", Doctor ID: " + doctorId + ", Date: " + date;
                    displayArea.append(appointmentInfo + "\n");
                    JOptionPane.showMessageDialog(frame, "Appointment booked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Clear the fields
                    patientIdField.setText("");
                    doctorIdField.setText("");
                    dateField.setText("");
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                patientIdField.setText("");
                doctorIdField.setText("");
                dateField.setText("");
            }
        });

        // Display frame
        frame.setVisible(true);
    }
}