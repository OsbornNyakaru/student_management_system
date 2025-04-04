import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingConstants;
import java.awt.CardLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class StudentGui extends JPanel {
    private JTextField regNoField, programmeField, fullnameField, emailField, phoneNoField;
    private JLabel label, regNoLabel, programmeLabel, fullnameLabel, emailLabel, phoneNoLabel;
    private JButton button1, button2, button3, button4, button5, button6, submit, exit, btnBack;
    private JPanel panel, formPanel;
    

    public StudentGui() {
        setLayout(new BorderLayout());
        
        // Back Button Panel
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnBack = new JButton("Back to Login");
        btnBack.addActionListener(e -> {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "login");
        });
        backPanel.add(btnBack);
        add(backPanel, BorderLayout.NORTH);
        
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.white);
        add(panel, BorderLayout.CENTER);
        
        GridBagConstraints gdc = new GridBagConstraints();
        gdc.insets = new Insets(10, 10, 10, 10);
        gdc.fill = GridBagConstraints.HORIZONTAL;

        label = new JLabel();
        label.setText("Students Panel");
        label.setSize(10, 10);
        gdc.gridx = 1;
        gdc.gridy = 0;
        panel.add(label, gdc);

        button1 = new JButton("Register Student");
        button1.addActionListener(e -> registerGui());
        gdc.gridx = 0;
        gdc.gridy = 1;
        panel.add(button1, gdc);

        button2 = new JButton("enroll student to a course");
        button2.addActionListener(e -> {
            String regNo = JOptionPane.showInputDialog("enter registration number");
            while (regNo == null) { 
                regNo = JOptionPane.showInputDialog("enter registration number");
            }
            CourseSelection selection = new CourseSelection(regNo);
        });
        gdc.gridx = 1;
        gdc.gridy = 1;
        panel.add(button2, gdc);

        button3 = new JButton("assign scores ");
        button3.addActionListener(e -> {
            ScoreAssignmentGUI score = new ScoreAssignmentGUI();
        });
        gdc.gridx = 0;
        gdc.gridy = 2;
        panel.add(button3, gdc);

        button4 = new JButton("Search Student");
        button4.addActionListener(e -> {
            String regNo = JOptionPane.showInputDialog("enter registration number");
            while (regNo == null) { 
                regNo = JOptionPane.showInputDialog("enter registration number");
            }
            Student student = new Student(regNo);
            student.searchStudent();
        });
        gdc.gridx = 1;
        gdc.gridy = 2;
        panel.add(button4, gdc);

        button5 = new JButton("retrieve results");
        button5.addActionListener(e -> {
            // Implementation for retrieve results
        });
        gdc.gridx = 0;
        gdc.gridy = 3;
        panel.add(button5, gdc);
        
        button6 = new JButton("update results");
        button6.addActionListener(e -> {
            // Implementation for update results
        });
        gdc.gridx = 1;
        gdc.gridy = 3;
        panel.add(button6, gdc);
    }

    public static void main(String[] args) {
        try{
        SwingUtilities.invokeLater(()->{
            new StudentGui().setVisible(true);
        });}
        catch(Exception e){
            e.printStackTrace();
        }
    }

    

    

    public void registerGui() {
        JFrame frame = new JFrame("Student Registration");
        frame.setLayout(new BorderLayout());
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // Center the frame on screen

        formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gdc = new GridBagConstraints();
        gdc.insets = new Insets(10, 10, 10, 10);
        gdc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(formPanel, BorderLayout.CENTER);

        // Registration Number
        regNoLabel = new JLabel("Registration Number:");
        gdc.gridx = 0;
        gdc.gridy = 1;
        gdc.weightx = 0.3; // Less space for label
        formPanel.add(regNoLabel, gdc);

        regNoField = new JTextField();
        regNoField.setPreferredSize(new Dimension(200, 30)); // Set large width
        gdc.gridx = 1;
        gdc.weightx = 1; // More space for input
        formPanel.add(regNoField, gdc);

        // Full Name
        fullnameLabel = new JLabel("Full Name:");
        gdc.gridx = 0;
        gdc.gridy = 2;
        gdc.weightx = 0.3;
        formPanel.add(fullnameLabel, gdc);

        fullnameField = new JTextField();
        fullnameField.setPreferredSize(new Dimension(200, 30));
        gdc.gridx = 1;
        gdc.weightx = 1;
        formPanel.add(fullnameField, gdc);

        // Programme
        programmeLabel = new JLabel("Programme:");
        gdc.gridx = 0;
        gdc.gridy = 3;
        formPanel.add(programmeLabel, gdc);

        programmeField = new JTextField();
        programmeField.setPreferredSize(new Dimension(200, 30));
        gdc.gridx = 1;
        formPanel.add(programmeField, gdc);

        // Email
        emailLabel = new JLabel("Email:");
        gdc.gridx = 0;
        gdc.gridy = 4;
        formPanel.add(emailLabel, gdc);

        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(200, 30));
        gdc.gridx = 1;
        formPanel.add(emailField, gdc);

        // Phone Number
        phoneNoLabel = new JLabel("Phone Number:");
        gdc.gridx = 0;
        gdc.gridy = 5;
        formPanel.add(phoneNoLabel, gdc);

        phoneNoField = new JTextField();
        phoneNoField.setPreferredSize(new Dimension(200, 30));
        gdc.gridx = 1;
        formPanel.add(phoneNoField, gdc);

        // Submit Button
        submit = new JButton("SUBMIT");
        gdc.gridx = 1;
        gdc.gridy = 6;
        formPanel.add(submit, gdc);

        // Exit Button
        exit = new JButton("Exit");
        exit.addActionListener(e -> frame.dispose());
        gdc.gridx = 2;
        gdc.gridy = 6;
        formPanel.add(exit, gdc);

        // Submit Button Action
        submit.addActionListener(e -> {
            String regNo = regNoField.getText().trim();
            String fullname = fullnameField.getText().trim();
            String programme = programmeField.getText().trim();
            String email = emailField.getText().trim();
            String phoneNo = phoneNoField.getText().trim();

            // Validate registration number format
            if (!AdminGUI.isValidRegNo(regNo)) {
                JOptionPane.showMessageDialog(frame, 
                    "Invalid registration number format. Use format: EB1/66784/23", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate other fields
            if (fullname.isEmpty() || programme.isEmpty() || email.isEmpty() || phoneNo.isEmpty()) {
                JOptionPane.showMessageDialog(frame, 
                    "All fields are required!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Student s = new Student(regNo);
                s.registerStudent(fullname, programme, email, phoneNo);
                JOptionPane.showMessageDialog(frame, s.message, "notification", JOptionPane.PLAIN_MESSAGE);
                
                // Clear fields after successful registration
                regNoField.setText("");
                fullnameField.setText("");
                programmeField.setText("");
                emailField.setText("");
                phoneNoField.setText("");
                regNoField.requestFocus();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, 
                    ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
   
    
}

class Reusable extends JFrame {
    public Reusable(String message) {
        setTitle("Student Details");
        setSize(800, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(message);
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton button = new JButton("Close");
        button.addActionListener(e -> dispose());
        add(button, BorderLayout.SOUTH);

        setVisible(true);
    }
}

 class CourseSelection {
    private JCheckBox[] checkBoxes;
    private JTextArea outputArea = new JTextArea(5, 30);
    private StringBuilder selectedCourses = new StringBuilder();
    private String regNo;

    public CourseSelection(String regNo) {
        this.regNo=regNo;
        enrollGui();
    }

    public void enrollGui() {
        JFrame frame = new JFrame("Course Selection");
        frame.setSize(450, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Add Label
        frame.add(new JLabel("Select 3 courses:\n"));

        // Sample Course Data
        Map<String, String> courses = eb1Courses();
        checkBoxes = new JCheckBox[courses.size()];

        // Create Checkboxes
        int index = 0;
        for (String code : courses.keySet()) {
            checkBoxes[index] = new JCheckBox(code + " - " + courses.get(code));
            frame.add(checkBoxes[index]);
            index++;
        }

        // Submit Button
        JButton submitButton = new JButton("Submit");
        frame.add(submitButton);

        JButton exitButton = new JButton("exit");
        exitButton.addActionListener(e->{
            frame.dispose();
        });
        frame.add(exitButton);

        JButton finishButton = new JButton("finish");
        finishButton.addActionListener(e->{
            finishButton.setEnabled(false); // Disable button to prevent multiple clicks
            
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    getSelectedCourses();
                    frame.dispose(); // Exit entire application after delay
                }
            }, 30000); // 30-second delay
        });
        frame.add(finishButton);
        
        // Output Area
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea));

        // Button ActionListener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCourses.setLength(0); // Clear previous selection
                int count = 0;

                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        if (count < 3) {
                            selectedCourses.append(checkBox.getText()).append("\n");
                            count++;
                        } else {
                            JOptionPane.showMessageDialog(frame, "You can only select 3 courses!", "Warning", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                }

                if (count < 3) {
                    JOptionPane.showMessageDialog(frame, "Please select exactly 3 courses!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    outputArea.setText("Selected Courses:\n" + selectedCourses);
                }
            }
        });

        frame.setVisible(true);
    }

    // Getter method to retrieve selected courses
    public StringBuilder getSelectedCourses() {
            // Simulate retrieving the selected courses after the user interacts with the GUI
        Student student=new Student(regNo);
        student.enrollCourse(selectedCourses.toString());
        return selectedCourses;
    }


    // Sample Course Data
    public Map<String, String> eb1Courses() {
        Map<String, String> courses = new HashMap<>();
        courses.put("COSC101", "Introduction to Computer Science");
        courses.put("COSC102", "Computer Logics");
        courses.put("MATH241", "Probability and Statistics");
        courses.put("COSC110", "Introduction to Networking");
        courses.put("COSC142", "Techno Entrepreneurship");
        return courses;
    }

    
}




class ScoreAssignmentGUI {
    public ScoreAssignmentGUI() {
        // Create JFrame
        JFrame frame = new JFrame("Assign Student Score");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create Main Panel
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        // Create UI Components
        JLabel lblProgramme = new JLabel("Programme Code:");
        JTextField txtProgramme = new JTextField();

        JLabel lblRegNo = new JLabel("Registration No:");
        JTextField txtRegNo = new JTextField();

        JLabel lblCourse = new JLabel("Course Code:");
        JTextField txtCourse = new JTextField();

        JLabel lblScore = new JLabel("Score:");
        JTextField txtScore = new JTextField();

        JButton btnSubmit = new JButton("Assign Score");
        JLabel lblMessage = new JLabel("", SwingConstants.CENTER);

        // Add Components to Panel
        panel.add(lblProgramme);
        panel.add(txtProgramme);
        panel.add(lblRegNo);
        panel.add(txtRegNo);
        panel.add(lblCourse);
        panel.add(txtCourse);
        panel.add(lblScore);
        panel.add(txtScore);
        panel.add(btnSubmit);
        panel.add(lblMessage);

        // Create Exit Button
        JButton btnExit = new JButton("Exit");
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Aligns the button to the right
        bottomPanel.add(btnExit);

        // Add Components to Frame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Button ActionListener for Submit
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String programmeCode = txtProgramme.getText().trim();
                String regNo = txtRegNo.getText().trim();
                String courseCode = txtCourse.getText().trim();
                String scoreText = txtScore.getText().trim();

                // Validate input
                if (programmeCode.isEmpty() || regNo.isEmpty() || courseCode.isEmpty() || scoreText.isEmpty()) {
                    lblMessage.setText("All fields are required!");
                    lblMessage.setForeground(Color.RED);
                    return;
                }

                // Validate score as integer
                int score;
                try {
                    score = Integer.parseInt(scoreText);
                    if (score < 0 || score > 100) {
                        lblMessage.setText("Score must be between 0 and 100!");
                        lblMessage.setForeground(Color.RED);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    lblMessage.setText("Invalid score! Enter a number.");
                    lblMessage.setForeground(Color.RED);
                    return;
                }

                // Assign score using Course class
                Course course = new Course();
                String result = course.assignScore(programmeCode, regNo, courseCode, score);

                // Display result
                lblMessage.setText(result);
                lblMessage.setForeground(result.contains("Error") ? Color.RED : Color.GREEN);
            }
        });

        // Button ActionListener for Exit
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the window
            }
        });

        // Show Frame
        frame.setVisible(true);
    }


}


