package br.com.edu.ufcg.osindico.registerSyndic.mvp;


import br.com.edu.ufcg.osindico.registerSyndic.model.CondoModel;
import br.com.edu.ufcg.osindico.data.models.SyndicDetails;

public class RegisterSyndicModelImpl implements RegisterSyndicMVPContract.RegisterSyndicModel{

    @Override
    public void register(String username, String password, OnRegisterSyndicFinishedListener listener) {

    }

    @Override
    public void validateCredentialsSyndic(SyndicDetails syndicModel, OnValidateSyndicFinishedListener listener) {

    }

    @Override
    public void validateCredentialsCondo(CondoModel condoModel, OnValidateCondoFinishedListener listener) {

    }

}
