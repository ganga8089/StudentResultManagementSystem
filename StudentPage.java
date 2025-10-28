package view;

import controller.StudentController;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentPage extends JFrame {
    private JLabel lblName, lblClass, lblMaths, lblEng, lblSci, lblSoc, lblHin, lblTotal, lblPercent, lblResult;

    public StudentPage(StudentController controller, int studentId) {
        setTitle("RESULT");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 247, 250));

        // ==== HEADER ====
        JLabel lblHeader = new JLabel("RESULT", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblHeader.setOpaque(true);
        lblHeader.setBackground(new Color(33, 150, 243));
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setPreferredSize(new Dimension(600, 60));
        add(lblHeader, BorderLayout.NORTH);

        // ==== MAIN PANEL ====
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // ==== INFO CARD ====
        JPanel infoCard = createCardPanel();
        lblName = createValueLabel();
        lblClass = createValueLabel();
        addRow(infoCard, "Name:", lblName);
        addRow(infoCard, "Class:", lblClass);
        mainPanel.add(infoCard);

        // ==== MARKS CARD ====
        JPanel marksCard = createCardPanel();
        lblMaths = createValueLabel();
        lblEng = createValueLabel();
        lblSci = createValueLabel();
        lblSoc = createValueLabel();
        lblHin = createValueLabel();

        addRow(marksCard, "Maths:", lblMaths);
        addRow(marksCard, "English:", lblEng);
        addRow(marksCard, "Science:", lblSci);
        addRow(marksCard, "Social:", lblSoc);
        addRow(marksCard, "Hindi:", lblHin);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(marksCard);

        // ==== SUMMARY CARD ====
        JPanel summaryCard = createCardPanel();
        lblTotal = createValueLabel();
        lblPercent = createValueLabel();
        lblResult = new JLabel("", SwingConstants.CENTER);
        lblResult.setFont(new Font("Segoe UI", Font.BOLD, 16));

        addRow(summaryCard, "Total Marks:", lblTotal);
        addRow(summaryCard, "Percentage:", lblPercent);
        addRow(summaryCard, "Result:", lblResult);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(summaryCard);

        add(mainPanel, BorderLayout.CENTER);

        // Load student data
        loadStudentData(studentId);
    }

    // === Helper methods ===
    private JPanel createCardPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        return panel;
    }

    private JLabel createValueLabel() {
        JLabel lbl = new JLabel("");
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lbl.setForeground(new Color(60, 60, 60));
        return lbl;
    }

    private void addRow(JPanel panel, String labelText, JLabel valueLabel) {
        JLabel lbl = new JLabel(labelText);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lbl.setForeground(new Color(80, 80, 80));
        panel.add(lbl);
        panel.add(valueLabel);
    }

    private void loadStudentData(int id) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_db", "student_db", "123"); // your DB credentials
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String cls = rs.getString("class_name");
                int m = rs.getInt("maths");
                int e = rs.getInt("english");
                int s = rs.getInt("science");
                int so = rs.getInt("social");
                int h = rs.getInt("hindi");

                int total = m + e + s + so + h;
                double percent = total / 5.0;
                String result = (percent >= 33) ? "PASS" : "FAIL";

                lblName.setText(name);
                lblClass.setText(cls);
                lblMaths.setText(String.valueOf(m));
                lblEng.setText(String.valueOf(e));
                lblSci.setText(String.valueOf(s));
                lblSoc.setText(String.valueOf(so));
                lblHin.setText(String.valueOf(h));
                lblTotal.setText(String.valueOf(total));
                lblPercent.setText(String.format("%.2f%%", percent));
                lblResult.setText(result);

                // Result color
                lblResult.setForeground(result.equals("PASS") ? new Color(0, 128, 0) : Color.RED);
            } else {
                JOptionPane.showMessageDialog(this, "No record found for ID: " + id);
                dispose();
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading student data!");
        }
    }
}
