package controller;

import view.Request;
import view.Response;

public class GotoUnParkController implements BaseController {
    private Request request;
    private Response response;

    public GotoUnParkController(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String process() {
        response.send("请输入小票编号：");
        return "main/1/2";
    }
}
