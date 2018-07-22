package com.thoughtworks.tdd.controller;

import com.thoughtworks.controller.ErrorController;
import com.thoughtworks.view.Request;
import com.thoughtworks.view.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ErrorControllerTest {

    private Request request;
    private Response response;
    private ErrorController errorController;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        errorController = new ErrorController(request, response);
    }

    @Test
    public void should_print_main_page(){
        //given
        //when
        errorController.process();

        //then
        verify(response).send("非法指令，请查证后再输入");
    }
}