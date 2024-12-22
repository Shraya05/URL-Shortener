package com.hub.url_shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
@Service
public class URLShortener_Service {
    @Autowired
    private URLMappingRepo repository;  // Ensure that repository is injected

    private static final String DOMAIN = "https://short.ly/";

    public String shortenURL(String originalUrl) {
        try {
            // Check if the URL already exists in the database
            Optional<URLMapping> existingMapping = repository.findByOriginalUrl(originalUrl);
            if (existingMapping.isPresent()) {
                return DOMAIN + existingMapping.get().getHash(); // Return the existing short URL if found
            }

            // Generate MD5 hash for the original URL
            String hash = generateMD5Hash(originalUrl).substring(0, 6);  // Use the first 6 characters of the MD5 hash

            // Save the new URL mapping to the database
            URLMapping urlMapping = new URLMapping();
            urlMapping.setHash(hash);
            urlMapping.setOriginalUrl(originalUrl);
            repository.save(urlMapping);

            return DOMAIN + hash;  // Return the newly created short URL
        } catch (Exception e) {
            // Log the exception (you can use a logger here)
            throw new RuntimeException("Failed to shorten URL", e);  // Rethrow as runtime exception for easier debugging
        }
    }

    public String getOriginalURL(String hash) {
        try {
            // Fetch the original URL based on the hash
            Optional<URLMapping> mapping = repository.findByHash(hash);
            return mapping.map(URLMapping::getOriginalUrl).orElse(null);  // Return the original URL or null if not found
        } catch (Exception e) {
            // Log the exception (you can use a logger here)
            throw new RuntimeException("Failed to retrieve original URL", e);  // Rethrow as runtime exception for easier debugging
        }
    }

    // Method to generate MD5 hash
    private String generateMD5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));  // Convert byte to hexadecimal
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating MD5 hash", e);
        }
    }
}
