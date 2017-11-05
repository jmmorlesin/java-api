package com.jmms.api.core;

import com.jmms.api.utils.Routes;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.jmms.api.core.jwt.JWTAuthenticationFilter;
import com.jmms.api.core.jwt.JWTLoginFilter;

@Configuration
@EnableGlobalAuthentication
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // disable caching
        http.headers().cacheControl();

        http.csrf().disable(); // disable csrf for our requests.

        http
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .antMatchers(HttpMethod.GET,Routes.PATH_HEALTH).permitAll()
                    .antMatchers(HttpMethod.POST,Routes.PATH_LOGIN).permitAll()
                    .anyRequest().authenticated()
                    .and()
                // We filter the api/login requests
                .addFilterBefore(new JWTLoginFilter(Routes.PATH_LOGIN, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)

                ;
    }

}
