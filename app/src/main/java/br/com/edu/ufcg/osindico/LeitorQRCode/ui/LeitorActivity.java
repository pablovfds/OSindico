package br.com.edu.ufcg.osindico.LeitorQRCode.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.cadastroMorador.ui.CadastroMoradorActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeitorActivity extends AppCompatActivity {

    @BindView(R.id.ler_qrcode_btn)
    Button leitor_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ler_qrcode_btn)
    public void ler_qrcode(View view){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Posicione o QRCode para leitura.");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Erro", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, CadastroMoradorActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("dadosQRCode", result.getContents());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
