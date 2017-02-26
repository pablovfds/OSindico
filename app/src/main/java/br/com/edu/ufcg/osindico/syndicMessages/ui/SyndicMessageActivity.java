package br.com.edu.ufcg.osindico.syndicMessages.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import br.com.edu.ufcg.osindico.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SyndicMessageActivity extends AppCompatActivity {

    @BindView(R.id.editTextMessage) EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syndic_message);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSendMessage)
    public void sendMessge(){
        String message = editTextMessage.getText().toString();
    }
}
