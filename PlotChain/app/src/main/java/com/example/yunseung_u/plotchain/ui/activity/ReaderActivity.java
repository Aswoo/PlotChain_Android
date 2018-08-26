package com.example.yunseung_u.plotchain.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
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
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReaderActivity extends AppCompatActivity {

    @BindView(R.id.tv_reader_subtitle)
    TextView subtitle;
    @BindView(R.id.tv_reader_content)
    TextView content;
    @BindView(R.id.reader_scroll)
    NestedScrollView scrollView;


    @BindView(R.id.btn_header_before)
    ImageView headerBefore;
    @BindView(R.id.btn_header_heart)
    ImageView headerHeart;


    @BindView(R.id.header_view)
    View headerView;
    @BindView(R.id.footer_view)
    View footerView;
    @BindView(R.id.tv_reader_eptitle)
    TextView eptitle;
    @BindView(R.id.before_view)
    View beforeView;
    @BindView(R.id.after_view)
    View afterView;
    @BindView(R.id.tv_writer_before)
    TextView before;
    @BindView(R.id.tv_writer_next)
    TextView next;
    @BindView(R.id.ig_reader_before)
    ImageView readerBefore;
    @BindView(R.id.ig_reader_next)
    ImageView readerNext;
    @BindView(R.id.view10)
    View footerView3;
    @BindView(R.id.view9)
    View footerView2;
    @BindView(R.id.view8)
    View footerView1;

    @BindView(R.id.tv_change_scroll)
    TextView changeScroll;
    @BindView(R.id.imageView3)
    ImageView commentImagae;


    BaseApiService baseApiService;
    ProgressDialog loading;

    private boolean isTouched;

    @OnClick(R.id.before_view)
    public void OnClickBefore() {

        Intent intent = new Intent(ReaderActivity.this, ReaderActivity.class);
        intent.putExtra("nid", nid);
        if (eid != "0") {
            intent.putExtra("uid", eid);
            startActivity(intent);
        }
    }

    @OnClick(R.id.after_view)
    public void OnClickNext() {

        Intent intent = new Intent(ReaderActivity.this, ReaderActivity.class);
        intent.putExtra("nid", nid);
        //if(eid )
        intent.putExtra("uid", eid);
        startActivity(intent);
    }

    private boolean isHeart;
    @OnClick(R.id.btn_header_heart)
    public void OnClickHeart() {

        isHeart = !isHeart;
        if(isHeart){
            User user = PlotChainApplication.getCurrentUser();
            headerHeart.setImageResource(R.drawable.ic_like_on);
            baseApiService.reqHeart(nid,user.getSession()).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }else{
            headerHeart.setImageResource(R.drawable.ic_like_off);
            User user = PlotChainApplication.getCurrentUser();
            baseApiService.deleteHeart(nid,user.getSession()).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        }


    }


    String nid = "";
    String eid = "";
    String NovelTitle = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.NoToolbarThme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        ButterKnife.bind(this);

        baseApiService = UtilsApi.getAPIService();

        isHeart = false;
        isTouched = true;
        showAndHidefooterAndHeader();
        Intent intent = getIntent();
        nid = intent.getStringExtra("nid");
        eid = intent.getStringExtra("eid");
        NovelTitle = intent.getStringExtra("title");

        getEpisodeContents(nid, eid);

        GestureDetector.OnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {

                showAndHidefooterAndHeader();
                //Toast.makeText(ReaderActivity.this, "LongClick", Toast.LENGTH_SHORT).show();
            }
        };

        final GestureDetector gestureDetector = new GestureDetector(this, listener);

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

    }

    public void getEpisodeContents(String nid, final String eid) {
        //oading = new ProgressDialog(this, R.style.MyAlertDialogStyle);
        loading = ProgressDialog.show(this, null, "Login...", true, false);

        User user = PlotChainApplication.getCurrentUser();
        baseApiService.getEpisode(nid, eid, user.getSession())
                .enqueue(new Callback<EpisodeGetResponse>() {
                    @Override
                    public void onResponse(Call<EpisodeGetResponse> call, Response<EpisodeGetResponse> response) {
                        if (response.isSuccessful()) {
                            EpisodeGetResponse episodeGetResponse = response.body();
                            String name = episodeGetResponse.getName();
                            String contents = episodeGetResponse.getContent();

                            int num = Integer.parseInt(eid) + 1;
                            String epsode = NovelTitle.toString() + " " + String.valueOf(num);


                            subtitle.setText(name);
                            eptitle.setText(epsode);
                            content.setText(contents);
                        }

                        loading.dismiss();
                    }

                    @Override
                    public void onFailure(Call<EpisodeGetResponse> call, Throwable t) {
                        loading.dismiss();
                    }
                });
    }

    public void showAndHidefooterAndHeader() {
        isTouched = !isTouched;

        if (isTouched) {
            //터치시
            headerView.setVisibility(View.VISIBLE);
            headerBefore.setVisibility(View.VISIBLE);
            headerHeart.setVisibility(View.VISIBLE);


            footerView.setVisibility(View.VISIBLE);
            eptitle.setVisibility(View.VISIBLE);
            before.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            readerBefore.setVisibility(View.VISIBLE);
            readerNext.setVisibility(View.VISIBLE);

            footerView1.setVisibility(View.VISIBLE);
            footerView2.setVisibility(View.VISIBLE);
            footerView3.setVisibility(View.VISIBLE);

            commentImagae.setVisibility(View.VISIBLE);
            changeScroll.setVisibility(View.VISIBLE);

        } else {

            headerView.setVisibility(View.GONE);
            headerBefore.setVisibility(View.GONE);
            headerHeart.setVisibility(View.GONE);


            footerView.setVisibility(View.GONE);
            eptitle.setVisibility(View.GONE);
            before.setVisibility(View.GONE);
            next.setVisibility(View.GONE);
            readerBefore.setVisibility(View.GONE);
            readerNext.setVisibility(View.GONE);

            footerView1.setVisibility(View.GONE);
            footerView2.setVisibility(View.GONE);
            footerView3.setVisibility(View.GONE);


            commentImagae.setVisibility(View.GONE);
            changeScroll.setVisibility(View.GONE);

        }
    }
}
