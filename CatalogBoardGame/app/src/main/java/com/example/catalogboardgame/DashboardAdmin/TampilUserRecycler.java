package com.example.catalogboardgame.DashboardAdmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.catalogboardgame.KonfirmasiHistory.Adapter.KonfirmasiHistoryAdapter;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.firebaseauth.Akunfirebase;
import com.example.catalogboardgame.model.History;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TampilUserRecycler extends AppCompatActivity {

    RecyclerView Tampiluser;
    recyclertampiladapter recyclertampiladapter;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Akuns");

    ArrayList<Akunfirebase> akunfirebases = new ArrayList<Akunfirebase>();
    //    ArrayList<String> ALKey = new ArrayList<String>();
    ArrayList<String> data = new ArrayList<String>();
    Button export;
    String tgl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_user_recycler);

        Tampiluser = findViewById(R.id.Tampilkanuser);

        export=findViewById(R.id.exportuser);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH.mm.ss");
        tgl=sdf.format(new Date()).toString();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                akunfirebases.clear();
//                ALKey.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Akunfirebase akunfirebase = dataSnapshot.getValue(Akunfirebase.class);
//                    String key = dataSnapshot.getKey();
                    String nama=dataSnapshot.child("nama").getValue(String.class);
                    String email=dataSnapshot.child("email").getValue(String.class);
                    String manag=dataSnapshot.child("manag").getValue(String.class);
                    if (manag.equals("0"))
                        manag="Admin";
                    else if (manag.equals("1"))
                        manag="User";
                    String datas= ""+nama+","+""+email+","+""+manag;
                    data.add(datas);
                    Log.d("TAG", "onDataChange: "+data);


                    akunfirebases.add(akunfirebase);
//                        ALKey.add(key);
                }
//                Toast.makeText(KonfirmasiHistory.this, ""+ALKey, Toast.LENGTH_SHORT).show();
                recyclertampiladapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        Tampiluser.setLayoutManager(layoutManager);
//        konfirmasiHistoryAdapter = new KonfirmasiHistoryAdapter(this,  ALHistory, ALKey, ALGambarGame);
        recyclertampiladapter = new recyclertampiladapter(this,  akunfirebases);
        Tampiluser.setAdapter(recyclertampiladapter);

        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,0x1);
                askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,0x2);
                String da="Nama,Email,Acces Level\n";
                StringBuffer as=new StringBuffer();
                for (String s:data)
                {
                    as.append(s);
                    as.append("\n");
                }
                String asa = as.toString();
                String ca=da+asa;
                generateNoteOnSD(TampilUserRecycler.this,"/"+" "+tgl+".csv",ca);


            }

            private void askForPermission(String readExternalStorage, int i) {

            }
        });
    }
    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(TampilUserRecycler.this, permission)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    TampilUserRecycler.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(TampilUserRecycler.this,
                        new String[]{permission}, requestCode);

            } else {
                ActivityCompat.requestPermissions(TampilUserRecycler.this,
                        new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, permission + " is already granted.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {
            String path=context.getFilesDir().getAbsolutePath();
            String pth= "/storage/emulated/0/CatalogBoardGame";
            File root = new File(pth + "/Daftar User Account");
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
    public void generateNoteOnSD2(Context context, String sFileName, String sBody) {
        try {
            String path=context.getFilesDir().getAbsolutePath();
            File root = new File(path + "/Daftar User Account");
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