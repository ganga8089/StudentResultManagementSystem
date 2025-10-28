package model;

import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class StudentModel {
    private Connection conn;

    public StudentModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "student_db", "123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validateTeacher(String user, String pass) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM teachers WHERE username=? AND password=?")) {
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int validateStudent(String user, String pass) {
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT student_id FROM student_login WHERE username=? AND password=?")) {
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("student_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean addStudent(String name, String className, int maths, int eng, int sci, int soc, int hin) {
        String sql = "INSERT INTO students (name, class_name, maths, english, science, social, hindi) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, className);
            ps.setInt(3, maths);
            ps.setInt(4, eng);
            ps.setInt(5, sci);
            ps.setInt(6, soc);
            ps.setInt(7, hin);

            int rows = ps.executeUpdate();
            if (rows == 1) {
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    int studentId = keys.getInt(1);
                    PreparedStatement ps2 = conn.prepareStatement(
                            "INSERT INTO student_login (student_id, username, password) VALUES (?, ?, 'stud123')");
                    ps2.setInt(1, studentId);
                    ps2.setString(2, String.valueOf(studentId)); 
                    ps2.executeUpdate();
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public DefaultTableModel getAllStudents() {
        String[] cols = {"ID", "Name", "Class", "Maths", "English", "Science", "Social", "Hindi"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM students")) {
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("class_name"));
                row.add(rs.getInt("maths"));
                row.add(rs.getInt("english"));
                row.add(rs.getInt("science"));
                row.add(rs.getInt("social"));
                row.add(rs.getInt("hindi"));
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }
}
