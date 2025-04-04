import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

public class Course {
    // Theme colors - matching AdminGUI
    public static final Color DARK_BG = new Color(25, 25, 25);
    public static final Color DARKER_BG = new Color(15, 15, 15);
    public static final Color ORANGE_ACCENT = new Color(255, 140, 0);
    public static final Color LIGHT_TEXT = new Color(240, 240, 240);
    public static final Color DARK_TEXT = new Color(25, 25, 25);
    public static final Color SUCCESS_COLOR = new Color(0, 200, 0);
    public static final Color ERROR_COLOR = new Color(255, 60, 60);
   
    private final Map<String, String> eb1 = new HashMap<>();
    private final Map<String, String> cb1 = new HashMap<>();
    private final Map<String, String> eb2 = new HashMap<>();
    private final Map<String, String> eb3 = new HashMap<>();

    public Course() {
        addCourses();
    }

    // Assign Score Method
    public String assignScore(String programmeCode, String regNo, String courseCode, int score) {
        Student student = new Student(regNo);
        Map<String, String> selectedProgramme;

        // Select the appropriate programme map
        switch (programmeCode.toLowerCase()) {
            case "eb1": selectedProgramme = eb1; break;
            case "cb1": selectedProgramme = cb1; break;
            case "eb2": selectedProgramme = eb2; break;
            case "eb3": selectedProgramme = eb3; break;
            default:
                return "Invalid programme code!";
        }

        // Check if courseCode exists in the selected programme
        if (selectedProgramme.containsKey(courseCode)) {
            student.assignStudentResults(courseCode, score);
            return "Score assigned successfully!";
        } else {
            return "Error: Course " + courseCode + " is not part of " + programmeCode;
        }
    }

    public String assignLecturerCourse(String lecturerID, String courseCode) {
        // Validate inputs
        if (lecturerID == null || courseCode == null || lecturerID.trim().isEmpty() || courseCode.trim().isEmpty()) {
            return "Error: All fields are required!";
        }

        // Format input
        lecturerID = lecturerID.trim();
        courseCode = courseCode.trim().toUpperCase();
        String fileName = lecturerID + ".txt"; // Store assignments in a file named after lecturerID

        try {
            // Check if the file exists and if the course is already assigned
            File file = new File(fileName);
            boolean courseExists = false;

            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    if (scanner.nextLine().contains(courseCode)) {
                        courseExists = true;
                        break;
                    }
                }
                scanner.close();
            }

            if (courseExists) {
                return "Error: Course already assigned to this lecturer!";
            } else {
                // Append course assignment to the file
                FileWriter writer = new FileWriter(fileName, true);
                writer.write("Course Assigned: " + courseCode + "\n");
                writer.close();
                return "Success: Course " + courseCode + " assigned to Lecturer " + lecturerID + " successfully!";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Unable to assign course!";
        }
    }

    public void attachCourseToAProgramme(String programmeCode, String courseCode, String courseName) {
        // Validate inputs
        if (programmeCode == null || courseCode == null || courseName == null ||
            programmeCode.trim().isEmpty() || courseCode.trim().isEmpty() || courseName.trim().isEmpty()) {
            showErrorMessage("Error: All fields are required!");
            return;
        }
    
        // Normalize input
        programmeCode = programmeCode.trim().toLowerCase();
        courseCode = courseCode.trim().toUpperCase();
        courseName = courseName.trim();
    
        // Select the correct programme map
        Map<String, String> selectedProgramme;
        switch (programmeCode) {
            case "eb1": selectedProgramme = eb1; break;
            case "cb1": selectedProgramme = cb1; break;
            case "eb2": selectedProgramme = eb2; break;
            case "eb3": selectedProgramme = eb3; break;
            default: selectedProgramme = null;
        }
    
        // Validate programme code
        if (selectedProgramme == null) {
            showErrorMessage("Error: Invalid Programme Code! Supported codes: eb1, cb1, eb2, eb3.");
            return;
        }
    
        // Check if course already exists
        if (selectedProgramme.containsKey(courseCode)) {
            showWarningMessage("Warning: Course " + courseCode + " already exists in " + programmeCode);
        } else {
            // Add course to the programme
            selectedProgramme.put(courseCode, courseName);
            showSuccessMessage("Success: Course " + courseCode + " added to " + programmeCode + " successfully!");
        }
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

    // Getters for course lists
    public Map<String, String> eb1Courses() { return this.eb1; }
    public Map<String, String> cb1Courses() { return this.cb1; }
    public Map<String, String> eb2Courses() { return this.eb2; }
    public Map<String, String> eb3Courses() { return this.eb3; }

    // Method to add courses
    private void addCourses() {
        cb1.put("MATH241", "Probability and Statistics");
        
        eb1.put("COSC101", "Introduction to Computer Science");
        eb1.put("COSC102", "Computer Logics");
        eb1.put("COSC110", "Introduction to Networking");
        eb1.put("COSC142", "Techno Entrepreneurship");
        eb1.put("COSC210", "Data Structures and Algorithms");
        eb1.put("COSC220", "Operating Systems");
        eb1.put("COSC230", "Software Engineering");
        eb1.put("COSC240", "Database Management Systems");

        eb3.putAll(eb1); // Copy courses from eb1 to eb3
        eb3.put("COSC250", "Web Development");
        eb3.put("COSC260", "Data Communications and Networks");

        eb2.put("COSC250", "Web Development");
        eb2.put("COSC260", "Data Communications and Networks");
        eb2.put("MATH250", "Discrete Mathematics");
        eb2.put("MATH270", "Calculus III");
        eb2.put("PHY210", "Electronics for Computer Science");

        cb1.put("STA230", "Statistical Methods");
        cb1.put("BIO101", "Introduction to Biology");
        cb1.put("CHEM102", "Organic Chemistry");
        cb1.put("ECON201", "Microeconomics");
        cb1.put("ECON202", "Macroeconomics");
    }
    
    // Method to display course list in a themed UI
    public void displayCourseList(String programmeCode) {
        Map<String, String> courses;
        
        switch (programmeCode.toLowerCase()) {
            case "eb1": courses = eb1; break;
            case "cb1": courses = cb1; break;
            case "eb2": courses = eb2; break;
            case "eb3": courses = eb3; break;
            default:
                showErrorMessage("Invalid programme code: " + programmeCode);
                return;
        }
        
        // Create a JFrame with the themed look
        JFrame frame = new JFrame("Courses for " + programmeCode.toUpperCase());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.getContentPane().setBackground(DARK_BG);
        
        // Create a panel with a border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(DARK_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create a header
        JLabel headerLabel = new JLabel("COURSES FOR " + programmeCode.toUpperCase());
        headerLabel.setForeground(ORANGE_ACCENT);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Create a table model for the courses
        String[] columnNames = {"Course Code", "Course Name"};
        Object[][] data = new Object[courses.size()][2];
        
        int i = 0;
        for (Map.Entry<String, String> entry : courses.entrySet()) {
            data[i][0] = entry.getKey();
            data[i][1] = entry.getValue();
            i++;
        }
        
        // Create the table with custom renderer for the theme
        JTable table = new JTable(data, columnNames);
        table.setBackground(DARKER_BG);
        table.setForeground(LIGHT_TEXT);
        table.setGridColor(ORANGE_ACCENT);
        table.getTableHeader().setBackground(DARKER_BG);
        table.getTableHeader().setForeground(ORANGE_ACCENT);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Add components to the panel
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        // Create a close button
        JButton closeButton = new JButton("Close");
        closeButton.setBackground(ORANGE_ACCENT);
        closeButton.setForeground(DARK_TEXT);
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.addActionListener(e -> frame.dispose());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(DARK_BG);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        buttonPanel.add(closeButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Set the content pane and display the frame
        frame.setContentPane(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

