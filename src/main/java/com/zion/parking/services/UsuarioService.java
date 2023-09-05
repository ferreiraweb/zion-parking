package com.zion.parking.services;

import com.zion.parking.entities.Usuario;
import com.zion.parking.repositories.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;


    @Transactional
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario não encontrado") );
    }

    @Transactional
    public Usuario editarSenha(Long id, String password) {

        Usuario user = findById(id);
        user.setPassword(password);
        return user;
    }

    @Transactional(readOnly = true)
    public List<Usuario> findAll() {

        List<Usuario> usuarios = repository.findAll();
        return usuarios;
    }
}
