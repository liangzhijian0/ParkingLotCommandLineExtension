package controller;

import model.ParkingBoy;
import model.ParkingLot;
import view.Request;
import view.Response;

import java.util.List;

public class ParkingLotsInfoController implements BaseController {
    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;
    private int parkCarNum;

    public ParkingLotsInfoController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        response.send(formateInfos(parkingBoy.getParkingLotList()));
        return "forward:main";
    }

    private String formateInfos(List<ParkingLot> parkingLotList) {
        StringBuffer result = new StringBuffer();
        result.append("|停车场ID|名称|车位|已停车辆|剩余车位|\n");
        for(int i=0;i<parkingLotList.size();i++){
            String id = parkingLotList.get(i).getId();
            String name = parkingLotList.get(i).getName();
            int size = parkingLotList.get(i).getSize();
            int parkingSpace = parkingLotList.get(i).getReceiptCar().size();
            int remainSpace = size - parkingSpace;
            result.append("|"+ id +"|"+ name +"|"+ size +"|"+ parkingSpace +"|"+ remainSpace +"|\n");
        }

        return result.toString();
    }
}
