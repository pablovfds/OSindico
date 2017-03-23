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

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.adapters.AllowedVisitorsAdapter;
import br.com.edu.ufcg.osindico.adapters.ServiceRequestAdapter;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;
import butterknife.BindView;
import butterknife.ButterKnife;


public class AllowedVisitorsListFragment extends Fragment {

    @BindView(R.id.visitors_list)
    RecyclerView recyclerView;
    private AllowedVisitorsAdapter adapter;

    public AllowedVisitorsListFragment(){}


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
        initializeViews();
    }

    private void initializeViews() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        loadVisitorsList();
    }

    private void loadVisitorsList() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("preferencesOSindico",
                Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(getString(R.string.user_token), null);

    }

//    @Override
//    public void setVisitorsList(List<ServiceVisitorsList> visitorsList) {
//        adapter = new AllowedVisitorsAdapter(visitorsList);
//        recyclerView.setAdapter(adapter);
//    }

}
