package com.sagar_vt_02_07_2024.URL.Shortner.utils;

import com.sagar_vt_02_07_2024.URL.Shortner.entity.Url;

public class ApiResponse {

    private boolean success;
    private String message;
    private Url data;

    public ApiResponse(boolean success, String message, Url data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Url getData() {
        return data;
    }

    public void setData(Url data) {
        this.data = data;
    }
}
