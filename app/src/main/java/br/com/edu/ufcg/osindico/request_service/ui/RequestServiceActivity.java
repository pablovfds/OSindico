package br.com.edu.ufcg.osindico.request_service.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.services.DwellerService;
import br.com.edu.ufcg.osindico.navigationDweller.ui.NavigationDwellerActivity;
import br.com.edu.ufcg.osindico.request_service.mvp.RequestServiceContract;
import br.com.edu.ufcg.osindico.request_service.mvp.RequestServicePresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestServiceActivity extends AppCompatActivity implements RequestServiceContract.View {

    @BindView(R.id.editTextTitleService) EditText editTextTitleProblem;
    @BindView(R.id.editTextProblemDescription) EditText editTextProblemDescription;
    @BindView(R.id.pbRequestService) ProgressBar progressBar;

    private RequestServiceContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_service);

        ButterKnife.bind(this);

        setTitle(getString(R.string.label_title_request_service));
        setUpIndicator();
    }

    @Override
    protected void onStart() {
        super.onStart();
        DwellerService dwellerService = new DwellerService();
        this.presenter = new RequestServicePresenterImpl(dwellerService, null); //Revisar com manel
        this.presenter.setView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_request_service, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.button_request) {
            String serviceDescription = editTextProblemDescription.getText().toString();
            String titleService = editTextTitleProblem.getText().toString();

            this.presenter.validateService(getToken(), titleService, serviceDescription);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

    private void setTitle(String newTitle){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(newTitle);
        }
    }

    private void setUpIndicator() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_window_close);
        }
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
    public void setServerError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSuccess() {
        startActivity(new Intent(this, NavigationDwellerActivity.class));
        finish();
    }

    @Override
    public void showTokenError() {
        Toast.makeText(this, getString(R.string.msg_token_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTitleError() {
        Toast.makeText(this, getString(R.string.msg_title_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDescriptionError() {
        Toast.makeText(this, getString(R.string.msg_description_error), Toast.LENGTH_SHORT).show();
    }

    private String getToken(){
        SharedPreferences sharedpreferences = getSharedPreferences(
                getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);
        return sharedpreferences.getString(getString(R.string.user_token), "");
    }
}
