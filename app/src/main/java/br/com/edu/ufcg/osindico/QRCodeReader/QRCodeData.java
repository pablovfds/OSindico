package br.com.edu.ufcg.osindico.QRCodeReader;

public class QRCodeData {

    private Long id;
    private String nome;

    public QRCodeData(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
