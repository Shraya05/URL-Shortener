package com.hub.url_shortener;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface URLMappingRepo extends JpaRepository<URLMapping,Long> {
    Optional<URLMapping> findByHash(String hash);
    Optional<URLMapping> findByOriginalUrl(String originalUrl);
}
