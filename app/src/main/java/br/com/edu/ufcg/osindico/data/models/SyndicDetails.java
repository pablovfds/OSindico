package br.com.edu.ufcg.osindico.data.models;

public class SyndicDetails {

    private String name;
    private String email;
    private String password;
    private Contact contact;

    public SyndicDetails(String name, String email, String password, Contact contact) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = password;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
