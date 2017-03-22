package br.com.edu.ufcg.osindico.homeDweller.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.adapters.ResidentMessagesFeedAdapter;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.services.DwellerService;
import br.com.edu.ufcg.osindico.homeDweller.mvp.HomeDwellerContract;
import br.com.edu.ufcg.osindico.homeDweller.mvp.HomeDwellerPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DwellerMessagesFragment extends Fragment implements HomeDwellerContract.View {

    @BindView(R.id.resident_home_recycler_view) RecyclerView recyclerView;

    private HomeDwellerContract.Presenter presenter;
    private ResidentMessagesFeedAdapter adapter;

    public DwellerMessagesFragment() {}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DwellerService service = new DwellerService();
        presenter = new HomeDwellerPresenterImpl(service);
        presenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_dweller_home, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initViews();
    }

    private void initViews(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        loadList();
    }

    private void loadList(){

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(
                getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);
        String token = sharedpreferences.getString(getString(R.string.user_token), "");
        presenter.setView(this);
        presenter.loadMessages(token);
    }

    @Override
    public void setMessagesList(List<MessageResponse> messages) {
        adapter = new ResidentMessagesFeedAdapter(messages);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setServerError(String errorMessage) {

    }
}
