package br.com.edu.ufcg.osindico.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by emanoel on 19/02/17.
 */
public class DadosMorador {

    @SerializedName("name")
    private String nome;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String senha;
    @SerializedName("contact")
    private Contato contato;
    @SerializedName("condominiumId")
    private Long idCondominio;

    public DadosMorador(String nome, String email, String senha, Contato contato, Long idCondominio) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.contato = contato;
        this.idCondominio = idCondominio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Long getIdCondominio() {
        return idCondominio;
    }

    public void setIdCondominio(Long idCondominio) {
        this.idCondominio = idCondominio;
    }
}
