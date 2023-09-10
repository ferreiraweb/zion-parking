package com.zion.parking.web.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioSenhaDto {

    public UsuarioSenhaDto() {
    }

    public UsuarioSenhaDto(String senhaAtual, String novaSenha, String confirmaSenha) {
        this.senhaAtual = senhaAtual;
        this.novaSenha = novaSenha;
        this.confirmaSenha = confirmaSenha;
    }

    @NotBlank(message = "Informe a senha atual")
    @Size(min = 6, max = 6, message = "A senha deve ter 6 caracteres")
    private String senhaAtual;
    @NotBlank(message = "Informe a senha atual")
    @Size(min = 6, max = 6, message = "A senha deve ter 6 caracteres")
    private String novaSenha;
    @NotBlank(message = "Informe a senha atual")
    @Size(min = 6, max = 6, message = "A senha deve ter 6 caracteres")
    private String confirmaSenha;

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    @Override
    public String toString() {
        return "UsuarioSenhaDto{" +
                "senhaAtual='" + senhaAtual + '\'' +
                ", novaSenha='" + novaSenha + '\'' +
                ", confirmaSenha='" + confirmaSenha + '\'' +
                '}';
    }
}
