package com.example.yunseung_u.plotchain.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.ui.activity.WriteNovelAcitvity;
import com.example.yunseung_u.plotchain.ui.activity.WriterInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyPageFragment extends Fragment {


    private Unbinder mUnbinder = null;

    @OnClick(R.id.myinfo_view)
    public void onClickMyinfo(){

    }

    @OnClick(R.id.mycomment_view)
    public void onClickMycomment(){

    }

    @OnClick(R.id.setting_view)
    public void onClickSetting(){

    }

    @OnClick(R.id.notice_view)
    public void onClickNotice(){

    }

    @OnClick(R.id.service_view)
    public void onClickService(){

    }

    @OnClick(R.id.writer_view)
    public void onClickWriter(){

        Intent intent = new Intent(getContext(), WriterInfoActivity.class);
        startActivity(intent);

    }
    @BindView(R.id.fb_writepage_btn)
    FloatingActionButton floatingActionButton;

    public MyPageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage,container,false);

        mUnbinder = ButterKnife.bind(this,view);



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), WriteNovelAcitvity.class);
                startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
