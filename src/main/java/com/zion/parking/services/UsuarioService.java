package com.zion.parking.services;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.zion.parking.entities.Usuario;
import com.zion.parking.exceptions.EntityNotFoundException;
import com.zion.parking.exceptions.UserNameUniqueViolationExcetion;
import com.zion.parking.repositories.UsuarioRepository;
import org.springframework.dao.DataIntegrityViolationException;
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
    public Usuario salvar(Usuario usuario)  {
        try{
            return repository.save(usuario);
        } catch (DataIntegrityViolationException ex) {
            throw new UserNameUniqueViolationExcetion(ex.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuario id = %s não encontrado", id)) );
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {

        if (!novaSenha.equals(confirmaSenha)) {
            throw new RuntimeException("Nova senha não confere com confirma senha");
        }

        Usuario user = findById(id);

        if (!user.getPassword().equals(senhaAtual)) {
            throw new RuntimeException("Senha não confere");
        }

        user.setPassword(novaSenha);
        return user;
    }

    @Transactional(readOnly = true)
    public List<Usuario> findAll() {

        List<Usuario> usuarios = repository.findAll();
        return usuarios;
    }
}
