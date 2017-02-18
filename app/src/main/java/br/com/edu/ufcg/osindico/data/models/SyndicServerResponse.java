package br.com.edu.ufcg.osindico.data.models;

public class SyndicServerResponse {
    private String id;
    private String message;
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Status: " + status + " Message: " + message + "id:" + id;
    }
}
