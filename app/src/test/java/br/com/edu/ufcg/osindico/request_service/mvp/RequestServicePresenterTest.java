package br.com.edu.ufcg.osindico.request_service.mvp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class RequestServicePresenterTest {

    private RequestServicePresenterImpl presenter;

    @Before
    public void setUp() {
        presenter = new RequestServicePresenterImpl();
    }

    @Test
    public void test() {
        Assert.assertEquals(1, 1);
    }

}
