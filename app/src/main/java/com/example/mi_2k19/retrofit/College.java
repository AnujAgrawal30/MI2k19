package com.example.mi_2k19.retrofit;

public class College {
    private int id;
    private String college_name;
    private String status;
    private int located_city;

    public College(int id, String college_name, String status, int located_city){
        this.id = id;
        this.college_name = college_name;
        this.status = status;
        this.located_city = located_city;
    }

    public int getId() {
        return id;
    }

    public int getLocated_city() {
        return located_city;
    }

    public String getCollege_name() {
        return college_name;
    }

    public String getStatus() {
        return status;
    }
}
