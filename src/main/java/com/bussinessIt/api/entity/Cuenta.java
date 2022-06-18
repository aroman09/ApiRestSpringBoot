package com.bussinessIt.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity()
@Table(name = "Cuenta")
public class Cuenta {
    @Id
    @Column(name = "num_cuenta",nullable = false, unique = true)
    private Long num_cuenta;
    @Column(name = "tipo_cuenta", length = 20,nullable = false)
    private String tipo_cuenta;
    @Column(name = "saldo_inicial", nullable = false)
    private double saldo_inicial;
    @Column(name = "estado",nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "clienteId", referencedColumnName = "id_cliente")
    private Cliente clienteId;

    public Cuenta(Long num_cuenta, String tipo_cuenta, double saldo_inicial, Boolean estado, Cliente clienteId) {
        this.num_cuenta = num_cuenta;
        this.tipo_cuenta = tipo_cuenta;
        this.saldo_inicial = saldo_inicial;
        this.estado = estado;
        this.clienteId = clienteId;
    }

    public Cuenta() {
    }
}
