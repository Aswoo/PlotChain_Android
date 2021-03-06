package com.example.yunseung_u.plotchain.ui.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.application.PlotChainApplication;
import com.example.yunseung_u.plotchain.model.GraphHistory;
import com.example.yunseung_u.plotchain.model.NovelInfo;
import com.example.yunseung_u.plotchain.model.User;
import com.example.yunseung_u.plotchain.service.response.NovelGetResponse;
import com.example.yunseung_u.plotchain.service.response.NovelHeartResponse;
import com.example.yunseung_u.plotchain.ui.CustomGenreDialog;
import com.example.yunseung_u.plotchain.ui.EthWalletDialog;
import com.example.yunseung_u.plotchain.ui.adapter.NovelsHeartRecyclerViewAdapter;
import com.example.yunseung_u.plotchain.util.api.BaseApiService;
import com.example.yunseung_u.plotchain.util.api.UtilsApi;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriterInfoActivity extends AppCompatActivity {


    private BaseApiService baseApiService;
    @BindView(R.id.tv_writer_name)
    TextView writerName;
    @BindView(R.id.fb_mypage_btn)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.tv_writer_heart)
    TextView writerHeart;
    @BindView(R.id.chart)
    LineChart lineChart;
    @BindView(R.id.recycler_novels)
    RecyclerView myNovelreyclerview;
    @BindView(R.id.tv_writer_all)
    TextView episodeCount;


    ProgressDialog loading;

    private Double totalHeart;


    NovelsHeartRecyclerViewAdapter novelsHeartRecyclerViewAdapter;

    ArrayList<NovelInfo> mNovels;

    private ArrayList<GraphHistory> graphHistoryArrayList;

    @OnClick(R.id.tv_wrtier_wallet)
    public void onClickWallet(){
        final EthWalletDialog addCateDialog = new EthWalletDialog(this);
        addCateDialog.show();

        addCateDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override public void onDismiss(DialogInterface dialog) {
                String addCategoryStr = addCateDialog.getAddGenreStr();
                if(addCategoryStr != null){
                    //introNovelGenre.setText(addCategoryStr);
                }
            }
        });
    }
    @OnClick(R.id.ig_writer_back)
    public void onClickBack(){
        onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMyNovels();
        getMyAutorInfo();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.NoToolbarThme);
        setContentView(R.layout.activity_writer);
        ButterKnife.bind(this);
        mNovels = new ArrayList<>();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WriterInfoActivity.this, WriteNovelAcitvity.class);
                startActivity(intent);
            }
        });


        novelsHeartRecyclerViewAdapter = new NovelsHeartRecyclerViewAdapter(mNovels,this);
        LinearLayoutManager verticallayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        myNovelreyclerview.setLayoutManager(verticallayoutManager);
        myNovelreyclerview.scrollToPosition(0);
        myNovelreyclerview.setHasFixedSize(true);
        myNovelreyclerview.setAdapter(novelsHeartRecyclerViewAdapter);
        baseApiService = UtilsApi.getAPIService();

    }

    public void getMyAutorInfo(){

        //loading = new ProgressDialog(this,R.style.MyAlertDialogStyle);
        loading = ProgressDialog.show(this, null, "Loading...", true, false);


        User user = PlotChainApplication.getCurrentUser();
        writerName.setText(user.getmNickname() +" 작가님");
        baseApiService.getNovelHeartShare(user.getSession()).enqueue(new Callback<NovelHeartResponse>() {
            @Override
            public void onResponse(Call<NovelHeartResponse> call, Response<NovelHeartResponse> response) {
               if(response.isSuccessful()){
                   NovelHeartResponse novelHeartResponse = response.body();
                   graphHistoryArrayList = novelHeartResponse.getGraphHistories();
                   makeGraph();
               }

               loading.dismiss();
            }

            @Override
            public void onFailure(Call<NovelHeartResponse> call, Throwable t) {
                loading.dismiss();
            }
        });
    }
    public void getMyNovels(){
        User user = PlotChainApplication.getCurrentUser();
        baseApiService.getMyNovelList(user.getSession(),user.getmNickname())
                .enqueue(new Callback<NovelGetResponse>() {
                    @Override
                    public void onResponse(Call<NovelGetResponse> call, Response<NovelGetResponse> response) {
                        if(response.isSuccessful()){
                            NovelGetResponse novelGetResponse = response.body();
                            mNovels = novelGetResponse.getArrayList();
                            Double total = novelGetResponse.getTotalHeart();
                            totalHeart =total;
                            novelsHeartRecyclerViewAdapter.setTotalHeart(total);
                            novelsHeartRecyclerViewAdapter.setmHeartNovels(mNovels);
                            novelsHeartRecyclerViewAdapter.notifyDataSetChanged();
                            //Double percent = (graphHistoryArrayList.get(graphHistoryArrayList.size()-1)).getHeart() / total * 100;
                            //Double.parseDouble(String.format("%.2f"))
                            //String num = String.format("%.2f",percent);
                            if(mNovels != null){
                                Double sumHeart = 0.0;
                                for(int i = 0;i<mNovels.size();i++){
                                    sumHeart += mNovels.get(i).getHeart();
                                }

                                if(total != 0){
                                    sumHeart = sumHeart / total;
                                    sumHeart = sumHeart * 100;
                                    String Sum = String.format("%.2f",sumHeart);
                                    //Sum = Sum / total;
                                    writerHeart.setText(Sum+"%");
                                }else {
                                    writerHeart.setText("0%");
                                }

                                episodeCount.setText("전체 (" + mNovels.size() + ")");
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<NovelGetResponse> call, Throwable t) {

                    }
                });
    }

    public void makeGraph(){
        // 0~23 24~47 48~71 72~95 96~119 120~143 44~167
        List<Entry> entries = new ArrayList<>();

        //float first = graphHistoryArrayList;
        for(int i =0;i<graphHistoryArrayList.size();i++)
        {
            entries.add(new Entry((float)i,
                    (float) ((float)graphHistoryArrayList.get(i).getHeart()/totalHeart)*100f));
        }

        LineDataSet lineDataSet = new LineDataSet(entries,"좋아요 수");
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(3);
        lineDataSet.setCircleColor(Color.parseColor("#5c69fa"));
        lineDataSet.setColor(Color.parseColor("#5c69fa"));
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);
        //lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        LineData lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setCenterAxisLabels(true);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(20, 10, 0);
        xAxis.setGranularity(graphHistoryArrayList.size() / 5f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            private Date now = new Date();
            private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return format.format(new Date(now.getTime() - (graphHistoryArrayList.size() - (long)value - 1) * 10 * 1000));
            }
        });

        YAxis yLAxis = lineChart.getAxisLeft();

        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();

        //yRAxis.setDrawLabels(false);
        //yRAxis.setDrawAxisLine(false);
        //ßyRAxis.setDrawGridLines(false);


        Description description = new Description();
        description.setText("");

        lineChart.setDescription(description);


        lineChart.animateY(1500, Easing.EasingOption.EaseInCubic);
        lineChart.invalidate();
    }
        public float toNumber(Date now) {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(now);
            int hour = c.get(Calendar.HOUR_OF_DAY);// 0-23
            int minute = c.get(Calendar.MINUTE);// 0-59

            return toNumber(hour, minute);
        }

        public float toNumber(int hour, int minute) {
            return hour + minute / 60f;
    }
}
