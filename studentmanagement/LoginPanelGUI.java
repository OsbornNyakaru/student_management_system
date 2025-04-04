import javax.swing.*;
import java.awt.*;

public class LoginPanelGUI extends JFrame {
    private static final long serialVersionUID = 1L; // Added serialVersionUID
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel loginPanel;
    private AdminGUI adminPanel;
    private StudentGui studentPanel;

    public LoginPanelGUI() {
        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Initialize CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Create login panel
        createLoginPanel();
        
        // Initialize other panels (but don't create them yet)
        adminPanel = null;
        studentPanel = null;
        
        // Add panels to card layout
        mainPanel.add(loginPanel, "login");
        
        add(mainPanel);
        setVisible(true);
    }

    private void createLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new BorderLayout(10, 10));

        // Title Label
        JLabel titleLabel = new JLabel("Welcome to Student System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loginPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton btnAdmin = new JButton("Admin Login");
        JButton btnStudent = new JButton("Student Login");
        JButton btnExit = new JButton("Exit");

        // Increase button size
        btnAdmin.setPreferredSize(new Dimension(200, 50));
        btnStudent.setPreferredSize(new Dimension(200, 50));
        btnExit.setPreferredSize(new Dimension(200, 50));

        buttonPanel.add(btnAdmin);
        buttonPanel.add(btnStudent);
        buttonPanel.add(btnExit);
        
        loginPanel.add(buttonPanel, BorderLayout.CENTER);

        // Button Actions
        btnAdmin.addActionListener(e -> openAdminPanel());
        btnStudent.addActionListener(e -> openStudentPanel());
        btnExit.addActionListener(e -> System.exit(0));
    }

    private void openAdminPanel() {
        if (adminPanel == null) {
            adminPanel = new AdminGUI();
            mainPanel.add(adminPanel, "admin");
        }
        cardLayout.show(mainPanel, "admin");
    }

    private void openStudentPanel() {
        if (studentPanel == null) {
            studentPanel = new StudentGui();
            mainPanel.add(studentPanel, "student");
        }
        cardLayout.show(mainPanel, "student");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPanelGUI::new);
    }
}
