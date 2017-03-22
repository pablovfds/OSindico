package br.com.edu.ufcg.osindico.homeSyndic.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.condominium_rules.ui.CondominiumRulesActivity;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.dwellerRequests.ui.RequestsDwellersActivity;
import br.com.edu.ufcg.osindico.homeSyndic.mvp.HomeSyndicContract;
import br.com.edu.ufcg.osindico.homeSyndic.mvp.HomeSyndicPresenterImpl;
import br.com.edu.ufcg.osindico.serviceRequestList.ui.ServiceRequestListActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SyndicHomeFragment extends Fragment implements HomeSyndicContract.View{

    private HomeSyndicPresenterImpl presenter;

    public SyndicHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SyndicService service = new SyndicService();
        presenter = new HomeSyndicPresenterImpl(service);
        presenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_syndic_home, container, false);
        ButterKnife.bind(this, view);
        //setBottomBar(view);
        return view;
    }

//    public void setBottomBar(View view){
//        BottomBar bottomBar = (BottomBar) view.findViewById(R.id.bottomBar);
//        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelected(@IdRes int tabId) {
//                presenter.onItemClicked(tabId);
//            }
//        });
//    }

    @OnClick(R.id.btn_dweller_list)
    public void openDwellerList(){
        navigateToDwellerList();
    }

    @OnClick(R.id.btn_dweller_requests)
    public void openDwellerRequests(){
        navigateToDwellerRequests();
    }

    @OnClick(R.id.btn_condo_rules)
    public void openCondoRules(){
        navigateToCondoRules();
    }

    @OnClick(R.id.btn_service_request_list)
    public void openServiceRequestList(){
        navigateToServiceRequestList();
    }

    @Override
    public void navigateToDwellerList() {
        Toast.makeText(getActivity(), "Novo morador", Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToDwellerRequests() {
        Toast.makeText(getActivity(), "Lista de moradores", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), RequestsDwellersActivity.class));
    }

    @Override
    public void navigateToCondoRules() {
        Toast.makeText(getActivity(), "Regras do condominio", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), CondominiumRulesActivity.class);
        intent.putExtra("type", "sindico");
        startActivity(intent);
    }

    @Override
    public void navigateToServiceRequestList() {
        startActivity(new Intent(getActivity(), ServiceRequestListActivity.class));
    }
}
