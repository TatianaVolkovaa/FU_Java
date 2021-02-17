package com.company.task1;

public class Main {

    public static void main(String[] args) {
        MyStringBuilder stringBuilder = new MyStringBuilder("Hello");
        stringBuilder.setListener(new Worker());
        stringBuilder.append(", ");
        stringBuilder.append("World!");
        stringBuilder.replace(0,5,"Goodbye");
        }
}
