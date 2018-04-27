package org.softuni.laboratory.core.config;

import org.softuni.laboratory.core.common.JwtAuthenticationFilterEmployee;
import org.softuni.laboratory.core.common.JwtAuthorizationFilter;
import org.softuni.laboratory.employee.services.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final EmployeeService employeeService;


    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfiguration(EmployeeService employeeService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeService = employeeService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/analyzes/**").permitAll()
                .antMatchers("/patient/register", "/occurrence/create").access("hasRole('ROLE_REGISTRAR') or hasRole('ROLE_ADMIN')")
                .antMatchers("/analyses/**", "/occurrence/create").access("hasRole('ROLE_REGISTRAR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE')")
                .antMatchers("/employee/register").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .addFilter(new JwtAuthenticationFilterEmployee(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.employeeService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.employeeService)
                .passwordEncoder(this.bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
