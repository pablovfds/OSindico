package br.com.edu.ufcg.osindico.base;

public interface BaseListener {
    void onSuccess();
    void onServerError(String message);
}
