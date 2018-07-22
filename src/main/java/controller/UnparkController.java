package controller;

import model.Car;
import model.ParkingBoy;
import model.Receipt;
import view.Request;
import view.Response;
import Exception.WrongReceiptException;

public class UnparkController implements BaseController {
    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;

    public UnparkController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {
        try {
            Receipt receipt = new Receipt(request.getParameter());
            Car car = parkingBoy.unPark(receipt);
            response.send("车已取出，您的车牌号是：" + car.getCarId());
        }catch (WrongReceiptException e){
            response.send("非法小票，无法取出车，请查证后再输");
        }
        return "forward:main";
    }
}
