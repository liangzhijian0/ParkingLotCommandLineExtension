package com.thoughtworks.tdd;

import com.thoughtworks.Router;
import com.thoughtworks.controller.*;
import com.thoughtworks.view.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


public class RouterTest {

    MainController mainController;
    ErrorController errorController;
    ServerMainController serverMainController;
    GotoParkController gotoParkController;
    ParkController parkController;
    GotoUnParkController gotoUnParkController;
    UnparkController unparkController;
    ManageMainController manageMainController;
    ParkingLotsInfoController parkingLotsInfoController;
    GotoAddParkinglotController gotoAddParkinglotController;
    AddParkinglotController addParkinglotController;
    GotoDeleteParkinglotController gotoDeleteParkinglotController;
    DeleteParkingLotController deleteParkingLotController;

    @BeforeEach
    void setUp() {
        mainController = mock(MainController.class);
        errorController = mock(ErrorController.class);
        serverMainController = mock(ServerMainController.class);
        gotoParkController = mock(GotoParkController.class);
        parkController = mock(ParkController.class);
        gotoUnParkController = mock(GotoUnParkController.class);
        unparkController = mock(UnparkController.class);
        manageMainController = mock(ManageMainController.class);
        parkingLotsInfoController = mock(ParkingLotsInfoController.class);
        gotoAddParkinglotController = mock(GotoAddParkinglotController.class);
        addParkinglotController = mock(AddParkinglotController.class);
        gotoDeleteParkinglotController = mock(GotoDeleteParkinglotController.class);
        deleteParkingLotController = mock(DeleteParkingLotController.class);

    }


    @Test
    void should_register_router_and_lunch_main_page_when_init_main_to_root_path() {
        //given
        Router router = new Router("main");
        router.registerRouter("main", mainController);

        //when
        router.launch();

        //then
        verify(mainController, times(1)).process();
    }

    @Test
    void should_process_error_router_when_process_4_command_after_router_launch() {
        //given
        Router router = new Router("main");
        router.registerRouter("main", mainController);
        router.registerRouter("main/*", errorController);
        router.launch();

        //when
        Request request = new Request("4");
        router.processRequest(request);

        //then
        verify(errorController, times(1)).process();
    }

    @Test
    void should_process_main_slash_1_router_when_process_1_command_after_router_launch() {
        //given
        Router router = new Router("main");
        router.registerRouter("main", mainController);
        router.registerRouter("main/1", serverMainController);
        router.launch();

        //when
        Request request = new Request("1");
        router.processRequest(request);

        //then
        verify(serverMainController, times(1)).process();
    }


    @Test
    void should_process_main_slash_1_slash_1_router_when_process_1_command_and_current_router_is_main_slash_1() {
        //given
        Router router = new Router("main/1");
        router.registerRouter("main/1", serverMainController);
        router.registerRouter("main/1/1", gotoParkController);
        router.launch();

        //when
        Request request = new Request("1");
        router.processRequest(request);

        //then
        verify(gotoParkController, times(1)).process();
    }

    @Test
    void should_process_forward_main_router_when_process_1_command_and_process_goto_park_controller_return_forward_main_and_current_router_is_main_slash_1() {
        //given
        Router router = new Router("main/1");
        router.registerRouter("main", mainController);
        router.registerRouter("main/1", serverMainController);
        router.registerRouter("main/1/1", gotoParkController);
        router.launch();
        when(gotoParkController.process()).thenReturn("forward:main");

        //when
        Request request = new Request("1");
        router.processRequest(request);

        //then
        verify(gotoParkController, times(1)).process();
        verify(mainController, times(1)).process();
    }

    @Test
    void should_process_park_controller_when_process_car_id_and_current_route_is_main_slash_1_slash_1() {
        //given
        Router router = new Router("main/1/1");
        router.registerRouter("main/1/1", gotoParkController);
        router.registerRouter("main/1/1/*", parkController);
        router.launch();

        //when
        Request request = new Request("abc123");
        router.processRequest(request);

        //then
        verify(parkController, times(1)).process();
    }

    @Test
    void should_process_main_slash_1_slash_2_router_when_process_2_command_and_current_router_is_main_slash_1() {
        //given
        Router router = new Router("main/1");
        router.registerRouter("main/1", serverMainController);
        router.registerRouter("main/1/2", gotoUnParkController);
        router.launch();

        //when
        Request request = new Request("2");
        router.processRequest(request);

        //then
        verify(gotoUnParkController, times(1)).process();
    }

    @Test
    void should_process_unPark_controller_when_process_receipt_id_and_current_route_is_main_slash_1_slash_2() {
        //given
        Router router = new Router("main/1/2");
        router.registerRouter("main/1/2", gotoUnParkController);
        router.registerRouter("main/1/2/*", unparkController);
        router.launch();

        //when
        Request request = new Request("4e6b310d-77b0-4192-96c6-7fe354db59b3");
        router.processRequest(request);

        //then
        verify(unparkController, times(1)).process();
    }

    @Test
    void should_process_main_slash_2_router_when_process_2_command_after_router_launch() {
        //given
        Router router = new Router("main");
        router.registerRouter("main", mainController);
        router.registerRouter("main/2", manageMainController);
        router.launch();

        //when
        Request request = new Request("2");
        router.processRequest(request);

        //then
        verify(manageMainController, times(1)).process();
    }

    @Test
    void should_process_main_slash_2_slash_1_router_when_process_1_command_and_current_router_is_main_slash_2() {
        //given
        Router router = new Router("main/2");
        router.registerRouter("main/2", manageMainController);
        router.registerRouter("main/2/1", parkingLotsInfoController);
        router.launch();

        //when
        Request request = new Request("1");
        router.processRequest(request);

        //then
        verify(parkingLotsInfoController, times(1)).process();
    }

    @Test
    void should_process_main_slash_2_slash_2_router_when_process_2_command_and_current_router_is_main_slash_2() {
        //given
        Router router = new Router("main/2");
        router.registerRouter("main/2", manageMainController);
        router.registerRouter("main/2/2", gotoAddParkinglotController);
        router.launch();

        //when
        Request request = new Request("2");
        router.processRequest(request);

        //then
        verify(gotoAddParkinglotController, times(1)).process();
    }

    @Test
    void should_process_add_parking_lot_controller_when_process_name_and_size_and_current_route_is_main_slash_2_slash_2() {
        //given
        Router router = new Router("main/2/2");
        router.registerRouter("main/2/2", gotoAddParkinglotController);
        router.registerRouter("main/2/2/*", addParkinglotController);
        router.launch();

        //when
        Request request = new Request("西南停车场，3");
        router.processRequest(request);

        //then
        verify(addParkinglotController, times(1)).process();
    }

    @Test
    void should_process_main_slash_2_slash_3_router_when_process_3_command_and_current_router_is_main_slash_2() {
        //given
        Router router = new Router("main/2");
        router.registerRouter("main/2", manageMainController);
        router.registerRouter("main/2/3", gotoDeleteParkinglotController);
        router.launch();

        //when
        Request request = new Request("3");
        router.processRequest(request);

        //then
        verify(gotoDeleteParkinglotController, times(1)).process();
    }

    @Test
    void should_process_delete_parking_lot_controller_when_process_id_and_current_route_is_main_slash_2_slash_3() {
        //given
        Router router = new Router("main/2/3");
        router.registerRouter("main/2/3", gotoDeleteParkinglotController);
        router.registerRouter("main/2/3/*", deleteParkingLotController);
        router.launch();

        //when
        Request request = new Request("15348");
        router.processRequest(request);

        //then
        verify(deleteParkingLotController, times(1)).process();
    }
}
