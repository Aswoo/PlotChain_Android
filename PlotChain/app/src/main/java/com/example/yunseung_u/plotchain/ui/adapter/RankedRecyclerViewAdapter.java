package com.example.yunseung_u.plotchain.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.model.Novel;
import com.example.yunseung_u.plotchain.model.NovelInfo;
import com.example.yunseung_u.plotchain.ui.activity.NovelInfoPageAcitivty;

import java.util.ArrayList;

public class RankedRecyclerViewAdapter extends RecyclerView.Adapter<RankedRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RankedRecyclerViewAdapter";

    //vars
    private ArrayList<NovelInfo> mNovels;
    private Context mContext;

    public ArrayList<NovelInfo> getmNovels() {
        return mNovels;
    }

    public void setmNovels(ArrayList<NovelInfo> mNovels) {
        this.mNovels = mNovels;
    }

    public RankedRecyclerViewAdapter(Context context, ArrayList<NovelInfo> mNovels) {
        this.mNovels = mNovels;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_vertical_novel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //Log.d(TAG, "onBindViewHolder: called.");
        final NovelInfo novelInfo = mNovels.get(position);

        holder.novelTitle.setText(novelInfo.getName());
        holder.novelWriter.setText(novelInfo.getAuthor());
        holder.novelEpisode.setText(novelInfo.getEpisodeCount()+"화");
        holder.novelThumbnail.setBackgroundColor(Color.parseColor(novelInfo.getColor()));
        holder.novelShareholdingView.setText(novelInfo.getHeart().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NovelInfoPageAcitivty.class);
                intent.putExtra("id",novelInfo.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return  mNovels == null ? 0 : mNovels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView novelTitle;
        TextView novelWriter;
        ImageView novelThumbnail;
        TextView novelShareholdingView;
        TextView novelEpisode;


        public ViewHolder(View itemView) {
            super(itemView);
            novelTitle = itemView.findViewById(R.id.tv_vertical_title);
            novelWriter = itemView.findViewById(R.id.tv_vertical_writer);
            novelEpisode = itemView.findViewById(R.id.tv_novel_episode);
            novelThumbnail = itemView.findViewById(R.id.image_vertical_thumbnail);
            novelShareholdingView = itemView.findViewById(R.id.tv_like_number);

        }
    }

}
