package br.com.edu.ufcg.osindico.allow_visitors.ui;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.adapters.AllowVisitorsAdapter;
import br.com.edu.ufcg.osindico.allow_visitors.mvp.AllowVisitorsContract;
import br.com.edu.ufcg.osindico.allow_visitors.mvp.AllowVisitorsPresenterImpl;
import br.com.edu.ufcg.osindico.data.models.VisitorDetails;
import br.com.edu.ufcg.osindico.data.services.DwellerService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllowVisitorsActivity extends AppCompatActivity implements AllowVisitorsContract.View, AddVisitorDialogFragment.OnAddVisitorDialogListener {

    @BindView(R.id.visitors_recycler_view)
    RecyclerView recyclerView;

    private AllowVisitorsContract.Presenter presenter;
    private AllowVisitorsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allow_visits);
        ButterKnife.bind(this);

        DwellerService service = new DwellerService();
        presenter = new AllowVisitorsPresenterImpl(service);
        presenter.setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        initializeViews();
    }

    private void initializeViews() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AllowVisitorsAdapter(new ArrayList<VisitorDetails>());
        recyclerView.setAdapter(adapter);
        Toast.makeText(this, String.valueOf(adapter.getItemCount()), Toast.LENGTH_LONG).show();
    }
/*
    @OnClick(R.id.fabAddVisitor)
    public void onClickButtonAddVisitor() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        AddVisitorDialogFragment addVisitorDialogFragment = new AddVisitorDialogFragment();
        addVisitorDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        addVisitorDialogFragment.show(ft, "addVisitorDialog");
    }
*/
    @Override
    public void setSuccess() {
        Toast.makeText(this, "Lista de visitas enviada com sucesso.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setServerError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setNameError() {
        //addVisitorDialogFragment.setNameError();
    }

    @Override
    public void setCpfError() {
        //addVisitorDialogFragment.setCpfError();
    }

    @Override
    public void onFinishAddDialog(String name, String cpf) {
         adapter.addVisitor(new VisitorDetails(name, cpf, "", 1L));
    }
}
