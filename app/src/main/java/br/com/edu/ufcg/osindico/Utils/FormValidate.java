package br.com.edu.ufcg.osindico.Utils;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

public class FormValidate {

    public static boolean isValidName(CharSequence target) {
        return !TextUtils.isEmpty(target);
    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean isValidPassword(CharSequence target) {
        return !TextUtils.isEmpty(target) && target.length() >= 8;
    }

    public static boolean isValidConfirmPassword(CharSequence password, CharSequence confirmPassword) {
        return password.equals(confirmPassword);
    }

    public static boolean isValidPhone(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.PHONE.matcher(target).matches();
    }



}