package com.pgt360.payment.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import java.util.Collection;

public interface JWTService {
    public String create(Authentication auth) throws IOException;
    public boolean validate(String token);
    public Claims getClaims(String token);
    public String getUsername(String token);
    public String getId(String token);
    public String resolve(String token);
}
