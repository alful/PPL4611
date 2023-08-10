package com.example.catalogboardgame.firebaseauth;

public class Akunfirebase {
    public String toPrinta()
    {
        return this.nama+ " "+email;
    }


    public Akunfirebase(){

    }
    public  String sas(){
        return this.manag;
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

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Akunfirebase(String nama, String email, String password, String manag, String UID, String ID) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.manag = manag;
        this.UID = UID;
        this.ID = ID;
    }

    private String nama;
    private String email;
    private String password;
    private String manag;
    private String UID;
    private String ID;
}
