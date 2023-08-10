package com.example.catalogboardgame.DaftarGameClient.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catalogboardgame.DaftarGameClient.Model.CKategori;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.model.CatalogBoardGame;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DaftarGameClientAdapter extends RecyclerView.Adapter<DaftarGameClientAdapter.DaftarGameClientViewHolder>{

    private Context context;
    private List<CKategori> CKategoriList;

    public DaftarGameClientAdapter(Context context, List<CKategori> CKategoriList) {
        this.context = context;
        this.CKategoriList = CKategoriList;
    }

    @NonNull
    @Override
    public DaftarGameClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DaftarGameClientViewHolder(LayoutInflater.from(context).inflate(R.layout.lihat_game_client_recycler_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarGameClientViewHolder holder, final int position) {
        holder.TVKategori.setText(CKategoriList.get(position).getKategori());
        setGambarGameRecycler(holder.RVGambarGame, CKategoriList.get(position).getALCatalogBoardGame());
    }

    @Override
    public int getItemCount() {
        return CKategoriList.size();
    }


    public static final class DaftarGameClientViewHolder extends RecyclerView.ViewHolder{
        TextView TVKategori;
        RecyclerView RVGambarGame;

        public DaftarGameClientViewHolder(@NonNull View itemView) {
            super(itemView);

            TVKategori=itemView.findViewById(R.id.idKategori);
            RVGambarGame=itemView.findViewById(R.id.idRVGambarGame);
        }
    }

    private void setGambarGameRecycler(RecyclerView recyclerView, final ArrayList<CatalogBoardGame> ALCatalogBoardGame){
        final DaftarGameClientGambarGameRecyclerAdapter daftarGameClientGambarGameRecyclerAdapter = new DaftarGameClientGambarGameRecyclerAdapter(context, ALCatalogBoardGame);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(daftarGameClientGambarGameRecyclerAdapter);
    }

}
