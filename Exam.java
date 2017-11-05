package com.course.c02.c03;

public class Exam {
    Student student = new Student();
    Teacher teacher = new Teacher();
    int grade;

    String serialize() {
        return student.first_name + "," + student.last_name + "," + teacher.first_name + "," + teacher.last_name + "," + grade + ";";
    }

    void deserialize(String serialized) {
        String[] words = serialized.split(",");
        student.first_name = words[0];
        student.last_name = words[1];
        teacher.first_name = words[2];
        teacher.last_name = words[3];
        grade = Integer.parseInt(words[4]);
    }
}