package org.softuni.laboratory.core.config;

import org.softuni.laboratory.core.common.JwtAuthenticationFilterEmployee;
import org.softuni.laboratory.core.common.JwtAuthenticationFilterPatient;
import org.softuni.laboratory.core.common.JwtAuthorizationFilter;
import org.softuni.laboratory.employee.services.EmployeeService;
import org.softuni.laboratory.patient.services.PatientService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final PatientService patientService;

    private final EmployeeService employeeService;


    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfiguration(PatientService patientService, EmployeeService employeeService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.patientService = patientService;
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
                .antMatchers("/patient/register").hasAuthority("ROLE_REGISTRAR")
                .antMatchers("/analyses/**").hasAuthority("ROLE_EMPLOYEE")
                .antMatchers("/employee/register").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/employee/login").permitAll()
                .loginProcessingUrl("/employee/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/error")
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/patient/login").permitAll()
                .loginProcessingUrl("/patient/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/error")
                .and()
                .addFilter(new JwtAuthenticationFilterPatient(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.patientService))
                .addFilter(new JwtAuthenticationFilterEmployee(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.employeeService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.employeeService)
                .passwordEncoder(this.bCryptPasswordEncoder)
                .and()
                .userDetailsService(this.patientService)
                .passwordEncoder(this.bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}