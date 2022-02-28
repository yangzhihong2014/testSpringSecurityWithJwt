package com.example.testSpringSecurityWithJwt.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Intellij IDEA.
 * User:  zhi13
 * Date:  2022/2/28
 */
public class JwtTokenUtils {

    /**
     * 用户名称
     */
    private static final String USERNAME = Claims.SUBJECT;

    /**
     * 密钥
     */
    private static final String SECRET = "abcdefgh";

    /**
     * 有效期12小时
     */
    public static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;

    /**
     * 从数据声明生成令牌
     *
     * 数据声明（Claim）其实就是一个Map，比如我们想放入用户名，可以简单的创建一个Map然后put进去就可以了
     *
     * 注意用户名有自己的key
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private static String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>() ;
        claims.put(USERNAME,userDetails.getUsername());
        return generateToken(claims);
    }


    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }


    public static void main(String[] args) {
        Map<String, Object> claims = new HashMap<>() ;
        claims.put(USERNAME,"yang");
        claims.put("userCode","123123123");
        String generateToken = generateToken(claims);
        System.out.println("generateToken: "+generateToken);
        System.out.println("-------------");


        Claims claimsFromToken = getClaimsFromToken(generateToken);
        System.out.println("username: "+claimsFromToken.getSubject());
        System.out.println("userCode: "+claimsFromToken.get("userCode"));

        // 打印-------------
        /**
         * generateToken: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ5YW5nIiwiZXhwIjoxNjQ2MDU2Njg2LCJ1c2VyQ29kZSI6IjEyMzEyMzEyMyJ9.CfxsZ3Ku7S2ntz2k3YyAOQmSX1dJsTbcwU35gW6GCQjvaqNWFvMnmySZtLIJtRreF4-VsZszQ2PMr4ZTjnM7Xw
         * -------------
         * username: yang
         * userCode: 123123123
         */
    }

    public static String getUsernameFromToken(String oldToken) {
        Claims claimsFromToken = getClaimsFromToken(oldToken);
        return claimsFromToken.getSubject() ;
    }
}