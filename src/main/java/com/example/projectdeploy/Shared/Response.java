package com.example.projectdeploy.Shared;

import com.example.projectdeploy.User.Model.LoginResponse;

import java.util.List;

public class Response<T> {
    public boolean status;
    public String message;
    public List<T> data;

    public Response(boolean status, String message, List<T> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public Response(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
