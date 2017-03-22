package br.com.edu.ufcg.osindico.allow_visitors.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.VisitorDetails;

public interface AllowVisitorsContract {

    interface View {
        void setSuccess();
        void setServerError(String message);
    }

    interface Presenter {
        void validateData(String token, String name, String cpf);
    }

    interface Model {
        void sendVisitorsList(String token, List<VisitorDetails> visitorDetails);
    }
}
