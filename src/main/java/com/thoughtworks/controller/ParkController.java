package com.thoughtworks.controller;


import com.thoughtworks.model.Car;
import com.thoughtworks.model.ParkingBoy;
import com.thoughtworks.model.Receipt;
import com.thoughtworks.view.Request;
import com.thoughtworks.view.Response;

public class ParkController implements BaseController {
    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;

    public ParkController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {
        Receipt receipt = parkingBoy.park(new Car(request.getParameter()));
        response.send("停车成功，您的小票是：\n" + receipt.getReceiptId());
        return "forward:main";
    }
}
