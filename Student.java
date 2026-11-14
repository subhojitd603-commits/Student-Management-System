package com.bca.sms;

public class Student {
    private final int id;
    private final String name;
    private final String department;
    private final int semester;
    private final String email;
    private final double subject1;
    private final double subject2;
    private final double subject3;

    public Student(int id, String name, String department, int semester, String email,
                   double subject1, double subject2, double subject3) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.semester = semester;
        this.email = email;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getSemester() { return semester; }
    public String getEmail() { return email; }
    public double getSubject1() { return subject1; }
    public double getSubject2() { return subject2; }
    public double getSubject3() { return subject3; }

    public double getTotal() { return subject1 + subject2 + subject3; }
    public double getAverage() { return getTotal() / 3.0; }

    public String getGrade() {
        double average = getAverage();
        if (average >= 90) return "A+";
        if (average >= 80) return "A";
        if (average >= 70) return "B";
        if (average >= 60) return "C";
        if (average >= 50) return "D";
        return "F";
    }
}
