package com.example.catalogboardgame.user;

import androidx.annotation.NonNull;
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

import com.example.catalogboardgame.DashboardAdmin.EditAdmin;
import com.example.catalogboardgame.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditUser extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText nama,pass,email;
    Button edit;
    String Skey,Snama,Semail,Spass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        nama=findViewById(R.id.editnamausers);
        email=findViewById(R.id.editemailusers);
        pass=findViewById(R.id.editpasswordusers);
        edit=findViewById(R.id.edit);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Akuns");

        Intent intent=getIntent();
        Skey=intent.getStringExtra("idkey");
        Semail=intent.getStringExtra("idemail");
        Spass=intent.getStringExtra("idpass");
        Snama=intent.getStringExtra("idnama");

        nama.setText(Snama);
        email.setText(Semail);
        pass.setText(Spass);

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

        AlertDialog.Builder builder = new AlertDialog.Builder(EditUser.this);
        builder.setTitle("Edit Akun");
        builder.setMessage("Apakah kamu yakin akan mengedit akun?");
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (nama.getText().toString().isEmpty()==false && pass.getText().toString().isEmpty()==false &&email.getText().toString().isEmpty()==false) {
                    if (pass.getText().toString().length() <7)
                        pass.setError("Password Minimal 7 Huruf");
                    else {
                        String nam = nama.getText().toString();
                        final String mail = email.getText().toString();
                        final String password = pass.getText().toString();

                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        AuthCredential credential= EmailAuthProvider.getCredential(Semail,Spass);
                        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d("TAG", "User-Reauth");
                                FirebaseUser user1=FirebaseAuth.getInstance().getCurrentUser();
                                user1.updateEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d("TAG", "User email address updated.");
                                        }
                                    }
                                });
                                user1.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d("TAG", "User pass updated.");
                                        }
                                    }
                                });

                            }
                        });

                        HashMap hashMap = new HashMap();
                        hashMap.put("nama", nam);
                        hashMap.put("email", mail);
                        hashMap.put("password", password);
                        databaseReference.child(Skey).updateChildren(hashMap);
                        Toast.makeText(EditUser.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (nama.getText().toString().isEmpty()==false && pass.getText().toString().isEmpty()== true && email.getText().toString().isEmpty()==false){
                    pass.setError("Passsword Tidak Boleh Kosong");

                }
                else if (nama.getText().toString().isEmpty()==true && pass.getText().toString().isEmpty()== false && email.getText().toString().isEmpty()==false){
                    nama.setError("Nama Tidak Boleh Kosong");

                }
                else if (nama.getText().toString().isEmpty()==true && pass.getText().toString().isEmpty()== true && email.getText().toString().isEmpty()==false){
                    nama.setError("Nama Tidak Boleh Kosong");
                    pass.setError("Password Tidak Boleh Kosong");

                }
                else if (nama.getText().toString().isEmpty()==false && pass.getText().toString().isEmpty()== false && email.getText().toString().isEmpty()==true) {
                    email.setError("Email Tidak Boleh Kosong");
                }
                else if (nama.getText().toString().isEmpty()==false && pass.getText().toString().isEmpty()== true && email.getText().toString().isEmpty()==true) {
                    pass.setError("Password Tidak Boleh Kosong");
                    email.setError("Email Tidak Boleh Kosong");
                }
                else if (nama.getText().toString().isEmpty()==true && pass.getText().toString().isEmpty()== false && email.getText().toString().isEmpty()==true) {
                    nama.setError("Nama Tidak Boleh Kosong");
                    email.setError("Email Tidak Boleh Kosong");
                }
                else if (nama.getText().toString().isEmpty()==true && pass.getText().toString().isEmpty()== true && email.getText().toString().isEmpty()==true) {
                    nama.setError("Nama Tidak Boleh Kosong");
                    pass.setError("Password Tidak Boleh Kosong");
                    email.setError("Email Tidak Boleh Kosong");
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