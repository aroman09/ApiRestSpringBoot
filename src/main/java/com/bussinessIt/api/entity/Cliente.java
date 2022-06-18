package com.bussinessIt.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Cliente")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false, unique = true)
    private long id_cliente;
    private String contrasenia;
    private boolean estado;

    @OneToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id_persona")
    private Persona personaId;

    @Override
    public String toString() {
        return "Cliente{" +
                ", contrasenia='" + contrasenia + '\'' +
                ", estado=" + estado +
                ", personaId=" + personaId.toString() +
                '}';
    }

    public Cliente(String contrasenia, boolean estado, Persona personaId) {
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.personaId = personaId;
    }

    public Cliente() {
    }
}
