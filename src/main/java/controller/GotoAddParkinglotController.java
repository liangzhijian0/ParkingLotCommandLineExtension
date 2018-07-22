package controller;

import view.Request;
import view.Response;

public class GotoAddParkinglotController implements BaseController {
    private Request request;
    private Response response;

    public GotoAddParkinglotController(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String process() {
        response.send("请输入你套添加的停车场信息（格式为：名称，车位）：");
        return "main/2/2";
    }
}
