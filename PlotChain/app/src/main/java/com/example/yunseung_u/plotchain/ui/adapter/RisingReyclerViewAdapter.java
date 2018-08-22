package com.example.yunseung_u.plotchain.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.model.NovelInfo;
import com.example.yunseung_u.plotchain.ui.activity.NovelInfoPageAcitivty;

import java.util.ArrayList;
import java.util.List;

public class RisingReyclerViewAdapter extends RecyclerView.Adapter<RisingReyclerViewAdapter.ViewHolder> {



    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final String TAG = "RisingRecyclerViewAdapter";
    private boolean isLoadingAdded = false;

    //vars
    private ArrayList<NovelInfo> mNovels;
    private Context mContext;

    public RisingReyclerViewAdapter(Context mContext,ArrayList<NovelInfo> mNovels) {
        this.mNovels = mNovels;
        this.mContext = mContext;
    }
    public void setMovies(ArrayList<NovelInfo> movieResults) {
        this.mNovels = movieResults;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RisingReyclerViewAdapter.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.card_horizontal_novel, parent, false);
        viewHolder = new ViewHolder(v1);
        return viewHolder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;
         switch (viewType){
            case ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_horizontal_novel, parent, false);
                break;
            case LOADING:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress,parent,false);
                break;
        }
        return new RisingReyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final NovelInfo novelObject = mNovels.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                //Glide.with(mContext).load(mNovels.get(position).getThumbnail());

                holder.novelTitle.setText(novelObject.getName());
                holder.novelWriter.setText(novelObject.getAuthor());
                holder.novelShareholdingView.setText(novelObject.getHeart().toString());
                holder.novelThumbnail.setBackgroundColor(Color.parseColor(novelObject.getColor()));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, NovelInfoPageAcitivty.class);
                        intent.putExtra("id",novelObject.getId());
                        mContext.startActivity(intent);

                    }
                });
                holder.novelGenre.setText(novelObject.getGenre());
                break;
            case LOADING:
                //Do nothing
                break;

        }




        /*
        *   //Log.d(TAG, "onBindViewHolder: called.");
        final Novel novelObject = mNovels.get(position);


        Glide.with(mContext)
                .load(mNovels.get(position).getThumbnail())
                .into(holder.novelThumbnail);


        holder.novelTitle.setText(novelObject.getTitle());
        holder.novelWriter.setText(novelObject.getWriter());
        holder.novelLikeRate.setText(novelObject.getShareholding().toString());


        //View onClick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG, "onClick: clicked on an image: " + mNames.get(position));
                //Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
                //startAcitvity 들어갈 예정
            }*/

    }
    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView novelTitle;
        TextView novelWriter;
        TextView novelGenre;
        ImageView novelThumbnail;
        ImageView novelShareholdingImgae;
        TextView novelShareholdingView;
        TextView novelEpisode;


        public ViewHolder(View itemView) {
            super(itemView);
            novelTitle = itemView.findViewById(R.id.novel_title);
            novelWriter = itemView.findViewById(R.id.novel_writer);
            novelThumbnail = itemView.findViewById(R.id.novel_image);
            novelShareholdingImgae = itemView.findViewById(R.id.shareholding_rate_image);
            novelShareholdingView = itemView.findViewById(R.id.shareholding_rate);
            novelEpisode = itemView.findViewById(R.id.novel_episode);
            novelGenre = itemView.findViewById(R.id.novel_read);

        }
    }

    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return mNovels == null ? 0 : mNovels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mNovels.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

      /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(NovelInfo r) {
        mNovels.add(r);
        notifyItemInserted(mNovels.size() - 1);
    }

    public void addAll(List<NovelInfo> moveResults) {
        for (NovelInfo result : moveResults) {
            add(result);
        }
    }

    public void remove(NovelInfo r) {
        int position = mNovels.indexOf(r);
        if (position > -1) {
            mNovels.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        //add(new Novel());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = mNovels.size() - 1;
        NovelInfo result = getItem(position);

        if (result != null) {
            mNovels.remove(position);
            notifyItemRemoved(position);
        }
    }

    public NovelInfo getItem(int position) {
        return mNovels.get(position);
    }


}
