package com.example.catalogboardgame.DashboardAdmin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catalogboardgame.KonfirmasiHistory.Adapter.KonfirmasiHistoryAdapter;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.firebaseauth.Akunfirebase;
import com.example.catalogboardgame.firebaseauth.LoginAuth;
import com.example.catalogboardgame.model.History;
import com.example.catalogboardgame.user.DashUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;

public class recyclertampiladapter extends RecyclerView.Adapter<recyclertampiladapter.recyclertampilViewHolder>{

    private Context context;
    private ArrayList<Akunfirebase> akunfirebases;
    //    private ArrayList<String> ALKey;
    private ArrayList<String> ALGambarGame;

    public recyclertampiladapter(Context context, ArrayList<Akunfirebase> akunfirebases){
        this.context=context;
        this.akunfirebases=akunfirebases;
//        this.ALKey=ALKey;
    }

    @NonNull
    @Override
    public recyclertampilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View VItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_tampil_usr, parent, false);
        return new recyclertampiladapter.recyclertampilViewHolder(VItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final recyclertampilViewHolder holder, int position) {

        String akses= akunfirebases.get(position).getManag();
        holder.namas.setText(akunfirebases.get(position).getNama());
        holder.emails.setText(akunfirebases.get(position).getEmail());
        if (akses.equals("1"))
            akses="User";
        else if (akses.equals("0"))
            akses="Admin";
        holder.access.setText(akses);


        final String mail=akunfirebases.get(position).getEmail();
        final String password=akunfirebases.get(position).getPassword();
        final String id=akunfirebases.get(position).getUID();
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Hapus Akun");
                builder.setMessage("Apakah kamu yakin hapus akun?");
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(context, "2:"+gambargamep, Toast.LENGTH_SHORT).show();
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Akuns");
                        final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
//                        final FirebaseUser user=firebaseAuth.getUid();
                        final AuthCredential credential= EmailAuthProvider.getCredential(mail,password);
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                if(snapshot.getKey().equals(ALKey)) {
                                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    Akunfirebase akuns = dataSnapshot.getValue(Akunfirebase.class);
                                    String idakuns=dataSnapshot.getKey();
                                    if (id.equals(idakuns)) {
//                                        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
//                                        Log.d("TAG", "onDataChange: "+databaseReference);

                                        dataSnapshot.getRef().removeValue();


                                    }


//                                    if(gambarGame.equals(gambargamep)&&key.equals(ALKey)){

//                                Toast.makeText(context, "key:"+key+"gambar:"+gambarGame, Toast.LENGTH_SHORT).show();
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

                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        holder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Akuns");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            Akunfirebase akuns = dataSnapshot.getValue(Akunfirebase.class);
                            String idakuns=dataSnapshot.getKey();
                            if (id.equals(idakuns)) {
//                                        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
//                                        Log.d("TAG", "onDataChange: "+databaseReference);
                                Log.d("TAG", "onDataChangssae: "+id);
                                Log.d("TAG", "onDataChangsase: "+idakuns);

                                String key = dataSnapshot.getKey();
                                String nama= dataSnapshot.child("nama").getValue(String.class);
                                String email= dataSnapshot.child("email").getValue(String.class);
                                String pass= dataSnapshot.child("password").getValue(String.class);
                                String access= dataSnapshot.child("manag").getValue(String.class);

                                Intent intent =new Intent(context, EditData.class);
                                intent.putExtra("idkey",key);
                                intent.putExtra("idemail",email);
                                intent.putExtra("idpass",pass);
                                intent.putExtra("idnama",nama);
                                intent.putExtra("access",access);
                                Log.d("TAG", "onDataChangse: "+nama);
                                Log.d("TAG", "onDataChangse: "+email);
                                Log.d("TAG", "onDataChangse: "+pass);
                                Log.d("TAG", "onDataChangse: "+access);

                                context.startActivity(intent);
//                                dataSnapshot.getRef().removeValue();


                            }





                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }


    @Override
    public int getItemCount() {
        return akunfirebases.size();
    }

    public class recyclertampilViewHolder extends RecyclerView.ViewHolder{
        TextView namas,emails, access;
        ImageView Delete,Edit;

        public recyclertampilViewHolder(@NonNull View itemView) {
            super(itemView);
            namas = itemView.findViewById(R.id.namas);
            emails = itemView.findViewById(R.id.emails);
            access = itemView.findViewById(R.id.Access);
            Delete = itemView.findViewById(R.id.deletes);
            Edit=itemView.findViewById(R.id.edites);

        }
    }
}
