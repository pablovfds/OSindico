package br.com.edu.ufcg.osindico.cadastroMorador.mvp;

import br.com.edu.ufcg.osindico.Utils.FormValidate;
import br.com.edu.ufcg.osindico.data.models.Contato;
import br.com.edu.ufcg.osindico.data.models.DadosMorador;
import br.com.edu.ufcg.osindico.data.models.MoradorServerResponse;
import br.com.edu.ufcg.osindico.data.services.MoradorService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by emanoel on 17/02/17.
 */

public class CadastroMoradorModelImpl implements ICadastroMoradorMVP.Model {

    private MoradorService service;

    public CadastroMoradorModelImpl(MoradorService service) {
        this.service = service;
    }

    @Override
    public void cadastrarMorador(String nome, Contato contato, String email, String senha, String confirmarSenha, Long idCondominio, final OnCadastroMoradorListener listener) {
        boolean error = false;

        if (!FormValidate.isNomeValido(nome)) {
            listener.onNomeError();
            error = true;
        }

        if (!FormValidate.isTelefoneValido(contato.getTelefone())) {
            listener.onTelefoneError();
            error = true;
        }

        if (!FormValidate.isEmailValido(email)) {
            listener.onEmailError();
            error = true;
        }

        if (!FormValidate.isSenhaValida(senha)) {
            listener.onSenhaError();
            error = true;
        }

        if (!FormValidate.isConfirmarSenhaValida(senha, confirmarSenha)) {
            listener.onConfirmarSenhaError();
            error = true;
        }

        if (!error) {

            DadosMorador dadosMorador = new DadosMorador(nome, contato, email, senha, idCondominio);

            Call<MoradorServerResponse> mService = service.getMoradorApi().cadastraMorador(dadosMorador);

            mService.enqueue(new Callback<MoradorServerResponse>() {
                @Override
                public void onResponse(Call<MoradorServerResponse> call, Response<MoradorServerResponse> response) {
                    MoradorServerResponse serverResponse = response.body();

                    if (response.isSuccessful()) {
                        listener.onSuccess(serverResponse.getMensagem());
                    } else {
                        listener.onServerError(serverResponse.getMensagem());
                    }
                }

                @Override
                public void onFailure(Call<MoradorServerResponse> call, Throwable t) {
                    call.cancel();
                    listener.onServerError("Erro ao tentar se conectar com servidor.");
                }
            });

        }
    }
}
