package controller;

import model.ParkingBoy;
import model.ParkingLot;
import view.Request;
import view.Response;

import java.util.List;

public class ParkingManageController {
    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;
    private MainController mainController;

    public ParkingManageController(Request request, Response response,ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
        mainController = new MainController(request,response,parkingBoy);
    }

    public String parkingManagePage(Request request) {
        String currentPage = "main";
        switch (request.getParameter()){
            case "1" :
                checkParkingLot();
                mainController.printMainInterface();
                break;
            case "2" :
                currentPage = "parkingManage_add";
                response.send("请输入你套添加的停车场信息（格式为：名称，车位）：");
                break;
            case "3" :
                currentPage = "parkingManage_delete";
                response.send("请输入需要删除的被管理停车场ID:");
                break;
            default:
                response.send("非法指令，请查证后再输");
                mainController.printMainInterface();
        }
        return currentPage;
    }

    private void checkParkingLot() {
        StringBuffer result = new StringBuffer();
        result.append("|停车场ID|名称|车位|已停车辆|剩余车位|\n");
        List<ParkingLot> parkingLotList = parkingBoy.getParkingLotList();
        for(int i=0;i<parkingLotList.size();i++){
            String id = parkingLotList.get(i).getId();
            String name = parkingLotList.get(i).getName();
            int size = parkingLotList.get(i).getSize();
            int parkingSpace = parkingLotList.get(i).getReceiptCar().size();
            int remainSpace = size - parkingSpace;
            result.append("|"+ id +"|"+ name +"|"+ size +"|"+ parkingSpace +"|"+ remainSpace +"|\n");
        }
        mainController.printMainInterface();
        response.send(result.toString());
    }

    public void parkingManage_addPage(Request request) {
        String[] bulid = request.getParameter().split(",");
        if(bulid.length != 2){
            response.send("输入格式有误！正确格式为（名称，车位）");
        }else{
            ParkingLot parkingLot = new ParkingLot("007",bulid[0],Integer.valueOf(bulid[1]).intValue());
            parkingBoy.getParkingLotList().add(parkingLot);
            response.send("停车场添加成功！");
        }

        mainController.printMainInterface();
    }

    public void parkingManage_deletePage(Request request) {
        response.send("dddeeelete");
        mainController.printMainInterface();
    }
}
