package ua.nure.andriimatiashov.kpp.example.demo;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ua.nure.andriimatiashov.kpp.example.dao.DAOConfig;

@Configuration
@EnableConfigurationProperties(DAOConfig.class)
@EnableWebMvc
@ComponentScan(basePackages = "ua.nure.andriimatiashov.kpp.example")
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/jquery/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/jquery/3.3.1-1/");

        registry.addResourceHandler("/popper/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/popper.js/1.14.1/umd/");

        registry.addResourceHandler("/bootstrap/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap/4.1.1/");
    }
}
