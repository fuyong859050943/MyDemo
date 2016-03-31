package com.example.administrator.myapplication.entity;

/**
 * Created by Administrator on 2016/3/30.
 */
public class Contact {
    private Integer _id;
    private String name;
    private String phone;

    public Contact(){}

    public Contact(Integer id, String name, String phone){
        this._id = id;
        this.name = name;
        this.phone = phone;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
