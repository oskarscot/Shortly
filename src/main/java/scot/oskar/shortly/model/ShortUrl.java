package scot.oskar.shortly.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "urls")
@Entity
public class ShortUrl {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String shortCode;
  private String redirectUrl;
}
