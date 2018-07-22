import controller.*;
import model.ParkingBoy;
import model.ParkingLot;
import view.Cli;
import view.Request;
import view.Response;
import java.util.Arrays;
import java.util.List;


public class main {
    public  static void main(String[] args){
        String initRootPath = "main";
        Request request = new Request();
        Cli cli = new Cli();

        Router router = initRouter(initRootPath, request);
        router.launch();
        while (true) {
            String command = cli.read();
            request.setParameter(command);
            router.processRequest(request);
        }
    }

    private static Router initRouter (String currentPage, Request request) {
        ParkingLot parkingLot1 = new ParkingLot("2","lian",2);
        ParkingLot parkingLot2 = new ParkingLot("32","ddd",4);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1,parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Response response = new Response();

        Router router = new Router(currentPage);

        router.registerRouter("main", new MainController(request,response));
        router.registerRouter("main/*",new ErrorController(request,response));
        router.registerRouter("main/",new MainController(request,response));
        router.registerRouter("main/1",new ServerMainController(request,response));
        router.registerRouter("main/1/1",new GotoParkController(request,response,parkingBoy));
        router.registerRouter("main/1/1/*",new ParkController(request,response,parkingBoy));
        router.registerRouter("main/1/2",new GotoUnParkController(request,response));
        router.registerRouter("main/1/2/*", new UnparkController(request,response,parkingBoy));
        router.registerRouter("main/2",new ManageMainController(request,response));
        router.registerRouter("main/2/1",new ParkingLotsInfoController(request,response,parkingBoy));
        router.registerRouter("main/2/2",new GotoAddParkinglotController(request,response));
        router.registerRouter("main/2/2/*",new AddParkinglotController(request,response,parkingBoy));
        router.registerRouter("main/2/3",new GotoDeleteParkinglotController(request,response));
        router.registerRouter("main/2/3/*",new DeleteParkingLotController(request,response,parkingBoy));
        return router;
    }
}
