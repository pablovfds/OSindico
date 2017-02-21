package br.com.edu.ufcg.osindico.data.models;

/**
 * Created by emanoel on 20/02/17.
 */

public class Contato {

    private String phoneNumber;

    public Contato(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
