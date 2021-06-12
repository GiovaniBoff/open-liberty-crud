package com.crud.movies.crud.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotEmpty
    private String nome;
    // @Email(message = "Email must be valid")
    // @NotEmpty
    private String email;

    private String senha;

}
