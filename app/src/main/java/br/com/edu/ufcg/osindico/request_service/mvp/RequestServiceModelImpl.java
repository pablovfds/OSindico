package br.com.edu.ufcg.osindico.request_service.mvp;

import br.com.edu.ufcg.osindico.base.BaseListener;
import br.com.edu.ufcg.osindico.data.models.ServiceRequest;
import br.com.edu.ufcg.osindico.data.services.DwellerService;

public class RequestServiceModelImpl implements RequestServiceContract.Model {

    private DwellerService dwellerService;

    public RequestServiceModelImpl(DwellerService service) {
        this.dwellerService = service;
    }

    @Override
    public void sendRequest(String token, ServiceRequest request, BaseListener listener) {

    }
/*
    @Override
    public boolean validateData(String token, String title, String type, String description, OnRequestServiceListener listener) {
        boolean error = false;

        if (token == null || token.trim().isEmpty()){
            listener.onTokenError();
            error = true;
        } else if (title == null || title.trim().isEmpty() || title.length() < 5){
            listener.onTitleError();
            error = true;
        } else if (type == null || type.trim().isEmpty()){
            listener.onTypeError();
            error = true;
        } else if (description == null || description.trim().isEmpty() || title.length() < 10){
            listener.onTypeError();
            error = true;
        }

        return error;
    }
*/
}
