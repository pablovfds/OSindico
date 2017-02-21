package br.com.edu.ufcg.osindico.cadastroMorador.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import br.com.edu.ufcg.osindico.LeitorQRCode.ui.DadosQRCode;
import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.cadastroMorador.mvp.CadastroMoradorPresenterImpl;
import br.com.edu.ufcg.osindico.cadastroMorador.mvp.ICadastroMoradorMVP;
import br.com.edu.ufcg.osindico.data.models.Contato;
import br.com.edu.ufcg.osindico.data.services.MoradorService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CadastroMoradorActivity extends AppCompatActivity implements ICadastroMoradorMVP.View {

    @BindView(R.id.nome_morador)
    EditText editTextMome;
    @BindView(R.id.telefone_morador)
    EditText editTextTelefone;
    @BindView(R.id.email_morador)
    EditText editTextEmail;
    @BindView(R.id.senha_morador)
    EditText editTextSenha;
    @BindView(R.id.confirmar_senha_morador)
    EditText editTextConfirmarSenha;
    @BindView(R.id.btn_limpar_dados)
    Button btn_limpar_dados;
    @BindView(R.id.btn_cadastrar)
    Button btn_cadastrar;

    private ICadastroMoradorMVP.Presenter presenter;
    private Long idCondominio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_morador);
        ButterKnife.bind(this);

        MoradorService service = new MoradorService();
        presenter = new CadastroMoradorPresenterImpl(service, this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        TextView textViewNomeCondominio = (TextView) findViewById(R.id.nome_condominio);
        String qrcode = bundle.getString("dadosQRCode");
        Gson gson = new Gson();
        DadosQRCode dadosQRCode = gson.fromJson(qrcode, DadosQRCode.class);
        textViewNomeCondominio.setText(dadosQRCode.getNome());

        Toast.makeText(this, dadosQRCode.getId().toString(), Toast.LENGTH_LONG).show();

        idCondominio = dadosQRCode.getId();

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = editTextMome.getText().toString();
                String telefone = editTextTelefone.getText().toString();
                Contato contato = new Contato(telefone);
                String email = editTextEmail.getText().toString();
                String senha = editTextSenha.getText().toString();
                String confirmarSenha = editTextConfirmarSenha.getText().toString();

                presenter.validarMorador(nome, contato, email, senha, confirmarSenha, idCondominio);
            }
        });
    }

    @Override
    public void setNomeError() {
        editTextMome.setError("Insira um nome válido.");
    }

    @Override
    public void setTelefoneError() {
        editTextTelefone.setError("Insira um numero válido");
    }

    @Override
    public void setEmailError() {
        editTextEmail.setError("Insira um email válido.");
    }

    @Override
    public void setSenhaError() {
        editTextSenha.setError("Insira uma senha válida.");
    }

    @Override
    public void setConfirmarSenhaError() {
        editTextConfirmarSenha.setError("A senha e a confirmação de senha devem ser iguais.");
        editTextSenha.setError("A senha e a confirmação de senha devem ser iguais.");
    }

    @Override
    public void onSuccess(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setServerError(String serverError) {
        Toast.makeText(this, serverError, Toast.LENGTH_LONG).show();
    }
}
