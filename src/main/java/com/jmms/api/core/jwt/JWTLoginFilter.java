package com.jmms.api.core.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.jmms.api.domain.jwt.AccountCredentials;
import com.jmms.api.domain.jwt.LoginInfo;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{

    private TokenAuthenticationService tokenAuthenticationService;

    public JWTLoginFilter(String url, AuthenticationManager authenticationManager)
    {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
        tokenAuthenticationService = new TokenAuthenticationService();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws AuthenticationException, IOException, ServletException {
        try {
            AccountCredentials credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), AccountCredentials.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
            return getAuthenticationManager().authenticate(token);
        }catch (AuthenticationException e) {
            throw e;
        } catch (Exception e){
            throw new BadCredentialsException("Problem in the credentials", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
            throws IOException, ServletException{
        String username = authentication.getName();
        Integer expirationInDays = getExpirationInDays(request);
        LoginInfo loginInfo = tokenAuthenticationService.generateToken(username, expirationInDays);
        LoginInfoRequestWrapper requestWrapper = new LoginInfoRequestWrapper(request, loginInfo);

        chain.doFilter(requestWrapper, response);
    }

    private Integer getExpirationInDays(HttpServletRequest request){
        Integer expirationInDays = null;
        try {
            String expiration = request.getParameter("expiration");
            if (expiration != null) {
                expirationInDays = Integer.parseInt(expiration);
            }
        } catch (Exception e) {
            logger.error("Invalid expiration value", e);
        }
        return expirationInDays;
    }

}