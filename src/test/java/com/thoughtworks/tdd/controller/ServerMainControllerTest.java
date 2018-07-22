package com.thoughtworks.tdd.controller;

import com.thoughtworks.controller.ServerMainController;
import com.thoughtworks.view.Request;
import com.thoughtworks.view.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ServerMainControllerTest {

    private Request request;
    private Response response;
    private ServerMainController serverMainController;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        serverMainController = new ServerMainController(request, response);
    }

    @Test
    public void should_print_main_page(){
        //given
        //when
        serverMainController.process();

        //then
        verify(response).send("1. 停车\n" +
                "2. 取车 \n" +
                "请输入您要进行的操作：");
    }
}