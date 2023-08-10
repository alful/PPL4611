package com.example.catalogboardgame.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catalogboardgame.DashboardAdmin.DashAdmin;
import com.example.catalogboardgame.MainActivity2;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.user.DashUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LogAuth2 extends AppCompatActivity {
//    EditText emails, passwords;
//    Button login, signup;
//    boolean valid = true;
//    FirebaseAuth firebaseAuth;
//    FirebaseDatabase firebaseDatabase;
//    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Akuns");
//    Query akunfirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_auth2);
//
//
//
//        emails = findViewById(R.id.emailuserx);
//        passwords = findViewById(R.id.passworduserx);
//        login = findViewById(R.id.loginx);
//        signup = findViewById(R.id.signupx);
//        firebaseAuth = FirebaseAuth.getInstance();
////        akunfirebase = new Akunfirebase();
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                checkField(emails);
//                checkField(passwords);
//
//                if (valid) {
//                    firebaseAuth.signInWithEmailAndPassword(emails.getText().toString(), passwords.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                        @Override
//                        public void onSuccess(AuthResult authResult) {
//                            Toast.makeText(LogAuth2.this, "Berhasil Masuk", Toast.LENGTH_SHORT).show();
//
//                            checkuserAccesLevel(authResult.getUser().getUid());
//
//
//                            signup.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    startActivity(new Intent(getApplicationContext(), RgisterAuth.class));
//
//                                }
//                            });
//
//                        }
//                    });
//                }
//            }
//        });
//    }
//
//    private void checkuserAccesLevel(final String uid) {
//
//        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Akuns");
//        Log.d("TAG", "UIDNYA: " + uid);
//        akunfirebase= databaseReference1.orderByChild("manag");
//        Query a = databaseReference1.orderByChild("manag");
//        Log.d("TAG", "onSuccess: " + a);
//        akunfirebase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//                Akunfirebase af = snapshot.getValue(Akunfirebase.class);
//                if (af.getManaga().equals("0")) {
//                    Toast.makeText(LogAuth2.this, "Admin", Toast.LENGTH_SHORT).show();
//
//                    startActivity(new Intent(getApplicationContext(), DashAdmin.class));
//
//                } else if (af.getManaga().equals("1")) {
//                    Toast.makeText(LogAuth2.this, "User", Toast.LENGTH_SHORT).show();
//
//                    startActivity(new Intent(getApplicationContext(), DashUser.class));
//
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//
//        private boolean checkField(EditText textField){
//            if (textField.getText().toString().isEmpty()) {
//                textField.setError("ERROR");
//                valid = false;
//            } else {
//                valid = true;
//            }
//            return valid;
        }

}

