package br.com.edu.ufcg.osindico.registerDweller.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import br.com.edu.ufcg.osindico.QRCodeReader.QRCodeData;
import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.base.BaseActivity;
import br.com.edu.ufcg.osindico.data.models.Contact;
import br.com.edu.ufcg.osindico.data.services.DwellerService;
import br.com.edu.ufcg.osindico.loginUser.ui.LoginUserActivity;
import br.com.edu.ufcg.osindico.registerDweller.mvp.RegisterDwellerContract;
import br.com.edu.ufcg.osindico.registerDweller.mvp.RegisterDwellerPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterDwellerActivity extends BaseActivity implements RegisterDwellerContract.View {

    @BindView(R.id.nome_morador)
    EditText editTextName;
    @BindView(R.id.telefone_morador)
    EditText editTextPhoneNumber;
    @BindView(R.id.email_morador)
    EditText editTextEmail;
    @BindView(R.id.senha_morador)
    EditText editTextPassword;
    @BindView(R.id.confirmar_senha_morador)
    EditText editTextConfirmPassword;
    @BindView(R.id.nome_condominio)
    TextView textViewCondominiumName;

    private RegisterDwellerContract.Presenter presenter;
    private Long condominiumId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_morador);
        ButterKnife.bind(this);

        DwellerService service = new DwellerService();
        presenter = new RegisterDwellerPresenterImpl(service, this);

        Intent readerIntent = getIntent();
        Bundle bundle = readerIntent.getExtras();

        String qrcode = bundle.getString("qrCodeData");
        Gson gson = new Gson();
        QRCodeData qrCodeData = gson.fromJson(qrcode, QRCodeData.class);
        textViewCondominiumName.setText(qrCodeData.getNome());

        condominiumId = qrCodeData.getId();
    }

    @OnClick(R.id.btn_cadastrar_morador)
    public void registerNewDweller() {
        String name = editTextName.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();
        Contact contact = new Contact(phoneNumber);
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        presenter.validateCredentials(name, contact, email, password, confirmPassword, condominiumId);
    }

    @OnClick(R.id.btn_limpar_dados)
    public void clearFields() {
        editTextName.setText("");
        editTextPhoneNumber.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
        editTextConfirmPassword.setText("");
    }

    @Override
    public void setNameError() {
        editTextName.setError("Insira um nome válido.");
    }

    @Override
    public void setPhoneNumberError() {
        editTextPhoneNumber.setError("Insira um número válido");
    }

    @Override
    public void setEmailError() {
        editTextEmail.setError("Insira um email válido.");
    }

    @Override
    public void setPasswordError() {
        editTextPassword.setError("Insira uma senha válida.");
    }

    @Override
    public void setConfirmPasswordError() {
        editTextConfirmPassword.setError("A senha e a confirmação de senha devem ser iguais.");
        editTextPassword.setError("A senha e a confirmação de senha devem ser iguais.");
    }

    @Override
    public void setSuccess(String message) {
        startActivity(new Intent(this, LoginUserActivity.class));
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void setServerError(String serverError) {
        Toast.makeText(this, serverError, Toast.LENGTH_LONG).show();
    }
}
