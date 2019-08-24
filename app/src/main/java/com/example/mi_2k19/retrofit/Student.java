package com.example.mi_2k19.retrofit;

public class Student {
    private String name;
    private String type1;
    private String fb_id;
    private String insta_id;
    private String sop;
    private String college;

    public Student(String name, String type1, String fb_id, String insta_id, String sop, String college){
        this.college = college;
        this.fb_id = fb_id;
        this.name = name;
        this.type1 = type1;
        this.insta_id = insta_id;
        this.sop = sop;
    }

    public String getCollege() {
        return college;
    }

    public String getFb_id() {
        return fb_id;
    }

    public String getInsta_id() {
        return insta_id;
    }

    public String getName() {
        return name;
    }

    public String getSop() {
        return sop;
    }

    public String getType1() {
        return type1;
    }
}
