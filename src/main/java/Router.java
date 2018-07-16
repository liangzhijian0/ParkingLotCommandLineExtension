import controller.MainController;
import controller.ParkingController;
import controller.ParkingManageController;
import view.Request;
import view.Response;

public class Router {
    private String currentPage = "main";
    private MainController mainController;
    private ParkingController parkingController;
    private ParkingManageController parkingManageController;
    private Response response = new Response();


    public Router(MainController mainController,ParkingController parkingController, ParkingManageController parkingManageController){
        this.mainController = mainController;
        this.parkingController = parkingController;
        this.parkingManageController = parkingManageController;
    }


    public void handleRequest(Request request) {
        switch (currentPage){
            case "main" :
                currentPage = mainController.mainPage(request);
                break;
            case "parkingService" :{
                currentPage = parkingController.parkingServicePage(request);
                break;
            }
            case "parkingService_park" :{
                parkingController.parkingService_parkPage(request);
                currentPage = "main";
                break;
            }
            case "parkingService_unpark" :{
                parkingController.parkingService_unparkPage(request);
                currentPage = "main";
                break;
            }
            case "parkingManage" :{
                currentPage = parkingManageController.parkingManagePage(request);
                break;
            }
//            case "parkingManage_check" :{
//                controller.unparkPage(request);
//                currentPage = "main";
//                break;
//            }
            case "parkingManage_add" :{
                parkingManageController.parkingManage_addPage(request);
                currentPage = "main";
                break;
            }
            case "parkingManage_delete" :{
                parkingManageController.parkingManage_deletePage(request);
                currentPage = "main";
                break;
            }
        }
    }




}
