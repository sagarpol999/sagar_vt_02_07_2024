package com.sagar_vt_02_07_2024.URL.Shortner.controller;

import com.sagar_vt_02_07_2024.URL.Shortner.entity.Url;
import com.sagar_vt_02_07_2024.URL.Shortner.service.UrlService;
import com.sagar_vt_02_07_2024.URL.Shortner.utils.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<ApiResponse> shortenUrl(@RequestBody UrlRequest urlRequest) {
        try {
            Url shortenedUrl = urlService.shortenUrl(urlRequest);
            return ResponseEntity.ok(new ApiResponse(true, "URL shortened successfully", shortenedUrl));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Failed to shorten URL", null));
        }
    }


    @PostMapping("/update")
    public boolean updateUrl(@RequestBody UrlUpdateRequest request) {
        return urlService.updateUrl(request.getShortUrl(), request.getDestinationUrl());
    }

    @GetMapping("/{shortUrl}")
    public void redirectToFullUrl(HttpServletResponse response, @PathVariable String shortUrl) {
        urlService.getOriginalUrl(shortUrl).ifPresentOrElse(url -> {
            try {
                response.sendRedirect(url.getOriginalUrl());
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not redirect to the full URL", e);
            }
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found");
        });
    }

    @PostMapping("/update_expiry")
    public boolean updateExpiry(@RequestBody ExpiryUpdateRequest request) {
        return urlService.updateExpiry(request.getShortUrl(), request.getDaysToAdd());
    }}
