package br.com.edu.ufcg.osindico.data.models.ServerResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by emanoel on 04/03/17.
 */

public class RuleResponse {

    private Long id;
    @SerializedName("ruleDetails")
    private String rule;

    public RuleResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
