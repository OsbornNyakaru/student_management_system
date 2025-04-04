import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Student {
    // Theme colors - matching AdminGUI
    public static final Color DARK_BG = new Color(25, 25, 25);
    public static final Color DARKER_BG = new Color(15, 15, 15);
    public static final Color ORANGE_ACCENT = new Color(255, 140, 0);
    public static final Color LIGHT_TEXT = new Color(240, 240, 240);
    public static final Color DARK_TEXT = new Color(25, 25, 25);
    public static final Color SUCCESS_COLOR = new Color(0, 200, 0);
    public static final Color ERROR_COLOR = new Color(255, 60, 60);
    
    private String regNo;
    private String fullname;
    private String programme;
    private String email;
    private String phoneNo;
    private ArrayList<String> courseCode;
    private ArrayList<String> courseCodeMarks;
    public String message = "";  // Made public to access from GUI

    // Constructor for class student
    public Student(String regNo) {
        if (!AdminGUI.isValidRegNo(regNo)) {
            throw new IllegalArgumentException("Invalid registration number format. Use format: EB1/66784/23");
        }
        this.regNo = regNo;
        this.courseCode = new ArrayList<>(); 
        this.courseCodeMarks = new ArrayList<>(); 
    }

    // Method for register Student
    public void registerStudent(String fullname, String programme, String email, String phoneNo) {
        this.fullname = fullname;
        this.programme = programme;
        this.email = email;
        this.phoneNo = phoneNo;
        String filename = regNo.replace("/", "_") + ".txt";
        File file = new File(filename);

        try {
            if (file.createNewFile()) {
                // File doesn't exist, create & write data
                try (BufferedWriter myWriter = new BufferedWriter(new FileWriter(filename))) {
                    myWriter.write("Reg No: " + regNo + "\n");
                    myWriter.flush();
                    myWriter.write("Full Name: " + fullname + "\n");
                    myWriter.flush();
                    myWriter.write("Programme: " + programme + "\n");
                    myWriter.flush();
                    myWriter.write("Email: " + email + "\n");
                    myWriter.flush();
                    myWriter.write("Phone: " + phoneNo + "\n");
                }
                this.message = "Successfully registered: " + regNo;
                showSuccessMessage(this.message);
            } else {
                this.message = "The user already exists.";
                showWarningMessage(this.message);
            }
        } catch (IOException e) {
            this.message = "Error: " + e.getMessage();
            showErrorMessage(this.message);
        }
    }

    public void enrollCourse(String course) {
        try {
            File myObj = new File(regNo + ".txt");
            if (myObj.exists()) {
                FileWriter writer = new FileWriter(myObj, true); // Append mode
                writer.append("\nCourses Enrolled:\n--------------------------------------------------\n");
                writer.append(course).append("\n");
                writer.close();
                this.message = "Enrolling successful";
                showSuccessMessage(this.message);
            } else {
                this.message = "The user does not exist! Register first!";
                showWarningMessage(this.message);
            }
        } catch (Exception e) {
            this.message = "Server: error enrolling course!";
            showErrorMessage(this.message);
            e.printStackTrace();
        }
    }

    // Assign student scores
    public void assignStudentResults(String code, int score) {
        try {
            File myObj = new File(regNo + ".txt");
            if (myObj.exists()) {
                // Read registered courses
                List<String> registeredCourses = new ArrayList<>();
                Scanner scanner = new Scanner(myObj);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.startsWith("CourseCode: ")) {
                        registeredCourses.add(line.split(":")[1].trim());
                    }
                }
                scanner.close();

                // Check if the course is registered
                if (registeredCourses.contains(code)) {
                    // Append results
                    FileWriter fw = new FileWriter(regNo + ".txt", true); // Enable append mode
                    BufferedWriter writer = new BufferedWriter(fw);

                    writer.newLine();
                    writer.write("--------------------------------------------------");
                    writer.newLine();
                    writer.write("Results Section:");
                    writer.newLine();
                    writer.write("CourseCode: " + code + " | Score: " + score);
                    writer.newLine();
                    writer.close();

                    this.message = "Results assigned successfully!";
                    showSuccessMessage(this.message);
                } else {
                    this.message = "Error: Course " + code + " is not registered for this student!";
                    showWarningMessage(this.message);
                }
            } else {
                this.message = "Unregistered student!";
                showErrorMessage(this.message);
            }
        } catch (Exception e) {
            this.message = "Error assigning results!";
            showErrorMessage(this.message);
            e.printStackTrace();
        }
    }

    // Method for searching a student
    public void searchStudent() {
        try {
            File myObj = new File(regNo + ".txt");
            
            if (myObj.exists()) {
                StringBuilder details = new StringBuilder();
                Scanner scanner = new Scanner(myObj);
                
                while (scanner.hasNextLine()) {
                    details.append(scanner.nextLine()).append("\n");
                }
                
                scanner.close();
                displayStudentDetails(details.toString());
                this.message = "Student exists!";
            } else {
                this.message = "The student does not exist!";
                showWarningMessage(this.message);
            }
        } catch (Exception e) {
            this.message = "Error accessing student!";
            showErrorMessage(this.message);
            e.printStackTrace();
        }
    }

    // Method for retrieving students results
    public void retrieveStudentResults(String courseCode) {
        try {
            File myobj = new File(regNo + ".txt");
            if (myobj.exists()) {
                Scanner scanner = new Scanner(myobj);
                String line;
                boolean found = false;
                StringBuilder results = new StringBuilder();
                
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    if (line.contains("Results Section:")) {
                        results.append("Results for ").append(regNo).append(":\n");
                    }
                    if (line.contains("CourseCode: " + courseCode)) {
                        results.append(line).append("\n");
                        found = true;
                    }
                }
                scanner.close();
                
                if (found) {
                    displayResults(results.toString());
                } else {
                    this.message = "No results found for course: " + courseCode;
                    showWarningMessage(this.message);
                }
            } else {
                this.message = "No student details found. Register first!";
                showWarningMessage(this.message);
            }
        } catch (Exception e) {
            this.message = "Server error: searching student result";
            showErrorMessage(this.message);
            e.printStackTrace();
        }
    }

    public void updateResults() {
        // Create a themed dialog for updating results
        JFrame frame = new JFrame("Update Results");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 300);
        frame.getContentPane().setBackground(DARK_BG);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(DARK_BG);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Header
        JLabel headerLabel = new JLabel("UPDATE RESULTS");
        headerLabel.setForeground(ORANGE_ACCENT);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(DARK_BG);
        
        JLabel courseLabel = new JLabel("Course Code:");
        courseLabel.setForeground(LIGHT_TEXT);
        courseLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JTextField courseField = new JTextField();
        courseField.setBackground(DARKER_BG);
        courseField.setForeground(LIGHT_TEXT);
        courseField.setCaretColor(LIGHT_TEXT);
        courseField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ORANGE_ACCENT, 1),
                BorderFactory.createEmptyBorder(5, 7, 5, 7)));
        
        JLabel scoreLabel = new JLabel("New Score:");
        scoreLabel.setForeground(LIGHT_TEXT);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JTextField scoreField = new JTextField();
        scoreField.setBackground(DARKER_BG);
        scoreField.setForeground(LIGHT_TEXT);
        scoreField.setCaretColor(LIGHT_TEXT);
        scoreField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ORANGE_ACCENT, 1),
                BorderFactory.createEmptyBorder(5, 7, 5, 7)));
        
        formPanel.add(courseLabel);
        formPanel.add(courseField);
        formPanel.add(scoreLabel);
        formPanel.add(scoreField);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(DARK_BG);
        
        JButton updateButton = new JButton("Update");
        updateButton.setBackground(ORANGE_ACCENT);
        updateButton.setForeground(DARK_TEXT);
        updateButton.setFocusPainted(false);
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(DARKER_BG, 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(DARKER_BG);
        cancelButton.setForeground(LIGHT_TEXT);
        cancelButton.setFocusPainted(false);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ORANGE_ACCENT, 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        
        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);
        
        // Add hover effects
        updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateButton.setBackground(new Color(255, 160, 20));
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateButton.setBackground(ORANGE_ACCENT);
            }
        });
        
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(new Color(40, 40, 40));
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(DARKER_BG);
            }
        });
        
        // Add action listeners
        updateButton.addActionListener(e -> {
            String course = courseField.getText().trim();
            String scoreText = scoreField.getText().trim();
            
            if (course.isEmpty() || scoreText.isEmpty()) {
                showErrorMessage("All fields are required!");
                return;
            }
            
            try {
                int score = Integer.parseInt(scoreText);
                if (score < 0 || score > 100) {
                    showErrorMessage("Score must be between 0 and 100");
                    return;
                }
                
                // Here you would implement the actual update logic
                this.message = "Feature coming soon: Update score for " + course + " to " + score;
                showSuccessMessage(this.message);
                frame.dispose();
                
            } catch (NumberFormatException ex) {
                showErrorMessage("Score must be a valid number");
            }
        });
        
        cancelButton.addActionListener(e -> frame.dispose());
        
        // Add components to main panel
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.setContentPane(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    // Helper method to display student details in a themed window
    private void displayStudentDetails(String details) {
        JFrame frame = new JFrame("Student Details");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.getContentPane().setBackground(DARK_BG);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(DARK_BG);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Header
        JLabel headerLabel = new JLabel("STUDENT DETAILS");
        headerLabel.setForeground(ORANGE_ACCENT);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Details area
        JTextArea detailsArea = new JTextArea(details);
        detailsArea.setBackground(DARKER_BG);
        detailsArea.setForeground(LIGHT_TEXT);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        detailsArea.setEditable(false);
        detailsArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(ORANGE_ACCENT, 1));
        
        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.setBackground(ORANGE_ACCENT);
        closeButton.setForeground(DARK_TEXT);
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(DARKER_BG, 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeButton.setBackground(new Color(255, 160, 20));
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeButton.setBackground(ORANGE_ACCENT);
            }
        });
        
        closeButton.addActionListener(e -> frame.dispose());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(DARK_BG);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        buttonPanel.add(closeButton);
        
        // Add components to main panel
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.setContentPane(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    // Helper method to display results in a themed window
    private void displayResults(String results) {
        JFrame frame = new JFrame("Student Results");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 300);
        frame.getContentPane().setBackground(DARK_BG);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(DARK_BG);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Header
        JLabel headerLabel = new JLabel("STUDENT RESULTS");
        headerLabel.setForeground(ORANGE_ACCENT);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Results area
        JTextArea resultsArea = new JTextArea(results);
        resultsArea.setBackground(DARKER_BG);
        resultsArea.setForeground(LIGHT_TEXT);
        resultsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultsArea.setEditable(false);
        resultsArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(ORANGE_ACCENT, 1));
        
        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.setBackground(ORANGE_ACCENT);
        closeButton.setForeground(DARK_TEXT);
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(DARKER_BG, 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeButton.setBackground(new Color(255, 160, 20));
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeButton.setBackground(ORANGE_ACCENT);
            }
        });
        
        closeButton.addActionListener(e -> frame.dispose());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(DARK_BG);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        buttonPanel.add(closeButton);
        
        // Add components to main panel
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.setContentPane(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    // Helper methods for displaying themed messages
    private void showErrorMessage(String message) {
        JLabel label = new JLabel(message);
        label.setForeground(ERROR_COLOR);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        
        JOptionPane optionPane = new JOptionPane(label, JOptionPane.ERROR_MESSAGE);
        optionPane.setBackground(DARK_BG);
        
        JDialog dialog = optionPane.createDialog("Error");
        dialog.setBackground(DARK_BG);
        dialog.setVisible(true);
        
        System.out.println(message); // Also print to console for logging
    }
    
    private void showWarningMessage(String message) {
        JLabel label = new JLabel(message);
        label.setForeground(ORANGE_ACCENT);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        
        JOptionPane optionPane = new JOptionPane(label, JOptionPane.WARNING_MESSAGE);
        optionPane.setBackground(DARK_BG);
        
        JDialog dialog = optionPane.createDialog("Warning");
        dialog.setBackground(DARK_BG);
        dialog.setVisible(true);
        
        System.out.println(message); // Also print to console for logging
    }
    
    private void showSuccessMessage(String message) {
        JLabel label = new JLabel(message);
        label.setForeground(SUCCESS_COLOR);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        
        JOptionPane optionPane = new JOptionPane(label, JOptionPane.INFORMATION_MESSAGE);
        optionPane.setBackground(DARK_BG);
        
        JDialog dialog = optionPane.createDialog("Success");
        dialog.setBackground(DARK_BG);
        dialog.setVisible(true);
        
        System.out.println(message); // Also print to console for logging
    }
}

