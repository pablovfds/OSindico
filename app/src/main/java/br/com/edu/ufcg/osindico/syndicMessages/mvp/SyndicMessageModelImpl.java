package br.com.edu.ufcg.osindico.syndicMessages.mvp;

import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class SyndicMessageModelImpl implements SyndicMessageContract.Model {

    @Override
    public void sendMessage(String message, SyndicService service, OnSendMessageListener listener) {
        if (message.isEmpty()){
            listener.onMessageError();
        } else {
            // TODO: 26/02/2017 Implementação do envio da mensagem/notificação
        }
    }
}
