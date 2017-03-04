package br.com.edu.ufcg.osindico.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Lucio on 04/03/2017.
 */

public class RuleDetails implements Serializable {


    @SerializedName("regraSyndic")
    private String regraSyndic;

    public RuleDetails(String regraSyndic) {

        this.regraSyndic = regraSyndic;
    }

    public String getRegraSyndic() {
        return regraSyndic;
    }

    public void setRegraSyndic(String regraSyndic) {

        this.regraSyndic = regraSyndic;
    }

}
