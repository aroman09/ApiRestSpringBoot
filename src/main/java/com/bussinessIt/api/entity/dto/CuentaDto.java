package com.bussinessIt.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CuentaDto {
    private Long num_cuenta;
    private String tipo_cuenta;
    private double saldo_inicial;
    private Boolean estado;
    private Long clienteId;

}
