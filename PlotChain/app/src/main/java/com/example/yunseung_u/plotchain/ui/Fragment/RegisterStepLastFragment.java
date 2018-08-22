package com.example.yunseung_u.plotchain.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.model.User;
import com.example.yunseung_u.plotchain.ui.activity.LoginActivity;
import com.example.yunseung_u.plotchain.ui.activity.RegisterStepActivity;
import com.example.yunseung_u.plotchain.util.api.BaseApiService;
import com.example.yunseung_u.plotchain.util.api.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterStepLastFragment extends Fragment{


    private static String TAG = "RegisterStepLastFragment";

    Unbinder unbinder;

    @BindView(R.id.et_password_first)
    TextInputEditText et_password_first;
    @BindView(R.id.btn_register_back)
    ImageView backImgae;

    private ArrayList<String> arrayInfoList;
    private HashMap<String,String> hashMap;

    BaseApiService mApiService;

    @OnClick(R.id.btn_register_back)
    public void onBack(){
        ((RegisterStepActivity)getActivity()).viewPager.setCurrentItem(1);
    }

    @OnClick(R.id.btn_register)
    public void onClickRegister(){
        hashMap = ((RegisterStepActivity)getActivity()).registerInfo;


        String email = hashMap.get("email");
        String nick = hashMap.get("nickname");
        String pw =et_password_first.getText().toString();

        Log.d(TAG,nick + ',' + email+ ',' + pw);

        requestJSONRegister(nick,email,pw);
    }

    public RegisterStepLastFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = (View) inflater.inflate(R.layout.fragment_register_last, container, false);
        unbinder = ButterKnife.bind(this,view);
        mApiService = UtilsApi.getAPIService();
        backImgae.setImageResource(R.drawable.ic_back_arrow);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void requestJSONRegister(String email, String nick, final String pw) {
        User user = new User(email,nick,pw);

        mApiService.registerJSONRequest(user)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){

                            Log.i("debug", "onResponse: BERHASIL");
                            //loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("success").equals("true")){
                                    createWallet(pw);
                                    //Toast.makeText(mContext, "BERHASIL REGISTRASI", Toast.LENGTH_SHORT).show();
                                    //startActivity(new Intent(getActivity(), LoginActivity.class));
                                    getActivity().finish();
                                } else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(getActivity(), error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("debug", "onResponse: Debug");
                            //loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        //loading.dismiss();
                    }
                });
    }
    public String[] createWallet(final String password) {
        String[] result = new String[2];
        try {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS); //도큐먼트 path 가져오기
            //path = path + "/.keystore"

            if (!path.exists()) {
                path.mkdir();
            }
            String fileName = WalletUtils.generateLightNewWalletFile(password, new File(String.valueOf(path))); //지갑생성
            result[0] = path+"/"+fileName;

            Credentials credentials = WalletUtils.loadCredentials(password,result[0]);

            result[1] = credentials.getAddress();

            return result;
        } catch (NoSuchAlgorithmException
                | NoSuchProviderException
                | InvalidAlgorithmParameterException
                | IOException
                | CipherException e) {
            e.printStackTrace();
            return null;
        }
    }



}
