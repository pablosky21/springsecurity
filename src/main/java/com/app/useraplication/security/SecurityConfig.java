package com.app.useraplication.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * The Class UserLoggedConfig.
 *
 * @author everis
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    
    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()      
        .httpBasic().and()
        .authorizeRequests()
            .antMatchers("/createUser").authenticated()
            .antMatchers("/deleteUser/**").authenticated()
            .antMatchers("/getAllUser").permitAll()
            .antMatchers("/getUser/**").permitAll()         
            .antMatchers("/swagger-ui/**").permitAll()    
            .antMatchers("/swagger-ui.html").permitAll()  
            .antMatchers("/getParamCounter/**").permitAll()
            .antMatchers(AUTH_WHITELIST).permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin();
    }

}
