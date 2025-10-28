package view;

import controller.StudentController;
import javax.swing.*;
import java.awt.*;

public class AddStudent extends JFrame {
    private JTextField name, className, maths, eng, sci, soc, hin;

    public AddStudent(StudentController controller) {
        setTitle("Add Student");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2, 8, 8));

        add(new JLabel("Name:")); name = new JTextField(); add(name);
        add(new JLabel("Class Name:")); className = new JTextField(); add(className);
        add(new JLabel("Maths:")); maths = new JTextField(); add(maths);
        add(new JLabel("English:")); eng = new JTextField(); add(eng);
        add(new JLabel("Science:")); sci = new JTextField(); add(sci);
        add(new JLabel("Social:")); soc = new JTextField(); add(soc);
        add(new JLabel("Hindi:")); hin = new JTextField(); add(hin);

        JButton btnSave = new JButton("Save");
        btnSave.setBackground(new Color(0, 123, 255));
        btnSave.setForeground(Color.WHITE);

        btnSave.addActionListener(e -> {
            try {
                String n = name.getText().trim();
                String c = className.getText().trim();
                int m = Integer.parseInt(maths.getText());
                int e1 = Integer.parseInt(eng.getText());
                int s1 = Integer.parseInt(sci.getText());
                int so = Integer.parseInt(soc.getText());
                int h = Integer.parseInt(hin.getText());

                if (controller.addStudent(n, c, m, e1, s1, so, h)) {
                    JOptionPane.showMessageDialog(this, "Student added successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add student!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid details!");
            }
        });

        add(new JLabel());
        add(btnSave);
    }
}
