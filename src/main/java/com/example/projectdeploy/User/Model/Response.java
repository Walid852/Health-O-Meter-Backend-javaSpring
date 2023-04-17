package com.example.projectdeploy.User.Model;

public class Response {
    boolean status;
    String message;
    LoginResponse data;

    public Response(boolean status, String message, LoginResponse data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}


