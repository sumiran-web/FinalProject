package com.example.restaurantfinder.required_classes;

public class UserModalAno {

    private String fname;
    private String lname;
    private String email;
    private String password;
    private String phone;
    private String address;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserModalAno(String fname, String lname, String email, String password, String phone, String address) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }
}
