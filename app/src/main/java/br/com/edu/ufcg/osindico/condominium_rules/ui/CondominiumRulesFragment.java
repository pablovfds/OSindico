package br.com.edu.ufcg.osindico.condominium_rules.ui;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.adapters.CondominiumRulesAdapter;
import br.com.edu.ufcg.osindico.condominium_rules.mvp.CondominiumRulesContract;
import br.com.edu.ufcg.osindico.condominium_rules.mvp.CondominiumRulesPresenterImpl;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.RuleResponse;
import br.com.edu.ufcg.osindico.data.services.RulesService;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.emptyFragment.EmptyFragment;
import br.com.edu.ufcg.osindico.registerRegraSyndic.ui.RegisterRegraActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CondominiumRulesFragment extends Fragment implements CondominiumRulesContract.View{

    @BindView(R.id.rules_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.rules_progressBar)
    ProgressBar progressBar;

    private CondominiumRulesContract.Presenter presenter;
    private CondominiumRulesAdapter adapter;
    private String typeUser;
    private AlertDialog dialog;
    private String token;

    public CondominiumRulesFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_condominium_rules, container, false);
        ButterKnife.bind(this, view);
        //typeUser = getActivity().getIntent().getExtras().getString("type");
        typeUser = "sindico";
        SyndicService service = new SyndicService();
        presenter = new CondominiumRulesPresenterImpl(service);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeViews();
    }

    public void initializeViews() {
        Log.e("inicializar views", "iniciar adapter");
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadRules();
    }

    private void loadRules() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("preferencesOSindico", Context.MODE_PRIVATE);
        token = sharedPreferences.getString(getString(R.string.user_token), null);

        presenter.setView(this);
        presenter.loadCondominiumRules(token);
    }

    @Override
    public void onDestroy() {
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
        Toast.makeText(this.getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCondominiumRulesList(List<RuleResponse> ruleResponseList) {
        Collections.reverse(ruleResponseList);
//        if(ruleResponseList.isEmpty()){
//            EmptyFragment emptyFragment = new EmptyFragment();
//            emptyFragment.setTitle("Sem regras!");
//            emptyFragment.setMessage("Você ainda não cadastrou regras em seu condomínio!");
//            setFragment(emptyFragment);
//        }else{
            adapter = new CondominiumRulesAdapter(ruleResponseList);
            recyclerView.setAdapter(adapter);
            //Toast.makeText(this.getActivity(), "Existem " + ruleResponseList.size() + " regras cadastradas.", Toast.LENGTH_SHORT).show();
       // }

    }

    private void setFragment(Fragment newFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame_syndic, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if(typeUser.equals("sindico")){
            inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.menu_rules, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.button_next){
           // startActivity(new Intent(this.getActivity(), RegisterRegraActivity.class));
            showAddRuleDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAddRuleDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.activity_cadastro_regra_syndic, null);
        builder.setView(view)
                .setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText userEditText = (EditText) view.findViewById(R.id.texto_nova_regra);
                        presenter.validateRule(userEditText.getText().toString(), token);

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        dialog = builder.show();
    }

}
