package com.matchday.api.security;

import com.matchday.api.security.jwt.JwtEntryPoint;
import com.matchday.api.security.jwt.JwtFilter;
import com.matchday.api.security.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // Enable cors
        http.cors();

        // Disable CSRF (cross-site request spoofing)
        http.csrf().disable();

        // Spring Security will not create or use any session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeRequests()//
                .antMatchers("/auth/signin").permitAll()//
                .antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")//
                .antMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")//
                .antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")//
                .anyRequest().authenticated();

        // If a user tries to access a resource without having sufficient permissions
        http.exceptionHandling().accessDeniedPage("/login");
        http.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);

        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        // Optional, if you want to test the API from a browser
        http.httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Allow swagger to be accessed without authentication
        web.ignoring()//
                // .antMatchers("/v3/api-docs/**")// Use when we do not customize the route
                // .antMatchers("/swagger-ui/**")// Use when we do not customize the route
                .antMatchers("/docs/**")// Use when we do not customize the route
                .antMatchers("/swagger/**")// Use when we do not customize the route
                .antMatchers("/swagger-ui/**")// Use when we do not customize the route
                .antMatchers("/swagger-ui.html")// Always use
        ;
    }

    // Enable cors globally
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

}
