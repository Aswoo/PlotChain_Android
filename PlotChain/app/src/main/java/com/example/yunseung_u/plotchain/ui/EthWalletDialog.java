package com.example.yunseung_u.plotchain.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.application.PlotChainApplication;
import com.example.yunseung_u.plotchain.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EthWalletDialog extends Dialog {




    @BindView(R.id.et_wallet_address)
    EditText etWallet;
    private String addGenreStr;
    Context mContext;

    public EthWalletDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ether_dialog_layout);
        ButterKnife.bind(this);

        User user = PlotChainApplication.getCurrentUser();

        etWallet.setText(user.getmAddress());
    }

    public String getAddGenreStr() { return addGenreStr; }
    public void setAddGenreStr(String addCategoryStr) { this.addGenreStr = addCategoryStr; }

    @OnClick(R.id.tv_wallet_cancle)
    public void onClickCancle(){
        dismiss();
    }
    @OnClick(R.id.tv_wallet_clip)
    public void onClickCiip(){

    }
    @OnClick(R.id.tv_wallet_ok)
    public void onClickOk(){


    }

}

