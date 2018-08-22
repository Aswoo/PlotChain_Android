package com.example.yunseung_u.plotchain.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yunseung_u.plotchain.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomGenreDialog extends Dialog{

    private String addGenreStr;
    Context mContext;

    @BindView(R.id.tv_genre_fantasy)
    TextView tvFantasy;
    @BindView(R.id.tv_genre_romance)
    TextView tvRomance;
    @BindView(R.id.tv_genre_china)
    TextView tvChina;

    @BindView(R.id.tv_genre_game)
    TextView tvGame;
    @BindView(R.id.tv_genre_think)
    TextView tvThink;
    @BindView(R.id.tv_genre_parody)
    TextView tvParody;
    @BindView(R.id.tv_genre_light)
    TextView tvLight;
    @BindView(R.id.tv_genre_bl)
    TextView tvBL;
    @BindView(R.id.tv_genre_gl)
    TextView tvGL;


    int isFantasy,isRomance,isChina,isGame,isThink,isBL,isParody,isLight,isGL;

    public CustomGenreDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_dialog_layout);
        ButterKnife.bind(this);

        isFantasy = 0;
        isRomance = 0;
        isChina = 0;
        isGame = 0;
        isThink = 0;
        isBL = 0;
        isParody =0;
        isLight = 0;
        isGL = 0;

    }
    @OnClick(R.id.tv_genre_fantasy)
    public void onClick0(){
        if(isFantasy == 0){
            isFantasy = 1;
            tvFantasy.setTextColor(mContext.getResources().getColor(R.color.white));
            tvFantasy.setBackgroundColor(mContext.getResources().getColor(R.color.charcoal_grey));

        }else{
            isFantasy = 0;
            tvFantasy.setTextColor(mContext.getResources().getColor(R.color.gunmetal));
            tvFantasy.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
    }
    @OnClick(R.id.tv_genre_romance)
    public void onClick1(){
        if(isRomance == 0){
            isRomance = 1;

            tvRomance.setTextColor(mContext.getResources().getColor(R.color.white));
            tvRomance.setBackgroundColor(mContext.getResources().getColor(R.color.charcoal_grey));

        }else{
            isRomance = 0;
            tvRomance.setTextColor(mContext.getResources().getColor(R.color.gunmetal));
            tvRomance.setBackgroundColor(mContext.getResources().getColor(R.color.white));

        }
    }
    @OnClick(R.id.tv_genre_china)
    public void onClick2(){
        if(isChina == 0){
            isChina = 1;
            tvChina.setTextColor(mContext.getResources().getColor(R.color.white));
            tvChina.setBackgroundColor(mContext.getResources().getColor(R.color.charcoal_grey));
        }else{
            isChina = 0;
            tvChina.setTextColor(mContext.getResources().getColor(R.color.gunmetal));
            tvChina.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
    }
    @OnClick(R.id.tv_genre_game)
    public void onClick3(){
        if(isGame == 0){
            isGame = 1;
            tvGame.setTextColor(mContext.getResources().getColor(R.color.white));
            tvGame.setBackgroundColor(mContext.getResources().getColor(R.color.charcoal_grey));
        }else{
            isGame = 0;
            tvGame.setTextColor(mContext.getResources().getColor(R.color.gunmetal));
            tvGame.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
    }
    @OnClick(R.id.tv_genre_think)
    public void onClick4(){
        if(isThink == 0){
            isThink = 1;
            tvThink.setTextColor(mContext.getResources().getColor(R.color.white));
            tvThink.setBackgroundColor(mContext.getResources().getColor(R.color.charcoal_grey));
        }else{
            isThink = 0;
            tvThink.setTextColor(mContext.getResources().getColor(R.color.gunmetal));
            tvThink.setBackgroundColor(mContext.getResources().getColor(R.color.white));

        }
    }

    @OnClick(R.id.tv_genre_bl)
    public void onClick5(){
        if(isBL == 0){
            isBL = 1;
            tvBL.setTextColor(mContext.getResources().getColor(R.color.white));
            tvBL.setBackgroundColor(mContext.getResources().getColor(R.color.charcoal_grey));
        }else{
            isBL = 0;
            tvBL.setTextColor(mContext.getResources().getColor(R.color.gunmetal));
            tvBL.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
    }
    @OnClick(R.id.tv_genre_parody)
    public void onClick6(){
        if(isParody == 0){
            isParody = 1;
            tvParody.setTextColor(mContext.getResources().getColor(R.color.white));
            tvParody.setBackgroundColor(mContext.getResources().getColor(R.color.charcoal_grey));
        }else{
            isParody = 0;
            tvParody.setTextColor(mContext.getResources().getColor(R.color.gunmetal));
            tvParody.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
    }
    @OnClick(R.id.tv_genre_light)
    public void onClick7(){
        if(isLight == 0){
            isLight = 1;
            tvLight.setTextColor(mContext.getResources().getColor(R.color.white));
            tvLight.setBackgroundColor(mContext.getResources().getColor(R.color.charcoal_grey));
        }else{
            isLight = 0;
            tvLight.setTextColor(mContext.getResources().getColor(R.color.gunmetal));
            tvLight.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
    }
    @OnClick(R.id.tv_genre_gl)
    public void onClick8(){
        if(isGL == 0){
            isGL = 1;
            tvGL.setTextColor(mContext.getResources().getColor(R.color.white));
            tvGL.setBackgroundColor(mContext.getResources().getColor(R.color.charcoal_grey));
        }else{
            isGL = 0;
            tvGL.setTextColor(mContext.getResources().getColor(R.color.gunmetal));
            tvGL.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
    }

    @OnClick(R.id.tv_genre_ok)
    public void onClickOk(){

         int total = (isFantasy + isRomance + isGL
                + isGame + isThink + isBL
                + isParody + isChina + isLight);

         if(total != 1){
             Toast.makeText(getContext(),"하나의 장르만을 선택해주세요",Toast.LENGTH_SHORT).show();
         }else{
             addGenreStr = check();
         }
         dismiss();

    }
    @OnClick(R.id.tv_genre_cancle)
    public void onClickCancle(){
        dismiss();
    }

    public String getAddGenreStr() { return addGenreStr; }
    public void setAddGenreStr(String addCategoryStr) { this.addGenreStr = addCategoryStr; }

    public String check(){

        if(isFantasy == 1){
            addGenreStr = "판타지";
        }
        if(isLight == 1){
            addGenreStr = "라이트노벨";
        }
        if(isChina == 1){
            addGenreStr = "무협";
        }
        if(isParody == 1){
            addGenreStr ="패러디";
        }
        if(isBL == 1){
            addGenreStr = "BL";
        }
        if(isThink == 1){
            addGenreStr = "추리";
        }
        if(isGame == 1){
            addGenreStr = "게임";
        }
        if(isRomance == 1){
            addGenreStr ="로맨스";
        }
        if(isGL == 1){
            addGenreStr = "GL";
        }
        return addGenreStr;

    }
}
