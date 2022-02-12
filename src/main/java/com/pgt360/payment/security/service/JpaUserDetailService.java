package com.pgt360.payment.security.service;

import com.pgt360.payment.exception.Message;
import com.pgt360.payment.exception.MessageDescription;
import com.pgt360.payment.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("jpaUsuarioDetailsService")
public class JpaUserDetailService implements UserDetailsService {
    @Autowired
    private UsuarioService usuarioService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.pgt360.payment.model.entity.Usuario user = usuarioService.getUserByUserName(s);
        if (user == null){
            throw new UsernameNotFoundException(Message.GetNotFound(MessageDescription.UsernameNoEncontrado, s).getMessage());
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));

        boolean bandera = true;
        if(user.getEstado().equals("BL"))
            bandera = false;
        else
            bandera = true;
        return new User(user.getUsername(), user.getPassword(), bandera, true, true, true, authorities);
    }
}
