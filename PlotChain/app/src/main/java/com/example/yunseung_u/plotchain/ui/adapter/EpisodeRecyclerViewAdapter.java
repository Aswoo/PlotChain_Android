package com.example.yunseung_u.plotchain.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.model.Episode;
import com.example.yunseung_u.plotchain.ui.activity.NovelInfoPageAcitivty;
import com.example.yunseung_u.plotchain.ui.activity.ReaderActivity;

import java.util.List;

public class EpisodeRecyclerViewAdapter extends RecyclerView.Adapter<EpisodeRecyclerViewAdapter.ViewHolder>{


    private List<Episode> mEpisodes;
    private Context mContext;

    public List<Episode> getmEpisodes() {
        return mEpisodes;
    }

    public void setmEpisodes(List<Episode> mEpisodes) {
        this.mEpisodes = mEpisodes;
    }

    public EpisodeRecyclerViewAdapter(List<Episode> mEpisodes, Context mContext) {
        this.mEpisodes = mEpisodes;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_item, parent, false);
        return new EpisodeRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeRecyclerViewAdapter.ViewHolder holder, final int position) {

        final Episode episodeObj = mEpisodes.get(position);


        holder.episodeCreated.setText(episodeObj.getCreateData());
        holder.episodeNum.setText(String.valueOf(position+1));
        holder.episodeSubTitle.setText(episodeObj.getSubtitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo startactivity readactivity
                Intent intent = new Intent(mContext,ReaderActivity.class);
                String nid = ((NovelInfoPageAcitivty)mContext).getNovelid();
                intent.putExtra("eid",episodeObj.getEpsodeid());
                intent.putExtra("nid",nid);
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mEpisodes == null ? 0 : mEpisodes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView episodeNum;
        TextView episodeSubTitle;
        TextView episodeCreated;
        CardView episdoeCardView;


        public ViewHolder(View itemView) {
            super(itemView);
            episdoeCardView = itemView.findViewById(R.id.episode_card);
            episodeNum = itemView.findViewById(R.id.tv_episode_num);
            episodeSubTitle = itemView.findViewById(R.id.tv_episode_subtitle);
            episodeCreated = itemView.findViewById(R.id.tv_episode_created);

        }
    }
}
