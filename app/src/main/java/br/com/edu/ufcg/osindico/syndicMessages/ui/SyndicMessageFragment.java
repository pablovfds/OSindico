package br.com.edu.ufcg.osindico.syndicMessages.ui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.navigationSyndic.ui.NavigationSyndicActivity;
import br.com.edu.ufcg.osindico.syndicMessages.mvp.SyndicMessageContract;
import br.com.edu.ufcg.osindico.syndicMessages.mvp.SyndicMessagePresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SyndicMessageFragment extends Fragment implements SyndicMessageContract.View{

    @BindView(R.id.editTextMessage)
    EditText editTextMessage;

    @BindView(R.id.progressBar2)
    ProgressBar progressBar;

    private SyndicMessageContract.Presenter presenter;

    public SyndicMessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SyndicMessagePresenterImpl();
        presenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_syndic_message, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btnSendMessage)
    public void sendMessge(){
        SyndicService service = new SyndicService();
        String message = editTextMessage.getText().toString();
        editTextMessage.setError(null);
        presenter.validateMessage(message, getToken(), service);
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
    public void setMessageLengthError() {
        editTextMessage.setError(getString(R.string.msg_error_message));
    }

    @Override
    public void setMessageNullError() {
        editTextMessage.setError(getString(R.string.msg_error_message_null));
    }

    @Override
    public void setServerFailed(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToHomeSyndic() {
        Toast.makeText(getActivity(), getString(R.string.msg_success_send_message), Toast.LENGTH_SHORT).show();
        editTextMessage.setText("");
    }

    private String getToken(){
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(
                getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);
        return sharedpreferences.getString(getString(R.string.user_token), "");
    }

}
