package com.thoughtworks.tdd.controller;

import com.thoughtworks.controller.MainController;
import com.thoughtworks.view.Request;
import com.thoughtworks.view.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MainControllerTest {

    private Request request;
    private Response response;
    private MainController controller;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        controller = new MainController(request, response);
    }

    @Test
    public void should_print_main_page(){
        //given
        //when
        controller.process();

        //then
        verify(response).send("1.停车服务\n" +
                "2.停车场管理\n" +
                "请输入您要进入的页面：");
    }
}