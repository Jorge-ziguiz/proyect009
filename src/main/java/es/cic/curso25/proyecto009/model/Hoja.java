package es.cic.curso25.proyecto009.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;

@Entity
public class Hoja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Version
    private Long Version;

    private String tipo;

    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rama_id")
    private Rama rama;



}
