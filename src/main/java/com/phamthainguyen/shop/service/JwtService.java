package com.phamthainguyen.shop.service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import com.phamthainguyen.shop.entity.user.User;

import io.jsonwebtoken.Claims;

public interface JwtService {

    public String extractUsername(String token);

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    public String generateToken(User User);

    public String generateToken(Map<String, Object> extraClaims, User User);

    public String generateRefreshToken(User User);

    public String buildToken(Map<String, Object> extraClaims, User User, long expiration);

    public boolean isTokenValid(String token, User User);

    public boolean isTokenExpired(String token);

    public Date extractExpiration(String token);

    public Claims extractAllClaims(String token);

    public Key getSignInKey();
}
