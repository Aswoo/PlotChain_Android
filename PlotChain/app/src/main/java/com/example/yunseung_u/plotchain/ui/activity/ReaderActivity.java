package com.example.yunseung_u.plotchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.application.PlotChainApplication;
import com.example.yunseung_u.plotchain.model.User;
import com.example.yunseung_u.plotchain.service.response.EpisodeGetResponse;
import com.example.yunseung_u.plotchain.util.api.BaseApiService;
import com.example.yunseung_u.plotchain.util.api.UtilsApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReaderActivity extends AppCompatActivity {

    @BindView(R.id.tv_reader_subtitle)
    TextView subtitle;
    @BindView(R.id.tv_reader_content)
    TextView content;
    @BindView(R.id.reader_scroll)
    ScrollView scrollView;

    BaseApiService baseApiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.NoToolbarThme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        baseApiService = UtilsApi.getAPIService();
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String nid = intent.getStringExtra("nid");
        String eid = intent.getStringExtra("eid");
        getEpisodeContents(nid,eid);

        GestureDetector.OnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e)
            {
                Toast.makeText(ReaderActivity.this, "LongClick", Toast.LENGTH_SHORT).show();
            }
        };

        final GestureDetector gestureDetector = new GestureDetector(this, listener);

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return gestureDetector.onTouchEvent(event);
            }
        });

    }

    public void getEpisodeContents(String nid,String eid){

        User user = PlotChainApplication.getCurrentUser();
        baseApiService.getEpisode(nid,eid,user.getSession())
                .enqueue(new Callback<EpisodeGetResponse>() {
                    @Override
                    public void onResponse(Call<EpisodeGetResponse> call, Response<EpisodeGetResponse> response) {

                        EpisodeGetResponse episodeGetResponse = response.body();
                        String name = episodeGetResponse.getName();
                        String contents = episodeGetResponse.getContent();

                        subtitle.setText(name);
                        content.setText(contents);
                    }

                    @Override
                    public void onFailure(Call<EpisodeGetResponse> call, Throwable t) {

                    }
                });
    }
}
