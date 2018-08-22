package com.example.yunseung_u.plotchain.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.application.PlotChainApplication;
import com.example.yunseung_u.plotchain.model.User;
import com.example.yunseung_u.plotchain.util.SharedPrefManager;
import com.example.yunseung_u.plotchain.util.ValidCheck;
import com.example.yunseung_u.plotchain.util.api.BaseApiService;
import com.example.yunseung_u.plotchain.util.api.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_pw)
    EditText etPassword;
    @BindView(R.id.idInputLayout)
    TextInputLayout idInputLayout;
    @BindView(R.id.pwInputLayout)
    TextInputLayout pwInputLayout;


    @BindView(R.id.btn_login)
    View viewLogin;
    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    SharedPrefManager sharedPrefManager;
    ValidCheck validCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.NoToolbarThme);
        setContentView(R.layout.activity_login_second);

        //getSupportActionBar().hide();



        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mContext = this;
        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper
        sharedPrefManager = new SharedPrefManager(this);

        viewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ValidCheck();
                loading = ProgressDialog.show(mContext, null, "Login...", true, false);
                //requestLogin();
                requestJSONLogin();

            }
        });

        // Code berikut berfungsi untuk mengecek session, Jika session true ( sudah login )
        // maka langsung memulai MainActivity.
        /*
        if (sharedPrefManager.getSPSudahLogin()){
            User user = new User()
            startActivity(new Intent(LoginActivity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        */
    }

    /*
    private boolean isvalid(AppCompatEditText email, AppCompatEditText password){

        mEditTextEmail.setError(null);
        mEditTextPassword.setError(null);

        if (validCheck.isEmail(email)) {
            mEditTextEmail.setError("Email is required");
            return false;
        } else if (!validCheck.isEmail(email)) {
            mEditTextEmail.setError("Enter a valid email");
            return false;
        }

        if (validCheck.isEmpty(password)) {
            mEditTextPassword.setError("Password is required");
            return false;
        }
        return true;

    }
    */

    public static boolean isEmailValid(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void showDialog(String errMsg){

        switch(errMsg) {
            case "100":
                new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error")
                        .setContentText("Something went wrong!")
                        .show();
                break;
            case "101":
                new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error")
                        .setContentText("Something went wrong!")
                        .show();
                break;
            case "102":
                new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error")
                        .setContentText("Something went wrong!")
                        .show();
                break;
            case "103":
                new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error")
                        .setContentText("Something went wrong!")
                        .show();
                break;
            case "104":
                new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error")
                        .setContentText("Something went wrong!")
                        .show();
                break;

        }

    }

    private void requestJSONLogin(){

        final User user = new User("",etEmail.getText().toString(),etPassword.getText().toString());
        mApiService.loginJSONRequest(user)
                .enqueue(new Callback<ResponseBody>(){
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response){
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("success").equals("true")){


                                    String name = jsonRESULTS.getString("nickname");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, name);
                                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);

                                    User user = new User(name,etEmail.getText().toString(),etPassword.getText().toString());
                                    user.setSession(jsonRESULTS.getString("session"));
                                    PlotChainApplication.setCurrentUser(user);

                                    startActivity(new Intent(mContext,MainActivity.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();
                                } else {
                                    // Jika login gag
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });

    }

    public void ValidCheck() {

        if (isEmpty(etEmail)){

            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("이메일이 비어있어요")
                    .show();
        }

        if (isEmpty(etPassword)) {

            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("비밀번호가 비어있어요")
                    .show();
        }
        if (isEmail(etEmail) == false) {

            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("이메일 형식이 적절하지 않아요")
                    .show();
        }


    }

    public boolean isEmail(EditText text){
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());

    }

    public boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    /*

    private void requestLogin(){
        mApiService.loginRequest(etEmail.getText().toString(), etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("success").equals("true")){

                                    String name = jsonRESULTS.getJSONObject("data").getString("name");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, name);
                                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);

                                    User user = new User(etEmail.getText().toString(),"",name);
                                    //user.setinoToken(jsonRESULTS.getString("token"));
                                    PlotChainApplication.setCurrentUser(user);

                                    startActivity(new Intent(mContext, com.example.yunseung_u.enobook.MainActivity.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();
                                } else {
                                    // Jika login gag
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });


        */
}
