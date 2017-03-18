package br.com.edu.ufcg.osindico.base;

interface BasePresenter {
    void setView(BaseView view);

    void onDestroy();
}
