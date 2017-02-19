package br.com.edu.ufcg.osindico.cadastroMorador.mvp;

import android.view.View;

/**
 * Created by emanoel on 17/02/17.
 */

public interface ICadastroMoradorMVP {

    interface CadastroMoradorModel {

        interface OnCadastroMoradorListener {
            void onNomeError();

            void onEmailError();

            void onSenhaError();

            void onConfirmarSenhaError();

            void onTelefoneError();
        }

        void cadastrarMorador(String nome, String telefone, String email, String senha, String confirmarSenha);
    }

    interface CadastroMoradorPresenter {

        void validarMorador(String nome, String telefone, String email, String senha, String confirmarSenha);

        void setView(View view);

        void onDestroy();
    }

    interface CadastroMoradorView {

        void setNomeError();

        void setTelefoneError();

        void setEmailError();

        void setSenhaError();

        void setConfirmarSenhaError();

        void setServerError();

    }
}
