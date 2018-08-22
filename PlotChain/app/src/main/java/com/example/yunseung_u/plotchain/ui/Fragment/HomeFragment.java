package com.example.yunseung_u.plotchain.ui.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.application.PlotChainApplication;
import com.example.yunseung_u.plotchain.model.Novel;
import com.example.yunseung_u.plotchain.model.NovelInfo;
import com.example.yunseung_u.plotchain.model.User;
import com.example.yunseung_u.plotchain.service.response.NovelGetResponse;
import com.example.yunseung_u.plotchain.ui.adapter.RankedRecyclerViewAdapter;
import com.example.yunseung_u.plotchain.ui.adapter.RisingReyclerViewAdapter;
import com.example.yunseung_u.plotchain.util.PaginationScrollListener;
import com.example.yunseung_u.plotchain.util.api.BaseApiService;
import com.example.yunseung_u.plotchain.util.api.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {


    private final String android_image_urls = "http://api.learn2crack.com/android/images/donut.png";

    @BindView(R.id.ranked_horizontal_recycler)
    RecyclerView mRankedNovelrecyclerView;
    @BindView(R.id.rising_novel_recycler)
    RecyclerView mRisingNovelRecyclerView;

    private RankedRecyclerViewAdapter mRankedAdapter;
    private RisingReyclerViewAdapter mRisingAdapter;

    private BaseApiService baseApiService;

    ArrayList<NovelInfo> mArrayNovels;
    ArrayList<NovelInfo> mRisingNovel;

    private Unbinder mUnbinder = null;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    private static final int PAGE_START = 0;
    private int currentPage = PAGE_START;


    public HomeFragment() {
        //empty fragment
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        getRisingNovelList();
        getRankedNovelList();
        mRankedAdapter.notifyDataSetChanged();
        mRisingAdapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        mRankedNovelrecyclerView.setHasFixedSize(true);
        LinearLayoutManager horizontallayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        //mRankedNovelrecyclerView.setLayoutManager(getActivity(),new LinearLayoutManager.HORIZONTAL,false);
        mRankedNovelrecyclerView.setLayoutManager(horizontallayoutManager);
        mRankedNovelrecyclerView.scrollToPosition(0);

        mRisingNovelRecyclerView.setHasFixedSize(true);
        LinearLayoutManager verticallayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRisingNovelRecyclerView.setLayoutManager(verticallayoutManager);
        mRisingNovelRecyclerView.scrollToPosition(0);

        //Adapter init
        mRankedAdapter = new RankedRecyclerViewAdapter(getActivity(), mArrayNovels);
        mRisingAdapter = new RisingReyclerViewAdapter(getActivity(), mRisingNovel);

        mRankedNovelrecyclerView.setAdapter(mRankedAdapter);
        mRankedNovelrecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRisingNovelRecyclerView.setAdapter(mRisingAdapter);
        mRisingNovelRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRisingNovelRecyclerView.setNestedScrollingEnabled(false);

        mRisingNovelRecyclerView.addOnScrollListener(new PaginationScrollListener(verticallayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 25;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return 0;
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return false;
            }
        });


        return view;


    }

    private void loadNextPage() {
        //Log.d(TAG, "loadNextPage: " + currentPage);
        User user = PlotChainApplication.getCurrentUser();

        baseApiService.getRecentlyNovel(user.getSession(), "1", 0)
                .enqueue(new Callback<NovelGetResponse>() {
                    @Override
                    public void onResponse(Call<NovelGetResponse> call, Response<NovelGetResponse> response) {
                        NovelGetResponse novelResponse = response.body();

                        mRisingNovel = novelResponse.getArrayList();

                        //mRisingAdapter.addAll(mRisingNovel);
                    }

                    @Override
                    public void onFailure(Call<NovelGetResponse> call, Throwable t) {
                        Log.d("Response Fail :", "fail to get novel response");
                    }
                });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseApiService = UtilsApi.getAPIService();
        //initDataset();


    }


    public void getRankedNovelList(){
        User user = PlotChainApplication.getCurrentUser();
        baseApiService.getRankedNovel(user.getSession(),"1",0)
                .enqueue(new Callback<NovelGetResponse>() {
                    @Override
                    public void onResponse(Call<NovelGetResponse> call, Response<NovelGetResponse> response) {
                        if(response.isSuccessful()){
                            NovelGetResponse novelResponse = response.body();


                            ArrayList<NovelInfo> result = novelResponse.getArrayList();
                            mArrayNovels = result;
                            mRankedAdapter.setmNovels(mArrayNovels);
                            mRankedAdapter.notifyDataSetChanged();

                        }
                    }

                    @Override
                    public void onFailure(Call<NovelGetResponse> call, Throwable t) {

                    }
                });
    }

    public void getRisingNovelList() {

        User user = PlotChainApplication.getCurrentUser();

        baseApiService.getRecentlyNovel(user.getSession(),"0",0)
                .enqueue(new Callback<NovelGetResponse>() {
                    @Override
                    public void onResponse(Call<NovelGetResponse> call, Response<NovelGetResponse> response) {

                        if(response.isSuccessful()){
                            NovelGetResponse novelResponse = response.body();

                            ArrayList<NovelInfo> result = novelResponse.getArrayList();
                            mRisingNovel = result;
                            mRisingAdapter.setMovies(mRisingNovel);
                            mRisingAdapter.notifyDataSetChanged();
                            //mRisingAdapter.addAll(result);
                        }
                    }

                    @Override
                    public void onFailure(Call<NovelGetResponse> call, Throwable t) {
                        Log.d("Response Fail :", "fail to get novel response");
                    }
                });
    }

}
