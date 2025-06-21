package scot.oskar.shortly.util;

import java.security.SecureRandom;
import org.springframework.stereotype.Component;

@Component
public class ShortCodeGenerator {

  private static final SecureRandom SECURE_RANDOM = new SecureRandom();

  private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  private static final int DEFAULT_LENGTH = 12;
  private static final int MIN_LENGTH = 4;
  private static final int MAX_LENGTH = 50;

  public String generateShortCode(int length) {
    validateLength(length);

    StringBuilder sb = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      int randomIndex = SECURE_RANDOM.nextInt(CHARACTERS.length());
      sb.append(CHARACTERS.charAt(randomIndex));
    }

    return sb.toString();
  }

  public String generateShortCode() {
    return generateShortCode(DEFAULT_LENGTH);
  }

  private void validateLength(int length) {
    if (length < MIN_LENGTH || length > MAX_LENGTH) {
      throw new IllegalArgumentException(
          String.format("Length must be between %d and %d, got: %d",
              MIN_LENGTH, MAX_LENGTH, length)
      );
    }
  }

}
