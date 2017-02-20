package br.com.edu.ufcg.osindico.registerCondo.ui;

import android.text.Editable;
import android.text.TextWatcher;

import br.com.edu.ufcg.osindico.registerCondo.mvp.RegisterCondoContract;

public class ZipCodeListener implements TextWatcher {
    private RegisterCondoContract.Presenter presenter;

    public ZipCodeListener(RegisterCondoContract.Presenter presenter ){
        this.presenter = presenter;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void afterTextChanged(Editable editable) {
        String zipCode = editable.toString();

        if( editable.length() == 8 ){
            presenter.getAddressByZipCode(zipCode);
        }
    }
}
