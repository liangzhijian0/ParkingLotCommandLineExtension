package com.thoughtworks.controller;

import com.thoughtworks.view.Request;
import com.thoughtworks.view.Response;

public class MainController implements BaseController{
    private Request request;
    private Response response;

    public MainController(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String process() {
        response.send("1.停车服务\n" +
                "2.停车场管理\n" +
                "请输入您要进入的页面：");
        return "";
    }
}
