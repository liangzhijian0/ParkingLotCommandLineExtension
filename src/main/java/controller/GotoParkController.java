package controller;

import model.ParkingBoy;
import view.Request;
import view.Response;

public class GotoParkController implements BaseController{
    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;
    public GotoParkController(Request request, Response response,ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {
        if(parkingBoy.isFull()){
            response.send("车已停满，请晚点再来");
            return "forward:main";
        }
        else{
            response.send("请输入车牌号：");
            return "main/1/1";
        }
    }
}
