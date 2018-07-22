package com.thoughtworks.tdd.controller;

import com.thoughtworks.controller.GotoParkController;
import com.thoughtworks.model.ParkingBoy;
import com.thoughtworks.view.Request;
import com.thoughtworks.view.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

class GotoParkControllerTest {


    private Request request;
    private Response response;
    private GotoParkController gotoParkController;
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy = mock(ParkingBoy.class);
        gotoParkController = new GotoParkController(request, response, parkingBoy);
    }

    @Test
    public void should_send_correct_page_info_when_parking_lot_not_full() {

        //given
        when(parkingBoy.isFull()).thenReturn(false);

        //when
        String forwardPath = gotoParkController.process();

        //then
        verify(response).send("请输入车牌号:");
        assertThat(forwardPath, not(containsString("forward:")));
    }

    @Test
    public void should_send_wrong_info_and_forward_main_page_when_parking_lot_is_full() {
        //given
        when(parkingBoy.isFull()).thenReturn(true);

        //when
        String forwardPath = gotoParkController.process();

        //then
        verify(response).send("车已停满，请晚点再来");
        assertThat(forwardPath, containsString("forward:"));
    }
}