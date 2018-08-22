package com.example.yunseung_u.plotchain.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.application.PlotChainApplication;
import com.example.yunseung_u.plotchain.model.History;
import com.example.yunseung_u.plotchain.model.User;
import com.example.yunseung_u.plotchain.service.response.ReadGetResponse;
import com.example.yunseung_u.plotchain.ui.adapter.ReadReyclerViewAdapter;
import com.example.yunseung_u.plotchain.util.api.BaseApiService;
import com.example.yunseung_u.plotchain.util.api.UtilsApi;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryFragment extends Fragment {

    private Unbinder mUnbinder = null;

    BaseApiService baseApiService;

    String state = "";
    public static String TAG = "HEART_SIGNAL";

    @BindView(R.id.readnovelView)
    View readnovelView;
    @BindView(R.id.likenovelView)
    View likenovelView;

    @OnClick(R.id.readNovelBtn)
    public void readNovel(){


        getReadNovelResponse();

        state = "read";
        readnovelView.setVisibility(View.VISIBLE);
        likenovelView.setVisibility(View.GONE);
        readReyclerViewAdapter.setHistoryList(historyList);
        readReyclerViewAdapter.notifyDataSetChanged();
    }
    @OnClick(R.id.likenovelBtn)
    public void likeNovel(){


        heartList.clear();
        getReadNovelResponse();

        state = "heart";
        likenovelView.setVisibility(View.VISIBLE);
        readnovelView.setVisibility(View.GONE);
        readReyclerViewAdapter.setHistoryList(heartList);
        readReyclerViewAdapter.notifyDataSetChanged();
    }
    @BindView(R.id.likenovelBtn)
    Button likenovelBtn;

    @BindView(R.id.libraryRecyclerview)
    RecyclerView libraryRecyclerview;

    @BindView(R.id.avloading)
    AVLoadingIndicatorView avi;

    ReadReyclerViewAdapter readReyclerViewAdapter;

    private ArrayList<History> historyList;

    private ArrayList<History> heartList;



    public LibraryFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_library, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        baseApiService = UtilsApi.getAPIService();
        state = "read";
        heartList = new ArrayList<>();
        avi.setIndicator( "LineScaleIndicator");
        readReyclerViewAdapter = new ReadReyclerViewAdapter(historyList, getContext(), new ReadReyclerViewAdapter.HistoryAdapterListener() {
            @Override
            public void heartOnClick(View v, int position) {
                User user = PlotChainApplication.getCurrentUser();
                if(state =="read"){
                    baseApiService.reqHeart(historyList.get(position).getNovelInfo().getId()
                            ,user.getSession()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                Log.i(TAG,"heart_SEND_SUCCESS");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.i(TAG,"heart_SEND_FAIL");
                        }
                    });
                }else{
                    baseApiService.reqHeart(heartList.get(position).getNovelInfo().getId()
                            ,user.getSession()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                Log.i(TAG,"heart_SEND_SUCCESS");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.i(TAG,"heart_SEND_FAIL");
                        }
                    });
                }

            }

            @Override
            public void unheartOnClick(View v, int position) {
                User user = PlotChainApplication.getCurrentUser();
                if(state == "read"){
                    baseApiService.deleteHeart(historyList.get(position).getNovelInfo().getId()
                            ,user.getSession()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                Log.i(TAG,"heart_DELETE");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.i(TAG,"heart_DELETE_FAIL");
                        }
                    });
                }else{
                    baseApiService.deleteHeart(heartList.get(position).getNovelInfo().getId()
                            ,user.getSession()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                Log.i(TAG,"heart_DELETE");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.i(TAG,"heart_DELETE_FAIL");
                        }
                    });
                }
            }
        });

        libraryRecyclerview.setHasFixedSize(true);
        LinearLayoutManager verticallayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        libraryRecyclerview.setLayoutManager(verticallayoutManager);
        libraryRecyclerview.scrollToPosition(0);
        libraryRecyclerview.setAdapter(readReyclerViewAdapter);
        //getReadedNovels();
        //readReyclerViewAdapter.notifyDataSetChanged();

        getReadNovelResponse();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }



    public void getReadNovelResponse(){

        avi.show();

        User user = PlotChainApplication.getCurrentUser();

        baseApiService.getReadNovel(user.getSession()).enqueue(new Callback<ReadGetResponse>() {
            @Override
            public void onResponse(Call<ReadGetResponse> call, Response<ReadGetResponse> response) {

                if(response.isSuccessful()){
                    //avi.hide();
                    ReadGetResponse readGetResponse = response.body();
                    historyList = readGetResponse.getHistory();

                    for(int i = 0;i < historyList.size();i++){
                        //해당 작품이 내가 좋아요를 누른 작품이면
                        if(historyList.get(i).getNovelInfo().isHeart()){
                            heartList.add(historyList.get(i));
                        }
                    }
                    if(state == "read"){
                        readReyclerViewAdapter.setHistoryList(historyList);
                    }else{
                        readReyclerViewAdapter.setHistoryList(heartList);
                    }
                    readReyclerViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ReadGetResponse> call, Throwable t) {
                Log.d("Fail getREADNOVELS!!", "FAIL FAIL FAIL");
            }
        });

    }
}