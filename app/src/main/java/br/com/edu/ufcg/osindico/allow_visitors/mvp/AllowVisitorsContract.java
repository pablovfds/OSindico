package br.com.edu.ufcg.osindico.allow_visitors.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.VisitorDetails;

public interface AllowVisitorsContract {

    interface View {
        void setSuccess();
        void setServerError(String message);
        void setNameError();
        void setCpfError();
        void onFinishAddDialog(String name, String cpf);
    }

    interface Presenter {
        void sendVisitorsList(String token, String date, List<VisitorDetails> visitors);
        void checkVisitor(String name, String cpf);
        void setView(View view);
        void onSuccess();
        void onServerError(String message);
    }

    interface Model {
        void registerVisitorsList(String token, String date, List<VisitorDetails> visitorDetails, AllowVisitorsContract.Presenter listener);
    }
}
