package com.example.yunseung_u.plotchain.util;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import java.nio.charset.Charset;
import java.util.regex.Pattern;

public class ValidCheck {

    public static boolean isEmail(TextInputEditText text){
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());

    }

    public static boolean isEmpty(TextInputEditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}
