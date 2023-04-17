package com.example.projectdeploy.User.Model;

public class Response {
    public boolean status;
    public String message;
    public LoginResponse data;

    public Response(boolean status, String message, LoginResponse data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public Response(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}


