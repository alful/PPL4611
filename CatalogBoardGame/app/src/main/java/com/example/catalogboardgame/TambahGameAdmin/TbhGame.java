package com.example.catalogboardgame.TambahGameAdmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.catalogboardgame.R;
import com.example.catalogboardgame.model.CatalogBoardGame;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class TbhGame extends AppCompatActivity {

    DatabaseReference databaseReference;
    StorageReference storageReference;
    CatalogBoardGame catalogBoardGame;
    EditText ETNamaGame, ETJumlahPemain, ETDeskripsiGame;
    ImageView IVGambarGame;
    Spinner SPKategori;
    Button BTambahGame;
    Uri UGambarGame;

    String SKeyCatalogBoardGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tbh_game);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("CatalogBoardGame");
        storageReference = FirebaseStorage.getInstance().getReference();

        SKeyCatalogBoardGame = databaseReference.push().getKey();

        catalogBoardGame=new CatalogBoardGame();

        ETNamaGame = findViewById(R.id.idETNamaGame);
        ETJumlahPemain = findViewById(R.id.idETJumlahPemain);
        ETDeskripsiGame = findViewById(R.id.idETDeskripsiGame);
        IVGambarGame = findViewById(R.id.idIVGambarGame);
        SPKategori = findViewById(R.id.idSPKategori);

        BTambahGame = findViewById(R.id.idBTambahGame);

        IVGambarGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 2);
            }
        });


        BTambahGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(TbhGame.this);
                builder.setTitle("Tambah Game");
                builder.setMessage("Apakah kamu yakin menambah game?");
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (UGambarGame!=null){
                            UploadGambarGameDanGame(UGambarGame);
                        }
                        else{
                            Toast.makeText(TbhGame.this, "GAMBAR BELUM di UPLOAD", Toast.LENGTH_LONG).show();
                        }
                        Toast.makeText(TbhGame.this, "Game Berhasil di Tambah", Toast.LENGTH_SHORT).show();

//                        Intent intent=new Intent(tambah_game_admin.this, baca_game_admin.class);
//                        startActivity(intent);

                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(TbhGame.this, "Game Gagal di Tambah", Toast.LENGTH_SHORT).show();
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
        }
    }

    private void UploadGambarGameDanGame(Uri UGambarGame){
        final StorageReference storageReferenceFile = storageReference.child(System.currentTimeMillis() +"."+ getFileExtension(UGambarGame));
        storageReferenceFile.putFile(UGambarGame).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReferenceFile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        catalogBoardGame = new CatalogBoardGame(uri.toString());
                        catalogBoardGame.setNamaGame(ETNamaGame.getText().toString().trim());
                        catalogBoardGame.setKategori(SPKategori.getSelectedItem().toString().trim());
                        catalogBoardGame.setJumlahPemain(Integer.parseInt(ETJumlahPemain.getText().toString()));
                        catalogBoardGame.setDeskripsiGame(ETDeskripsiGame.getText().toString().trim());
                        databaseReference.child(SKeyCatalogBoardGame).setValue(catalogBoardGame);
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TbhGame.this, "UPLOADING FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri UGambarGame){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(UGambarGame));
    }


}