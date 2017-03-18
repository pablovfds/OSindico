package br.com.edu.ufcg.osindico.data.models;

public class ServiceRequest {

    private String type;
    private String description;
    private String title;


    public ServiceRequest(String type, String description, String title) {
        this.type = type;
        this.description = description;
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
