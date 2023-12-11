package com.example.orderfoodversion3;

public class HelperClassUser {
    String firstname, lastename , password , email , phoneNumber ;

    public HelperClassUser(String firstname, String lastename, String password, String email, String phoneNumber) {
        this.firstname = firstname;
        this.lastename = lastename;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastename() {
        return lastename;
    }

    public void setLastename(String lastename) {
        this.lastename = lastename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
