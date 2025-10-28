package view;

import controller.StudentController;
import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    private JTextField txtUser;
    private JPasswordField txtPass;

    public LoginPage(StudentController controller) {
        setTitle("Login Portal");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Student Result Management System", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(70, 130, 180));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setPreferredSize(new Dimension(400, 50));
        add(lblTitle, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        centerPanel.add(new JLabel("Username:"));
        txtUser = new JTextField();
        centerPanel.add(txtUser);
        centerPanel.add(new JLabel("Password:"));
        txtPass = new JPasswordField();
        centerPanel.add(txtPass);
        add(centerPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnLogin = new JButton("Login");
        JButton btnClear = new JButton("Clear");
        btnLogin.setBackground(new Color(60, 179, 113));
        btnLogin.setForeground(Color.WHITE);
        btnClear.setBackground(new Color(220, 20, 60));
        btnClear.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnClear.setFocusPainted(false);

        btnPanel.add(btnLogin);
        btnPanel.add(btnClear);
        add(btnPanel, BorderLayout.SOUTH);

        btnLogin.addActionListener(e -> {
            String u = txtUser.getText().trim();
            String p = new String(txtPass.getPassword());

            if (u.equals("TCH001") && p.equals("teach123")) {
                JOptionPane.showMessageDialog(this, "Teacher login successful!");
                dispose();
                new TeachPage(controller).setVisible(true);
            }
            else {
                try {
                    int id = Integer.parseInt(u);
                    if (p.equals("stud123")) {
                        JOptionPane.showMessageDialog(this, "Student login successful!");
                        dispose();
                        new StudentPage(controller, id).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid student password!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid username or password!");
                }
            }
        });

        btnClear.addActionListener(e -> {
            txtUser.setText("");
            txtPass.setText("");
        });
    }
}
