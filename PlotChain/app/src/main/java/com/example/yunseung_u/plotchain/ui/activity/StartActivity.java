package com.example.yunseung_u.plotchain.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.yunseung_u.plotchain.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity{


    @OnClick(R.id.login_view)
    public void onMovetoLogin(){

        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.register_tv)
    public void onMovetoRegister(){
        Intent intent = new Intent(this,RegisterStepActivity.class);
        startActivity(intent);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.NoToolbarThme);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        Window window = getWindow();



        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.black));

    }
}
