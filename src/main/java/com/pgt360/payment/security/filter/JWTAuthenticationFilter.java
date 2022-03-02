package com.pgt360.payment.security.filter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgt360.payment.security.service.JWTService;
import com.pgt360.payment.security.service.JWTServiceImpl;
import com.pgt360.payment.service.UsuarioService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UsuarioService usuarioService;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                                   JWTService jwtService,
                                   UsuarioService usuarioService){
        this.authenticationManager = authenticationManager;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if(username != null && password != null){
            logger.info("Username desde request parameter (form-data): " + username);
            logger.info("Password desde request parameter (form-data): " + password);
        }else{
            com.pgt360.payment.model.entity.Usuario user = null;
            try {
                user = new ObjectMapper().readValue(request.getInputStream(), com.pgt360.payment.model.entity.Usuario.class);

                username = user.getUsername();
                password = user.getPassword();

                logger.info("Login desde request InputStream (raw): " + username);
//                logger.info("Password desde request InputStream (raw): " + password);

            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        username = username.trim();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authToken);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = jwtService.create(authResult);
        response.addHeader(JWTServiceImpl.HEADER_STRING, JWTServiceImpl.TOKEN_PREFIX + token);
        com.pgt360.payment.model.entity.Usuario user = usuarioService.getUserByUserName(((User) authResult.getPrincipal()).getUsername());
        String[] roles = {"admin"};
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("token", token);
        body.put("user", authResult.getPrincipal());
        body.put("name", (user.getNombre() + " " + user.getPaterno()));
        body.put("telcel", (user.getTelcel()));
        body.put("mensaje", String.format("Hola %s, has iniciado sesión con éxito!", ((User)authResult.getPrincipal()).getUsername()) );
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(200);
        response.setContentType("application/json");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("message", "Username o password incorrecto o usuario bloqueado!");
        body.put("error", failed.getMessage());

        try {
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setStatus(401);
        response.setContentType("application/json");
    }
}
