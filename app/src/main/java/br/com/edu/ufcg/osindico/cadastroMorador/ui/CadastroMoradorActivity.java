package br.com.edu.ufcg.osindico.cadastroMorador.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.cadastroMorador.mvp.ICadastroMoradorMVP;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CadastroMoradorActivity extends AppCompatActivity implements ICadastroMoradorMVP.CadastroMoradorView {

    @BindView(R.id.nome_morador) EditText editTextMome;
    @BindView(R.id.telefone_morador) EditText editTextTelefone;
    @BindView(R.id.email_morador) EditText editTextEmail;
    @BindView(R.id.senha_morador) EditText editTextSenha;
    @BindView(R.id.confirmar_senha_morador) EditText editTextConfirmarSenha;

    ICadastroMoradorMVP.CadastroMoradorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_morador);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        TextView textView = (TextView) findViewById(R.id.nome_condominio);
        textView.setText(bundle.getString("condominio"));
    }

    @Override
    public void setNomeError() {

    }

    @Override
    public void setTelefoneError() {

    }

    @Override
    public void setEmailError() {

    }

    @Override
    public void setSenhaError() {

    }

    @Override
    public void setConfirmarSenhaError() {

    }

    @Override
    public void setServerError() {

    }
}
