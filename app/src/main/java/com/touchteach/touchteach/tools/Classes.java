package com.touchteach.touchteach.tools;

/**
 * Created by Soheil on 8/27/2017.
 */

public class Classes {
    private static  String ClassName;
    private static  String Capacity;
    private static  String Instructor;
    private static  String Limitations;
    private static  String Cost;
    private static  String Date;
    public static String Subject;
    public static String Description;

    public static String getDescription() {
        return Description;
    }

    public static void setDescription(String description) {
        Description = description;
    }

    public static String getSubject() {
        return Subject;
    }

    public static void setSubject(String subject) {
        Subject = subject;
    }

    public static String getClassName() {
        return ClassName;
    }

    public static void setClassName(String className) {
        ClassName = className;
    }

    public static String getCapacity() {
        return Capacity;
    }

    public static void setCapacity(String capacity) {
        Capacity = capacity;
    }

    public static String getInstructor() {
        return Instructor;
    }

    public static void setInstructor(String instructor) {
        Instructor = instructor;
    }

    public static String getLimitations() {
        return Limitations;
    }

    public static void setLimitations(String limitations) {
        Limitations = limitations;
    }

    public static String getCost() {
        return Cost;
    }

    public static void setCost(String cost) {
        Cost = cost;
    }

    public static String getDate() {
        return Date;
    }

    public static void setDate(String date) {
        Date = date;
    }

    public Classes(String name){
        this.ClassName = name;
    }
}
