package br.com.edu.ufcg.osindico.registerDweller.mvp;

import com.google.gson.Gson;

import java.io.IOException;

import br.com.edu.ufcg.osindico.Utils.FormValidate;
import br.com.edu.ufcg.osindico.data.models.Contact;
import br.com.edu.ufcg.osindico.data.models.DwellerDetails;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerServerResponse;
import br.com.edu.ufcg.osindico.data.services.DwellerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterDwellerModelImpl implements RegisterDwellerContract.Model {

    private DwellerService service;

    public RegisterDwellerModelImpl(DwellerService service) {
        this.service = service;
    }

    @Override
    public void registerDweller(String name, Contact contact, String email, String password, String confirmPassword, Long condominiumId, final OnRegisterDwellerListener listener) {
        boolean error = false;

        if (!FormValidate.isValidName(name)) {
            listener.onNameError();
            error = true;
        }

        if (!FormValidate.isValidPhone(contact.getPhoneNumber())) {
            listener.onPhoneNumberError();
            error = true;
        }

        if (!FormValidate.isValidEmail(email)) {
            listener.onEmailError();
            error = true;
        }

        if (!FormValidate.isValidPassword(password)) {
            listener.onPasswordError();
            error = true;
        }

        if (!FormValidate.isValidConfirmPassword(password, confirmPassword)) {
            listener.onConfirmPasswordError();
            error = true;
        }

        if (!error) {

            DwellerDetails dwellerDetails = new DwellerDetails(name, email, password, contact, condominiumId);

            Call<DwellerServerResponse> mService = service.getDwellerApi().registerDweller(dwellerDetails);

            mService.enqueue(new Callback<DwellerServerResponse>() {
                @Override
                public void onResponse(Call<DwellerServerResponse> call, Response<DwellerServerResponse> response) {

                    if (response.isSuccessful()) {
                        listener.onSuccess(response.body().getMessage());
                    } else {
                        Gson gson = new Gson();
                        DwellerServerResponse serverResponse = null;
                        try {
                            serverResponse = gson.fromJson(response.errorBody().string(), DwellerServerResponse.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (serverResponse != null)
                            listener.onServerError(serverResponse.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<DwellerServerResponse> call, Throwable t) {
                    call.cancel();
                    listener.onServerError("Erro ao tentar se conectar com servidor.");
                }
            });

        }
    }
}