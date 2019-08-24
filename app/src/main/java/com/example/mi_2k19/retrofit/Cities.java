package com.example.mi_2k19.retrofit;

public class Cities {
    private int id;
    private String city_name;

    public Cities(int id, String city_name){
        this.id = id;
        this.city_name = city_name;
    }

    public int getId() {
        return id;
    }

    public String getCity_name() {
        return city_name;
    }
}
