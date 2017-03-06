package br.com.edu.ufcg.osindico.registerSyndic.mvp;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;
import java.lang.annotation.Annotation;

import br.com.edu.ufcg.osindico.Utils.FormValidate;
import br.com.edu.ufcg.osindico.data.models.Contact;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.SyndicServerResponse;
import br.com.edu.ufcg.osindico.data.models.SyndicDetails;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
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
            String fcmTokn = FirebaseInstanceId.getInstance().getToken();

            Contact contact = new Contact(phone);
            SyndicDetails syndicDetails = new SyndicDetails(name, email, password, contact, fcmTokn);

            final Call<SyndicServerResponse> mService = mSyndicService.getSyndicApi().registerSyndic(syndicDetails);


            mService.enqueue(new Callback<SyndicServerResponse>() {
                @Override
                public void onResponse(Call<SyndicServerResponse> call, Response<SyndicServerResponse> response) {
                    SyndicServerResponse serverResponse = response.body();

                    if (response.isSuccessful() && response.body() != null){
                        listener.onSuccess(serverResponse.getId());
                    } else {
                        Converter<ResponseBody, SyndicServerResponse> converter
                                = mSyndicService.getRetrofit().responseBodyConverter(
                                SyndicServerResponse.class, new Annotation[0]);
                        SyndicServerResponse errorResponse = null;
                        try {
                            errorResponse = converter.convert(response.errorBody());
                            listener.onServerError(errorResponse.getSpringException().getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<SyndicServerResponse> call, Throwable t) {
                    call.cancel();
                    listener.onServerError(t.getMessage());
                }
            });
        }
    }
}
