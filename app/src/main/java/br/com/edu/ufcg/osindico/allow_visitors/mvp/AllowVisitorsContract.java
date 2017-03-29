package br.com.edu.ufcg.osindico.allow_visitors.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.VisitorDetails;

public interface AllowVisitorsContract {

    interface View {
        void setSuccess();
        void setServerError(String message);
        void setNameError();
        void setCpfError();
    }

    interface Presenter {
        void sendVisitorsList(List<VisitorDetails> visitors);
        void checkVisitor(String name, String cpf);
    }

    interface Model {
        void registerVisitorsList(String token, List<VisitorDetails> visitorDetails);
    }
}
