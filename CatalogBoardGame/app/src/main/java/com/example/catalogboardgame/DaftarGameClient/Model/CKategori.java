package com.example.catalogboardgame.DaftarGameClient.Model;

import com.example.catalogboardgame.model.CatalogBoardGame;

import java.util.ArrayList;

public class CKategori {
    String Kategori;

    ArrayList<CatalogBoardGame> ALCatalogBoardGame;

    public CKategori(String Kategori, ArrayList<CatalogBoardGame> ALCatalogBoardGame) {
        this.Kategori = Kategori;
        this.ALCatalogBoardGame = ALCatalogBoardGame;
    }


    public String getKategori() {
        return Kategori;
    }

    public void setKategori(String kategori) {
        this.Kategori = kategori;
    }

    public ArrayList<CatalogBoardGame> getALCatalogBoardGame() {
        return ALCatalogBoardGame;
    }

    public void setALCatalogBoardGame(ArrayList<CatalogBoardGame> ALCatalogBoardGame) {
        this.ALCatalogBoardGame = ALCatalogBoardGame;
    }
}
