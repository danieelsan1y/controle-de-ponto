package com.controledeponto.application.model;

import com.controledeponto.application.anonation.NotEmpty;
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

    @NotEmpty
    @NotUpperCase
    @Column(name = "login_pessoa", nullable = false)
    public String login;

    @NotEmpty
    @Column(name = "nome_pessoa", nullable = false)
    public String name;

    @NotEmpty
    @Column(name = "sobrenome_pessoa", nullable = false)
    public String lastname;

    @NotEmpty
    @NotUpperCase
    @Column(name = "senha_pessoa", nullable = false)
    private String password;

    @Column(name = "acesso_pessoa", nullable = false)
    private AcessPerson acess;

    @Column(name = "status_pessoa", nullable = false)
    private StatusPerson status;
}
