package com.controledeponto.application.model;

import com.controledeponto.application.enums.TypeRecord;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "tb_registro")
@Entity
@Getter
@Setter
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro", nullable = false)
    private Integer id;
    @Column(name = "tipo_registro", nullable = false)
    private TypeRecord type;
    @Column(name = "instante_registro", nullable = false)
    private LocalDateTime instantRecord;
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Person person;

}
