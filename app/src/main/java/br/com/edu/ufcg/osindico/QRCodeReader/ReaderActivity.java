package br.com.edu.ufcg.osindico.QRCodeReader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.Utils.FullscreenModeManager;
import br.com.edu.ufcg.osindico.base.BaseActivity;
import br.com.edu.ufcg.osindico.registerDweller.ui.RegisterDwellerActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReaderActivity extends BaseActivity {

    private final String qrCodePrompt = "Posicione o QRCode para leitura.";
    private final String qrCodeFailMessage = "Não foi possível ler QRCode.";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = R.layout.activity_leitor;
        setContentView(id);
        ButterKnife.bind(this);

        FullscreenModeManager fullscreenModeManager = new FullscreenModeManager(this);
        fullscreenModeManager.setFullscreenMode(R.id.activity_leitor);
    }

    @OnClick(R.id.ler_qrcode_btn)
    public void readQRCode(View view){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt(qrCodePrompt);
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
                Toast.makeText(this, qrCodeFailMessage, Toast.LENGTH_LONG).show();
            } else {
                Intent readerIntent = new Intent(this, RegisterDwellerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("qrCodeData", result.getContents());
                readerIntent.putExtras(bundle);
                startActivity(readerIntent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
