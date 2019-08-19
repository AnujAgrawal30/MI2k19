package com.example.mi_2k19.retrofit;


public class Hero {

    private String bloger_name;
    private String pic_url;
    private String types;
    private String College;
    private String bloger_topic;
    private String bloger_blog;
    private String bloger_status;
    private String bloger_pic;
    private String fblink;
    private String instalink;


    public Hero(String bloger_name, String pic_url, String types, String College, String bloger_topic, String bloger_blog, String bloger_status, String bloger_pic, String fblink, String instalink) {
        this.bloger_name = bloger_name;
        this.pic_url = pic_url;
        this.types = types;
        this.College = College;
        this.bloger_topic = bloger_topic;
        this.bloger_blog = bloger_blog;
        this.bloger_status = bloger_status;
        this.bloger_pic = bloger_pic;
        this.bloger_pic = fblink;
        this.bloger_pic = instalink;
    }

    public String getBloger_name() {
        return bloger_name;
    }

    public String getPic_url() {
        return pic_url;
    }

    public String getTypes() {
        return types;
    }

    public String getCollege() {
        return College;
    }

    public String getBloger_topic() {
        return bloger_topic;
    }

    public String getBloger_blog() {
        return bloger_blog;
    }

    public String getBloger_status() {
        return bloger_status;
    }

    public String getBloger_pic() {
        return bloger_pic;
    }

    public String getFblink() {
        return fblink;
    }

    public String getInstalink() {
        return instalink;
    }
}