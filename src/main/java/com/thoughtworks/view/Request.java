package com.thoughtworks.view;

public class Request {
    private String parameter;

    public Request(String parameter) {
        this.parameter = parameter;
    }

    public Request() {

    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
