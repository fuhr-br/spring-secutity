package api.aplication.config.security;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {

    registry.addMapping("/**")
        .allowCredentials(false)
        .allowedHeaders("*")
        .allowedMethods("GET", "POST", "HEAD", "PATCH", "PUT", "DELETE")
        .allowedOrigins("*");
  }

}
