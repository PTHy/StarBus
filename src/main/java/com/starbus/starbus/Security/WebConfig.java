package com.starbus.starbus.Security;

import com.starbus.starbus.Security.Interceptors.OpenApiTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    private final String RIDE_BELL_END_POINT = "/api/routes/ridebell";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
//        registry.addInterceptor(createOpenApiTokenInterceptor()).addPathPatterns("/api/cities/**");
        registry.addInterceptor(createOpenApiTokenInterceptor()).addPathPatterns(RIDE_BELL_END_POINT);
    }

    @Bean
    public OpenApiTokenInterceptor createOpenApiTokenInterceptor() {return new OpenApiTokenInterceptor();}
}
