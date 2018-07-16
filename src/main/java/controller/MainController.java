package controller;

import model.ParkingBoy;
import view.Request;
import view.Response;

public class MainController {
    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;


    public MainController(Request request, Response response,ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    public String mainPage(Request request){
        String currentPage = "main";
        switch (request.getParameter()){
            case "1" :
                currentPage = "parkingService";
                response.send("1. 停车\n" + "2. 取车 \n" + "请输入您要进行的操作：");
                break;
            case "2" :
                currentPage = "parkingManage";
                response.send("1.查看停车场详情\n" + "2.添加停车场\n" + "3.删除停车场");
                break;
            default:
                response.send("非法指令，请查证后再输");
                printMainInterface();
        }
        return currentPage;
    }

    public void printMainInterface(){
        response.send("1. 停车服务\n" + "2. 停车场管理 \n" + "请输入您要进入的页面：");
    }
}
