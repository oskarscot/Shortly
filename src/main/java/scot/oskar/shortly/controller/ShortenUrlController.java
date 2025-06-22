package scot.oskar.shortly.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scot.oskar.shortly.dto.ShortenUrlRequest;
import scot.oskar.shortly.dto.ShortenUrlResponse;
import scot.oskar.shortly.service.UrlShortenerService;

@RestController
@RequestMapping("api/v1/urls")
public class ShortenUrlController {

  private final UrlShortenerService shortenerService;

  public ShortenUrlController(UrlShortenerService shortenerService) {
    this.shortenerService = shortenerService;
  }

  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody ShortenUrlRequest request) {
    final ShortenUrlResponse response = shortenerService.createShortUrl(request);

    return ResponseEntity.ok(response);
  }
}
