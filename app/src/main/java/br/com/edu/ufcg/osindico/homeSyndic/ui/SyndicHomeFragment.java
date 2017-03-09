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
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.dwellerRequests.ui.RequestsDwellersActivity;
import br.com.edu.ufcg.osindico.homeSyndic.mvp.HomeSyndicContract;
import br.com.edu.ufcg.osindico.homeSyndic.mvp.HomeSyndicPresenterImpl;
import butterknife.ButterKnife;

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
        setBottomBar(view);
        return view;
    }

    public void setBottomBar(View view){
        BottomBar bottomBar = (BottomBar) view.findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                presenter.onItemClicked(tabId);
            }
        });
    }

    @Override
    public void navigateToDwellerList() {
        Toast.makeText(getActivity(), "Novo morador", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getActivity(), RequestsDwellersActivity.class));
    }

    @Override
    public void navigateToDwellerRequests() {
        Toast.makeText(getActivity(), "Lista de moradores", Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToCondoRules() {
        Toast.makeText(getActivity(), "Regras do condominio", Toast.LENGTH_LONG).show();
    }

    private void setFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame_syndic, fragment).commit();
    }
}
