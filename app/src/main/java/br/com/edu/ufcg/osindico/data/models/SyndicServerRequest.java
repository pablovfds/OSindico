package br.com.edu.ufcg.osindico.data.models;

public class SyndicServerRequest {
    private SyndicDetails syndicDetails;
    private CondoDetails condoDetails;

    public void setSyndicDetails(SyndicDetails syndicDetails) {
        this.syndicDetails = syndicDetails;
    }

    public void setCondoDetails(CondoDetails condoDetails) {
        this.condoDetails = condoDetails;
    }
}
