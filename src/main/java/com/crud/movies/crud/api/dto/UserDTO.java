package com.crud.movies.crud.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {

    @NotEmpty(message = "Nome must not be empty")
    private String nome;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email must not be empty")
    private String email;

    @NotEmpty(message = "Senha must not be empty")
    private String senha;

}
