package scot.oskar.shortly.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import scot.oskar.shortly.dto.ShortenUrlRequest;
import scot.oskar.shortly.dto.ShortenUrlResponse;
import scot.oskar.shortly.service.UrlShortenerService;

@WebMvcTest(ShortenUrlController.class)
public class ShortenUrlControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UrlShortenerService shortenerService;

  @Autowired
  private ObjectMapper objectMapper;


  @Test
  public void createShortUrl() throws Exception {
    String originalUrl = "https://www.example.com";
    String redirectCode = "muPNCjSuTq14";

    ShortenUrlRequest request = ShortenUrlRequest.builder()
        .url(originalUrl).build();

    ShortenUrlResponse response = ShortenUrlResponse.builder()
        .id(1L)
        .shortCode(redirectCode)
        .redirectUrl(originalUrl).build();

    when(shortenerService.createShortUrl(any(ShortenUrlRequest.class)))
        .thenReturn(response);

    // When & Then
    mockMvc.perform(post("/api/v1/urls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(response.getId()))
        .andExpect(jsonPath("$.shortCode").value(response.getShortCode()))
        .andExpect(jsonPath("$.redirectUrl").value(originalUrl));
  }

}
