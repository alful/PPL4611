package com.example.catalogboardgame.LeaderboardClient.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.catalogboardgame.LeaderboardClient.LeaderboardClient;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.model.History;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardClientAdapter extends RecyclerView.Adapter<LeaderboardClientAdapter.LeaderboardClientViewHolder> {
    Context context;
    ArrayList<History> ALHistory;

    ArrayList<Integer> ALIDay=null;
//    ArrayList<String> ALSNama;
    ArrayList<String> ALSNama=null;
//    ArrayList<Integer> ALIMonth=null;
    ArrayList<String> ALNamaD=null;
    ArrayList<String> ALNamaM=null;

    Integer no=1;

    public LeaderboardClientAdapter(Context context, ArrayList<History> ALHistory) {
        this.context = context;
        this.ALHistory = ALHistory;
    }

//    public LeaderboardClientAdapter(Context context, ArrayList<History> ALHistory, ArrayList<Integer> ALIDay, ArrayList<String> ALSNama) {
    public LeaderboardClientAdapter(Context context, ArrayList<History> ALHistory, ArrayList<String> ALSNama) {
        this.context = context;
        this.ALHistory = ALHistory;
//        this.ALIDay = ALIDay;
        this.ALSNama = ALSNama;
    }

//    public LeaderboardClientAdapter(Context context, ArrayList<History> ALHistory, ArrayList<Integer> ALIDay, ArrayList<String> ALSNama, ArrayList<String> ALNamaDay) {
    public LeaderboardClientAdapter(Context context, ArrayList<History> ALHistory, ArrayList<String> ALSNama, ArrayList<String> ALNamaD, ArrayList<String> ALNamaM) {
        this.context = context;
        this.ALHistory = ALHistory;
//        this.ALIDay = ALIDay;
        this.ALSNama = ALSNama;
//        this.ALIMonth = ALIMonth;
        this.ALNamaD = ALNamaD;
        this.ALNamaM = ALNamaM;
    }

    public class LeaderboardClientViewHolder extends RecyclerView.ViewHolder{
        TextView TVNo,TVName, TVWin, TVLose, TVDay, TVMonth, TVYear;

        public LeaderboardClientViewHolder(@NonNull View itemView) {
            super(itemView);
            TVNo = itemView.findViewById(R.id.idTVNo);
            TVName = itemView.findViewById(R.id.idTVName);
            TVWin = itemView.findViewById(R.id.idTVWin);
            TVLose = itemView.findViewById(R.id.idTVLose);
            TVDay = itemView.findViewById(R.id.idTVDay);
            TVMonth = itemView.findViewById(R.id.idTVMonth);
            TVYear = itemView.findViewById(R.id.idTVYear);
        }
    }

    @NonNull
    @Override
    public LeaderboardClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View VItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_leaderboard_client, parent, false);
        return new LeaderboardClientViewHolder(VItem);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardClientViewHolder holder, int position) {
        holder.TVNo.setText(Integer.toString(no));
        holder.TVName.setText(ALHistory.get(position).getName());
        holder.TVWin.setText(Integer.toString(ALHistory.get(position).getWin()));
        holder.TVLose.setText(Integer.toString(ALHistory.get(position).getLose()));
        no++;
        if(ALSNama==null){
            holder.TVDay.setText(Integer.toString(ALHistory.get(position).getDay()));
            holder.TVMonth.setText(Integer.toString(ALHistory.get(position).getMonth()));
            holder.TVYear.setText(Integer.toString(ALHistory.get(position).getYear()));
        }
        else if(ALSNama!=null&&ALNamaM==null){
            if(ALSNama.contains(ALHistory.get(position).getName())){
                holder.TVDay.setText("0");
                holder.TVMonth.setText(Integer.toString(ALHistory.get(position).getMonth()));
            }
            else if(!ALSNama.contains(ALHistory.get(position).getName())){
                holder.TVDay.setText(Integer.toString(ALHistory.get(position).getDay()));
                holder.TVMonth.setText(Integer.toString(ALHistory.get(position).getMonth()));
            }
            holder.TVYear.setText(Integer.toString(ALHistory.get(position).getYear()));
        }
        else if(ALNamaM!=null){
            if(ALSNama.contains(ALHistory.get(position).getName())){
                holder.TVDay.setText("0");
                holder.TVMonth.setText("0");
            }
            if(ALNamaD.contains(ALHistory.get(position).getName())){
                holder.TVDay.setText(Integer.toString(ALHistory.get(position).getDay()));
                holder.TVMonth.setText(Integer.toString(ALHistory.get(position).getMonth()));
            }
            if(ALNamaM.contains(ALHistory.get(position).getName())){
                holder.TVDay.setText("0");
                holder.TVMonth.setText(Integer.toString(ALHistory.get(position).getMonth()));
            }
            holder.TVYear.setText(Integer.toString(ALHistory.get(position).getYear()));
        }
    }

    @Override
    public int getItemCount() {
        return ALHistory.size();
    }
}
