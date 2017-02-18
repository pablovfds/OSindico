package br.com.edu.ufcg.osindico.cadastroMorador.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.cadastroMorador.mvp.ICadastroMoradorMVP;

public class CadastroMoradorActivity extends AppCompatActivity implements ICadastroMoradorMVP.CadastroMoradorView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_morador);
    }
}
