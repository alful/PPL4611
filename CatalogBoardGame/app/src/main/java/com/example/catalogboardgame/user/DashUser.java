package com.example.catalogboardgame.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.catalogboardgame.DaftarGameClient.DaftarGameCLientActiviti;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.Sign.Login;
import com.example.catalogboardgame.firebaseauth.LoginAuth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashUser extends AppCompatActivity {

    String Snama,Skey,Spass,Semail;
    FirebaseUser firebaseUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_user);
        Intent intent=getIntent();
        Skey=intent.getStringExtra("idkey");
        Semail=intent.getStringExtra("idemail");
        Spass=intent.getStringExtra("idpass");
        Snama=intent.getStringExtra("idnama");
    }

    public void DAFTARGAME(View view) {
        Intent intent=new Intent(DashUser.this, DaftarGameCLientActiviti.class);
        startActivity(intent);

    }

    public void Historyuser(View view) {
    }
//    public void onBackPressed() {
//        Intent startMain = new Intent(Intent.ACTION_MAIN);
//        startMain.addCategory(Intent.CATEGORY_HOME);
//        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(startMain);
//        //klik back nanti ke home
//    }

    public void LogOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginAuth.class));
//        Intent intent=new Intent(DashUser.this, Login.class);
//        startActivity(intent);
        finish();

    }

    public void EditUser(View view) {

        Intent intent=new Intent(DashUser.this, EditUser.class);
        intent.putExtra("idkey",Skey);
        intent.putExtra("idemail",Semail);
        intent.putExtra("idpass",Spass);
        intent.putExtra("idnama",Snama);
        Log.d("TAG", "onClick: "+Semail);
        Log.d("TAG", "onClick: "+Skey);

        startActivity(intent);

    }
}