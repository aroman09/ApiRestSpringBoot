package com.bussinessIt.api.service;

import com.bussinessIt.api.entity.Cuenta;
import com.bussinessIt.api.service.interfaceService.CuentaService;
import com.bussinessIt.api.repository.CuentaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CuentaServiceImpl implements CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        log.info(CuentaService.class.getSimpleName()+" Crear cuenta: " + cuenta.toString());
        cuentaRepository.save(cuenta);
        return cuenta;
    }

    @Override
    public Cuenta actualizarCuenta(Cuenta cuenta) {
        log.info(CuentaService.class.getSimpleName()+" Actualizar cuenta: " + cuenta.toString());
        return cuentaRepository.save(cuenta);
    }

    @Override
    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public boolean eliminarCuenta(Long idCuenta) {
        log.info(CuentaService.class.getSimpleName()+" Eliminar cuenta: " + idCuenta);
        if(existeCuenta(idCuenta)){
            cuentaRepository.deleteById(idCuenta);
            log.info(CuentaService.class.getSimpleName()+" Cuenta eliminado: " + idCuenta+" - "+true);
            return true;
        }
        log.info(CuentaService.class.getSimpleName()+" Cuenta eliminado: " + idCuenta+" - "+false);
        return false;
    }

    @Override
    public boolean existeCuenta(Long idCuenta) {
        return obtenerCuenta(idCuenta)!=null;
    }

    @Override
    public Cuenta obtenerCuenta(Long idCuenta) {
        log.info(CuentaService.class.getSimpleName()+" Recuperar cuenta: " + idCuenta);
        Cuenta cuenta = null;
        try{
            cuenta = cuentaRepository.findById(idCuenta).get();
            log.info(CuentaService.class.getSimpleName()+" Cuenta recuperada: "+cuenta.toString());
        }catch (Exception ex){
            log.error(CuentaService.class.getSimpleName()+ex.getMessage());
        }finally {
            return cuenta;
        }
    }

    @Override
    public List<Cuenta> listarCuentasDeCliente(Long idCliente) {
        log.info(CuentaService.class.getSimpleName()+" Listar cuentas del cliente: " + idCliente);
        List<Cuenta> cuentas = new ArrayList<>();
        for (Cuenta c:listarCuentas()) {
            if(c.getClienteId().getId_cliente()==idCliente)
                cuentas.add(c);
        }
        log.info(CuentaService.class.getSimpleName()+" Total de cuentas del cliente: " + idCliente+ " = "+cuentas.size());
        return cuentas;
    }
}
