package com.example.wifioutagetracker.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "wifi_outage")
public class WifiOutage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "duration_seconds")
    private Long durationSeconds;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "target_host")
    private String targetHost;

    @Column(name = "is_resolved")
    private Boolean isResolved = false;

    public void resolve() {
        this.endTime = LocalDateTime.now();
        this.isResolved = true;
        if (this.startTime != null) {
            this.durationSeconds = Duration.between(this.startTime, this.endTime).getSeconds();
        }
    }
}
