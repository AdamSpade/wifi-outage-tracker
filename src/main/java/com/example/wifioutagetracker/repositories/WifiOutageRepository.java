package com.example.wifioutagetracker.repositories;

import com.example.wifioutagetracker.models.WifiOutage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WifiOutageRepository extends JpaRepository<WifiOutage, Long> {

    Optional<WifiOutage> findFirstByIsResolvedFalseOrderByStartTimeDesc();

    List<WifiOutage> findByStartTimeBetweenOrderByStartTimeDesc(final LocalDateTime start, final LocalDateTime end);

    @Query("SELECT COUNT(w) FROM WifiOutage w WHERE w.startTime >= :since")
    long countOutagesSince(final LocalDateTime since);

    @Query("SELECT AVG(w.durationSeconds) FROM WifiOutage w WHERE w.isResolved = true AND w.startTime >= :since ")
    Double getAverageDurationSince(final LocalDateTime since);

    @Query("SELECT w FROM WifiOutage w WHERE w.isResolved = true ORDER BY w.durationSeconds DESC")
    List<WifiOutage> findLongestOutages();
}
