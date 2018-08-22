package com.example.yunseung_u.plotchain.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.model.NovelHeartShare;
import com.example.yunseung_u.plotchain.model.NovelInfo;

import java.util.ArrayList;

public class NovelsHeartRecyclerViewAdapter extends RecyclerView.Adapter<NovelsHeartRecyclerViewAdapter.ViewHolder>{


    private ArrayList<NovelInfo> mHeartNovels;
    private Double totalHeart;

    public Double getTotalHeart() {
        return totalHeart;
    }

    public void setTotalHeart(Double totalHeart) {
        this.totalHeart = totalHeart;
    }

    private Context mContext;

    public NovelsHeartRecyclerViewAdapter(ArrayList<NovelInfo> mHeartNovels, Context mContext) {
        this.mHeartNovels = mHeartNovels;
        this.mContext = mContext;
    }

    public ArrayList<NovelInfo> getmHeartNovels() {

        return mHeartNovels;
    }

    public void setmHeartNovels(ArrayList<NovelInfo> mHeartNovels) {
        this.mHeartNovels = mHeartNovels;
    }

    @NonNull
    @Override
    public NovelsHeartRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_novel_rate, parent, false);
        return new NovelsHeartRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NovelsHeartRecyclerViewAdapter.ViewHolder holder, int position) {

        final NovelInfo novelInfo = mHeartNovels.get(position);

        holder.episodeCount.setText(novelInfo.getEpisodeCount()+"화");
        holder.novelTitle.setText(novelInfo.getName());
        Double heart = novelInfo.getHeart();
        holder.episodeHeartRate.setText(String.valueOf(heart/totalHeart * 100));
    }

    @Override
    public int getItemCount() {
        return mHeartNovels == null ? 0 : mHeartNovels.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView novelTitle;
        TextView episodeCount;
        TextView episodeHeartRate;
        ImageView episodeEtc;


        public ViewHolder(View itemView) {
            super(itemView);
            novelTitle = itemView.findViewById(R.id.tv_writer_novel);
            episodeCount = itemView.findViewById(R.id.tv_writer_ep);
            episodeHeartRate = itemView.findViewById(R.id.tv_writer_heart);
            episodeEtc = itemView.findViewById(R.id.tv_writer_etc);

        }
    }
}
