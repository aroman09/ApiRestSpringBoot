package com.bussinessIt.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class MovimientoDto { //DTO (Data Transfer Object). Los DTO son un tipo de objetos que sirven únicamente para transportar datos

    private Long id_movimiento;
    private LocalDate fecha;
    private String tipo_movimiento;
    private double valor;
    private double saldo;
    private Long idCuenta;

}
