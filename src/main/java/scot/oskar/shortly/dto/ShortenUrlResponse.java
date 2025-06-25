package scot.oskar.shortly.dto;

import lombok.Builder;
import lombok.Data;
import scot.oskar.shortly.model.ShortUrl;

@Data
@Builder
public class ShortenUrlResponse {

  private Long id;
  private String shortCode;
  private String redirectUrl;

  public static ShortenUrlResponse fromShortUrl(ShortUrl url) {
    return new ShortenUrlResponse(
        url.getId(),
        url.getShortCode(),
        url.getRedirectUrl()
    );
  }

}
