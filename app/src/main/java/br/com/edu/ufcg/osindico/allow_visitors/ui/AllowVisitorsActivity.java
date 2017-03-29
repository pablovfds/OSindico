package br.com.edu.ufcg.osindico.allow_visitors.ui;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.adapters.AllowVisitorsAdapter;
import br.com.edu.ufcg.osindico.allow_visitors.mvp.AllowVisitorsContract;
import br.com.edu.ufcg.osindico.allow_visitors.mvp.AllowVisitorsPresenterImpl;
import br.com.edu.ufcg.osindico.data.services.DwellerService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllowVisitorsActivity extends AppCompatActivity implements AllowVisitorsContract.View {

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
    }

    @OnClick(R.id.fabAddVisitor)
    public void onClickButtonAddVisitor() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        AddVisitorDialogFragment addVisitorDialogFragment = new AddVisitorDialogFragment();
        addVisitorDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        addVisitorDialogFragment.show(ft, "addVisitorDialog");
    }

    @Override
    public void setSuccess() {

    }

    @Override
    public void setServerError(String message) {

    }

    @Override
    public void setNameError() {

    }

    @Override
    public void setCpfError() {

    }
}
