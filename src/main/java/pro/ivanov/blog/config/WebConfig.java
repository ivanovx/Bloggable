package pro.ivanov.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
