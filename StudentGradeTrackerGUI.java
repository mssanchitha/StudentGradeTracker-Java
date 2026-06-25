import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class StudentGradeTrackerGUI extends JFrame implements ActionListener {
    private JTextField nameField, gradeField;
    private JTextArea outputArea;
    private JButton addButton, reportButton, clearButton;
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<Integer> grades = new ArrayList<>();

    public StudentGradeTrackerGUI() {
        setTitle("Student Grade Tracker");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // 🎨 Background color
        getContentPane().setBackground(new Color(230, 240, 255)); // light blue

        // 🧩 Labels and input fields
        add(new JLabel("Student Name:"));
        nameField = new JTextField(15);
        add(nameField);

        add(new JLabel("Grade:"));
        gradeField = new JTextField(5);
        add(gradeField);

        // 🖱 Buttons
        addButton = new JButton("Add Student");
        reportButton = new JButton("Show Report");
        clearButton = new JButton("Clear");
        add(addButton);
        add(reportButton);
        add(clearButton);

        // 📝 Output area
        outputArea = new JTextArea(12, 35);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        outputArea.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(outputArea));

        // 🔗 Button actions
        addButton.addActionListener(this);
        reportButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = nameField.getText().trim();
            String gradeText = gradeField.getText().trim();

            if (name.isEmpty() || gradeText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both name and grade.");
                return;
            }

            try {
                int grade = Integer.parseInt(gradeText);
                names.add(name);
                grades.add(grade);
                outputArea.append("Added: " + name + " - " + grade + "\n");
                nameField.setText("");
                gradeField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Grade must be a number.");
            }
        } else if (e.getSource() == reportButton) {
            if (grades.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No students added yet!");
                return;
            }

            double avg = calculateAverage(grades);
            int highest = Collections.max(grades);
            int lowest = Collections.min(grades);

            outputArea.append("\n--- Student Grade Report ---\n");
            for (int i = 0; i < names.size(); i++) {
                outputArea.append(names.get(i) + " : " + grades.get(i) + "\n");
            }
            outputArea.append("-----------------------------\n");
            outputArea.append("Average Score: " + avg + "\n");
            outputArea.append("Highest Score: " + highest + "\n");
            outputArea.append("Lowest Score: " + lowest + "\n");
        } else if (e.getSource() == clearButton) {
            names.clear();
            grades.clear();
            outputArea.setText("");
            nameField.setText("");
            gradeField.setText("");
        }
    }

    private double calculateAverage(ArrayList<Integer> grades) {
        int sum = 0;
        for (int g : grades) sum += g;
        return (double) sum / grades.size();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentGradeTrackerGUI().setVisible(true);
        });
    }
}

