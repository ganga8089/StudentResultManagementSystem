package view;

import controller.StudentController;
import javax.swing.*;

public class ViewStudents extends JFrame {
    public ViewStudents(StudentController controller) {
        setTitle("All Students");
        setSize(700, 300);
        setLocationRelativeTo(null);

        JTable table = new JTable(controller.getStudents());
        add(new JScrollPane(table));
    }
}
