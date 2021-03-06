package br.com.edu.ufcg.osindico.condominium_rules.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.adapters.CondominiumRulesAdapter;
import br.com.edu.ufcg.osindico.condominium_rules.mvp.CondominiumRulesContract;
import br.com.edu.ufcg.osindico.condominium_rules.mvp.CondominiumRulesPresenterImpl;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.RuleResponse;
import br.com.edu.ufcg.osindico.data.services.RulesService;
import br.com.edu.ufcg.osindico.registerRegraSyndic.ui.RegisterRegraActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.type;

public class CondominiumRulesActivity extends AppCompatActivity implements CondominiumRulesContract.View {

    @BindView(R.id.rules_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.rules_progressBar)
    ProgressBar progressBar;

    private CondominiumRulesContract.Presenter presenter;
    private CondominiumRulesAdapter adapter;
    private String typeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condominium_rules);
        ButterKnife.bind(this);

        typeUser = getIntent().getExtras().getString("type");

//        RulesService service = new RulesService();
//        presenter = new CondominiumRulesPresenterImpl(service);

        getSupportActionBar().setTitle("Regras do Condomínio");
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeViews();
    }

     public void initializeViews() {

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadRules();
    }

    private void loadRules() {
        SharedPreferences sharedPreferences = getSharedPreferences("preferencesOSindico", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(getString(R.string.user_token), null);

        presenter.setView(this);
        presenter.loadCondominiumRules(token);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
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
    public void setServerError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCondominiumRulesList(List<RuleResponse> ruleResponseList) {
        adapter = new CondominiumRulesAdapter(ruleResponseList);
        recyclerView.setAdapter(adapter);
        Toast.makeText(this, "Existem " + ruleResponseList.size() + " regras cadastradas.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(typeUser.equals("sindico")){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_rules, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.button_next){
            startActivity(new Intent(this, RegisterRegraActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
