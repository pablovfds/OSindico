package br.com.edu.ufcg.osindico.request_service.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.request_service.mvp.RequestServiceContract;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestServiceActivity extends AppCompatActivity implements RequestServiceContract.View {

    @BindView(R.id.radioGroup) RadioGroup radioGroupTypeProblem;

    @BindView(R.id.editTextProblemDescription) EditText editTextProblemDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_service);

        ButterKnife.bind(this);

        setTitle(getString(R.string.label_title_request_service));
        setUpIndicator();
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
            String problemDescription = editTextProblemDescription.getText().toString();
            String typeProblem = getSelectedTypeProblem();

            Toast.makeText(this, "Ok: " + problemDescription + " - " + typeProblem, Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

    private String getSelectedTypeProblem() {
        int selectedId = radioGroupTypeProblem.getCheckedRadioButtonId();
        if(selectedId == R.id.hydraulic_service){
            return getString(R.string.label_hydraulic_service);
        } else if(selectedId == R.id.electric_service){
            return getString(R.string.label_electric_service);
        } else if(selectedId == R.id.other_service){
            return getString(R.string.label_other_service);
        }
        return "";
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

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showTokenError() {

    }

    @Override
    public void showTitleError() {

    }

    @Override
    public void showDescriptionError() {

    }
}
