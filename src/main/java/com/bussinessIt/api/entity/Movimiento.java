package com.bussinessIt.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Movimientos")
public class Movimiento{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento", nullable = false, unique = true)
    private Long id_movimiento;
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
    private String tipo_movimiento;
    private double valor;
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "cuentaId",referencedColumnName = "num_cuenta")
    private Cuenta cuentaId;

    public Movimiento( LocalDate fecha, String tipo_movimiento, double valor, double saldo, Cuenta cuentaId) {
        this.fecha = fecha;
        this.tipo_movimiento = tipo_movimiento;
        this.valor = valor;
        this.saldo = saldo;
        this.cuentaId = cuentaId;
    }

    public Movimiento() {
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "id_movimiento=" + id_movimiento +
                ", fecha=" + fecha +
                ", tipo_movimiento='" + tipo_movimiento + '\'' +
                ", valor=" + valor +
                ", saldo=" + saldo +
                ", cuentaId=" + cuentaId +
                '}';
    }
}
