package view;

import controller.StudentController;
import javax.swing.*;
import java.awt.*;

public class TeachPage extends JFrame {

    public TeachPage(StudentController controller) {
        setTitle("Teacher Dashboard");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 247, 250));
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(33, 150, 243));
        headerPanel.setPreferredSize(new Dimension(500, 60));

        JLabel lblTitle = new JLabel("Teacher Dashboard", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(30, 80, 30, 80),
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1)
        ));

        JButton btnAdd = createStyledButton("Add Student", new Color(46, 204, 113));
        JButton btnView = createStyledButton("View Students", new Color(33, 150, 243));
        JButton btnLogout = createStyledButton("Logout", new Color(231, 76, 60));

        btnAdd.addActionListener(e -> {
            AddStudent add = new AddStudent(controller);
            add.setVisible(true);         
        });

        btnView.addActionListener(e -> {
            ViewStudents view = new ViewStudents(controller);
            view.setVisible(true);       
        });

        btnLogout.addActionListener(e -> {
            dispose();
            LoginPage login = new LoginPage(controller);
            login.setVisible(true);     
        });

        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnView.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);

        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(btnAdd);
        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(btnView);
        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(btnLogout);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(245, 247, 250));
        centerPanel.add(cardPanel);

        add(centerPanel, BorderLayout.CENTER);
    }


    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setMaximumSize(new Dimension(180, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        return button;
    }
}
