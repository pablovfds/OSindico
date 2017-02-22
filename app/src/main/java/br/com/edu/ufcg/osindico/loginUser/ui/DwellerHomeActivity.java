package br.com.edu.ufcg.osindico.loginUser.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.com.edu.ufcg.osindico.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DwellerHomeActivity extends AppCompatActivity {

    @BindView(R.id.dweller_name)
    TextView textViewName;
    @BindView(R.id.dweller_email)
    TextView textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dweller_home);
        ButterKnife.bind(this);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);

        textViewName.setText(sharedPreferences.getString(getString(R.string.user_name), null));
        textViewEmail.setText(sharedPreferences.getString(getString(R.string.user_email), null));
        //Log.d("Nome: ", sharedPreferences.getString("name", null));
    }
}
