package com.honour.manager.utils;

import com.honour.manager.entity.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 工具类
 */
@Component
public class JwtTokenUtil {

    private static final long EXPIRATION_TIME = 432000000;
    private static final String SECRET = "mySecret";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_KEY_USERNAME = "sub";

    public static final String TOKEN_HEAD = "Bearer";
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * 签发JWT
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 签发JWT
     */
    public String generateToken(String userName) {
        Map<String, Object> map = new HashMap<>(16);
        map.put(CLAIM_KEY_USERNAME, userName);
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 签发JWT
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + EXPIRATION_TIME * 1000);
    }

    /**
     * 验证JWT
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    /**
     * 获取token是否过期
     */
    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 根据token获取username
     */
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    /**
     * 获取token的过期时间
     */
    private Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    /**
     * 解析JWT
     */
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 判断token是否可以被刷新,有效期内的就可以被刷新，过期的不可以
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }


    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
}