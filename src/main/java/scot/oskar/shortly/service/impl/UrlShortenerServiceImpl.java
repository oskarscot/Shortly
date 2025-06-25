package scot.oskar.shortly.service.impl;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import scot.oskar.shortly.dto.ShortenUrlRequest;
import scot.oskar.shortly.dto.ShortenUrlResponse;
import scot.oskar.shortly.exception.ResourceNotFoundException;
import scot.oskar.shortly.model.ShortUrl;
import scot.oskar.shortly.repository.ShortUrlRepository;
import scot.oskar.shortly.service.UrlShortenerService;
import scot.oskar.shortly.util.ShortCodeGenerator;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

  private final ShortUrlRepository urlRepository;
  private final ShortCodeGenerator codeGenerator;

  public UrlShortenerServiceImpl(ShortUrlRepository urlRepository, ShortCodeGenerator codeGenerator) {
    this.urlRepository = urlRepository;
    this.codeGenerator = codeGenerator;
  }

  @Override
  public ShortenUrlResponse createShortUrl(@NonNull ShortenUrlRequest request) {
    final String shortCode = codeGenerator.generateShortCode();
    final ShortUrl shortUrl = ShortUrl.builder()
        .shortCode(shortCode)
        .redirectUrl(request.getUrl())
        .build();

    urlRepository.save(shortUrl);

    return ShortenUrlResponse.builder()
        .id(shortUrl.getId())
        .shortCode(shortCode)
        .redirectUrl(request.getUrl())
        .build();
  }

  @Override
  public ShortenUrlResponse retrieveById(Long id) {
    final ShortUrl shortUrl = urlRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("url", "id", id));

    return ShortenUrlResponse.fromShortUrl(shortUrl);
  }
}
