package com.example.catalogboardgame.TambahHistoryClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.catalogboardgame.BacaGameClient.BacaGameClient;
import com.example.catalogboardgame.BacaGameClient.BacaGameClients;
import com.example.catalogboardgame.LeaderboardClient.Adapter.LeaderboardClientAdapter;
import com.example.catalogboardgame.LeaderboardClient.LeaderboardClient;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.TambahGameAdmin.tambah_game_admin;
import com.example.catalogboardgame.model.CatalogBoardGame;
import com.example.catalogboardgame.model.History;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;


public class TambahHistoryClient extends AppCompatActivity {
    EditText ETNamaPemain, ETMenang, ETKalah;
    Button BTanggal, BAddHistory;
    TextView TVTanggal, TVNamaGame;
    ImageView IVKembali, IVGambarGame;
    Uri UGambarGame;

    DatabaseReference databaseReference;
    History history;

    String SKeyHistory, SDay, SMonth, SYear, SMinDay, SMinMonth, SMinYear;
    Integer IWin, ILose;

    String SNamaGame;
    final ArrayList<String> ALKey= new ArrayList<String>();
    final ArrayList<Integer> ALWin= new ArrayList<Integer>();
    final ArrayList<Integer> ALLose= new ArrayList<Integer>();

    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_history_client);

        ETNamaPemain = findViewById(R.id.idETNamaPemain);
        ETMenang = findViewById(R.id.idETMenang);
        ETKalah = findViewById(R.id.idETKalah);
        BTanggal = findViewById(R.id.idBTanggal);
        BAddHistory = findViewById(R.id.idBAddHistory);
        IVGambarGame = findViewById(R.id.idIVGambarGame);
        TVTanggal = findViewById(R.id.idTVTanggal);
        IVKembali = findViewById(R.id.idIVKembali);
        TVNamaGame = findViewById(R.id.idTVNamaGame);

        history = new History();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        SKeyHistory = databaseReference.push().getKey();

        Intent intent = getIntent();
        SNamaGame = intent.getStringExtra("NamaGame");
        TVNamaGame.setText(SNamaGame);

        IVKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        IVGambarGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 2);
            }
        });

        BAddHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TambahHistoryClient.this);
                builder.setTitle("Tambah History");
                builder.setMessage("Apakah kamu yakin menambah history?");
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        databaseReference.child("History").addValueEventListener(new ValueEventListener() {
                        databaseReference.child("History").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    String Key = dataSnapshot.getKey();
                                    String Name = dataSnapshot.child("name").getValue(String.class);
                                    Integer Win = dataSnapshot.child("win").getValue(Integer.class);
                                    Integer Lose = dataSnapshot.child("lose").getValue(Integer.class);
//                                    Integer Day = dataSnapshot.child("day").getValue(Integer.class);
//                                    Integer Month = dataSnapshot.child("month").getValue(Integer.class);
//                                    Integer Year = dataSnapshot.child("year").getValue(Integer.class);
//                                    Integer minDay = dataSnapshot.child("minDay").getValue(Integer.class);
//                                    Integer minMonth = dataSnapshot.child("minMonth").getValue(Integer.class);
//                                    Integer minYear = dataSnapshot.child("minYear").getValue(Integer.class);

                                    if(Name.equals(ETNamaPemain.getText().toString())){
                                        ALKey.add(Key);
                                        ALWin.add(Win);
                                        ALLose.add(Lose);
//                                        ALDay.add(Day);
//                                        ALMonth.add(Month);
//                                        ALYear.add(Year);
//                                        ALminDay.add(minDay);
//                                        ALminMonth.add(minMonth);
//                                        ALminYear.add(minYear);
//                                        Toast.makeText(TambahHistoryClient.this, "Nama Ada:"+Name+"ALKEY:"+ALKey, Toast.LENGTH_SHORT).show();
                                    }
                                }
//                                Toast.makeText(TambahHistoryClient.this, "ALKEY"+ALKey, Toast.LENGTH_SHORT).show();
                                if(ALKey.isEmpty()){
//                                    Toast.makeText(TambahHistoryClient.this, "Nama baru"+"ALKey:"+ALKey, Toast.LENGTH_SHORT).show();
//                                    if(ETNamaPemain.getText().toString().isEmpty()&&ETMenang.getText().toString().isEmpty()&&ETKalah.getText().toString().isEmpty()&&TVTanggal.getText().toString().isEmpty()==true){
                                    if(TextUtils.isEmpty(ETNamaPemain.getText().toString())){
                                        ETNamaPemain.setError("Masukkan Data");
                                    }
                                    if(TextUtils.isEmpty(ETMenang.getText().toString())){
                                        ETMenang.setError("Masukkan Data");
                                    }
                                    if(TextUtils.isEmpty(ETKalah.getText().toString())){
                                        ETKalah.setError("Masukkan Data");
                                    }
                                    if(TVTanggal.getText().toString().isEmpty()){
                                        Toast.makeText(TambahHistoryClient.this, "Masukkan Tanggal", Toast.LENGTH_LONG).show();
                                    }
                                    if(UGambarGame==null){
                                        Toast.makeText(TambahHistoryClient.this, "Masukkan Gambar", Toast.LENGTH_LONG).show();
                                    }

                                    if(ETNamaPemain.getText().toString().isEmpty()==false && ETMenang.getText().toString().isEmpty() == false && ETKalah.getText().toString().isEmpty() == false && TVTanggal.getText().toString().isEmpty() == false && UGambarGame!=null){
                                        history.setKonfirmasi("0");

                                        history.setNamaGame(SNamaGame);
                                        history.setName(ETNamaPemain.getText().toString());
                                        history.setWin(Integer.parseInt(ETMenang.getText().toString()));
                                        history.setLose(Integer.parseInt(ETKalah.getText().toString()));
                                        history.setTanggal(TVTanggal.getText().toString());
                                        IWin =  Integer.parseInt(ETMenang.getText().toString());
                                        ILose = Integer.parseInt(ETKalah.getText().toString());
                                        HitungPoint(IWin, ILose);
                                        history.setDay(Integer.parseInt(SDay));
                                        history.setMonth(Integer.parseInt(SMonth));
                                        history.setYear(Integer.parseInt(SYear));
                                        history.setMinDay(Integer.parseInt(SMinDay));
                                        history.setMinMonth(Integer.parseInt(SMinMonth));
                                        history.setMinYear(Integer.parseInt(SMinYear));
                                        databaseReference.child("History").child(SKeyHistory).setValue(history);
                                        Toast.makeText(TambahHistoryClient.this, "Tunggu Konfirmasi Admin", Toast.LENGTH_SHORT).show();
                                        finish();
                                        Intent intenta = new Intent(TambahHistoryClient.this, LeaderboardClient.class);
                                        intenta.putExtra("NamaGame", SNamaGame);
                                        startActivity(intenta);
                                    }
                                }

                                else if(ALKey!=null){
//                                    Toast.makeText(TambahHistoryClient.this, "Nama ada"+"ALKEY:"+ALKey, Toast.LENGTH_SHORT).show();
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("konfirmasi", "0");
                                    hashMap.put("gambarGame", history.getGambarGame());
                                    hashMap.put("win", ALWin.get(0)+Integer.parseInt(ETMenang.getText().toString()));
                                    hashMap.put("lose", ALLose.get(0)+Integer.parseInt(ETKalah.getText().toString()));
                                    hashMap.put("tanggal", TVTanggal.getText().toString());
                                    IWin =  ALWin.get(0)+Integer.parseInt(ETMenang.getText().toString());
                                    ILose = ALLose.get(0)+Integer.parseInt(ETKalah.getText().toString());
                                    HitungPoint(IWin, ILose);
                                    hashMap.put("day", Integer.parseInt(SDay));
                                    hashMap.put("month", Integer.parseInt(SMonth));
                                    hashMap.put("year", Integer.parseInt(SYear));
                                    hashMap.put("minDay", Integer.parseInt(SMinDay));
                                    hashMap.put("minMonth", Integer.parseInt(SMinMonth));
                                    hashMap.put("minYear", Integer.parseInt(SMinYear));
                                    databaseReference.child("History").child(ALKey.get(0)).updateChildren(hashMap);
                                    Toast.makeText(TambahHistoryClient.this, "Menuggu konfirmasi", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(TambahHistoryClient.this, "History Gagal di Tambah", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2 && resultCode==RESULT_OK && data!=null){
            UGambarGame=data.getData();
            IVGambarGame.setImageURI(UGambarGame);
            UploadGambarGame(UGambarGame);
        }
    }

    private void UploadGambarGame(Uri UGambarGame){
        final StorageReference storageReferenceFile = storageReference.child(System.currentTimeMillis() +"."+ getFileExtension(UGambarGame));
//        final StorageReference storageReferenceFile = storageReference.child("123" +".");
        storageReferenceFile.putFile(UGambarGame)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReferenceFile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
//                        history = new History(uri.toString());
                        history.setGambarGame(uri.toString());
                        Toast.makeText(TambahHistoryClient.this, "UPLOAD BERHASIL", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                Toast.makeText(TambahHistoryClient.this, "Gambar sedang diupload", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TambahHistoryClient.this, "UPLOADING FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri UGambarGame){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(UGambarGame));
    }


    public void OCTanggal(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
//                month= 0-11
                int selmonth = month + 1;
                SDay = Integer.toString(day);
                if(SDay.length()==1){
                    SDay="0"+SDay;
                }
                SMonth = Integer.toString(selmonth);
                if(SMonth.length()==1){
                    SMonth="0"+SMonth;
                }
                SYear = Integer.toString(year);
//                TVTanggal.setText(day + "/" + selmonth + "/" + year);
                TVTanggal.setText(SDay+"/"+SMonth+"/"+SYear);
//                SDay = Integer.toString(day);
//                SMonth = Integer.toString(selmonth);
//                SYear = Integer.toString(year);
            }
        }, 2021, 5, 24);
        datePickerDialog.show();
    }


    private void HitungPoint(Integer IWin ,Integer ILose){
        Integer x, y, z, a, b;
        x = IWin * 3;
        y = ILose * -1;
        Integer Day = x+y;
        SDay = Integer.toString(Day);
        Integer Month = x+y;
        SMonth = Integer.toString(Month);
        Integer Year = x+y;
        SYear = Integer.toString(Year);

        z = Day*-1;
        SMinDay = Integer.toString(z);
        a = Month*-1;
        SMinMonth = Integer.toString(a);
        b = Year*-1;
        SMinYear = Integer.toString(b);
    }
}