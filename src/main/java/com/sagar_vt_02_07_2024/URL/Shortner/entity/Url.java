package com.sagar_vt_02_07_2024.URL.Shortner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 30)
    private String shortUrl;

    @Column(columnDefinition = "TEXT")
    private String originalUrl;


    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    private LocalDateTime lastUpdatedAt;

}