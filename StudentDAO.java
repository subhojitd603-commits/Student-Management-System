package com.bca.sms;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public boolean addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (name, department, semester, email, subject1, subject2, subject3) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setStudentFields(ps, student);
            return ps.executeUpdate() == 1;
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY student_id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) students.add(mapStudent(rs));
        }
        return students;
    }

    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapStudent(rs) : null;
            }
        }
    }

    public boolean updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET name=?, department=?, semester=?, email=?, "
                   + "subject1=?, subject2=?, subject3=? WHERE student_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setStudentFields(ps, student);
            ps.setInt(8, student.getId());
            return ps.executeUpdate() == 1;
        }
    }

    public boolean deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    private void setStudentFields(PreparedStatement ps, Student s) throws SQLException {
        ps.setString(1, s.getName());
        ps.setString(2, s.getDepartment());
        ps.setInt(3, s.getSemester());
        ps.setString(4, s.getEmail());
        ps.setDouble(5, s.getSubject1());
        ps.setDouble(6, s.getSubject2());
        ps.setDouble(7, s.getSubject3());
    }

    private Student mapStudent(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("student_id"),
                rs.getString("name"),
                rs.getString("department"),
                rs.getInt("semester"),
                rs.getString("email"),
                rs.getDouble("subject1"),
                rs.getDouble("subject2"),
                rs.getDouble("subject3")
        );
    }
}
