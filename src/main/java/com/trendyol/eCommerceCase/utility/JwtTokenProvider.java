package com.trendyol.eCommerceCase.utility;

import com.trendyol.eCommerceCase.model.UserPrincipal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.trendyol.eCommerceCase.constants.SecurityConstants.*;

@Component
public class JwtTokenProvider {

    public String generateJwtToken(UserPrincipal userPrincipal) {
        return  Jwts.builder()
                .setSubject(( userPrincipal.getUsername()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512,SECRET.getBytes())
                .compact();
    }

    public HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_STRING, TOKEN_PREFIX + generateJwtToken(user));
        return headers;
    }

}
