package com.thoughtworks.tdd.controller;

import com.thoughtworks.controller.ParkController;
import com.thoughtworks.model.Car;
import com.thoughtworks.model.ParkingBoy;
import com.thoughtworks.model.Receipt;
import com.thoughtworks.view.Request;
import com.thoughtworks.view.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ParkControllerTest {


    private Request request;
    private Response response;
    private ParkController parkController;
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy = mock(ParkingBoy.class);
        parkController = new ParkController(request, response, parkingBoy);
    }

    @Test
    public void should_park_successfully(){
        //given
        Receipt receipt = new Receipt();
        when(parkingBoy.park(any(Car.class))).thenReturn(receipt);

        //when
        String forwardPath = parkController.process();

        //then
        verify(response).send("停车成功，您的小票是：\n" +
                receipt.getReceiptId());
        assertThat(forwardPath, is("forward:main"));

    }
}