import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;

public class AdminGUI extends JPanel {
    private static final long serialVersionUID = 1L;
    private JButton btnAttachProgram, btnAssignLecturer, btnExit, btnBack;
    private static final Pattern PROGRAMME_CODE_PATTERN = Pattern.compile("^(eb1|eb2|eb3|cb1)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern COURSE_CODE_PATTERN = Pattern.compile("^[A-Z]{4}\\d{3}$");
    private static final Pattern LECTURER_ID_PATTERN = Pattern.compile("^[A-Z]{2}\\d{3}$");
    private static final Pattern REG_NO_PATTERN = Pattern.compile("^(EB1|EB2|EB3|CB1)/\\d{5}/\\d{2}$");
    
    // Theme colors
    public static final Color DARK_BG = new Color(25, 25, 25);
    public static final Color DARKER_BG = new Color(15, 15, 15);
    public static final Color ORANGE_ACCENT = new Color(255, 140, 0);
    public static final Color LIGHT_TEXT = new Color(240, 240, 240);
    public static final Color DARK_TEXT = new Color(25, 25, 25);
    public static final Color BUTTON_HOVER = new Color(255, 160, 20);
    
    static {
        // Set up the UI theme
        setupUITheme();
    }
    
    public static void setupUITheme() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            
            // Panel backgrounds
            UIManager.put("Panel.background", DARK_BG);
            UIManager.put("OptionPane.background", DARK_BG);
            UIManager.put("Dialog.background", DARK_BG);
            UIManager.put("Frame.background", DARK_BG);
            
            // Text colors
            UIManager.put("Label.foreground", LIGHT_TEXT);
            UIManager.put("TextField.foreground", LIGHT_TEXT);
            UIManager.put("TextArea.foreground", LIGHT_TEXT);
            UIManager.put("ComboBox.foreground", LIGHT_TEXT);
            
            // Text field backgrounds
            UIManager.put("TextField.background", DARKER_BG);
            UIManager.put("TextArea.background", DARKER_BG);
            UIManager.put("ComboBox.background", DARKER_BG);
            UIManager.put("TextField.caretForeground", LIGHT_TEXT);
            UIManager.put("TextField.selectionBackground", ORANGE_ACCENT);
            UIManager.put("TextField.selectionForeground", DARK_TEXT);
            
            // Button styling
            UIManager.put("Button.background", ORANGE_ACCENT);
            UIManager.put("Button.foreground", DARK_TEXT);
            UIManager.put("Button.select", BUTTON_HOVER);
            UIManager.put("Button.focus", new Color(0, 0, 0, 0));
            
            // Borders
            UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
                    new LineBorder(ORANGE_ACCENT, 1),
                    new EmptyBorder(4, 6, 4, 6)));
            
            // Option pane
            UIManager.put("OptionPane.messageForeground", LIGHT_TEXT);
            UIManager.put("OptionPane.buttonBackground", ORANGE_ACCENT);
            UIManager.put("OptionPane.buttonForeground", DARK_TEXT);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AdminGUI() {
        setLayout(new BorderLayout());
        setBackground(DARK_BG);
        
        // Create a header panel with logo/title
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(DARKER_BG);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel titleLabel = new JLabel("ADMIN DASHBOARD");
        titleLabel.setForeground(ORANGE_ACCENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Create button panel with styled buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setBackground(DARK_BG);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Back Button
        btnBack = createStyledButton("Back to Login", KeyEvent.VK_B);
        btnBack.addActionListener(e -> {
            Container parent = getParent();
            if (parent != null && parent.getLayout() instanceof CardLayout) {
                CardLayout cl = (CardLayout) parent.getLayout();
                cl.show(parent, "login");
            }
        });
        
        // Attach Program Button
        btnAttachProgram = createStyledButton("Attach Program", KeyEvent.VK_A);
        btnAttachProgram.setToolTipText("Attach a course to a programme");
        btnAttachProgram.addActionListener(e -> {
            final Course course = new Course();
            try {
                CourseFormGUI form = new CourseFormGUI(course, this);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error initializing course form: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Assign Lecturer Button
        btnAssignLecturer = createStyledButton("Assign Lecturer", KeyEvent.VK_L);
        btnAssignLecturer.setToolTipText("Assign a lecturer to a course");
        btnAssignLecturer.addActionListener(e -> {
            try {
                new LecturerAssignmentForm(this);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error initializing lecturer form: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Exit Button
        btnExit = createStyledButton("Exit", KeyEvent.VK_X);
        btnExit.setToolTipText("Exit the application");
        btnExit.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        
        // Add buttons to panel
        buttonPanel.add(btnAttachProgram);
        buttonPanel.add(btnAssignLecturer);
        buttonPanel.add(btnBack);
        buttonPanel.add(btnExit);
        
        // Footer panel
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(DARKER_BG);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel footerLabel = new JLabel("© 2023 Student Management System");
        footerLabel.setForeground(LIGHT_TEXT);
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerPanel.add(footerLabel, BorderLayout.CENTER);
        
        // Add all panels to main panel
        add(headerPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }
    
    private JButton createStyledButton(String text, int mnemonic) {
        JButton button = new JButton(text);
        button.setMnemonic(mnemonic);
        button.setBackground(ORANGE_ACCENT);
        button.setForeground(DARK_TEXT);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(DARKER_BG, 1),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER);
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(ORANGE_ACCENT);
            }
        });
        
        return button;
    }

    // Validation methods
    public static boolean isValidProgrammeCode(String code) {
        return PROGRAMME_CODE_PATTERN.matcher(code).matches();
    }

    public static boolean isValidCourseCode(String code) {
        return COURSE_CODE_PATTERN.matcher(code).matches();
    }

    public static boolean isValidLecturerID(String id) {
        return LECTURER_ID_PATTERN.matcher(id).matches();
    }

    public static boolean isValidRegNo(String regNo) {
        return REG_NO_PATTERN.matcher(regNo).matches();
    }
}

class CourseFormGUI extends JFrame {
    private JTextField txtProgrammeCode, txtCourseCode, txtCourseName;
    private JButton btnSubmit, btnExit;
    private JLabel lblMessage;
    private final Course course;
    private AdminGUI parent;

    public CourseFormGUI(Course course, AdminGUI parent) {
        this.course = course;
        this.parent = parent;

        setTitle("Attach Course to Programme");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Set the frame background
        getContentPane().setBackground(AdminGUI.DARK_BG);
        
        // Create main panel with border
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(AdminGUI.DARK_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(AdminGUI.DARKER_BG);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel titleLabel = new JLabel("ATTACH COURSE TO PROGRAMME");
        titleLabel.setForeground(AdminGUI.ORANGE_ACCENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(AdminGUI.DARK_BG);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Form Labels & Fields
        JLabel lblProgrammeCode = new JLabel("Programme Code (eb1, cb1, eb2, eb3):");
        lblProgrammeCode.setForeground(AdminGUI.LIGHT_TEXT);
        lblProgrammeCode.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(lblProgrammeCode, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        txtProgrammeCode = createStyledTextField("Enter programme code (eb1, cb1, eb2, or eb3)");
        formPanel.add(txtProgrammeCode, gbc);

        JLabel lblCourseCode = new JLabel("Course Code (e.g., COSC101):");
        lblCourseCode.setForeground(AdminGUI.LIGHT_TEXT);
        lblCourseCode.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(lblCourseCode, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        txtCourseCode = createStyledTextField("Enter course code in format XXXX123 (e.g., COSC101)");
        formPanel.add(txtCourseCode, gbc);

        JLabel lblCourseName = new JLabel("Course Name:");
        lblCourseName.setForeground(AdminGUI.LIGHT_TEXT);
        lblCourseName.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(lblCourseName, gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        txtCourseName = createStyledTextField("Enter the full course name");
        formPanel.add(txtCourseName, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(AdminGUI.DARK_BG);
        
        // Submit Button
        btnSubmit = createStyledButton("Attach Course", KeyEvent.VK_A);
        buttonPanel.add(btnSubmit);

        // Exit Button
        btnExit = createStyledButton("Exit", KeyEvent.VK_X);
        buttonPanel.add(btnExit);
        
        // Message panel
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBackground(AdminGUI.DARK_BG);
        messagePanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        // Message Label
        lblMessage = new JLabel("", SwingConstants.CENTER);
        lblMessage.setFont(new Font("Arial", Font.BOLD, 14));
        messagePanel.add(lblMessage, BorderLayout.CENTER);
        
        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Create a panel for buttons and message
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(AdminGUI.DARK_BG);
        southPanel.add(buttonPanel, BorderLayout.NORTH);
        southPanel.add(messagePanel, BorderLayout.CENTER);
        
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        
        // Add main panel to frame
        setContentPane(mainPanel);
        setLocationRelativeTo(parent);

        // Submit Button Action
        btnSubmit.addActionListener(e -> {
            try {
                String programmeCode = txtProgrammeCode.getText().trim().toLowerCase();
                String courseCode = txtCourseCode.getText().trim().toUpperCase();
                String courseName = txtCourseName.getText().trim();

                // Validate inputs
                if (!AdminGUI.isValidProgrammeCode(programmeCode)) {
                    lblMessage.setText("Invalid programme code! Use eb1, cb1, eb2, or eb3");
                    lblMessage.setForeground(Color.RED);
                    return;
                }

                if (!AdminGUI.isValidCourseCode(courseCode)) {
                    lblMessage.setText("Invalid course code! Use format XXXX123 (e.g., COSC101)");
                    lblMessage.setForeground(Color.RED);
                    return;
                }

                if (courseName.isEmpty()) {
                    lblMessage.setText("Course name is required!");
                    lblMessage.setForeground(Color.RED);
                    return;
                }

                course.attachCourseToAProgramme(programmeCode, courseCode, courseName);
                lblMessage.setText("Course added successfully!");
                lblMessage.setForeground(new Color(0, 200, 0));
                
                // Clear fields after successful submission
                txtProgrammeCode.setText("");
                txtCourseCode.setText("");
                txtCourseName.setText("");
                txtProgrammeCode.requestFocus();
            } catch (Exception ex) {
                lblMessage.setText("Error: " + ex.getMessage());
                lblMessage.setForeground(Color.RED);
            }
        });

        // Exit Button Action
        btnExit.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                dispose();
            }
        });

        // Set default button
        getRootPane().setDefaultButton(btnSubmit);
        
        // Add window listener for cleanup
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // No cleanup needed as course is final
            }
        });

        setVisible(true);
    }
    
    private JTextField createStyledTextField(String tooltip) {
        JTextField textField = new JTextField(20);
        textField.setToolTipText(tooltip);
        textField.setBackground(AdminGUI.DARKER_BG);
        textField.setForeground(AdminGUI.LIGHT_TEXT);
        textField.setCaretColor(AdminGUI.LIGHT_TEXT);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AdminGUI.ORANGE_ACCENT, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }
    
    private JButton createStyledButton(String text, int mnemonic) {
        JButton button = new JButton(text);
        button.setMnemonic(mnemonic);
        button.setBackground(AdminGUI.ORANGE_ACCENT);
        button.setForeground(AdminGUI.DARK_TEXT);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AdminGUI.DARKER_BG, 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(AdminGUI.BUTTON_HOVER);
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(AdminGUI.ORANGE_ACCENT);
            }
        });
        
        return button;
    }
}

class LecturerAssignmentForm extends JFrame {
    private JTextField txtLecturerID, txtCourseCode;
    private JLabel lblMessage;
    private AdminGUI parent;

    public LecturerAssignmentForm(AdminGUI parent) {
        this.parent = parent;
        
        setTitle("Assign Lecturer to Course");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Set the frame background
        getContentPane().setBackground(AdminGUI.DARK_BG);
        
        // Create main panel with border
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(AdminGUI.DARK_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(AdminGUI.DARKER_BG);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel titleLabel = new JLabel("ASSIGN LECTURER TO COURSE");
        titleLabel.setForeground(AdminGUI.ORANGE_ACCENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(AdminGUI.DARK_BG);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Labels and Text Fields
        JLabel lblLecturerID = new JLabel("Lecturer ID:");
        lblLecturerID.setForeground(AdminGUI.LIGHT_TEXT);
        lblLecturerID.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(lblLecturerID, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        txtLecturerID = createStyledTextField("Enter lecturer ID in format XX123 (e.g., LT001)");
        formPanel.add(txtLecturerID, gbc);

        JLabel lblCourseCode = new JLabel("Course Code:");
        lblCourseCode.setForeground(AdminGUI.LIGHT_TEXT);
        lblCourseCode.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(lblCourseCode, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        txtCourseCode = createStyledTextField("Enter course code in format XXXX123 (e.g., COSC101)");
        formPanel.add(txtCourseCode, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(AdminGUI.DARK_BG);
        
        // Submit Button
        JButton btnSubmit = createStyledButton("Assign Course", KeyEvent.VK_A);
        buttonPanel.add(btnSubmit);
        
        // Message panel
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBackground(AdminGUI.DARK_BG);
        messagePanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        // Message Label
        lblMessage = new JLabel("", SwingConstants.CENTER);
        lblMessage.setFont(new Font("Arial", Font.BOLD, 14));
        messagePanel.add(lblMessage, BorderLayout.CENTER);
        
        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Create a panel for buttons and message
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(AdminGUI.DARK_BG);
        southPanel.add(buttonPanel, BorderLayout.NORTH);
        southPanel.add(messagePanel, BorderLayout.CENTER);
        
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        
        // Add main panel to frame
        setContentPane(mainPanel);
        setLocationRelativeTo(parent);

        // Button Action
        btnSubmit.addActionListener(e -> {
            String lecturerID = txtLecturerID.getText().trim().toUpperCase();
            String courseCode = txtCourseCode.getText().trim().toUpperCase();

            // Validate inputs
            if (!AdminGUI.isValidLecturerID(lecturerID)) {
                lblMessage.setText("Invalid lecturer ID! Use format XX123 (e.g., LT001)");
                lblMessage.setForeground(Color.RED);
                return;
            }

            if (!AdminGUI.isValidCourseCode(courseCode)) {
                lblMessage.setText("Invalid course code! Use format XXXX123 (e.g., COSC101)");
                lblMessage.setForeground(Color.RED);
                return;
            }

            final Course assignment = new Course();
            String result = assignment.assignLecturerCourse(lecturerID, courseCode);

            lblMessage.setText(result);
            lblMessage.setForeground(result.startsWith("Success") ? 
                new Color(0, 200, 0) : Color.RED);
            
            // Clear fields after successful submission
            if (result.startsWith("Success")) {
                txtLecturerID.setText("");
                txtCourseCode.setText("");
                txtLecturerID.requestFocus();
            }
        });

        // Set default button
        getRootPane().setDefaultButton(btnSubmit);
        
        // Add window listener for cleanup
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Cleanup if needed
            }
        });

        setVisible(true);
    }
    
    private JTextField createStyledTextField(String tooltip) {
        JTextField textField = new JTextField(20);
        textField.setToolTipText(tooltip);
        textField.setBackground(AdminGUI.DARKER_BG);
        textField.setForeground(AdminGUI.LIGHT_TEXT);
        textField.setCaretColor(AdminGUI.LIGHT_TEXT);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AdminGUI.ORANGE_ACCENT, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }
    
    private JButton createStyledButton(String text, int mnemonic) {
        JButton button = new JButton(text);
        button.setMnemonic(mnemonic);
        button.setBackground(AdminGUI.ORANGE_ACCENT);
        button.setForeground(AdminGUI.DARK_TEXT);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AdminGUI.DARKER_BG, 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(AdminGUI.BUTTON_HOVER);
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(AdminGUI.ORANGE_ACCENT);
            }
        });
        
        return button;
    }
}

