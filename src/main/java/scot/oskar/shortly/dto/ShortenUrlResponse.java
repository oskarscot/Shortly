package scot.oskar.shortly.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShortenUrlResponse {

  private Long id;
  private String shortCode;
  private String redirectUrl;

}
