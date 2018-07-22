package com.thoughtworks.tdd.controller;

import com.thoughtworks.controller.GotoUnParkController;
import com.thoughtworks.view.Request;
import com.thoughtworks.view.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GotoUnParkControllerTest {


    private Request request;
    private Response response;
    private GotoUnParkController gotoUnParkController;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        gotoUnParkController = new GotoUnParkController(request, response);
    }

    @Test
    public void should_print_unpark_page() {
        //given
        //when
        String forwardPath = gotoUnParkController.process();

        //then
        verify(response).send("请输入小票编号：");
        assertThat(forwardPath, not(containsString("forward:")));
    }

}