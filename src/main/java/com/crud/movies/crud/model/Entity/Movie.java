package com.crud.movies.crud.model.Entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String titulo;
    @Column
    private String genero;
    @Column
    private String score;
    @Column
    private String elenco;
    @Column
    private String ano;
    @Column
    private String diretor;

    @ManyToMany
    private Set<User> users;

}
