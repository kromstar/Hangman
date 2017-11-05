package com.course.c02.c03;

import java.io.*;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Student[] students = new Student[100];
        Teacher[] teachers = new Teacher[100];
        Exam[] exams = new Exam[100];
        int students_count = 0;
        int teachers_count = 0;
        int exams_count = 0;
        int exit_code = 1;
        while (exit_code == 1) {
            System.out.println("\nPLEASE SELECT WHAT DO YOU WANT TO DO:\n");
            System.out.println("Enter 'student' to add a new student.");
            System.out.println("Enter 'teacher' to add a new teacher.");
            System.out.println("Enter 'exam' to add a new exam.");
            System.out.println("Enter 'print' to print all teachers, students and exams.");
            System.out.println("Enter 'clear' to clear the entire information.");
            System.out.println("Enter 'save' to save all teachers, students and exams in the given filename.");
            System.out.println("Enter 'load' to clear all lists and load teachers, students and exams from the given filename.");
            System.out.println("Enter 'stop' to leave the menu.");
            String option = reader.readLine();
            switch (option) {
                case "student": {
                    students[++students_count] = new Student();
                    System.out.println("Enter the name in the following format: <First name>,<Last name>");
                    String name = reader.readLine();
                    students[students_count].deserialize(name);
                    break;
                }
                case "teacher": {
                    teachers[++teachers_count] = new Teacher();
                    System.out.println("Enter the name in the following format: <First name>,<Last name>");
                    String name = reader.readLine();
                    students[students_count].deserialize(name);
                    break;
                }
                case "exam": {
                    exams[++exams_count] = new Exam();
                    System.out.println("Enter the exam in the following format: <Student's first name>,<Student's last name>,<Teacher's first name>,<Teacher's last name>,<Grade>");
                    String name = reader.readLine();
                    exams[exams_count].deserialize(name);
                }
                case "print": {
                    if (teachers_count > 0) {
                        System.out.println("\nThe teachers are:");
                        for (int i = 1; i <= teachers_count; i++) {
                            System.out.print(teachers[i].serialize());
                        }
                    } else {
                        System.out.println("\nThere are no teachers registered.\n");
                    }
                    if (students_count > 0) {
                        System.out.println("\n\nThe students are:");
                        for (int i = 1; i <= students_count; i++) {
                            System.out.print(students[i].serialize());
                        }
                    } else {
                        System.out.println("\nThere are no students registered.\n");
                    }
                    if (exams_count > 0) {
                        System.out.println("\n");
                        for (int i = 1; i <= exams_count; i++) {
                            System.out.println("Teacher " + exams[i].teacher.first_name + " " + exams[i].teacher.last_name + " graded " + exams[i].student.first_name + " " + exams[i].student.last_name + " with " + exams[i].grade);
                        }
                    } else {
                        System.out.println("There are no exams registered.");
                    }
                    break;
                }
                case "clear": {
                    Arrays.fill(students, null);
                    Arrays.fill(teachers, null);
                    Arrays.fill(exams, null);
                    teachers_count = 0;
                    students_count = 0;
                    exams_count = 0;
                    System.out.println("\nThe entire information has been deleted.\n");
                    break;
                }
                case "save": {
                    System.out.println("Enter the file name:");
                    String file_name = reader.readLine();
                    try {
                        FileWriter writer = new FileWriter(file_name);
                        if (teachers_count > 0) {
                            for (int i = 1; i <= teachers_count; i++) {
                                writer.write(teachers[i].serialize());
                            }
                        } else {
                            writer.write("N/A");
                        }
                        writer.write("\n");
                        if (students_count > 0) {
                            for (int i = 1; i <= students_count; i++) {
                                writer.write(students[i].serialize());
                            }
                        } else {
                            writer.write("N/A");
                        }
                        writer.write("\n");
                        if (exams_count > 0) {
                            for (int i = 1; i <= exams_count; i++) {
                                writer.write(exams[i].serialize());
                            }
                        } else {
                            writer.write("N/A");
                        }
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "load": {
                    Arrays.fill(students, null);
                    Arrays.fill(teachers, null);
                    Arrays.fill(exams, null);
                    teachers_count = 0;
                    students_count = 0;
                    exams_count = 0;
                    System.out.println("Enter the file name:");
                    String file_name = reader.readLine();
                    BufferedReader fileReader = new BufferedReader(new FileReader(file_name));
                    String line = fileReader.readLine();
                    if (!line.equals("N/A")) {
                        String[] instances = line.split(";");
                        for (String instance : instances) {
                            teachers[++teachers_count] = new Teacher();
                            teachers[teachers_count].deserialize(instance);
                        }
                    }
                    line = fileReader.readLine();
                    if (!line.equals("N/A")) {
                        String[] instances = line.split(";");
                        for (String instance : instances) {
                            students[++students_count] = new Student();
                            students[students_count].deserialize(instance);
                        }
                    }
                    line = fileReader.readLine();
                    if (!line.equals("N/A")) {
                        String[] instances = line.split(";");
                        for (String instance : instances) {
                            exams[++exams_count] = new Exam();
                            exams[exams_count].deserialize(instance);
                        }
                    }
                    fileReader.close();
                    break;
                }
                case "stop": {
                    exit_code = 0;
                    break;
                }
                default:
                    System.out.println("\nYou didn't enter a right choice!\n");
            }
        }
        reader.close();
    }
}