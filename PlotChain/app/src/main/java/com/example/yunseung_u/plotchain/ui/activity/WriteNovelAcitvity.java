package com.example.yunseung_u.plotchain.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.application.PlotChainApplication;
import com.example.yunseung_u.plotchain.model.Episode;
import com.example.yunseung_u.plotchain.model.NovelInfo;
import com.example.yunseung_u.plotchain.model.User;
import com.example.yunseung_u.plotchain.service.response.NovelGetResponse;
import com.example.yunseung_u.plotchain.util.api.BaseApiService;
import com.example.yunseung_u.plotchain.util.api.UtilsApi;
import com.isapanah.awesomespinner.AwesomeSpinner;
import com.victor.loading.book.BookLoading;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class WriteNovelAcitvity extends AppCompatActivity {



    BaseApiService mApiService;;
    List<String> dataset = null;
    HashMap<String,String> hashMap = null;
    String currentNovelTitle = "";
    int currentNovelIndex = 0;


    @BindView(R.id.btn_write_cancel)
    ImageView cancelbtn;
    @BindView(R.id.btn_write_check)
    ImageView checkbtn;
    @BindView(R.id.et_write_subtitle)
    EditText etSubtitle;
    @BindView(R.id.et_write_main)
    EditText etMain;
    @BindView(R.id.novel_spinner)
    AwesomeSpinner novelSpinner;


    //ProgressDialog loading;

    @OnClick(R.id.btn_write_cancel)
    public void onClickCancel(){
        finish();
    }
    @OnClick(R.id.btn_write_check)
    public void registerNovelOnClick(){

        //loading = ProgressDialog.show(this, null, "Login...", true, false);
            User user = PlotChainApplication.getCurrentUser();
            String name = etSubtitle.getText().toString();
            String content = etMain.getText().toString();

            Episode episode = new Episode(name,content);
            //episode.setSession(user.getSession());
            currentNovelTitle = dataset.get(currentNovelIndex);
            String id = hashMap.get(currentNovelTitle);

          mApiService.registerEpisode(id,episode,user.getSession())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            //loading.hide();
                            if (response.isSuccessful()){
                                Log.d("TAG",response.toString());
                                startActivity(new Intent(WriteNovelAcitvity.this, MainActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            //loading.hide();
                            //loading.stop();
                        }
                    });

        }

    @OnClick(R.id.btn_write_intro)
    public void moveToIntroPage(){

        Intent intent=new Intent(WriteNovelAcitvity.this,WriteIntroActivity.class);
        startActivity(intent);
        //startActivityForResult();
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.NoToolbarThme);
        setContentView(R.layout.activity_write);
        ButterKnife.bind(this);
        dataset = new ArrayList<>();
        hashMap = new HashMap<>();
        mApiService = UtilsApi.getAPIService();
        checkbtn.setColorFilter(this.getResources().getColor(R.color.white));
        cancelbtn.setColorFilter(this.getResources().getColor(R.color.white));
        loadSpinner();

        novelSpinner.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int i, String s) {
                //Toast.makeText(WriteNovelAcitvity.this,"선택된 아이템 : "+novelSpinner.getSelectedItem()+ "" + novelSpinner.getSelectedItemPosition(),Toast.LENGTH_SHORT).show();

                currentNovelIndex = novelSpinner.getSelectedItemPosition();
            }
        });

        //initSpinner(dataset);

    }
    public void loadSpinner(){
        User user = PlotChainApplication.getCurrentUser();
        mApiService.getMyNovelList(user.getSession(),user.getmNickname())
                .enqueue(new Callback<NovelGetResponse>() {
                    @Override
                    public void onResponse(Call<NovelGetResponse> call, Response<NovelGetResponse> response) {
                        NovelGetResponse novelResponse = response.body();

                        ArrayList<NovelInfo> result = novelResponse.getArrayList();

                        for(int i =0;i < result.size();i++){
                            dataset.add(result.get(i).getName());
                            hashMap.put(result.get(i).getName(),result.get(i).getId());
                        }

                        initSpinner(dataset);
                    }

                    @Override
                    public void onFailure(Call<NovelGetResponse> call, Throwable t) {

                    }
                });
    }

    public void initSpinner(List<String> myNovels){
        dataset = myNovels;
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dataset);
        novelSpinner.setAdapter(categoriesAdapter);

    }
}
