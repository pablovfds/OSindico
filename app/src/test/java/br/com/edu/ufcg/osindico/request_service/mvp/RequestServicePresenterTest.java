package br.com.edu.ufcg.osindico.request_service.mvp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.edu.ufcg.osindico.base.BaseListener;
import br.com.edu.ufcg.osindico.data.models.ServiceRequest;
import br.com.edu.ufcg.osindico.data.services.DwellerService;
import br.com.edu.ufcg.osindico.request_service.ui.RequestServiceActivity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RequestServicePresenterTest {

    private RequestServiceContract.Presenter presenter;
    private DwellerService service;
    private RequestServiceContract.View view;
    private RequestServiceModelImpl model;
    private final String TOKEN = "df651re89fv1er89v1c8er";
    private final String TITLE = "Service Title";
    private final String DESCRIPTION = "Service Description";

    @Before
    public void setUp() {
        service = mock(DwellerService.class);
        view = mock(RequestServiceActivity.class);
        model = mock(RequestServiceModelImpl.class);
        presenter = new RequestServicePresenterImpl(service, view);
        presenter.setModel(model);
    }

    @Test
    public void sendServiceRequestWithValidData() {
        presenter.validateService(TOKEN, TITLE, DESCRIPTION);

        verify(model).sendRequest(eq(TOKEN), any(ServiceRequest.class), (BaseListener) eq(presenter));
    }

    @Test
    public void sendServiceRequestWithTokenError() {
        presenter.validateService("", TITLE, DESCRIPTION);

        verify(view).showTokenError();
    }

    @Test
    public void sendServiceRequestWithTitleError() {
        presenter.validateService(TOKEN, "", DESCRIPTION);

        verify(view).showTitleError();
    }

    @Test
    public void sendServiceRequestWithDescriptionError() {
        presenter.validateService(TOKEN, TITLE, "");

        verify(view).showDescriptionError();
    }

    @Test
    public void callOnSuccessView() {
        ((BaseListener) presenter).onSuccess();

        verify(view).setSuccess();
    }

    @Test
    public void callOnServerErrorView() {
        String errorMessage = "Error message";
        ((BaseListener) presenter).onServerError(errorMessage);

        verify(view).setServerError(eq(errorMessage));
    }

    @Test
    public void changeView() {
        RequestServiceContract.View newView = mock(RequestServiceActivity.class);

        presenter.setView(newView);

        Assert.assertEquals(presenter.getView(), newView);
    }

}
