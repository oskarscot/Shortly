package scot.oskar.shortly.service;

import org.springframework.lang.NonNull;
import scot.oskar.shortly.dto.ShortenUrlRequest;
import scot.oskar.shortly.dto.ShortenUrlResponse;

public interface UrlShortenerService {

  ShortenUrlResponse createShortUrl(@NonNull ShortenUrlRequest request);

  ShortenUrlResponse retrieveById(Long id);
}
