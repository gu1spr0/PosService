package com.pgt360.payment.service.impl;

import com.pgt360.payment.exception.Message;
import com.pgt360.payment.exception.MessageDescription;
import com.pgt360.payment.model.entity.Usuario;
import com.pgt360.payment.model.repository.UsuarioRepository;
import com.pgt360.payment.service.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public Usuario getUserById(long pUsuarioId) {
        Usuario vUsuario = usuarioRepository.getUserById(pUsuarioId).orElse(null);
        if(vUsuario == null){
            Object[] obj = {"Usuario","id", String.valueOf(pUsuarioId)};
            throw Message.GetBadRequest(MessageDescription.notExists, obj);
        }
        return vUsuario;
    }

    @Override
    public Usuario getUserByUserName(String pUsuario) {
        Usuario vUsuario = usuarioRepository.getUserByUsername(pUsuario).orElse(null);
        if (vUsuario == null){
            Object[] obj = {"usuario", "nombre de usuario", String.valueOf(pUsuario)};
            throw Message.GetBadRequest(MessageDescription.notExists, obj);
        }
        return vUsuario;
    }
}
