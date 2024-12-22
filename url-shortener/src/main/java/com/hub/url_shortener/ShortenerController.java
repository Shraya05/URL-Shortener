package com.hub.url_shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/v1")
public class ShortenerController {
    @Autowired
    private URLShortener_Service service;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenURL(@RequestParam String originalUrl) {
        String shortUrl = service.shortenURL(originalUrl);
        return new ResponseEntity<>(shortUrl, HttpStatus.OK);
    }

    @GetMapping("/{hash}")
    public RedirectView redirectToOriginalURL(@PathVariable String hash) {
        String originalUrl = service.getOriginalURL(hash);
        if (originalUrl == null) {
            throw new RuntimeException("URL not found!");
        }
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(originalUrl);
        return redirectView;
    }
}
