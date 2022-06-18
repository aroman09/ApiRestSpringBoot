package com.bussinessIt.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class Fechas {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long idCliente;
}
