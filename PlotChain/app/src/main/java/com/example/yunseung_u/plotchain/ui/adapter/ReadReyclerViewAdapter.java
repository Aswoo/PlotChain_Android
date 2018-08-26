package com.example.yunseung_u.plotchain.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.model.History;
import com.example.yunseung_u.plotchain.model.NovelInfo;
import com.example.yunseung_u.plotchain.ui.activity.NovelInfoPageAcitivty;
import com.example.yunseung_u.plotchain.util.MileSecondToDateHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ReadReyclerViewAdapter extends RecyclerView.Adapter<ReadReyclerViewAdapter.ViewHolder> {

    private ArrayList<History> historyList;
    private Context mContext;
    public HistoryAdapterListener onClickListener;

    public ReadReyclerViewAdapter(ArrayList<History> historyList, Context mContext, HistoryAdapterListener onClickListener) {
        this.historyList = historyList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    public ReadReyclerViewAdapter(ArrayList<History> historyList, Context context) {

        this.historyList = historyList;
        mContext = context;
    }


    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(ArrayList<History> historyList) {
        this.historyList = historyList;
    }

    @NonNull

    @Override
    public ReadReyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_read_novel, parent, false);
        return new ReadReyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReadReyclerViewAdapter.ViewHolder holder, int position) {

        final NovelInfo readNovelObj = historyList.get(position).getNovelInfo();
        final String readDate = historyList.get(position).getReadDate();
        holder.novelReadDate.setText(readDate.substring(0,10));
        holder.novelTitle.setText(readNovelObj.getName());

        if(readNovelObj.isHeart()){
            //holder.novelHeart.setImageResource(R.drawable.ic_like_color);
            holder.novelHeart.setVisibility(View.GONE);
            holder.novelHeartClick.setVisibility(View.VISIBLE);
        }else{
            holder.novelHeart.setVisibility(View.VISIBLE);
            holder.novelHeartClick.setVisibility(View.GONE);
        }
        holder.novelImage.setBackgroundColor(Color.parseColor(readNovelObj.getColor()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, NovelInfoPageAcitivty.class);
                String id = readNovelObj.getId();
                intent.putExtra("id",id);
                mContext.startActivity(intent);
                //todo 엑티비티 이동 Intent intent = new Intent(mContext,);

            }
        });
    }

    @Override
    public int getItemCount() {
        return historyList == null ? 0 : historyList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


      ImageView novelImage;
      TextView novelTitle;
      TextView novelReadDate;
      ImageView novelHeart;
      ImageView novelHeartClick;


        public ViewHolder(View itemView) {
            super(itemView);

            novelImage = itemView.findViewById(R.id.novel_image);
            novelTitle = itemView.findViewById(R.id.novel_title);
            novelReadDate = itemView.findViewById(R.id.novel_read);
            novelHeart = itemView.findViewById(R.id.novel_heart);
            novelHeartClick = itemView.findViewById(R.id.novel_heart_click);


            novelHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    novelHeart.setVisibility(View.GONE);
                    novelHeartClick.setVisibility(View.VISIBLE);
                    onClickListener.heartOnClick(v, getAdapterPosition());
                }
            });

            novelHeartClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    novelHeart.setVisibility(View.VISIBLE);
                    novelHeartClick.setVisibility(View.GONE);
                    onClickListener.unheartOnClick(v,getAdapterPosition());
                }
            });

        }
    }

    //region Interface Details listener
    public interface HistoryAdapterListener {

        void heartOnClick(View v, int position);

        void unheartOnClick(View v, int position);
    }
}
