package br.com.edu.ufcg.osindico.visitors_list.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.adapters.AllowedVisitorsAdapter;
import br.com.edu.ufcg.osindico.adapters.ServiceRequestAdapter;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.VisitorResponse;
import br.com.edu.ufcg.osindico.data.models.VisitorDetails;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.visitors_list.mvp.AllowedVisitorsListContract;
import br.com.edu.ufcg.osindico.visitors_list.mvp.AllowedVisitorsListPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;


public class AllowedVisitorsListFragment extends Fragment implements AllowedVisitorsListContract.View{

    @BindView(R.id.elv_visitors) ExpandableListView elvVisitors;

    private AllowedVisitorsAdapter adapter;
    private AllowedVisitorsListContract.Presenter presenter;

    public AllowedVisitorsListFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SyndicService service = new SyndicService();
        this.presenter = new AllowedVisitorsListPresenterImpl(service);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.allowed_visitors_list_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.presenter.setView(this);
        initializeViews();
    }

    private void initializeViews() {

        loadVisitorsList();
    }

    private void loadVisitorsList() {
        this.presenter.loadAllowedVisitorsList(getToken());
    }

    @Override
    public void setAllowedVisitorsList(List<VisitorResponse> visitorsList) {
        this.adapter = new AllowedVisitorsAdapter(getActivity(), visitorsList);
        elvVisitors.setAdapter(adapter);
    }

    @Override
    public void setServerError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private String getToken(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("preferencesOSindico",
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(getString(R.string.user_token), null);
    }
}
