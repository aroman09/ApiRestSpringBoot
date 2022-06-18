package com.bussinessIt.api.service.interfaceService;

import com.bussinessIt.api.entity.Cuenta;
import com.bussinessIt.api.entity.Movimiento;
import com.bussinessIt.api.entity.dto.Fechas;
import com.bussinessIt.api.entity.dto.MovimientoDto;

import java.util.List;
import java.util.Map;


public interface MovimientoService {
    static double limiteRetiro = 1000;

    Movimiento obtenerMovimiento(Long movimientoId);

    Movimiento crearMovimientos(Movimiento movimiento);

    Movimiento actualizarMovimientos(Movimiento movimiento);

    boolean eliminarMovimientos(Long movimientoId);

    List<Movimiento> listarMovimientos();

    List<Movimiento> listarMovimientosCliente(Long cliente);

    List<Map<String, String>> listarMovimientosDateCliente(Fechas fechaReporte);

     boolean existeMovimiento(Long idMovimiento) ;
    double realizarTransaccion(Cuenta cuenta, MovimientoDto movimientoDto);
    double reversarTransaccion(Movimiento movimiento);

}
