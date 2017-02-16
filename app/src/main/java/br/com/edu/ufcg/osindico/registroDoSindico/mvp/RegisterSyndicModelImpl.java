package br.com.edu.ufcg.osindico.registroDoSindico.mvp;


import br.com.edu.ufcg.osindico.registroDoSindico.model.CondoModel;
import br.com.edu.ufcg.osindico.registroDoSindico.model.SyndicDetails;

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
