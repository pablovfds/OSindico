package br.com.edu.ufcg.osindico.cadastroMorador.mvp;

import android.view.View;

import br.com.edu.ufcg.osindico.data.models.Contato;

/**
 * Created by emanoel on 17/02/17.
 */

public interface ICadastroMoradorMVP {

    interface Model {

        interface OnCadastroMoradorListener {
            void onNomeError();

            void onEmailError();

            void onSenhaError();

            void onConfirmarSenhaError();

            void onTelefoneError();

            void onSuccess(String mensagem);

            void onServerError(String serverError);
        }

        void cadastrarMorador(String nome, Contato contato, String email, String senha, String confirmarSenha, Long idCondominio, OnCadastroMoradorListener listener);
    }

    interface Presenter {

        void validarMorador(String nome, Contato contato, String email, String senha, String confirmarSenha, Long idCondominio);

        void setView(ICadastroMoradorMVP.View view);

        void onDestroy();
    }

    interface View {

        void setNomeError();

        void setTelefoneError();

        void setEmailError();

        void setSenhaError();

        void setConfirmarSenhaError();

        void onSuccess(String mensagem);

        void setServerError(String serverError);

    }
}
