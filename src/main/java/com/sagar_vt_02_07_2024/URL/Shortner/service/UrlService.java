package com.sagar_vt_02_07_2024.URL.Shortner.service;
import com.sagar_vt_02_07_2024.URL.Shortner.controller.UrlRequest;
import com.sagar_vt_02_07_2024.URL.Shortner.entity.Url;
import com.sagar_vt_02_07_2024.URL.Shortner.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    public Url shortenUrl(UrlRequest urlRequest) {
        String shortUrl = generateShortUrl();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt = now.plusMonths(10);

        Url url = new Url();
        url.setShortUrl(shortUrl);

        if (urlRequest.getOriginalUrl() != null && !urlRequest.getOriginalUrl().isEmpty()) {
            url.setOriginalUrl(urlRequest.getOriginalUrl());
        } else {
            url.setOriginalUrl(urlRequest.getDestinationUrl());
        }

        url.setCreatedAt(now);
        url.setExpiresAt(expiresAt);
        url.setLastUpdatedAt(now);

        return urlRepository.save(url);
    }


    public Optional<Url> getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl)
                .filter(url -> url.getExpiresAt().isAfter(LocalDateTime.now()));
    }

    public boolean updateUrl(String shortUrl, String newOriginalUrl) {
        return urlRepository.findByShortUrl(shortUrl).map(url -> {
            url.setOriginalUrl(newOriginalUrl);
            url.setLastUpdatedAt(LocalDateTime.now());
            urlRepository.save(url);
            return true;
        }).orElse(false);
    }

    public boolean updateExpiry(String shortUrl, int daysToAdd) {
        return urlRepository.findByShortUrl(shortUrl).map(url -> {
            url.setExpiresAt(url.getExpiresAt().plusDays(daysToAdd));
            url.setLastUpdatedAt(LocalDateTime.now());
            urlRepository.save(url);
            return true;
        }).orElse(false);
    }

    private String generateShortUrl() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
