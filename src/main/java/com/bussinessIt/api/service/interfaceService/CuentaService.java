package com.bussinessIt.api.service.interfaceService;

import com.bussinessIt.api.entity.Cuenta;

import java.util.List;


public interface CuentaService {
    public Cuenta crearCuenta(Cuenta cuenta);
    public Cuenta actualizarCuenta(Cuenta cuenta);
    public List<Cuenta> listarCuentas();
    public boolean eliminarCuenta(Long idCuenta);
    public boolean existeCuenta(Long idCuenta);
    public Cuenta obtenerCuenta(Long idCuenta);
    public List<Cuenta> listarCuentasDeCliente(Long idCliente);
}
