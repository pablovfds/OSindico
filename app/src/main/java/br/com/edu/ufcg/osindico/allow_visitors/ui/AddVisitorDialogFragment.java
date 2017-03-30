package br.com.edu.ufcg.osindico.allow_visitors.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.allow_visitors.mvp.AllowVisitorsContract;
import butterknife.BindView;
import butterknife.OnClick;

public class AddVisitorDialogFragment extends DialogFragment implements AllowVisitorsContract.View {

    @BindView(R.id.input_visitor_name)
    TextInputEditText inputName;

    @BindView(R.id.input_visitor_cpf)
    TextInputEditText inputCpf;

    private OnAddVisitorDialogListener mListener;

    public AddVisitorDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListener = (OnAddVisitorDialogListener) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_visitor, container);
    }

    @OnClick(R.id.buttonAdd)
    public void addVisitor() {
        String name = inputName.getText().toString();
        String cpf = inputCpf.getText().toString();
        mListener.onFinishAddDialog(name, cpf);
    }

    @OnClick(R.id.buttonCancel)
    public void dismissDialog() {
        this.dismiss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddVisitorDialogListener) {
            mListener = (OnAddVisitorDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddVisitorDialogListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setSuccess() {

    }

    @Override
    public void setServerError(String message) {

    }

    @Override
    public void setNameError() {
        inputName.setError("Insira um nome.");
    }

    @Override
    public void setCpfError() {
        inputCpf.setError("Insira um numero de CPF.");
    }

    @Override
    public void onFinishAddDialog(String name, String cpf) {
        
    }

    interface OnAddVisitorDialogListener {
        void onFinishAddDialog(String name, String cpf);
    }
}
