package org.softuni.laboratory.core.config;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.softuni.laboratory.core.entities.exception.ErrorMessage;
import org.softuni.laboratory.core.entities.exception.SuccessMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationBeanConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Gson gson() { return new Gson(); }

    @Bean
    public ErrorMessage errorMessage() { return  new ErrorMessage(); }

    @Bean
    public SuccessMessage successMessage() { return new SuccessMessage(); }
}
