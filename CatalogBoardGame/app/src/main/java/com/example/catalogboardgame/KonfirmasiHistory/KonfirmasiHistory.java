package com.example.catalogboardgame.KonfirmasiHistory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.catalogboardgame.DaftarGameClient.Adapter.DaftarGameClientAdapters;
import com.example.catalogboardgame.DashboardAdmin.DashAdmin;
import com.example.catalogboardgame.KonfirmasiHistory.Adapter.KonfirmasiHistoryAdapter;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.model.CatalogBoardGame;
import com.example.catalogboardgame.model.History;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KonfirmasiHistory extends AppCompatActivity {
    RecyclerView RVKonfirmasiHistory;
    KonfirmasiHistoryAdapter konfirmasiHistoryAdapter;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("History");

    ArrayList<History> ALHistory = new ArrayList<History>();
//    ArrayList<String> ALKey = new ArrayList<String>();
    ArrayList<String> ALGambarGame = new ArrayList<String>();

    ImageView IVKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_history);

        RVKonfirmasiHistory = findViewById(R.id.idRVKonfirmasiHistory);
        IVKembali = findViewById(R.id.idIVKembali);

//        final ArrayList<History> ALHistory = new ArrayList<>();
//        final ArrayList<String> ALKey = new ArrayList<String>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ALHistory.clear();
//                ALKey.clear();
                ALGambarGame.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    History history = dataSnapshot.getValue(History.class);
//                    String key = dataSnapshot.getKey();
                    String gambarGame = dataSnapshot.getValue(History.class).getGambarGame();

                    String Konfirmasi = dataSnapshot.getValue(History.class).getKonfirmasi();

                    if(Konfirmasi.equals("0")) {
                        ALHistory.add(history);
//                        ALKey.add(key);
                        ALGambarGame.add(gambarGame);
                    }
                }
//                Toast.makeText(KonfirmasiHistory.this, ""+ALKey, Toast.LENGTH_SHORT).show();
                konfirmasiHistoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        IVKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KonfirmasiHistory.this, DashAdmin.class);
                startActivity(intent);
            }
        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RVKonfirmasiHistory.setLayoutManager(layoutManager);
//        konfirmasiHistoryAdapter = new KonfirmasiHistoryAdapter(this,  ALHistory, ALKey, ALGambarGame);
        konfirmasiHistoryAdapter = new KonfirmasiHistoryAdapter(this,  ALHistory, ALGambarGame);
        RVKonfirmasiHistory.setAdapter(konfirmasiHistoryAdapter);

    }
}