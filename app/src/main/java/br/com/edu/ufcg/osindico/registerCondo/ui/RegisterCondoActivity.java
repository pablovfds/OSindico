package br.com.edu.ufcg.osindico.registerCondo.ui;

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
import br.com.edu.ufcg.osindico.Utils.Util;
import br.com.edu.ufcg.osindico.data.models.Address;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.data.services.ZipCodeService;
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

    @BindView(R.id.editTextComplement) EditText editTextComplement;

    @BindView(R.id.editTextNeighbor) EditText editTextNeighbor;

    @BindView(R.id.editTextStreet) EditText editTextStreet;

    @BindView(R.id.sp_state) Spinner spStates;

    @BindView(R.id.editTextCity) EditText editTextCity;

    @BindView(R.id.register_condo_progress) ProgressBar progressBar;

    private RegisterCondoContract.Presenter presenter;

    private Long syndicId = Long.valueOf(11);
    private Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_condo);

        //syndicId = getIntent().getExtras().getLong("syndicId");

        ButterKnife.bind(this);

        util = new Util(this,
                R.id.editTextZipCode,
                R.id.editTextStreet,
                R.id.editTextComplement,
                R.id.editTextNeighbor,
                R.id.editTextCity,
                R.id.sp_state,
                R.id.editTextNumber);

        SyndicService service = new SyndicService();
        ZipCodeService zipCodeService = new ZipCodeService();

        this.presenter = new RegisterCondoPresenterImpl(service, zipCodeService, this);

        editTextZipCode.addTextChangedListener( new ZipCodeListener( this, this.presenter ) );

        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this,
                        R.array.states,
                        android.R.layout.simple_spinner_item);
        spStates.setAdapter(adapter);

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
            String name = editTextName.getText().toString();
            String phoneNumber = editTextPhone.getText().toString();
            String street = editTextStreet.getText().toString();
            String state = spStates.getSelectedItem().toString();
            Integer number = Integer.getInteger(editTextNumber.getText().toString());
            String neighbor = editTextNeighbor.getText().toString();
            String complement = editTextComplement.getText().toString();
            String zipCode = editTextZipCode.getText().toString();
            String city = editTextCity.getText().toString();

            presenter.validateCondoCredentials(name, phoneNumber, street, number, complement,
                    neighbor, city, zipCode, state, syndicId);
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
        Toast.makeText(this, getString(R.string.msg_state_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setStreetError() {
        editTextStreet.setError(getString(R.string.msg_street_error));

    }

    @Override
    public void setNeighborError() {
        editTextNeighbor.setError(getString(R.string.msg_neighborhood_error));
    }

    @Override
    public void setComplementError() {

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

    @Override
    public void setAddressDataViews(Address address) {
        setField( R.id.editTextStreet, address.getStreet() );
        setField( R.id.editTextComplement, address.getComplement() );
        setField( R.id.editTextNeighbor, address.getNeighbor() );
        setField( R.id.editTextCity, address.getCity() );
        setSpinner( R.id.sp_state, R.array.states, address.getState() );
    }

    public String getUriZipCode(){
        return "https://viacep.com.br/ws/"+editTextZipCode.getText()+"/json/";
    }

    public void lockFields( boolean isToLock ){
        util.lockFields( isToLock );
    }

    public void setDataViews(Address address){
        setField( R.id.editTextStreet, address.getStreet() );
        setField( R.id.editTextComplement, address.getComplement() );
        setField( R.id.editTextNeighbor, address.getNeighbor() );
        setField( R.id.editTextCity, address.getCity() );
        setSpinner( R.id.sp_state, R.array.states, address.getState() );
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
