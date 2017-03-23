package br.com.edu.ufcg.osindico.data.models;

public class VisitorDetails {

    private String name;
    private String cpf;

    public VisitorDetails(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
