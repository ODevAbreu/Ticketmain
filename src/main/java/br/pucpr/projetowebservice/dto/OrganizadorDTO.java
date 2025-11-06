package br.pucpr.projetowebservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat; // <- Importante para formatar a data
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class OrganizadorDTO {

    private Integer id;

    @NotNull(message = "O nome precisa ser preenchido.")
    @NotBlank(message = "O nome precisa ser preenchido.")
    private String nome;

    @Email
    @NotBlank
    private String email;

    @NotNull(message = "A senha precisa ser preenchida.")
    private String senha;

}
