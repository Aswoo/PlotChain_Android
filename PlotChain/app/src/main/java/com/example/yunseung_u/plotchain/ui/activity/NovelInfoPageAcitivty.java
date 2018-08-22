package com.example.yunseung_u.plotchain.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.application.PlotChainApplication;
import com.example.yunseung_u.plotchain.model.Episode;
import com.example.yunseung_u.plotchain.model.User;
import com.example.yunseung_u.plotchain.service.response.EpisodeGetResponse;
import com.example.yunseung_u.plotchain.service.response.NovelIntroResponse;
import com.example.yunseung_u.plotchain.ui.adapter.EpisodeRecyclerViewAdapter;
import com.example.yunseung_u.plotchain.ui.adapter.RisingReyclerViewAdapter;
import com.example.yunseung_u.plotchain.util.GenreHelper;
import com.example.yunseung_u.plotchain.util.api.BaseApiService;
import com.example.yunseung_u.plotchain.util.api.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NovelInfoPageAcitivty extends AppCompatActivity {


    @BindView(R.id.thumbnail_view)
    View thumbnailView;
    @BindView(R.id.ig_info_image)
    ImageView novelImgae;
    @BindView(R.id.tv_info_title)
    TextView novelTitle;
    @BindView(R.id.tv_info_genre)
    TextView novelGenre;
    @BindView(R.id.tv_info_status)
    TextView novelStatus;
    @BindView(R.id.tv_info_des)
    TextView novelDes;
    @BindView(R.id.tv_info_writer)
    TextView novelAuthor;
    @BindView(R.id.tv_info_comment)
    TextView novelComment;
    @BindView(R.id.tv_info_heart)
    TextView novelHeart;
    @BindView(R.id.ig_info_heartsend)
    ImageView novelHeartSend;
    @BindView(R.id.tv_info_more)
    TextView novelMore;
    @BindView(R.id.ig_info_sort)
    ImageView novelSort;

    @BindView(R.id.tv_info_episodes)
    TextView novelEpisode;
    @BindView(R.id.recycler_episodes)
    RecyclerView episodeRecyclerview;
    @BindView(R.id.tv_info_sort)
    TextView novelSortOption;

    BaseApiService baseApiService;
    ProgressDialog loading;


    public String getNovelid() {
        return novelid;
    }

    public void setNovelid(String novelid) {
        this.novelid = novelid;
    }

    public String novelid;

    private boolean isClicked;
    private boolean isMore;
    private boolean isHeart;

    private List<Episode> episodeList;
    EpisodeRecyclerViewAdapter episodeRecyclerViewAdapter;
    // Onclick Method
    @OnClick(R.id.tv_info_more)
    public void onClickMore(){

        isMore = !isMore;
        if(isMore){

            novelMore.setText("접기");
            novelDes.setMaxLines(20);

        }else{
            novelMore.setText("더 보기");
            novelDes.setMaxLines(1);
        }
        // TODO 더보기

    }
    @OnClick(R.id.ig_info_back)
    public void onClickBack(){
        finish();
    }

    @OnClick(R.id.ig_info_sort)
    public void onClickSort(){

        isClicked = !isClicked;
        if(isClicked) {
            novelSortOption.setText("마지막편 부터");
        }else{
            novelSortOption.setText("첫편 부터");
        }
    }
    @OnClick(R.id.tv_info_first)
    public void onClickFirstEp(){

        Intent intent = new Intent(this,ReaderActivity.class);
        String nid = novelid;
        intent.putExtra("eid","0");
        intent.putExtra("nid",nid);
        startActivity(intent);
    }
    @OnClick(R.id.ig_info_heartsend)
    public void onClickHeartSend(){

        isHeart = !isHeart;
        if(isHeart){
            User user = PlotChainApplication.getCurrentUser();
            novelHeartSend.setImageResource(R.drawable.ic_like_on);
            baseApiService.reqHeart(novelid,user.getSession()).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }else{
            novelHeartSend.setImageResource(R.drawable.ic_like_off);
            User user = PlotChainApplication.getCurrentUser();
            baseApiService.deleteHeart(novelid,user.getSession()).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.NoToolbarThme);
        setContentView(R.layout.activity_novel_info);
        ButterKnife.bind(this);
        isClicked = false;
        isHeart = false;
        baseApiService = UtilsApi.getAPIService();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        novelid = id;

        requestNovelData(id);
        episodeRecyclerViewAdapter = new EpisodeRecyclerViewAdapter(episodeList,this);
        episodeRecyclerview.setAdapter(episodeRecyclerViewAdapter);
        episodeRecyclerview.setItemAnimator(new DefaultItemAnimator());

        LinearLayoutManager verticallayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        episodeRecyclerview.setLayoutManager(verticallayoutManager);
        episodeRecyclerview.scrollToPosition(0);


    }

    public void requestNovelData(String id){

        loading = ProgressDialog.show(this, null, "Loading...", true, false);

        User user = PlotChainApplication.getCurrentUser();
        baseApiService.getNovelIntro(id,user.getSession())
                .enqueue(new Callback<NovelIntroResponse>() {
                    @Override
                    public void onResponse(Call<NovelIntroResponse> call, Response<NovelIntroResponse> response) {
                        loading.hide();
                        if(response.isSuccessful()){

                           NovelIntroResponse novelIntroResponse = response.body();

                           //작품명. 작가명, 장르, 연재중
                           novelTitle.setText(novelIntroResponse.getTitle());
                           novelAuthor.setText(novelIntroResponse.getAuthor());
                           novelDes.setText(novelIntroResponse.getIntroduction());
                           novelGenre.setText(GenreHelper.switchCodeToString(novelIntroResponse.getGenre()));

                           episodeList = novelIntroResponse.getEpisodes();

                           episodeRecyclerViewAdapter.setmEpisodes(episodeList);
                           episodeRecyclerViewAdapter.notifyDataSetChanged();
                           //episodeRecyclerview.setAdapter(episodeRecyclerViewAdapter);
                           novelEpisode.setText(
                                   "전체 (" +
                                   String.valueOf(novelIntroResponse.getEpisodeCount())
                           + ")");
                            Double heartNum = novelIntroResponse.getHeart();
                            novelHeart.setText(String.valueOf(novelIntroResponse.getHeart()) + "%");
                            novelImgae.setBackgroundColor(Color.parseColor(novelIntroResponse.getColor()));
                            thumbnailView.setBackgroundColor(Color.parseColor(novelIntroResponse.getColor()));
                            //novelHeartSend.setImageDrawable(getDrawable(R.drawable.ic_like_on));

                        }
                    }

                    @Override
                    public void onFailure(Call<NovelIntroResponse> call, Throwable t) {
                        loading.hide();
                    }
                });


    }
}
