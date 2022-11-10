package com.controledeponto.application.model;

import com.controledeponto.application.anonation.ValidationFields;
import com.controledeponto.application.anonation.NotUpperCase;
import com.controledeponto.application.enums.AcessPerson;
import com.controledeponto.application.enums.StatusPerson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "tb_cliente")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa", nullable = false)
    private Long id;

    @ValidationFields(notEmpty = true)
    @NotUpperCase
    @Column(name = "login_pessoa", nullable = false)
    public String login;

    @ValidationFields(notEmpty = true)
    @Column(name = "nome_pessoa", nullable = false)
    public String name;

    @ValidationFields(notEmpty = true)
    @Column(name = "sobrenome_pessoa", nullable = false)
    public String lastname;

    @ValidationFields(notEmpty = true)
    @NotUpperCase
    @Column(name = "senha_pessoa", nullable = false)
    private String password;

    @Column(name = "acesso_pessoa", nullable = false)
    private AcessPerson access;

    @Column(name = "status_pessoa", nullable = false)
    private StatusPerson status;
}
