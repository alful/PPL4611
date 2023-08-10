package com.example.catalogboardgame.model;

public class CatalogBoardGame {
    private String NamaGame;
    private String Kategori;
    private String GambarGame;
    private Integer JumlahPemain;
    private String DeskripsiGame;

    public CatalogBoardGame(){

    }

    public CatalogBoardGame(String GambarGame){
        this.GambarGame = GambarGame;
    }

    public String getGambarGame() {
        return GambarGame;
    }

    public void setGambarGame(String gambarGame) {
        this.GambarGame = gambarGame;
    }

    public String getNamaGame() {
        return NamaGame;
    }

    public void setNamaGame(String namaGame) {
        this.NamaGame = namaGame;
    }

    public String getKategori() {
        return Kategori;
    }

    public void setKategori(String SKategori) {
        this.Kategori = SKategori;
    }

    public Integer getJumlahPemain() {
        return JumlahPemain;
    }

    public void setJumlahPemain(Integer jumlahPemain) {
        this.JumlahPemain = jumlahPemain;
    }

    public String getDeskripsiGame() {
        return DeskripsiGame;
    }

    public void setDeskripsiGame(String deskripsiGame) {
        this.DeskripsiGame = deskripsiGame;
    }
}
