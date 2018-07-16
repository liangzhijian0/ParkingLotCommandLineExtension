import controller.MainController;
import controller.ParkingController;
import controller.ParkingManageController;
import model.ParkingBoy;
import model.ParkingLot;
import view.Cli;
import view.Request;
import view.Response;

import java.util.ArrayList;
import java.util.List;


public class main {
    public  static void main(String[] args){
        Request request = new Request() ;
        Response response = new Response();

        List<ParkingLot> parkingLotlist = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot("001","西南停车场",3);
        ParkingLot parkingLot2 = new ParkingLot("002","东北停车场",5);
        parkingLotlist.add(parkingLot1);
        parkingLotlist.add(parkingLot2);

        ParkingBoy parkingBoy =  new ParkingBoy(parkingLotlist);

        Cli cli = new Cli();
        MainController mainController = new MainController(request,response,parkingBoy);
        ParkingController parkingController = new ParkingController(request,response,parkingBoy);
        ParkingManageController parkingManageController = new ParkingManageController(request,response,parkingBoy);
        Router router = new Router(mainController,parkingController,parkingManageController);
        mainController.printMainInterface();

        while (true) {
            String command = cli.read();
            request.setParameter(command);
            router.handleRequest(request);
        }
    }
}
