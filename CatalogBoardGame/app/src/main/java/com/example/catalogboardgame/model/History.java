package com.example.catalogboardgame.model;

public class History {
    private String Konfirmasi;
    private String GambarGame;
    private String NamaGame;
    private String Name;
    private Integer Win;
    private Integer Lose;
    private String Tanggal;
    private Integer Day;
    private Integer Month;
    private Integer Year;

    private Integer MinDay;
    private Integer MinMonth;
    private Integer MinYear;

    public History(){

    }

    public History(String name, Integer win, Integer lose, String tanggal, Integer day, Integer month, Integer year) {
        this.Name = name;
        this.Win = win;
        this.Lose = lose;
        this.Tanggal = tanggal;
        this.Day = day;
        this.Month = month;
        this.Year = year;
    }

    public String getKonfirmasi() {
        return Konfirmasi;
    }

    public void setKonfirmasi(String konfirmasi) {
        Konfirmasi = konfirmasi;
    }

    public String getGambarGame() {
        return GambarGame;
    }

    public void setGambarGame(String gambarGame) {
        GambarGame = gambarGame;
    }

    public String getNamaGame() {
        return NamaGame;
    }

    public void setNamaGame(String namaGame) {
        NamaGame = namaGame;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getWin() {
        return Win;
    }

    public void setWin(Integer win) {
        Win = win;
    }

    public Integer getLose() {
        return Lose;
    }

    public void setLose(Integer lose) {
        Lose = lose;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public Integer getDay() {
        return Day;
    }

    public void setDay(Integer day) {
        Day = day;
    }

    public Integer getMonth() {
        return Month;
    }

    public void setMonth(Integer month) {
        Month = month;
    }

    public Integer getYear() {
        return Year;
    }

    public void setYear(Integer year) {
        Year = year;
    }

    public Integer getMinDay() {
        return MinDay;
    }

    public void setMinDay(Integer minDay) {
        MinDay = minDay;
    }

    public Integer getMinMonth() {
        return MinMonth;
    }

    public void setMinMonth(Integer minMonth) {
        MinMonth = minMonth;
    }

    public Integer getMinYear() {
        return MinYear;
    }

    public void setMinYear(Integer minYear) {
        MinYear = minYear;
    }
}
