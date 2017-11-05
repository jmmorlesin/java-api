package com.jmms.api.core.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import com.jmms.api.domain.jwt.LoginInfo;
import com.jmms.api.utils.Config;
import com.jmms.api.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import java.util.Date;


public class TokenAuthenticationService {

    private final static Logger logger = Logger.getLogger(TokenAuthenticationService.class);

    private final static String TOKEN_PREFIX = "Bearer ";

    private Config config = Config.getInstance();

    private long EXPIRATION_TIME_DAY = 1000 * 60 * 60 * 24; // 1 day


    public LoginInfo generateToken(String username, Integer expirationInDays) {
        Date expirationDate = new Date(getExpirationTime(expirationInDays) + System.currentTimeMillis());
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, config.getJwtSecret())
                .compact();

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setToken(token);
        loginInfo.setUsername(username);
        loginInfo.setExpiration(expirationDate.getTime());
        return loginInfo;
    }

    private Long getExpirationTime(Integer expirationInDays) {
        if (expirationInDays == null) {
            expirationInDays = Constants.JWT_DEFAULT_EXPIRATION_DAYS;
        } else if (expirationInDays > Constants.JWT_MAX_EXPIRATION_DAYS) {
            expirationInDays = Constants.JWT_MAX_EXPIRATION_DAYS;
        }
        return expirationInDays * EXPIRATION_TIME_DAY;
    }

    public Authentication getAuthentication(HttpServletRequest request)
    {
        try {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (token != null && token.startsWith(TOKEN_PREFIX)) {
                token = token.substring(TOKEN_PREFIX.length());

                String username = Jwts.parser()
                        .setSigningKey(config.getJwtSecret())
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();

                if (username != null) { // we managed to retrieve a user
                    return new AuthenticatedUser(username);
                }
            }
        }catch (Exception e) {
            logger.error("Exception in getAuthentication", e);
        }
        return null;
    }
}