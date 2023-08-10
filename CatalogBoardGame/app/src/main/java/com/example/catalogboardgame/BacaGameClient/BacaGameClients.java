package com.example.catalogboardgame.BacaGameClient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.catalogboardgame.DaftarGameClient.Adapter.DaftarGameClientGambarGameRecyclerAdapters;
import com.example.catalogboardgame.LeaderboardClient.LeaderboardClient;
import com.example.catalogboardgame.LeaderboardUser;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.TambahHistoryClient.TambahHistoryClient;
import com.example.catalogboardgame.model.CatalogBoardGame;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BacaGameClients extends AppCompatActivity {
    DatabaseReference databaseReference;
    CatalogBoardGame catalogBoardGame;
    TextView TVNamaGame, TVJumlahPemain, TVDeskripsiGame, TVKategori;
    ImageView IVGambarGame, IVKembali;
    Button BLeaderboard, BAddHistory;

    String SNamaGame;
    String SKategori;
    String SDeskripsiGame;
    Integer IJumlahPemain;

    String SGambarGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baca_game_clients);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("CatalogBoardGame");

        catalogBoardGame = new CatalogBoardGame();

        TVNamaGame = findViewById(R.id.idTVNamaGame);
        TVJumlahPemain = findViewById(R.id.idTVJumlahPemain);
        TVDeskripsiGame = findViewById(R.id.idTVDeskripsiGame);
        TVKategori = findViewById(R.id.idTVKategori);
        IVGambarGame = findViewById(R.id.idIVGambarGame);
        IVKembali = findViewById(R.id.idIVKembali);

        BLeaderboard = findViewById(R.id.idBLeaderboard);
        BAddHistory = findViewById(R.id.idBAddHistory);

        Intent intent = getIntent();
        SNamaGame = intent.getStringExtra("NamaGame");
        IJumlahPemain = intent.getIntExtra("JumlahPemain", 0);
        SKategori = intent.getStringExtra("Kategori");
        SDeskripsiGame = intent.getStringExtra("DeskripsiGame");

        Glide.with(this).load(intent.getStringExtra("GambarGame")).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(IVGambarGame);
        TVNamaGame.setText(SNamaGame);
        TVJumlahPemain.setText(Integer.toString(IJumlahPemain));
        TVKategori.setText(SKategori);
        TVDeskripsiGame.setText(SDeskripsiGame);

        IVKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        BLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenta = new Intent(BacaGameClients.this, LeaderboardClient.class);
                intenta.putExtra("NamaGame", SNamaGame);
                startActivity(intenta);
            }
        });

        BAddHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenta = new Intent(BacaGameClients.this, TambahHistoryClient.class);
                intenta.putExtra("GambarGame", SGambarGame);
                intenta.putExtra("NamaGame", SNamaGame);
                intenta.putExtra("JumlahPemain", IJumlahPemain);
                intenta.putExtra("Kategori", SKategori);
                intenta.putExtra("DeskripsiGame", SDeskripsiGame);
                startActivity(intenta);
            }
        });
    }
}
