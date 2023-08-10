package com.example.catalogboardgame.DashboardAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.catalogboardgame.DaftarGameClient.DaftarGameCLientActiviti;
import com.example.catalogboardgame.DaftarGameClient.DaftarGameCLientActivitiAdm;
import com.example.catalogboardgame.KonfirmasiHistory.KonfirmasiHistory;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.TambahGameAdmin.TbhGame;
import com.example.catalogboardgame.firebaseauth.LoginAuth;
import com.example.catalogboardgame.user.DashUser;
import com.example.catalogboardgame.user.EditUser;
import com.google.firebase.auth.FirebaseAuth;

public class DashAdmin extends AppCompatActivity {
    String Snama,Skey,Spass,Semail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_admin);
        Intent intent=getIntent();
        Skey=intent.getStringExtra("idkeys");
        Semail=intent.getStringExtra("idemails");
        Spass=intent.getStringExtra("idpasss");
        Snama=intent.getStringExtra("idnamas");
    }

    public void DAFTARGAMEs(View view) {
        Intent intent=new Intent(DashAdmin.this, DaftarGameCLientActivitiAdm.class);
        startActivity(intent);

    }

    public void Historyadmin(View view) {
        Intent intent=new Intent(DashAdmin.this, KonfirmasiHistory.class);
        startActivity(intent);
    }

    public void TambahGame(View view) {
        Intent intent=new Intent(DashAdmin.this, TbhGame.class);
        startActivity(intent);
    }

    public void LogOutAdm(View view) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginAuth.class));
//        Intent intent=new Intent(DashUser.this, Login.class);
//        startActivity(intent);
            finish();
    }

    public void ListUser(View view) {
        startActivity(new Intent(getApplicationContext(),TampilUserRecycler.class));
    }

    public void EditAdmin(View view) {
        Intent intent=new Intent(DashAdmin.this, EditAdmin.class);
        intent.putExtra("idkeys",Skey);
        intent.putExtra("idemails",Semail);
        intent.putExtra("idpasss",Spass);
        intent.putExtra("idnamas",Snama);
        Log.d("TAG", "onClicssasak: "+Semail);
        Log.d("TAG", "onClicasaask: "+Skey);

        startActivity(intent);
    }
}