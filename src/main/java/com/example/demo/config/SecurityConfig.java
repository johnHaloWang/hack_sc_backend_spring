package com.example.demo.config;

import com.example.demo.security.filter.AuthenticationTokenFilter;
import com.example.demo.security.service.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    protected SecurityConfig(final TokenAuthenticationService tokenAuthenticationService) {
        super();
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Configuration
    @Order(1)                                                        
    public static class ApiStoreWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                .antMatcher("/api/store/**")
                .authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                .httpBasic()
            		.and()
            		.csrf().disable();
        }
    }    

    @Configuration
    @Order(2)                                                        
    public static class ApiProudctWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                .antMatcher("/api/product/**")
                .authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                .httpBasic()
            		.and()
            		.csrf().disable();
        }
    } 
    
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/auth").permitAll()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/user/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new AuthenticationTokenFilter(tokenAuthenticationService),
                        UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }
}
