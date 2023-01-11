package com.ssg4.be.config.jwt;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

    @Value("${jwt.password}")
    private String SECRET_KEY;

    /**
     * 토큰 생성 메소드
     * @param subject 토큰 제목
     * @return 토큰
     */
    public String createToken(String subject) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Duration.ofDays(1).toMillis()); // 만료기간 1일

        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
            .setIssuer("ssg4") // 토큰발급자(iss)
            .setIssuedAt(now) // 발급시간(iat)
            .setExpiration(expiration) // 만료시간(exp)
            .setSubject(subject) //  토큰 제목(subject)
            .signWith(SignatureAlgorithm.HS256,
                Base64.getEncoder().encodeToString(SECRET_KEY.getBytes())) // 알고리즘, 시크릿 키
            .compact();
    }

    /**
     * Jwt 토큰의 유효성 체크 메소드
     * @param token 토큰
     * @return 토큰 유효성
     */
    public Claims parseJwtToken(String token) {
        token = removeBearerByToken(token); // Bearer 제거
        return Jwts.parser()
            .setSigningKey(Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()))
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * 토큰 앞 부분('Bearer') 제거 메소드
     * @param token 토큰
     * @return 토큰 키
     */
    private String removeBearerByToken(String token) {
        return token.substring("Bearer ".length());
    }
}