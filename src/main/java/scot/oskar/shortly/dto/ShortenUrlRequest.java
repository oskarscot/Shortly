package scot.oskar.shortly.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ShortenUrlRequest {

  @NotEmpty
  private String url;

}
