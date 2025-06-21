package scot.oskar.shortly.service;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import scot.oskar.shortly.ContainerConfiguration;
import scot.oskar.shortly.dto.ShortenUrlRequest;
import scot.oskar.shortly.dto.ShortenUrlResponse;
import scot.oskar.shortly.model.ShortUrl;
import scot.oskar.shortly.repository.ShortUrlRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestConfiguration(proxyBeanMethods = false)
@ImportTestcontainers(ContainerConfiguration.class)
public class UrlShortenerServiceTest {

  @Autowired
  private UrlShortenerService service;

  @Autowired
  private ShortUrlRepository repository;

  @Test
  public void createShortUrl() {
    final ShortenUrlRequest request = ShortenUrlRequest.builder()
            .url("https://google.com")
            .build();
    final ShortenUrlResponse response = service.createShortUrl(request);

    System.out.println(response);

    final Optional<ShortUrl> byShortCode = repository.findByShortCode(response.getShortCode());

    assertEquals(response.getRedirectUrl(), "https://google.com");
    assertTrue(byShortCode.isPresent());
    assertEquals(repository.count(), 1);
  }

}
