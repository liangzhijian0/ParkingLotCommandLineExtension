package com.thoughtworks.tdd.controller;

import com.thoughtworks.controller.ParkingLotsInfoController;
import com.thoughtworks.model.ParkingBoy;
import com.thoughtworks.view.Request;
import com.thoughtworks.view.Response;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class ParkingLotsInfoControllerTest {
    private Request request;
    private Response response;
    private ParkingLotsInfoController parkingLotsInfoController;
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy = mock(ParkingBoy.class);
        parkingLotsInfoController = new ParkingLotsInfoController(request, response, parkingBoy);
    }

//    @Test
//    public void should_park_successfully(){
//        //given
//        when(parkingBoy.getParkingLotList()).thenReturn(new ArrayList<ParkingLot>());
//        when(parkingLotsInfoController.formateInfos(parkingBoy.getParkingLotList())).thenReturn("22");
//
//        //when
//        String forwardPath = parkingLotsInfoController.process();
//
//        //then
//        verify(response).send("22");
//        assertThat(forwardPath, is("forward:main"));
//
//    }
}
