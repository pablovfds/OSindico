package br.com.edu.ufcg.osindico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    View main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        main = findViewById(R.id.activity_main);
        main.setBackgroundColor(getResources().getColor(android.R.color.white));




    }
}


