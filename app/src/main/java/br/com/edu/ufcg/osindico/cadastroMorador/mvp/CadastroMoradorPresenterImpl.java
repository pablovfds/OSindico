package br.com.edu.ufcg.osindico.cadastroMorador.mvp;

import android.view.View;

/**
 * Created by emanoel on 17/02/17.
 */

public class CadastroMoradorPresenterImpl implements ICadastroMoradorMVP.CadastroMoradorPresenter, ICadastroMoradorMVP.CadastroMoradorModel.OnCadastroMoradorListener {

    ICadastroMoradorMVP.CadastroMoradorView view;
    ICadastroMoradorMVP.CadastroMoradorModel model;

    public CadastroMoradorPresenterImpl() {

    }

    @Override
    public void validarMorador(String nome, String telefone, String email, String senha, String confirmarSenha) {

    }

    @Override
    public void setView(View view) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onNomeError() {

    }

    @Override
    public void onEmailError() {

    }

    @Override
    public void onSenhaError() {

    }

    @Override
    public void onConfirmarSenhaError() {

    }

    @Override
    public void onTelefoneError() {

    }
}
