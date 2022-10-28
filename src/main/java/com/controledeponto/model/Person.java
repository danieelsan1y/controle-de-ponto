package com.controledeponto.model;

import com.controledeponto.anonation.ServiceName;
import com.controledeponto.enums.AcessPerson;
import com.controledeponto.enums.StatusPerson;
import com.controledeponto.service.PersonService;
import com.controledeponto.validations.Validation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Column(name = "login_pessoa", nullable = false)
    private String login;

    @Column(name = "nome_pessoa", nullable = false)
    private String name;

    @Column(name = "sobrenome_pessoa", nullable = false)
    private String lastname;

    @Column(name = "senha_pessoa", nullable = false)
    private String password;

    @Column(name = "acesso_pessoa", nullable = false)
    private AcessPerson acess;

    @Column(name = "status_pessoa", nullable = false)
    private StatusPerson status;
}
