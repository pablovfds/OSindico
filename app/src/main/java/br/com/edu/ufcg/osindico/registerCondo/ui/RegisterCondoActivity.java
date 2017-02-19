package br.com.edu.ufcg.osindico.registerCondo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.domain.Util;
import br.com.edu.ufcg.osindico.data.domain.ZipCodeListener;
import br.com.edu.ufcg.osindico.data.models.Address;
import br.com.edu.ufcg.osindico.data.models.CondoDetails;
import br.com.edu.ufcg.osindico.data.Services.SyndicService;
import br.com.edu.ufcg.osindico.registerCondo.mvp.RegisterCondoContract;
import br.com.edu.ufcg.osindico.registerCondo.mvp.RegisterCondoPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterCondoActivity extends AppCompatActivity implements
        RegisterCondoContract.View {

    @BindView(R.id.editTextCondoName) EditText editTextName;

    @BindView(R.id.editTextCondoPhone) EditText editTextPhone;

    @BindView(R.id.editTextNumber) EditText editTextNumber;

    @BindView(R.id.editTextZipCode) EditText editTextZipCode;

    @BindView(R.id.sp_state) Spinner spStates;

    @BindView(R.id.editTextCity) EditText editTextCity;

    @BindView(R.id.register_condo_progress) ProgressBar progressBar;

    private RegisterCondoContract.Presenter presenter;

    private Long syndicId;
    private Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_condo);

        //syndicId = getIntent().getExtras().getLong("syndicId");

        ButterKnife.bind(this);

        editTextZipCode.addTextChangedListener( new ZipCodeListener( this ) );

        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this,
                        R.array.states,
                        android.R.layout.simple_spinner_item);
        spStates.setAdapter(adapter);

        util = new Util(this,
                R.id.editTextZipCode,
                R.id.editTextStreet,
                R.id.editTextComplement,
                R.id.editTextNeighbor,
                R.id.editTextCity,
                R.id.sp_state,
                R.id.editTextNumber);

        SyndicService service = new SyndicService();

        this.presenter = new RegisterCondoPresenterImpl(service, this);
    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.condo_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.button_register){
            presenter.validateCondoCredentials("","","",0,"","","", syndicId);
        }

        return super.onOptionsItemSelected(item);
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
    public void setNameError() {
        editTextName.setError(getString(R.string.msg_name_error));
    }

    @Override
    public void setAddressError() {
        //editTextAddress.setError(getString(R.string.msg_address_error));
    }

    @Override
    public void setNumberError() {
        editTextNumber.setError(getString(R.string.msg_condo_number_error));
    }

    @Override
    public void setCityError() {
        editTextCity.setError(getString(R.string.msg_city_error));
    }

    @Override
    public void setZipCodeError() {
        editTextZipCode.setError(getString(R.string.msg_zipcode_error));
    }

    @Override
    public void setStateError() {
        //editTextState.setError(getString(R.string.msg_state_error));
    }

    @Override
    public void navigateToLogin() {
        //startActivity(new Intent(this, Login.class));
        Toast.makeText(this, getString(R.string.msg_registration_success), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setServerError() {
        Toast.makeText(this, getString(R.string.msg_server_error), Toast.LENGTH_SHORT).show();
    }

    public String getUriZipCode(){
        return "https://viacep.com.br/ws/"+editTextZipCode.getText()+"/json/";
    }


    public void lockFields( boolean isToLock ){
        util.lockFields( isToLock );
    }

    public void setDataViews(Address address){
        setField( R.id.editTextStreet, address.getLogradouro() );
        setField( R.id.editTextComplement, address.getComplemento() );
        setField( R.id.editTextNeighbor, address.getBairro() );
        setField( R.id.editTextCity, address.getLocalidade() );
        setSpinner( R.id.sp_state, R.array.states, address.getUf() );
    }

    private void setField( int id, String data ){
        ((EditText) findViewById(id)).setText( data );
    }

    private void setSpinner( int id, int arrayId, String data ){
        String[] itens = getResources().getStringArray(arrayId);

        for( int i = 0; i < itens.length; i++ ){

            if( itens[i].endsWith( "("+data+")" ) ){
                ((Spinner) findViewById(id)).setSelection( i );
                return;
            }
        }
        ((Spinner) findViewById(id)).setSelection( 0 );
    }
}
