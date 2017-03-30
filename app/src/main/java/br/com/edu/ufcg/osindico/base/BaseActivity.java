package br.com.edu.ufcg.osindico.base;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.Utils.UpdateTheme;

public class BaseActivity extends AppCompatActivity {

    private final static int THEME_BLUE = 1;
    private final static int THEME_ORANGE = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateTheme();
    }
    public void updateTheme() {
        if (UpdateTheme.getTheme(getApplicationContext()) <= THEME_BLUE) {
            setTheme(R.style.NoActionBarThemeSyndic);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkSyndic));
            }
        } else if (UpdateTheme.getTheme(getApplicationContext()) == THEME_ORANGE) {
            setTheme(R.style.NoActionBarTheme);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().setStatusBarColor(getResources().getColor(R.color.primaryDarkColorDweller));
            }
        }
    }
}