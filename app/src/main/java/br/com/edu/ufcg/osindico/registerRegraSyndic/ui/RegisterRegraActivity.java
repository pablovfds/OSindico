package br.com.edu.ufcg.osindico.registerRegraSyndic.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.base.BaseActivity;
import br.com.edu.ufcg.osindico.condominium_rules.ui.CondominiumRulesActivity;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.navigationSyndic.ui.NavigationSyndicActivity;
import br.com.edu.ufcg.osindico.registerRegraSyndic.mvp.RegisterRegraContract;
import br.com.edu.ufcg.osindico.registerRegraSyndic.mvp.RegisterRegraPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterRegraActivity extends BaseActivity implements RegisterRegraContract.View {

    private RegisterRegraContract.Presenter presenter;
    private String token;

    @BindView(R.id.texto_nova_regra)
    EditText editTextNovaRegra;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_regra_syndic);
        ButterKnife.bind(this);

        SyndicService service = new SyndicService();

        presenter = new RegisterRegraPresenterImpl(this, service);
        presenter.setView(this);

        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);
        token = sharedpreferences.getString(getString(R.string.user_token), "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastrar_regra, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.button_cadastrar_regra:

                String novaRegra = editTextNovaRegra.getText().toString();
                if(novaRegra.trim().isEmpty()){
                    Toast.makeText(this, "Primeiro, escreva uma regra", Toast.LENGTH_LONG).show();
                }else{

                    presenter.validateRegra(novaRegra, token);
                    Log.d("Regra antes:", editTextNovaRegra.getText().toString());
                    editTextNovaRegra.setText("");
                    return true;

                }


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setServerError(String errorMessage) {

    }

    @Override
    public void navigateToRulesCondoList() {
        startActivity(new Intent(this, NavigationSyndicActivity.class));
        finish();
    }

}
