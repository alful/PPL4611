package com.example.catalogboardgame.DashboardAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catalogboardgame.R;
import com.example.catalogboardgame.user.EditUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditData extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText nama,pass,email,access;
    Button edit;
    String Skey,Snama,Semail,Spass,Saccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        nama=findViewById(R.id.editnamauserss);
        email=findViewById(R.id.editemailuserss);
        pass=findViewById(R.id.editpassworduserss);
        edit=findViewById(R.id.edits);
        access=findViewById(R.id.editaccess);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Akuns");

        Intent intent=getIntent();
        Skey=intent.getStringExtra("idkey");
        Semail=intent.getStringExtra("idemail");
        Spass=intent.getStringExtra("idpass");
        Snama=intent.getStringExtra("idnama");
        Saccess=intent.getStringExtra("access");
        Log.d("TAG", "onCreate: "+Snama);

        nama.setText(Snama);
        email.setText(Semail);
        pass.setText(Spass);
        if (Saccess.equals("0"))
            Saccess="Admin";
        else if (Saccess.equals("1"))
            Saccess="User";
        access.setText(Saccess);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: "+Semail);
                Log.d("TAG", "onClick: "+Skey);
                UpdateData();
            }
        });



    }

    private void UpdateData() {

        AlertDialog.Builder builder = new AlertDialog.Builder(EditData.this);
        builder.setTitle("Edit Akun");
        builder.setMessage("Apakah kamu yakin akan mengedit akun?");
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (nama.getText().toString().isEmpty()==false && access.getText().toString().isEmpty()==false) {
                    if (pass.getText().toString().length() <7)
                        pass.setError("Password Minimal 7 Huruf");
                    else {
                        String nam = nama.getText().toString();
                        String mail = email.getText().toString();
                        String password = pass.getText().toString();

                        if (access.getText().toString().equals("Admin") || access.getText().toString().equals("User"))
                        {
                            String manag = access.getText().toString();

                            if (manag.equals("Admin"))
                                manag="0";
                            else if (manag.equals("User"))
                                manag="1";
                            HashMap hashMap = new HashMap();
                            hashMap.put("nama", nam);
                            hashMap.put("email", mail);
                            hashMap.put("manag", manag);
                            hashMap.put("password", password);
                            databaseReference.child(Skey).updateChildren(hashMap);
                            Toast.makeText(EditData.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            access.setError("Isi Access Dengan “Admin” atau “User”");
                        }

                    }
                }
//                else if (nama.getText().toString().isEmpty()==false && access.getText().toString().isEmpty()==false) {
//                    pass.setError("Passsword Tidak Boleh Kosong");
//
//                }
                else if (nama.getText().toString().isEmpty()==true && access.getText().toString().isEmpty()==false) {
                    nama.setError("Nama Tidak Boleh Kosong");

                }
                else if (nama.getText().toString().isEmpty()==true && access.getText().toString().isEmpty()==true) {
                    nama.setError("Nama Tidak Boleh Kosong");
                    access.setError("Access Tidak Boleh Kosong");

                }
                else if (nama.getText().toString().isEmpty()==false && access.getText().toString().isEmpty()==true) {
                    access.setError("Access Tidak Boleh Kosong");

                }

            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
}
