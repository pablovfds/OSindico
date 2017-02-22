package br.com.edu.ufcg.osindico.cadastroMorador.mvp;

import com.google.gson.Gson;

import java.io.IOException;

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

            DadosMorador dadosMorador = new DadosMorador(nome, email, senha, contato, idCondominio);

            Call<MoradorServerResponse> mService = service.getMoradorApi().cadastraMorador(dadosMorador);

            mService.enqueue(new Callback<MoradorServerResponse>() {
                @Override
                public void onResponse(Call<MoradorServerResponse> call, Response<MoradorServerResponse> response) {

                    if (response.isSuccessful()) {
                        listener.onSuccess(response.body().getMessage());
                    } else {
                        Gson gson = new Gson();
                        MoradorServerResponse serverResponse = null;
                        try {
                            serverResponse = gson.fromJson(response.errorBody().string(), MoradorServerResponse.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (serverResponse != null)
                            listener.onServerError(serverResponse.getMessage());
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
