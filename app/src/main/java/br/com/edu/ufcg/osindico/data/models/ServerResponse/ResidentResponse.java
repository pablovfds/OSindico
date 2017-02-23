package br.com.edu.ufcg.osindico.data.models.ServerResponse;


import br.com.edu.ufcg.osindico.data.models.Contact;

public class ResidentResponse {
    // TODO: 22/02/17 Campos: ativo, contato, email id, nome

    private Long id;
    private String name;
    private String email;
    private boolean ativo;
    private Contact contact;

    public ResidentResponse() {
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
