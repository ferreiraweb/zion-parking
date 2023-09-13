package com.zion.parking.web.controllers;


import com.zion.parking.entities.Usuario;
import com.zion.parking.exceptions.ErrorMessage;
import com.zion.parking.services.UsuarioService;
import com.zion.parking.web.dtos.UsuarioCreateDto;
import com.zion.parking.web.dtos.UsuarioResponseDto;
import com.zion.parking.web.dtos.UsuarioSenhaDto;
import com.zion.parking.web.mappers.UsuarioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios", description = "Contém todas as operações relativas aos recursos para cadastros, edição e leitura de um usuário")
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Operation(
            summary = "Cria um novo usuário", description = "Recurso para criar um novo usuário",
            responses = {
                @ApiResponse(
                    responseCode = "201",
                    description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "appliction/json",
                               schema = @Schema(implementation = UsuarioResponseDto.class)
                    )),
                @ApiResponse(
                    responseCode = "409",
                    description = "Usuario e e-mail já cadastrado",
                    content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = ErrorMessage.class))),
                @ApiResponse(
                    responseCode = "422",
                    description = "Recurso não processado por dados de entrada inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)))


        }
    )
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
