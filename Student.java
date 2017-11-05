package com.course.c02.c03;

public class Student {
    String first_name;
    String last_name;

    String serialize() {
        return first_name + "," + last_name + ";";
    }

    void deserialize(String serialized) {
        String[] words = serialized.split(",");
        first_name = words[0];
        last_name = words[1];
    }
}
