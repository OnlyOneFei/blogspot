package com.floating.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Mr_Fei
 * @description
 * @date 2020-12-21 23:39
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "floating.jwt")
public class JwtUtils {

    private String secret;

    private long expire;

    private String header;

    /**
     * 生成jwt token
     */
    public String generateToken(long userId) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId + "")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 获取jwt的信息
     *
     * @param token token
     * @return io.jsonwebtoken.Claims
     * @author Mr_Fei
     * @date 2020/12/22 23:25
     * @description getClaimByToken
     */
    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * token是否过期
     *
     * @param expiration 到期时间
     * @return boolean
     * @author Mr_Fei
     * @date 2020/12/22 23:25
     * @description isTokenExpired
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}

