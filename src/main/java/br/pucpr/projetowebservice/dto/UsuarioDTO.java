package br.pucpr.projetowebservice.dto;

import br.pucpr.projetowebservice.security.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat; // <- Importante para formatar a data
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Integer id;

    @NotNull(message = "O nome precisa ser preenchido.")
    @NotBlank(message = "O nome precisa ser preenchido.")
    private String nome;

    @Email
    @NotBlank
    private String email;

    @NotNull(message = "A senha precisa ser preenchida.")
    private String senha;

    @NotNull(message = "A data de nascimento precisa ser preenchida.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    private Role role;
}
