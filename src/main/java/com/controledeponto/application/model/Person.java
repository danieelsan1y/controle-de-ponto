package com.controledeponto.application.model;

import com.controledeponto.application.anonation.NotEmpty;
import com.controledeponto.application.anonation.ServiceName;
import com.controledeponto.application.enums.AcessPerson;
import com.controledeponto.application.enums.StatusPerson;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_cliente")
@ServiceName(name = "PersonService")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "login_pessoa", nullable = false)
    private String login;

    @NotEmpty
    @Column(name = "nome_pessoa", nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "sobrenome_pessoa", nullable = false)
    private String lastname;

    @NotEmpty
    @Column(name = "senha_pessoa", nullable = false)
    private String password;

    @Column(name = "acesso_pessoa", nullable = false)
    private AcessPerson acess;

    @Column(name = "status_pessoa", nullable = false)
    private StatusPerson status;
}
