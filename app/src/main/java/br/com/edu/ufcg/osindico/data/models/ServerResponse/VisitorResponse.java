package br.com.edu.ufcg.osindico.data.models.ServerResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.VisitorDetails;

public class VisitorResponse {

    @SerializedName("emailDweller")
    private String emailDweller;

    @SerializedName("nameDweller")
    private String nameDweller;

    @SerializedName("visitantesDTO")
    private List<VisitorDetails> visitorDetailsList;

    public VisitorResponse(String emailDweller, String nameDweller,
                           List<VisitorDetails> visitorDetailsList) {
        this.emailDweller = emailDweller;
        this.nameDweller = nameDweller;
        this.visitorDetailsList = visitorDetailsList;
    }

    public String getEmailDweller() {
        return emailDweller;
    }

    public void setEmailDweller(String emailDweller) {
        this.emailDweller = emailDweller;
    }

    public String getNameDweller() {
        return nameDweller;
    }

    public void setNameDweller(String nameDweller) {
        this.nameDweller = nameDweller;
    }

    public List<VisitorDetails> getVisitorDetailsList() {
        return visitorDetailsList;
    }

    public void setVisitorDetailsList(List<VisitorDetails> visitorDetailsList) {
        this.visitorDetailsList = visitorDetailsList;
    }
}
