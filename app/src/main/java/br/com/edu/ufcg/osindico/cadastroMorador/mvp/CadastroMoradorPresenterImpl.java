package br.com.edu.ufcg.osindico.cadastroMorador.mvp;

import br.com.edu.ufcg.osindico.data.models.Contato;
import br.com.edu.ufcg.osindico.data.services.MoradorService;

/**
 * Created by emanoel on 17/02/17.
 */

public class CadastroMoradorPresenterImpl implements ICadastroMoradorMVP.Presenter, ICadastroMoradorMVP.Model.OnCadastroMoradorListener {

    private ICadastroMoradorMVP.View view;
    private ICadastroMoradorMVP.Model model;

    public CadastroMoradorPresenterImpl(MoradorService service, ICadastroMoradorMVP.View view) {
        this.model = new CadastroMoradorModelImpl(service);
        this.view = view;
    }

    @Override
    public void validarMorador(String nome, Contato contato, String email, String senha, String confirmarSenha, Long idCondominio) {
        if (view != null) {
            this.model.cadastrarMorador(nome, contato, email, senha, confirmarSenha, idCondominio, this);
        }
    }

    @Override
    public void setView(ICadastroMoradorMVP.View view) {
        this.view = view;
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onNomeError() {
        if (view != null) {
            view.setNomeError();
        }
    }

    @Override
    public void onEmailError() {
        if (view != null) {
            view.setEmailError();
        }
    }

    @Override
    public void onSenhaError() {
        if (view != null) {
            view.setSenhaError();
        }
    }

    @Override
    public void onConfirmarSenhaError() {
        if (view != null) {
            view.setConfirmarSenhaError();
        }
    }

    @Override
    public void onTelefoneError() {
        if (view != null) {
            view.setTelefoneError();
        }
    }

    @Override
    public void onSuccess(String mensagem) {
        if (view != null) {
            view.onSuccess(mensagem);
        }
    }

    @Override
    public void onServerError(String serverError) {
        if (view != null) {
            view.setServerError(serverError);
        }
    }
}
