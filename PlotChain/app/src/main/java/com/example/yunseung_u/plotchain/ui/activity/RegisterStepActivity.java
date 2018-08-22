package com.example.yunseung_u.plotchain.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.ui.adapter.RegisterViewpagerAdapter;
import com.example.yunseung_u.plotchain.util.RegisterInfoSetListener;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterStepActivity extends FragmentActivity implements
        RegisterInfoSetListener{


    public HashMap<String,String> registerInfo;
    public ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setTheme(R.style.NoToolbarThme);
        setContentView(R.layout.activity_register_step);
        super.onCreate(savedInstanceState);
        registerInfo = new HashMap<String,String>();


        //뷰페이저 세팅
         viewPager = (ViewPager) findViewById(R.id.pager);
        //뷰페이저의 어댑터를 세팅

        viewPager.setAdapter(new RegisterViewpagerAdapter(getSupportFragmentManager()));
    }



    @Override
    public void onRegisterInfoSet(HashMap<String, String> info, String position) {

        if(position == "1"){
            String key = "nickname";
            String value = info.get("nickname");
            registerInfo.put(key,value);
        }
        if(position == "2"){
            String key = "email";
            String value = info.get("email");
            registerInfo.put(key,value);
        }
        /*
        if(position == "3"){
            String key = "password";
            String value = info.get("password");
            registerInfo.put(key,value);
        }*/
    }
}
