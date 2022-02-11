package com.pgt360.payment.service;

import com.pgt360.payment.model.entity.Usuario;

public interface UsuarioService {
    Usuario getUserById(long pUsuarioId);
    Usuario getUserByUserName(String pUsario);
}
