package br.com.edu.ufcg.osindico.request_service.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.zip.Inflater;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.services.DwellerService;
import br.com.edu.ufcg.osindico.request_service.mvp.RequestServiceContract;
import br.com.edu.ufcg.osindico.request_service.mvp.RequestServicePresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RequestServicesFragment extends Fragment implements RequestServiceContract.View{

    @BindView(R.id.radioGroup)
    RadioGroup radioGroupTypeProblem;
    @BindView(R.id.et_problem_title)
    EditText editTextProblemTitle;
    @BindView(R.id.editTextProblemDescription)
    EditText editTextProblemDescription;

    RequestServiceContract.Presenter presenter;

    public RequestServicesFragment(){}

    @Override
    public void onStart() {
        super.onStart();
        DwellerService dwellerService = new DwellerService();
        this.presenter = new RequestServicePresenterImpl(dwellerService, null); //Revisar com manel
        this.presenter.setView(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_request_service, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(android.view.Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_request_service, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.button_request) {
            String titleService = editTextProblemTitle.getText().toString();
            String serviceDescription = editTextProblemDescription.getText().toString();
            String typeProblem = getSelectedTypeProblem();

            Toast.makeText(getActivity(), "Ok: " + serviceDescription + " - " + typeProblem, Toast.LENGTH_SHORT).show();
            this.presenter.validateService(getToken(), titleService, serviceDescription);

        }

        return super.onOptionsItemSelected(item);
    }

    private void setFragment(android.app.Fragment newFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private String getSelectedTypeProblem() {
        int selectedId = radioGroupTypeProblem.getCheckedRadioButtonId();
        if(selectedId == R.id.hydraulic_service){
            return getString(R.string.label_hydraulic_service);
        } else if(selectedId == R.id.electric_service){
            return getString(R.string.label_electric_service);
        } else if(selectedId == R.id.other_service){
            return getString(R.string.label_other_service);
        }
        return "";
    }

    @Override
    public void setSuccess() {
        setFragment(new RequestSuccessFragment());
    }

    @Override
    public void setServerError(String message) {

    }

    @Override
    public void showTokenError() {

    }

    @Override
    public void showTitleError() {

    }

    @Override
    public void showDescriptionError() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private String getToken(){
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(
                getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);
        return sharedpreferences.getString(getString(R.string.user_token), "");
    }


}
