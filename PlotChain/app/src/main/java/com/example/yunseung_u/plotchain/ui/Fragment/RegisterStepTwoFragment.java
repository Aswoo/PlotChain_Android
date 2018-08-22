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

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterStepTwoFragment extends Fragment {


    private RegisterInfoSetListener onRegisterInfoSetListener;


    Unbinder unbinder;
    @BindView(R.id.et_emailText)
    TextInputEditText etEmailText;
    @BindView(R.id.btn_register_back_two)
    ImageView backImgae;



    @OnClick(R.id.btn_register_back_two)
    public void onClickBackBtn(){
        ((RegisterStepActivity)getActivity()).viewPager.setCurrentItem(0);
    }

    @OnClick(R.id.btn_next)
    public void onClickNext(){

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("email",etEmailText.getText().toString());
        onRegisterInfoSetListener.onRegisterInfoSet(hashMap,"2");

        ((RegisterStepActivity)getActivity()).viewPager.setCurrentItem(2);

    }

    public RegisterStepTwoFragment() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {


        View view = (View) inflater.inflate(R.layout.fragment_register_two, container, false);
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
