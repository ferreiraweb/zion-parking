package com.zion.parking.web.controllers;


import com.zion.parking.entities.Usuario;
import com.zion.parking.services.UsuarioService;
import com.zion.parking.web.dtos.UsuarioCreateDto;
import com.zion.parking.web.dtos.UsuarioResponseDto;
import com.zion.parking.web.dtos.UsuarioSenhaDto;
import com.zion.parking.web.dtos.mapper.UsuarioMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody  UsuarioCreateDto createDto) {
        Usuario user = service.salvar(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }



    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable Long id) {
        Usuario user = service.findById(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UsuarioSenhaDto dto) {
        Usuario user = service.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> findAll() {
        List<Usuario> usuarios = service.findAll();
        /*
        List<UsuarioResponseDto> listDtos = usuarios
                .stream()
                .map(user -> UsuarioMapper.toDto(user))
                .toList();
         */
        List<UsuarioResponseDto> listDtos = UsuarioMapper.toListDto(usuarios);
        return ResponseEntity.status(HttpStatus.OK).body(listDtos);
    }




}
