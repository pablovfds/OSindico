package br.com.edu.ufcg.osindico.data.models.ServerResponse;


import java.io.Serializable;

import br.com.edu.ufcg.osindico.data.models.Contact;

public class DwellerResponse implements Serializable {

    private Long id;
    private String name;
    private String email;
    private boolean ativo;
    private Contact contact;

    public DwellerResponse() {
    }

    public DwellerResponse(String nome, String email) {
        this.name = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
