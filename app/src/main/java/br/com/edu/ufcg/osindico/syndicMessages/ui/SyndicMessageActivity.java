package br.com.edu.ufcg.osindico.syndicMessages.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.navigationSyndic.ui.NavigationSyndicActivity;
import br.com.edu.ufcg.osindico.syndicMessages.mvp.SyndicMessageContract;
import br.com.edu.ufcg.osindico.syndicMessages.mvp.SyndicMessagePresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SyndicMessageActivity extends AppCompatActivity implements SyndicMessageContract.View{

    @BindView(R.id.editTextMessage) EditText editTextMessage;

    @BindView(R.id.progressBar2) ProgressBar progressBar;

    private SyndicMessageContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syndic_message);

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter = new SyndicMessagePresenterImpl();
        presenter.setView(this);
    }

    @OnClick(R.id.btnSendMessage)
    public void sendMessge(){
        SyndicService service = new SyndicService();
        String message = editTextMessage.getText().toString();
        editTextMessage.setError(null);
        presenter.validateMessage(message, getToken(), service);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMessageLengthError() {
        editTextMessage.setError(getString(R.string.msg_error_message));
    }

    @Override
    public void setMessageNullError() {
        editTextMessage.setError(getString(R.string.msg_error_message_null));
    }

    @Override
    public void setServerFailed(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToHomeSyndic() {
        Toast.makeText(this, getString(R.string.msg_success_send_message), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, NavigationSyndicActivity.class));
        finish();
    }

    private String getToken(){
        SharedPreferences sharedpreferences = getSharedPreferences(
                getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);
        return sharedpreferences.getString(getString(R.string.user_token), "");
    }
}
