package com.example.yunseung_u.plotchain.ui.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.ui.activity.RegisterStepActivity;
import com.example.yunseung_u.plotchain.util.RegisterInfoSetListener;
import com.example.yunseung_u.plotchain.util.ValidCheck;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterStepOneFragment extends Fragment {

    Unbinder unbinder;

    private RegisterInfoSetListener onRegisterInfoSetListener;

    @BindView(R.id.btn_register_back_one)
    ImageView backImgae;

    @BindView(R.id.et_nickname)
    TextInputEditText nickInputEditText;


    @OnClick(R.id.btn_register_back_one)
    public void onClickBackBtn(){

        ((RegisterStepActivity)getActivity()).onBackPressed();
        //((RegisterStepActivity)getActivity()).viewPager.setCurrentItem(0);
    }

    @OnClick(R.id.btn_next)
    public void onClickNext(){

        if(ValidCheck.isEmpty(nickInputEditText)){

        }else {
            String nickname = nickInputEditText.getText().toString();
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("nickname",nickname);
            onRegisterInfoSetListener.onRegisterInfoSet(hashMap,"1");

            ((RegisterStepActivity)getActivity()).viewPager.setCurrentItem(1);
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public RegisterStepOneFragment() {
    }

    @Override

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = (View) inflater.inflate(R.layout.fragment_register_one, container, false);
        unbinder = ButterKnife.bind(this,view);
        backImgae.setImageResource(R.drawable.ic_back_arrow);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof RegisterInfoSetListener) {

            onRegisterInfoSetListener = (RegisterInfoSetListener) context;

        }else {
            throw new RuntimeException(context.toString() + "must implements registerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onRegisterInfoSetListener = null;
    }
}
