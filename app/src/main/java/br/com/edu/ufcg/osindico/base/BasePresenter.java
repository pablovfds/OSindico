package br.com.edu.ufcg.osindico.base;

public interface BasePresenter {
    void setView(BaseView view);

    void onDestroy();
}
