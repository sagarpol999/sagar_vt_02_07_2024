package com.sagar_vt_02_07_2024.URL.Shortner.controller;

import lombok.Data;

@Data
public class ExpiryUpdateRequest {
    private String shortUrl;
    private int daysToAdd;
}
