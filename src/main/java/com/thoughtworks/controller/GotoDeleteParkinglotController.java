package com.thoughtworks.controller;

import com.thoughtworks.view.Request;
import com.thoughtworks.view.Response;

public class GotoDeleteParkinglotController implements BaseController {
    private Request request;
    private Response response;

    public GotoDeleteParkinglotController(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String process() {
        response.send("请输入需要删除的被管理停车场ID:");
        return "main/2/3";
    }
}
