package br.com.edu.ufcg.osindico.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import br.com.edu.ufcg.osindico.R;

/**
 * Created by Cathy on 01/03/2017.
 */

public class UpdateTheme {

    public static void setTheme(Context context, int theme) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putInt(context.getString(R.string.prefs_theme_key), theme).apply();
    }
    public static int getTheme(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(context.getString(R.string.prefs_theme_key), -1);
    }
}
