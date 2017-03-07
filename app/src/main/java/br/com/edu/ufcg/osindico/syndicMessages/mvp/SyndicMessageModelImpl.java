package br.com.edu.ufcg.osindico.syndicMessages.mvp;

import java.io.IOException;
import java.lang.annotation.Annotation;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class SyndicMessageModelImpl implements SyndicMessageContract.Model {

    @Override
    public void sendMessage(String message, String token , final SyndicService service, final OnSendMessageListener listener) {
        if (message == null || message.isEmpty() || message.length() < 5){
            listener.onMessageError();
        } else {

            Call<MessageResponse> mService = service.getSyndicApi().sendMessage(token, message);

            mService.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    if (response.isSuccessful()) {
                        listener.onSuccess();
                    } else {
                        Converter<ResponseBody, MessageResponse> converter
                                = service.getRetrofit().responseBodyConverter(
                                MessageResponse.class, new Annotation[0]);
                        MessageResponse errorResponse;
                        try {
                            errorResponse = converter.convert(response.errorBody());
                            listener.onServerError(errorResponse.getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {
                    call.cancel();
                    listener.onServerError(t.getMessage());
                }
            });
        }
    }
}
