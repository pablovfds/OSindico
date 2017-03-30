package br.com.edu.ufcg.osindico.allow_visitors.ui;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.adapters.AllowVisitorsAdapter;
import br.com.edu.ufcg.osindico.allow_visitors.mvp.AllowVisitorsContract;
import br.com.edu.ufcg.osindico.allow_visitors.mvp.AllowVisitorsPresenterImpl;
import br.com.edu.ufcg.osindico.condominium_rules.mvp.CondominiumRulesPresenterImpl;
import br.com.edu.ufcg.osindico.data.models.VisitorDetails;
import br.com.edu.ufcg.osindico.data.services.DwellerService;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.request_service.mvp.RequestServiceContract;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AllowVisitorsFragment extends Fragment implements AllowVisitorsContract.View, AddVisitorDialogFragment.OnAddVisitorDialogListener {

    @BindView(R.id.visitors_recycler_view)
    RecyclerView recyclerView;
    private AlertDialog dialog;
    private String token;

    private AllowVisitorsContract.Presenter presenter;
    private AllowVisitorsAdapter adapter;

    public AllowVisitorsFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_allow_visits, container, false);
        ButterKnife.bind(this, view);
        DwellerService service = new DwellerService();
        presenter = new AllowVisitorsPresenterImpl(service);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        initializeViews();
        presenter.setView(this);
    }

    private void initializeViews() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AllowVisitorsAdapter(new ArrayList<VisitorDetails>());
        recyclerView.setAdapter(adapter);
        //Toast.makeText(getActivity(), String.valueOf(adapter.getItemCount()), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_allow_visitor, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_allow_visitor:
                showAddVisitorDialog();
                break;
            case  R.id.send_visitors:
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("preferencesOSindico", Context.MODE_PRIVATE);
                token = sharedPreferences.getString(getString(R.string.user_token), null);

                presenter.sendVisitorsList(token, "30/03/2017", adapter.getVisitorsList());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAddVisitorDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_add_visitor, null);
        builder.setView(view)
                .setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        TextInputEditText inputName = (TextInputEditText) view.findViewById(R.id.input_visitor_name);
                        TextInputEditText inputCpf = (TextInputEditText) view.findViewById(R.id.input_visitor_cpf);
                        String name = inputName.getText().toString();
                        String cpf = inputCpf.getText().toString();
                        presenter.checkVisitor(name, cpf);

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        dialog = builder.show();
    }


    @Override
    public void setSuccess() {
        Toast.makeText(getActivity(), "Lista de visitas enviada com sucesso.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setServerError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setNameError() {
        Toast.makeText(getActivity(), "Nome invalido!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void setCpfError() {
        Toast.makeText(getActivity(), "CPF invalido!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFinishAddDialog(String name, String cpf) {
        Log.e("dialog", name + cpf);
        adapter.addVisitor(new VisitorDetails(name, cpf));
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}



