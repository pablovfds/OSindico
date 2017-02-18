package br.com.edu.ufcg.osindico.registerSyndic.mvp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import br.com.edu.ufcg.osindico.data.models.SyndicDetails;
import br.com.edu.ufcg.osindico.data.models.SyndicServerRequest;
import br.com.edu.ufcg.osindico.data.models.SyndicServerResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
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
        if (TextUtils.isEmpty(name)){
            listener.onNameError();
            error = true;
        }

        if (TextUtils.isEmpty(email)){
            listener.onEmailError();
            error = true;
        }

        if (TextUtils.isEmpty(password)){
            listener.onPasswordError();
            error = true;
        }

        if (TextUtils.isEmpty(confirmPassword)){
            listener.onConfirmPasswordError();
            error = true;
        }

        if (TextUtils.isEmpty(phone)){
            listener.onPhoneError();
            error = true;
        }

        if (!error){

            SyndicDetails syndicDetails = new SyndicDetails(name, email, password, phone);

            SyndicServerRequest request = new SyndicServerRequest();
            request.setSyndicDetails(syndicDetails);
            Call<SyndicServerResponse> mService = mSyndicService.getSyndicApi().registerSyndic(request);

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

            listener.onSuccess();
        }
    }
}
