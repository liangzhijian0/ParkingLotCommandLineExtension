package controller;

import model.Car;
import model.ParkingBoy;
import model.Receipt;
import view.Request;
import view.Response;

public class ParkingController {
    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;
    private MainController mainController;

    public ParkingController(Request request, Response response,ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
        mainController = new MainController(request,response,parkingBoy);
    }

    public Receipt park(String carId){
        Car car = new Car(carId);
        return parkingBoy.park(car);
    }

    public Car unpark(String parameter){
        return parkingBoy.unPark(new Receipt(parameter));
    }

    public boolean isFull() {
        return parkingBoy.isFull();
    }

    public void unparkPage(Request request) {
        try {
            Car car = unpark(request.getParameter());
            response.send("车已取出，您的车牌号是:"+ car.getCarId());
        } catch (RuntimeException exception) {
            response.send("非法小票，无法取出车，请查证后再输");
        }
        mainController.printMainInterface();
    }

    public void parkPage(Request request) {
        Receipt receipt = park(request.getParameter());
        response.send("停车成功，您的小票是：\n"+receipt.getReceiptId());
        mainController.printMainInterface();
    }

    public String mainPage(Request request){
        String currentPage = "main";
        switch (request.getParameter()){
            case "1" :
                if(isFull()){
                    response.send("车已停满，请晚点再来");
                    mainController.printMainInterface();
                }else{
                    currentPage = "park";
                    response.send("请输入车牌号码: ");
                }
                break;
            case "2" :
                currentPage = "unpark";
                response.send("请输入小票编号：");
                break;
            default:
                response.send("非法指令，请查证后再输");
                mainController.printMainInterface();
        }
        return currentPage;
    }


    public String parkingServicePage(Request request) {
        String currentPage = "main";
        switch (request.getParameter()){
            case "1" :
                if(isFull()){
                    response.send("车已停满，请晚点再来");
                    mainController.printMainInterface();
                }else{
                    currentPage = "parkingService_park";
                    response.send("请输入车牌号码: ");
                }
                break;
            case "2" :
                currentPage = "parkingService_unpark";
                response.send("请输入小票编号：");
                break;
            default:
                response.send("非法指令，请查证后再输");
                mainController.printMainInterface();
        }
        return currentPage;
    }

    public void parkingService_parkPage(Request request) {
        Receipt receipt = park(request.getParameter());
        response.send("停车成功，您的小票是：\n"+receipt.getReceiptId());
        mainController.printMainInterface();
    }

    public void parkingService_unparkPage(Request request) {
        try {
            Car car = unpark(request.getParameter());
            response.send("车已取出，您的车牌号是:"+ car.getCarId());
        } catch (RuntimeException exception) {
            response.send("非法小票，无法取出车，请查证后再输");
        }
        mainController.printMainInterface();
    }
}
