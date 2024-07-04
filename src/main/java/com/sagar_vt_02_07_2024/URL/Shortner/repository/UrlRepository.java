package com.sagar_vt_02_07_2024.URL.Shortner.repository;

import com.sagar_vt_02_07_2024.URL.Shortner.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
        Optional<Url> findByShortUrl(String shortUrl);
        }
