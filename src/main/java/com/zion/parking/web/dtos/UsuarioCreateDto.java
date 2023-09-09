package com.zion.parking.web.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class UsuarioCreateDto {


    @Email(message = "Formato de e-mail inválido")
    @NotBlank(message = "O username deve ser informado")
    private String username;

    @NotBlank(message = "A senha deve ser informada")
    @Size(min = 6, max = 6, message = "a senha deve ter 6 caracteres")
    private String password;

    public UsuarioCreateDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UsuarioCreateDto() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsuarioCreateDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
