package com.example.yunseung_u.plotchain.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.application.PlotChainApplication;
import com.example.yunseung_u.plotchain.model.GraphHistory;
import com.example.yunseung_u.plotchain.model.NovelInfo;
import com.example.yunseung_u.plotchain.model.User;
import com.example.yunseung_u.plotchain.service.response.NovelGetResponse;
import com.example.yunseung_u.plotchain.service.response.NovelHeartResponse;
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
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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


    NovelsHeartRecyclerViewAdapter novelsHeartRecyclerViewAdapter;

    ArrayList<NovelInfo> mNovels;

    private ArrayList<GraphHistory> graphHistoryArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.NoToolbarThme);
        setContentView(R.layout.activity_writer);
        ButterKnife.bind(this);
        mNovels = new ArrayList<>();
        novelsHeartRecyclerViewAdapter = new NovelsHeartRecyclerViewAdapter(mNovels,this);
        LinearLayoutManager verticallayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        myNovelreyclerview.setLayoutManager(verticallayoutManager);
        myNovelreyclerview.scrollToPosition(0);
        myNovelreyclerview.setHasFixedSize(true);
        myNovelreyclerview.setAdapter(novelsHeartRecyclerViewAdapter);
        baseApiService = UtilsApi.getAPIService();
        getMyAutorInfo();
        getMyNovels();
    }

    public void getMyAutorInfo(){

        User user = PlotChainApplication.getCurrentUser();
        writerName.setText(user.getmNickname());
        baseApiService.getNovelHeartShare(user.getSession()).enqueue(new Callback<NovelHeartResponse>() {
            @Override
            public void onResponse(Call<NovelHeartResponse> call, Response<NovelHeartResponse> response) {
               if(response.isSuccessful()){
                   NovelHeartResponse novelHeartResponse = response.body();
                   graphHistoryArrayList = novelHeartResponse.getGraphHistories();
                   makeGraph();

               }
            }

            @Override
            public void onFailure(Call<NovelHeartResponse> call, Throwable t) {

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
                            novelsHeartRecyclerViewAdapter.setTotalHeart(total);
                            novelsHeartRecyclerViewAdapter.setmHeartNovels(mNovels);
                            novelsHeartRecyclerViewAdapter.notifyDataSetChanged();
                            Double percent = (graphHistoryArrayList.get(graphHistoryArrayList.size()-1)).getHeart() / total * 100;
                            //Double.parseDouble(String.format("%.2f"))
                            String num = String.format("%.2f",percent);
                            writerHeart.setText(num+"%");
                        }
                    }

                    @Override
                    public void onFailure(Call<NovelGetResponse> call, Throwable t) {

                    }
                });
    }

    public void initLineChart(){

        //lineChart = (LineChart)findViewById(R.id.chart);

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 1));
        entries.add(new Entry(2, 2));
        entries.add(new Entry(3, 0));
        entries.add(new Entry(4, 4));
        entries.add(new Entry(5, 3));

        LineDataSet lineDataSet = new LineDataSet(entries, "속성명1");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleColorHole(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);


        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(8, 24, 0);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
        lineChart.invalidate();
    }

    public void makeGraph(){


        // x = 0, /1,2,3,4,5,6,7 일전 저장

        // 0~23 24~47 48~71 72~95 96~119 120~143 44~167
        List<Entry> entries = new ArrayList<>();
        for(int i =0;i<graphHistoryArrayList.size();i++)
        {
            entries.add(new Entry((float)i,
                    (float)graphHistoryArrayList.get(i).getHeart()));
        }


        LineDataSet lineDataSet = new LineDataSet(entries,"지분율");
        lineDataSet.setLineWidth(4f);
        lineDataSet.setCircleRadius(3);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        //lineDataSet.setCircleColorHole(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);


        LineData lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);

        final String[] quarters = new String[] { "Q1", "Q2", "Q3", "Q4" };
        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }

            // we don't draw numbers, so no decimal digits needed
            public int getDecimalDigits() {  return 0; }
        };

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(15, 5, 0);
        xAxis.setGranularity(10f); // minimum axis-step (interval) is 1
        //xAxis.setValueFormatter(formatter);

        YAxis yLAxis = lineChart.getAxisLeft();

        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();

        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        lineChart.setDescription(description);


        lineChart.animateY(1500, Easing.EasingOption.EaseInCubic);
        lineChart.invalidate();
    }
}
