package com.example.catalogboardgame.LeaderboardClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.catalogboardgame.DashboardAdmin.TampilUserRecycler;
import com.example.catalogboardgame.LeaderboardClient.Adapter.LeaderboardClientAdapter;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.model.History;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class LeaderboardAdmin extends AppCompatActivity {
    ArrayList<History> ALHistory = new ArrayList<History>();
    RecyclerView RVLeaderboardClient;
    LeaderboardClientAdapter leaderboardClientAdapter;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    ImageView IVKembali;
    Button BDay, BMonth, BYear;
    TextView TVNamaGame;

    Context context;

    ArrayList<String> ALTanggal = new ArrayList<String>();
    ArrayList<Integer> ALDay = new ArrayList<Integer>();
    //    ArrayList<Integer> ALMonth = new ArrayList<Integer>();
    ArrayList<String> ALNamaD = new ArrayList<String>();
    ArrayList<String> ALSNama = new ArrayList<String>();
    ArrayList<String> ALNamaM = new ArrayList<String>();
    ArrayList<String> datas = new ArrayList<String>();
//    ArrayList<String> ALNamaGame = new ArrayList<String>();

    History history= new History();

    String SNamaGame;
    Button export;
    String tgl;

    String namax,wins,loses;
    Integer win,lose;
    Integer no=0;
    String time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_admin);

        RVLeaderboardClient = findViewById(R.id.idRVLeaderboardClient);
        IVKembali = findViewById(R.id.idIVKembali);
        BDay = findViewById(R.id.idBDay);
        BMonth = findViewById(R.id.idBMonth);
        BYear = findViewById(R.id.idBYear);
        TVNamaGame = findViewById(R.id.idTVNamaGame);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH.mm.ss");
        tgl=sdf.format(new Date()).toString();

        Intent intent = getIntent();
        SNamaGame = intent.getStringExtra("NamaGame");
        TVNamaGame.setText(SNamaGame);
        export=findViewById(R.id.exporrts);


        IVKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        databaseReference.child("History").orderByChild("minDay").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ALHistory.clear();
                datas.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    History history = dataSnapshot.getValue(History.class);
                    String Tanggal = dataSnapshot.child("tanggal").getValue(String.class);
//                    Integer Day = dataSnapshot.child("day").getValue(Integer.class);
                    String NamaGame = dataSnapshot.child("namaGame").getValue(String.class);
                    namax=dataSnapshot.child("name").getValue(String.class);
                    win=dataSnapshot.child("win").getValue(Integer.class);
                    wins=win.toString();
                    lose=dataSnapshot.child("lose").getValue(Integer.class);
                    loses=lose.toString();
                    no++;


                    if(Tanggal.equals(SHariIni())&&NamaGame.equals(SNamaGame)){
                        String dat=""+namax+","+""+wins+","+""+loses+","+""+Tanggal;
                        time="Hari";


                        datas.add(dat);
                        Log.d("TAG", "onDataChange: "+datas);
                        ALHistory.add(history);
                    }
                }

                leaderboardClientAdapter = new LeaderboardClientAdapter(context, ALHistory);
                RVLeaderboardClient.setAdapter(leaderboardClientAdapter);

                leaderboardClientAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LeaderboardAdmin.this, "This Day", Toast.LENGTH_SHORT).show();
                databaseReference.child("History").orderByChild("minDay").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ALHistory.clear();
                        datas.clear();
                        time="Hari";
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            History history = dataSnapshot.getValue(History.class);
                            String Tanggal = dataSnapshot.child("tanggal").getValue(String.class);
                            String NamaGame = dataSnapshot.child("namaGame").getValue(String.class);
                            namax=dataSnapshot.child("name").getValue(String.class);
                            win=dataSnapshot.child("win").getValue(Integer.class);
                            wins=win.toString();
                            lose=dataSnapshot.child("lose").getValue(Integer.class);
                            loses=lose.toString();


                            if(Tanggal.equals(SHariIni())&&NamaGame.equals(SNamaGame)){
                                String dat=""+NamaGame+","+""+namax+","+""+wins+","+""+loses+","+""+Tanggal;
                                datas.add(dat);
                                Log.d("TAG", "onDataChange: "+datas);

                                ALHistory.add(history);
                            }
                        }
                        leaderboardClientAdapter = new LeaderboardClientAdapter(context, ALHistory);
                        RVLeaderboardClient.setAdapter(leaderboardClientAdapter);

                        leaderboardClientAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        BMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LeaderboardAdmin.this, "This Month", Toast.LENGTH_SHORT).show();
                databaseReference.child("History").orderByChild("minMonth").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ALHistory.clear();
                        ALDay.clear();
                        ALNamaD.clear();
                        ALSNama.clear();
                        datas.clear();
                        time="Bulan";

                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            History history = dataSnapshot.getValue(History.class);
                            String Tanggal = dataSnapshot.child("tanggal").getValue(String.class);
                            Integer day = dataSnapshot.child("day").getValue(Integer.class);
                            String nama = dataSnapshot.child("name").getValue(String.class);
                            String Bulan = Tanggal.substring(3, 5);
                            String Bulanini = SHariIni().substring(3, 5);
                            String NamaGame = dataSnapshot.child("namaGame").getValue(String.class);
                            namax=dataSnapshot.child("name").getValue(String.class);
                            win=dataSnapshot.child("win").getValue(Integer.class);
                            wins=win.toString();
                            lose=dataSnapshot.child("lose").getValue(Integer.class);
                            loses=lose.toString();

                            if(Bulan.equals(Bulanini)&&NamaGame.equals(SNamaGame)){
                                String dat=""+NamaGame+","+""+namax+","+""+wins+","+""+loses+","+""+Tanggal;
                                datas.add(dat);
                                Log.d("TAG", "onDataChangea: "+datas);

                                ALHistory.add(history);
                                ALDay.add(day);
                                ALSNama.add(nama);
                            }
                            if(Tanggal.equals(SHariIni())&&NamaGame.equals(SNamaGame)){
                                ALNamaD.add(nama);
                            }
                            ALSNama.removeAll(ALNamaD);
//                            if(ALNama.equals(ALSNama)){
//                                ALSNama.removeIf(namas->namas.equals(ALNama));
//                                ALSNama.removeAll(ALNama);
//                                ALNamaBulan.add(nama);
//                            }
                        }
                        leaderboardClientAdapter = new LeaderboardClientAdapter(context, ALHistory, ALSNama);
                        RVLeaderboardClient.setAdapter(leaderboardClientAdapter);

                        leaderboardClientAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        BYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LeaderboardAdmin.this, "This Year", Toast.LENGTH_SHORT).show();
                databaseReference.child("History").orderByChild("minYear").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ALHistory.clear();
                        ALDay.clear();
                        ALNamaD.clear();
                        ALSNama.clear();
                        ALNamaM.clear();
                        datas.clear();
                        time="Tahun";

//                        ALMonth.clear();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            History history = dataSnapshot.getValue(History.class);
                            String Tanggal = dataSnapshot.child("tanggal").getValue(String.class);
                            Integer day = dataSnapshot.child("day").getValue(Integer.class);
                            String nama = dataSnapshot.child("name").getValue(String.class);
                            String Tahun = Tanggal.substring(6);
                            String Bulan = Tanggal.substring(3, 5);
                            String Tahunini = SHariIni().substring(6);
                            String Bulanini = SHariIni().substring(3, 5);
                            String NamaGame = dataSnapshot.child("namaGame").getValue(String.class);
                            namax=dataSnapshot.child("name").getValue(String.class);
                            win=dataSnapshot.child("win").getValue(Integer.class);
                            wins=win.toString();
                            lose=dataSnapshot.child("lose").getValue(Integer.class);
                            loses=lose.toString();

                            if(Tahun.equals(Tahunini)&&NamaGame.equals(SNamaGame)){
                                String dat=""+NamaGame+","+""+namax+","+""+wins+","+""+loses+","+""+Tanggal;
                                datas.add(dat);
                                Log.d("TAG", "onDataChangea: "+datas);

                                ALHistory.add(history);
                                ALDay.add(day);
//                                ALMonth.add(month);
                                ALSNama.add(nama);
                            }
                            if(Bulan.equals(Bulanini)&&NamaGame.equals(SNamaGame)){
                                ALNamaM.add(nama);
                            }
                            if(Tanggal.equals(SHariIni())&&NamaGame.equals(SNamaGame)){
                                ALNamaD.add(nama);
                            }
                            ALSNama.removeAll(ALNamaM);
                            ALNamaM.removeAll(ALNamaD);

                        }
                        leaderboardClientAdapter = new LeaderboardClientAdapter(context, ALHistory, ALSNama, ALNamaD, ALNamaM);
                        RVLeaderboardClient.setAdapter(leaderboardClientAdapter);

                        leaderboardClientAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,0x1);
                askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,0x2);
                String da="Nama Game,Nama Pemain,Menang,Kalah,Tanggal\n";
                StringBuffer as=new StringBuffer();
                for (String s:datas)
                {
                    as.append(s);
                    as.append("\n");
                }
                String asa = as.toString();
                String ca=da+asa;
                generateNoteOnSD(LeaderboardAdmin.this,"/"+" "+tgl+".csv",ca);

            }
        });
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(LeaderboardAdmin.this, permission)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    LeaderboardAdmin.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(LeaderboardAdmin.this,
                        new String[]{permission}, requestCode);

            } else {
                ActivityCompat.requestPermissions(LeaderboardAdmin.this,
                        new String[]{permission}, requestCode);
            }
        } else {
//            Toast.makeText(this, permission + " is already granted.",
//                    Toast.LENGTH_SHORT).show();
        }
    }


    private String SHariIni(){
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }


    public void generateNoteOnSD(android.content.Context context, String sFileName, String sBody) {
        try {
            String path=context.getFilesDir().getAbsolutePath();
            String jenis = "";
            String pth= "/storage/emulated/0/CatalogBoardGame";
            if (time.equals("Hari"))
                jenis="Hari";
            else if (time.equals("Bulan"))
                jenis="Bulan";
            else if (time.equals("Tahun"))
                jenis="Tahun";

            File root = new File(pth + "/Daftar Leaderboard/"+jenis);
            root.mkdir();
            //awalnya file rooth pake path
            Log.d("TAG", "generateNoteOnSD: "+root);
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            Log.d("TAG", "generateNoteOnSD: "+root);

            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}