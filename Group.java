package com.course.c02.c03;

public class Group {
    int id;

    String serialize() {
        return id + ";";
    }

    void deserialize(String serialized) {
        String[] words = serialized.split(",");
        id = Integer.parseInt(words[0]);
    }
}
