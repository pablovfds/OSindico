package br.com.edu.ufcg.osindico.registerSyndic.mvp;

import br.com.edu.ufcg.osindico.Utils.FormValidate;
import br.com.edu.ufcg.osindico.data.models.Contact;
import br.com.edu.ufcg.osindico.data.models.SyndicDetails;
import br.com.edu.ufcg.osindico.data.models.SyndicServerResponse;
import br.com.edu.ufcg.osindico.data.Services.SyndicService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterSyndicModelImpl implements RegisterSyndicContract.Model{

    private SyndicService mSyndicService;

    public RegisterSyndicModelImpl(SyndicService mSyndicService) {
        this.mSyndicService = mSyndicService;
    }


    @Override
    public void registerSyndic(String name, String email, String password,
                               String confirmPassword, String phone, final OnRegisterSyndicListener listener) {
        boolean error = false;

        if (!FormValidate.isValidName(name)){
            listener.onNameError();
            error = true;
        }

        if (!FormValidate.isValidEmail(email)){
            listener.onEmailError();
            error = true;
        }

        if (!FormValidate.isValidPassword(password)){
            listener.onPasswordError();
            error = true;
        }

        if (!FormValidate.isValidConfirmPassword(password, confirmPassword)){
            listener.onConfirmPasswordError();
            error = true;
        }

        if (!FormValidate.isValidPhone(phone)){
            listener.onPhoneError();
            error = true;
        }

        if (!error){
            Contact contact = new Contact(phone);
            SyndicDetails syndicDetails = new SyndicDetails(name, email, password, contact);

            Call<SyndicServerResponse> mService = mSyndicService.getSyndicApi().registerSyndic(syndicDetails);

            mService.enqueue(new Callback<SyndicServerResponse>() {
                @Override
                public void onResponse(Call<SyndicServerResponse> call, Response<SyndicServerResponse> response) {

                    listener.onSuccess();
                }

                @Override
                public void onFailure(Call<SyndicServerResponse> call, Throwable t) {
                    call.cancel();
                    listener.onServerError();
                }
            });

        }
    }
}
