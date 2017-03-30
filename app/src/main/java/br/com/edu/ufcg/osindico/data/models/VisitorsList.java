package br.com.edu.ufcg.osindico.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by emanoel on 30/03/17.
 */

public class VisitorsList {

    @SerializedName("visitDay")
    private String date;
    @SerializedName("visitors")
    private List<VisitorDetails> visitorDetailsList;

    public VisitorsList(String date, List<VisitorDetails> visitorDetailsList) {
        this.date = date;
        this.visitorDetailsList = visitorDetailsList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<VisitorDetails> getVisitorDetailsList() {
        return visitorDetailsList;
    }

    public void setVisitorDetailsList(List<VisitorDetails> visitorDetailsList) {
        this.visitorDetailsList = visitorDetailsList;
    }


}
