package br.com.edu.ufcg.osindico.condominiumResidents.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.adapters.ResidentsListAdapter;
import br.com.edu.ufcg.osindico.condominiumResidents.mvp.CondominiumResidentsContract;
import br.com.edu.ufcg.osindico.condominiumResidents.mvp.CondominiumResidentsPresenterImpl;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.UserResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.emptyFragment.EmptyFragment;
import butterknife.BindView;
import butterknife.ButterKnife;


public class CondominiumResidentsFragment extends Fragment implements CondominiumResidentsContract.View {

    @BindView(R.id.residents_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.residents_progressBar)
    ProgressBar progressBar;

    private CondominiumResidentsContract.Presenter presenter;
    private ResidentsListAdapter adapter;

    public CondominiumResidentsFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_condo_residents, container, false);
        ButterKnife.bind(this, view);
        SyndicService service = new SyndicService();
        presenter = new CondominiumResidentsPresenterImpl(service);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeViews();
    }

    private void initializeViews() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadResidentsList();
    }

    private void loadResidentsList(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("preferencesOSindico", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(getString(R.string.user_token), null);

        presenter.setView(this);
        presenter.loadCondominiumResidents(token);
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
    public void setCondominiumResidentsList(List<DwellerResponse> residentsResponseList) {

        if(residentsResponseList.isEmpty()){
            EmptyFragment emptyFragment = new EmptyFragment();
            emptyFragment.setTitle("Nenhum morador :(");
            emptyFragment.setMessage("A lista de moradores do condomínio está vazia!");
            setFragment(emptyFragment);
        }else{
            Log.e("list", residentsResponseList.size() + "");
            adapter = new ResidentsListAdapter(residentsResponseList);
            recyclerView.setAdapter(adapter);
            Toast.makeText(this.getActivity(), "Existem " + residentsResponseList.size() + " moradores", Toast.LENGTH_SHORT).show();

        }
    }

    private void setFragment(Fragment newFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame_syndic, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
