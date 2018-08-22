package com.example.yunseung_u.plotchain.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yunseung_u.plotchain.ui.Fragment.RegisterStepLastFragment;
import com.example.yunseung_u.plotchain.ui.Fragment.RegisterStepOneFragment;
import com.example.yunseung_u.plotchain.ui.Fragment.RegisterStepTwoFragment;

public class RegisterViewpagerAdapter extends FragmentPagerAdapter {


    final int PAGE_COUNT = 3;



    public RegisterViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                RegisterStepOneFragment stepOneFragment = new RegisterStepOneFragment();
                return stepOneFragment;

            case 1:
                RegisterStepTwoFragment stepTwoFragment = new RegisterStepTwoFragment();
                return stepTwoFragment;

            case 2:
                RegisterStepLastFragment stepLastFragment = new RegisterStepLastFragment();
                return stepLastFragment;
        }
        //페이지 포지션이 없는 곳으로 이동을 하려는 경우 null을 리턴하여 움직임이 없게 한다
        return null;

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
