package br.com.edu.ufcg.osindico.visitors_list.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.adapters.AllowedVisitorsAdapter;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.VisitorResponse;
import br.com.edu.ufcg.osindico.data.models.VisitorDetails;
import butterknife.BindView;
import butterknife.ButterKnife;


public class TestActivity extends AppCompatActivity {

    @BindView(R.id.elv_visitors)
    ExpandableListView expandableListView;

    private AllowedVisitorsAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.allowed_visitors_list_fragment);

        ButterKnife.bind(this);

        loadList();
    }

    private void loadList() {
        List<VisitorResponse> vr = new ArrayList<>();
        for (int j=0;j<5;j++) {
            List<VisitorDetails> vd = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                vd.add(new VisitorDetails("nome " + i, "cpf " + i));
            }
            Log.d("size", vd.size()+"");
            vr.add(new VisitorResponse("email " + j, "name v "+j, vd));
        }

        this.adapter = new AllowedVisitorsAdapter(this, vr);
        expandableListView.setAdapter(adapter);
    }


}
