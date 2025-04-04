import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Apply our custom theme
        AdminGUI.setupUITheme();
        
        // Create and show the main frame
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Student Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            
            // Create a card layout for switching between panels
            JPanel mainPanel = new JPanel(new CardLayout());
            
            // Add the admin panel
            AdminGUI adminPanel = new AdminGUI();
            mainPanel.add(adminPanel, "admin");
            
            // Set the main panel as content pane
            frame.setContentPane(mainPanel);
            
            // Center on screen
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

