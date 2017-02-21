package br.com.edu.ufcg.osindico.registerSyndic.mvp;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;

import br.com.edu.ufcg.osindico.Utils.FormValidate;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.data.models.Contact;
import br.com.edu.ufcg.osindico.data.models.SyndicDetails;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.SyndicServerResponse;
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
            Contact contact = new Contact(phone);
            SyndicDetails syndicDetails = new SyndicDetails(name, email, password, contact);

            final Call<SyndicServerResponse> mService = mSyndicService.getSyndicApi().registerSyndic(syndicDetails);


            mService.enqueue(new Callback<SyndicServerResponse>() {
                @Override
                public void onResponse(Call<SyndicServerResponse> call, Response<SyndicServerResponse> response) {
                    SyndicServerResponse serverResponse = response.body();


                    if (response.isSuccessful()){
                        listener.onSuccess(serverResponse.getId());
                    } else {
                        try {
                            Log.d("error", response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        Gson gson = new Gson();
//
//                        SyndicServerResponse message = null;
//                        try {
//                            message = gson.fromJson(response.errorBody().
//                                    string(),SyndicServerResponse.class);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }


                        listener.onServerError("Já existe um usuario utilizando esse email");

//                        Converter<ResponseBody, SyndicServerResponse> converter
//                                = mSyndicService.getRetrofit().responseBodyConverter(
//                                SyndicServerResponse.class, new Annotation[0]);
//                        SyndicServerResponse errorResponse = null;
//                        try {
//                            errorResponse = converter.convert(response.errorBody());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                        listener.onServerError(errorResponse.getSpringException().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<SyndicServerResponse> call, Throwable t) {
                    call.cancel();
                    listener.onServerError("Erro ao tentar se conectar com servidor");
                }
            });
        }
    }
}
