package com.example.catalogboardgame.DashboardAdmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.catalogboardgame.R;
import com.example.catalogboardgame.firebaseauth.Akunfirebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class TampilUser extends AppCompatActivity {
    ListView listView;
    private static final String TAG = "TampilSemuaUser";
    private EditText editTextExcel;
    private File filePath = new File(Environment.getExternalStorageState() + "/Demo.csv");
    int count=0;
    Button add,buka;
    ArrayAdapter arrayAdapter;
    ArrayList<String> arrayTampil=new ArrayList<>();
    ArrayList<String> arrayTampil1=new ArrayList<>();
    ArrayList<String> arrayTampil2=new ArrayList<>();
    ArrayList<String> arrayEdit=new ArrayList<>();
    ArrayList<String> arrayHapus=new ArrayList<>();
    String tgl;

    DatabaseReference dreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_user);


        listView =findViewById(R.id.listdatas);
        add=findViewById(R.id.btmAdd);
        buka=findViewById(R.id.open);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH.mm.ss");
        tgl=sdf.format(new Date()).toString();

        dreference= FirebaseDatabase.getInstance().getReference().child("Akuns");
        arrayAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arrayTampil);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String nama = arrayTampil.get(position);
                String email =arrayTampil.get(position);
                String UID =arrayTampil.get(position);

                String keyTarget = arrayEdit.get(position).toString().trim();

            }
        });
        dreference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d(TAG, "onChildChanged:" + snapshot.getKey());
                dreference= FirebaseDatabase.getInstance().getReference().child("Akuns").child(snapshot.getKey());

                Log.d(TAG, "onChildChanged:" + dreference);
                Log.d(TAG, "onChildChanged:" + snapshot.getValue());
                count++;
                Log.d(TAG, "count: " + count);


                String hasil=(String) snapshot.child("nama").getValue();
                String mails=(String) snapshot.child("email").getValue();
                String hasil2=(String) snapshot.child("namaa").getValue();
                String mails2=(String) snapshot.child("emaila").getValue();
                String Spave= "   |  |   ";
                String ssas=",";
                String data= hasil +Spave+ mails;
                String data1= hasil +ssas+ mails;
                String data2=hasil2 +ssas+mails2;

                arrayTampil1.add(data1);

                arrayTampil.add(data);
                Log.d(TAG, "onChildChanged:" + arrayTampil);

                //arrayTampil.add(hasil);
//                Mahasiswa mahasiswa=snapshot.getValue(Mahasiswa.class);
//                String nim=mahasiswa.nim;
//                String nama=mahasiswa.nama;


                String key= snapshot.getKey();


                arrayEdit.add(key);
                arrayHapus.add(key);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,0x1);
                askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,0x2);
                String da="Nama,Email\n";
                StringBuffer as=new StringBuffer();
                for (String s:arrayTampil1)
                {
                    as.append(s);
                    as.append("\n");
                }
                String asa = as.toString();
                String ca=da+asa;
                generateNoteOnSD(TampilUser.this,"/"+" "+tgl+".csv",ca);


            }
        });

    }
    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(TampilUser.this, permission)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    TampilUser.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(TampilUser.this,
                        new String[]{permission}, requestCode);

            } else {
                ActivityCompat.requestPermissions(TampilUser.this,
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
            File root = new File(pth + "/Daftar USER");
            root.mkdir();
            //awalnya file rooth pake path
            Log.d(TAG, "generateNoteOnSD: "+root);
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            Log.d(TAG, "generateNoteOnSD: "+root);

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
            File root = new File(path + "/Notes.txt");
            Log.d(TAG, "generateNoteOnSD: "+root);
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            Log.d(TAG, "generateNoteOnSD: "+root);

            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}