package com.example.catalogboardgame.model;

public class akn {
    private String nama;
    private String email;
    private String password;
    private String manag;

    public akn(){

    }


    public akn(String nama, String email, String password, String manag) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.manag = manag;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getManag() {
        return manag;
    }

    public void setManag(String manag) {
        this.manag = manag;
    }
}
