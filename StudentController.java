package controller;

import model.StudentModel;
import javax.swing.table.DefaultTableModel;

public class StudentController {
    private StudentModel model;

    public StudentController() {
        model = new StudentModel();
    }

    public void start() {
        new view.LoginPage(this).setVisible(true);
    }

    public boolean validateTeacher(String user, String pass) {
        return model.validateTeacher(user, pass);
    }

    public int validateStudent(String user, String pass) {
        return model.validateStudent(user, pass);
    }

    public boolean addStudent(String name, String className, int maths, int eng, int sci, int soc, int hin) {
        return model.addStudent(name, className, maths, eng, sci, soc, hin);
    }

    public DefaultTableModel getStudents() {
        return model.getAllStudents();
    }
}
