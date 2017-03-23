package br.com.edu.ufcg.osindico.allow_visitors.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.allow_visitors.mvp.AllowVisitorsContract;
import butterknife.OnClick;

public class AllowVisitorsActivity extends AppCompatActivity implements AllowVisitorsContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allow_visits);
    }

    @OnClick(R.id.fabAddEditTexts)
    public void onClickButtonAdd() {
        /*

            Tava tentando adicionar os campos dinamicamente ao clicar no fab, mas nao consegui.

         */
        Log.d("OK", "click");
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        EditText name = new EditText(this);
        name.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        EditText cpf = new EditText(this);
        cpf.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

        linearLayout.addView(name);
        linearLayout.addView(cpf);

        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_allow_visits);
        layout.addView(linearLayout);
        Toast.makeText(this, "Adicionado!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSuccess() {

    }

    @Override
    public void setServerError(String message) {

    }
}
