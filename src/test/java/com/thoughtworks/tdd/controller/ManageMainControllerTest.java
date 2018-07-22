package com.thoughtworks.tdd.controller;

import com.thoughtworks.controller.ManageMainController;
import com.thoughtworks.view.Request;
import com.thoughtworks.view.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ManageMainControllerTest {

    private Request request;
    private Response response;
    private ManageMainController manageMainController;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        manageMainController = new ManageMainController(request, response);
    }

    @Test
    public void should_print_main_page(){
        //given
        //when
        manageMainController.process();

        //then
        verify(response).send("1.查看停车场详情\n" +
                "2.添加停车场\n" +
                "3.删除停车场");
    }
}