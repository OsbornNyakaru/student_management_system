import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class LoginPanelGUI extends JPanel {
    // Theme colors - matching AdminGUI
    public static final Color DARK_BG = new Color(25, 25, 25);
    public static final Color DARKER_BG = new Color(15, 15, 15);
    public static final Color ORANGE_ACCENT = new Color(255, 140, 0);
    public static final Color LIGHT_TEXT = new Color(240, 240, 240);
    public static final Color DARK_TEXT = new Color(25, 25, 25);
    public static final Color BUTTON_HOVER = new Color(255, 160, 20);
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton exitButton;
    private JPanel parentPanel;
    
    public LoginPanelGUI(JPanel parentPanel) {
        this.parentPanel = parentPanel;
        setLayout(new BorderLayout());
        setBackground(DARK_BG);
        
        initComponents();
    }
    
    private void initComponents() {
        // Create a header panel with logo/title
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(DARKER_BG);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        JLabel titleLabel = new JLabel("STUDENT MANAGEMENT SYSTEM");
        titleLabel.setForeground(ORANGE_ACCENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Create login form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(DARK_BG);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(LIGHT_TEXT);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);
        
        // Username field
        usernameField = new JTextField(20);
        usernameField.setBackground(DARKER_BG);
        usernameField.setForeground(LIGHT_TEXT);
        usernameField.setCaretColor(LIGHT_TEXT);
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ORANGE_ACCENT, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(usernameField, gbc);
        
        // Password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(LIGHT_TEXT);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);
        
        // Password field
        passwordField = new JPasswordField(20);
        passwordField.setBackground(DARKER_BG);
        passwordField.setForeground(LIGHT_TEXT);
        passwordField.setCaretColor(LIGHT_TEXT);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ORANGE_ACCENT, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(DARK_BG);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        // Login button
        loginButton = createStyledButton("Login", KeyEvent.VK_L);
        buttonPanel.add(loginButton);
        
        // Exit button
        exitButton = createStyledButton("Exit", KeyEvent.VK_X);
        buttonPanel.add(exitButton);
        
        // Add button panel to form panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);
        
        // Footer panel
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(DARKER_BG);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel footerLabel = new JLabel("© 2025 Student Management System");
        footerLabel.setForeground(LIGHT_TEXT);
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerPanel.add(footerLabel, BorderLayout.CENTER);
        
        // Add all panels to main panel
        add(headerPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
        
        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                // Simple authentication (in a real app, this would be more secure)
                if (username.equals("admin") && password.equals("admin")) {
                    // Show admin panel
                    CardLayout cl = (CardLayout) parentPanel.getLayout();
                    cl.show(parentPanel, "admin");
                } else {
                    showErrorMessage("Invalid username or password!");
                }
            }
        });
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                    LoginPanelGUI.this,
                    "Are you sure you want to exit?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION
                );
                
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        
        // Add key listener to password field to respond to Enter key
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick(); // Simulate clicking the login button
            }
        });
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
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        
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
    
    private void showErrorMessage(String message) {
        JLabel label = new JLabel(message);
        label.setForeground(new Color(255, 60, 60));
        label.setFont(new Font("Arial", Font.BOLD, 14));
        
        JOptionPane optionPane = new JOptionPane(label, JOptionPane.ERROR_MESSAGE);
        optionPane.setBackground(DARK_BG);
        
        JDialog dialog = optionPane.createDialog("Error");
        dialog.setBackground(DARK_BG);
        dialog.setVisible(true);
    }
}

