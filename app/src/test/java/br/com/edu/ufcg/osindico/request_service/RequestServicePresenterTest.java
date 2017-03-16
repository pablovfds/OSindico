package br.com.edu.ufcg.osindico.request_service;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by emanoel on 16/03/17.
 */

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
