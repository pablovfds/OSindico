package br.com.edu.ufcg.osindico.cadastroMorador.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.cadastroMorador.mvp.ICadastroMoradorMVP;

public class CadastroMoradorActivity extends AppCompatActivity implements ICadastroMoradorMVP.CadastroMoradorView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_morador);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        TextView textView = (TextView) findViewById(R.id.nome_condominio);
        textView.setText(bundle.getString("condominio"));
    }
}
