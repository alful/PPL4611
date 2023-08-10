package com.example.catalogboardgame.DaftarGameClient.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.catalogboardgame.BacaGameClient.BacaGameAdm;
import com.example.catalogboardgame.BacaGameClient.BacaGameClients;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.model.CatalogBoardGame;

import java.util.ArrayList;

public class DaftarGameClientGambarGameRecyclerAdaptersAdm extends RecyclerView.Adapter<DaftarGameClientGambarGameRecyclerAdaptersAdm.DaftarGameClientGambarGameViewHolder> {
    private Context context;

    private ArrayList<CatalogBoardGame> ALCatalogBoardGame;

    public DaftarGameClientGambarGameRecyclerAdaptersAdm(Context context, ArrayList<CatalogBoardGame> ALCatalogBoardGame) {
        this.context = context;
        this.ALCatalogBoardGame = ALCatalogBoardGame;
    }

    @NonNull
    @Override
    public DaftarGameClientGambarGameRecyclerAdaptersAdm.DaftarGameClientGambarGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DaftarGameClientGambarGameRecyclerAdaptersAdm.DaftarGameClientGambarGameViewHolder(LayoutInflater.from(context).inflate(R.layout.lihat_game_client_recycler_row_gambar_game, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarGameClientGambarGameRecyclerAdaptersAdm.DaftarGameClientGambarGameViewHolder holder, final int position) {
        Glide.with(context).load(ALCatalogBoardGame.get(position).getGambarGame()).into(holder.IVGambarGame);
        holder.IVGambarGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(context.getApplicationContext(), BacaGameAdm.class);
                intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intents.putExtra("GambarGame", ALCatalogBoardGame.get(position).getGambarGame());
                intents.putExtra("NamaGame", ALCatalogBoardGame.get(position).getNamaGame());
                intents.putExtra("Kategori", ALCatalogBoardGame.get(position).getKategori());
                intents.putExtra("JumlahPemain", ALCatalogBoardGame.get(position).getJumlahPemain());
                intents.putExtra("DeskripsiGame", ALCatalogBoardGame.get(position).getDeskripsiGame());
                context.startActivity(intents);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ALCatalogBoardGame.size();    }
    public static final class DaftarGameClientGambarGameViewHolder extends RecyclerView.ViewHolder{
        ImageView IVGambarGame;

        public DaftarGameClientGambarGameViewHolder(@NonNull View itemView) {
            super(itemView);

            IVGambarGame = itemView.findViewById(R.id.idIVGambarGame);
        }
    }
}

