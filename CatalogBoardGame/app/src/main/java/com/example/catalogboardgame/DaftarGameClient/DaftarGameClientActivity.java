package com.example.catalogboardgame.DaftarGameClient;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catalogboardgame.DaftarGameClient.Adapter.DaftarGameClientAdapter;
import com.example.catalogboardgame.DaftarGameClient.Model.CKategori;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.model.CatalogBoardGame;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DaftarGameClientActivity extends AppCompatActivity {
    RecyclerView RVDaftarGameClient;
    DaftarGameClientAdapter daftarGameClientAdapter;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("CatalogBoardGame");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_game_client);

        RVDaftarGameClient = findViewById(R.id.idRVLihatGameClient);

        final ArrayList<CatalogBoardGame> ALCatalogBoardGameEasy = new ArrayList<>();
        final ArrayList<CatalogBoardGame> ALCatalogBoardGameMedium = new ArrayList<>();
        final ArrayList<CatalogBoardGame> ALCatalogBoardGameHard = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    CatalogBoardGame catalogBoardGame = dataSnapshot.getValue(CatalogBoardGame.class);

                    String Skategori=dataSnapshot.child("kategori").getValue(String.class);

                    if(Skategori.equals("EASY")){
                        ALCatalogBoardGameEasy.add(catalogBoardGame);
                    }
                    else if(Skategori.equals("MEDIUM")){
                        ALCatalogBoardGameMedium.add(catalogBoardGame);
                    }
                    else if(Skategori.equals("HARD")){
                        ALCatalogBoardGameHard.add(catalogBoardGame);
                    }
                }
                daftarGameClientAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        List<CKategori> CKategoriList = new ArrayList<>();
        CKategoriList.add(new CKategori("EASY", ALCatalogBoardGameEasy));
        CKategoriList.add(new CKategori("MEDIUM", ALCatalogBoardGameMedium));
        CKategoriList.add(new CKategori("HARD", ALCatalogBoardGameHard));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RVDaftarGameClient.setLayoutManager(layoutManager);
        daftarGameClientAdapter = new DaftarGameClientAdapter(this, CKategoriList);
        RVDaftarGameClient.setAdapter(daftarGameClientAdapter);
    }

}