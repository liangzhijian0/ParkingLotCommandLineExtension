package com.thoughtworks.tdd.controller;

import com.thoughtworks.controller.UnparkController;
import com.thoughtworks.model.Car;
import com.thoughtworks.model.ParkingBoy;
import com.thoughtworks.model.Receipt;
import com.thoughtworks.view.Request;
import com.thoughtworks.view.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

class UnparkControllerTest {


    private Request request;
    private Response response;
    private UnparkController unparkController;
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy = mock(ParkingBoy.class);
        unparkController = new UnparkController(request, response, parkingBoy);
    }

    @Test
    public void should_print_pick_up_success_info_when_recipt_id_is_exist_in_parking_lot() {

        //given
        Receipt receipt = new Receipt();
        when(request.getParameter()).thenReturn(receipt.getReceiptId());
        Car car = new Car("abc123");
        when(parkingBoy.unPark(receipt)).thenReturn(car);

        //when
        String forwardPath = unparkController.process();

        //then
        verify(response).send("车已取出，您的车牌号是: " + car.getCarId());
        assertThat(forwardPath, is("forward:main"));
    }


//    @Test
//    public void should_sent_warong_msg_when_recipt_ID_is_not_exist_in_parking_lot() throws WrongReceiptException {
//
//        //given
//        when(request.getParameter()).thenReturn(UUID.randomUUID().toString());
//
////        when(parkingBoy.unPark(any())).thenReturn(null);
//
//        //when
//        try{
//            String forwardPath = unparkController.process();
//        }catch (WrongReceiptException e){
//
//        }
//
//
//        //then
////        verify(response).send("非法小票，无法取出车，请查证后再输");
////        assertThat(forwardPath, is("forward:main"));
//    }
}
