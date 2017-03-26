package br.com.edu.ufcg.osindico.allow_visitors.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.allow_visitors.mvp.AllowVisitorsContract;
import br.com.edu.ufcg.osindico.request_service.mvp.RequestServiceContract;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AllowVisitorsFragment extends Fragment implements AllowVisitorsContract.View{


    public AllowVisitorsFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //setContentView(R.layout.activity_requests_residents);
        View view = inflater.inflate(R.layout.activity_allow_visits, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.fabAddEditTexts)
    public void onClickButtonAdd() {
        /*

            Tava tentando adicionar os campos dinamicamente ao clicar no fab, mas nao consegui.

         */
        Log.d("OK", "click");
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        EditText name = new EditText(getActivity());
        name.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        EditText cpf = new EditText(getActivity());
        cpf.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

        linearLayout.addView(name);
        linearLayout.addView(cpf);

        RelativeLayout layout = (RelativeLayout) getActivity().findViewById(R.id.activity_allow_visits);
        layout.addView(linearLayout);
        Toast.makeText(getActivity(), "Adicionado!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSuccess() {

    }

    @Override
    public void setServerError(String message) {

    }
}



