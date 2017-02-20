package br.com.edu.ufcg.osindico.data.models;

/**
 * Created by emanoel on 19/02/17.
 */
public class DadosMorador {
    private String nome;
    private Contato contato;
    private String email;
    private String senha;
    private Long idCondominio;

    public DadosMorador(String nome, Contato contato, String email, String senha, Long idCondominio) {
        this.nome = nome;
        this.contato = contato;
        this.email = email;
        this.senha = senha;
        this.idCondominio = idCondominio;
    }

    public Long getIdCondominio() {
        return idCondominio;
    }

    public void setIdCondominio(Long idCondominio) {
        this.idCondominio = idCondominio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Contato getContato() {
        return contato;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
