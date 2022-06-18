package com.bussinessIt.api.service;

import com.bussinessIt.api.entity.dto.Fechas;
import com.bussinessIt.api.entity.dto.MovimientoDto;
import com.bussinessIt.api.entity.Cuenta;
import com.bussinessIt.api.entity.Movimiento;
import com.bussinessIt.api.repository.CuentaRepository;
import com.bussinessIt.api.repository.MovimientoRepository;
import com.bussinessIt.api.service.interfaceService.MovimientoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MovimientoServiceImpl implements MovimientoService {
    
    @Autowired
    private MovimientoRepository movimientoRepository;
    
    @Override
    public Movimiento obtenerMovimiento(Long idMovimiento) {
        log.info(MovimientoService.class.getSimpleName()+" Recuperar movimiento: " + idMovimiento);
        Movimiento movimiento = null;
        try{
            movimiento= movimientoRepository.findById(idMovimiento).get();
            log.info(MovimientoService.class.getSimpleName()+" Movimiento recuperado: "+movimiento.toString());
        }catch (Exception ex){
            log.error(MovimientoService.class.getSimpleName()+ex.getMessage());
        }finally {
            return movimiento;
        }
    }

    @Override
    public Movimiento crearMovimientos(Movimiento movimiento) {
        log.info(MovimientoService.class.getSimpleName()+" Crear movimiento: " + movimiento.toString());
        movimientoRepository.save(movimiento);
        return movimiento;
    }

    @Override
    public Movimiento actualizarMovimientos(Movimiento movimiento) {
        log.info(MovimientoService.class.getSimpleName()+" Actualizar movimiento: " + movimiento.toString());
        return movimientoRepository.save(movimiento);
    }

    @Override
    public boolean eliminarMovimientos(Long idMovimiento) {
        log.info(MovimientoService.class.getSimpleName()+" Eliminar movimiento: " + idMovimiento);
        if(existeMovimiento(idMovimiento)){
            movimientoRepository.deleteById(idMovimiento);
            log.info(MovimientoService.class.getSimpleName()+" Movimiento eliminado: " + idMovimiento+" - "+true);
            return true;
        }
        log.info(MovimientoService.class.getSimpleName()+" Movimiento eliminado: " + idMovimiento+" - "+false);
        return false;
    }

    @Override
    public boolean existeMovimiento(Long idMovimiento) {
        return obtenerMovimiento(idMovimiento)!=null;
    }

    @Override
    public double realizarTransaccion(Cuenta cuenta, MovimientoDto movimientoDto) {
            return movimientoDto.getTipo_movimiento().equals("Deposito")
                    ? cuenta.getSaldo_inicial() + movimientoDto.getValor()
                    : cuenta.getSaldo_inicial() - movimientoDto.getValor();
    }

    @Override
    public double reversarTransaccion(Movimiento movimiento) {
        log.info(MovimientoService.class.getSimpleName()+" Reverso del movimiento "+movimiento.getId_movimiento());
        double valorReversado =0;
        if(movimiento.getTipo_movimiento().equals("Deposito")){
            valorReversado = movimiento.getCuentaId().getSaldo_inicial()-movimiento.getValor();
        }
        else {
            valorReversado=movimiento.getCuentaId().getSaldo_inicial()+movimiento.getValor();
        }
        log.info(MovimientoService.class.getSimpleName()+" Valor a reversar "+valorReversado);
        return valorReversado;

    }


    @Override
    public List<Movimiento> listarMovimientos() {
        return movimientoRepository.findAll();
    }

    @Override
    public List<Movimiento> listarMovimientosCliente(Long idCliente) {
        log.info(MovimientoService.class.getSimpleName()+" Listar movimiento del cliente: " + idCliente);
        List<Movimiento> lista = new ArrayList<>();

        for (Movimiento m : listarMovimientos()) {
            if(m.getCuentaId().getClienteId().getId_cliente()==idCliente){
                lista.add(m);
            }
        }
        return lista;
    }

    @Override
    public List<Map<String, String>> listarMovimientosDateCliente(Fechas fechaReporte) {
        log.info(MovimientoService.class.getSimpleName()+
                " Listar movimiento desde la fecha: " + fechaReporte.getFechaInicio()+
                " hasta la fecha: "+fechaReporte.getFechaFin()+" del cliente: "+fechaReporte.getIdCliente());

        List<Map<String, String>> lista = new ArrayList<>();

        for (Movimiento m : listarMovimientosCliente(fechaReporte.getIdCliente())) {
            if((m.getFecha().isAfter(fechaReporte.getFechaInicio()) && m.getFecha().isBefore(fechaReporte.getFechaFin()))
                    ||m.getFecha().isEqual(fechaReporte.getFechaInicio())||m.getFecha().isEqual(fechaReporte.getFechaFin())){

                Map<String, String> mapa = new HashMap<>();

                mapa.put("Fecha", m.getFecha().toString());
                mapa.put("Cliente", m.getCuentaId().getClienteId().getPersonaId().getNombre());
                mapa.put("Numero Cuenta", String.valueOf(m.getCuentaId().getNum_cuenta()));
                mapa.put("Tipo", m.getTipo_movimiento());
                mapa.put("Saldo Inicial", String.valueOf(String.valueOf(m.getSaldo())));
                mapa.put("Estado", m.getCuentaId().getEstado().toString());
                mapa.put("Movimiento", m.getTipo_movimiento().equals("Deposito")?"+"+String.valueOf(m.getValor()):"-"+String.valueOf(m.getValor()));
                mapa.put("Saldo Disponible", String.valueOf(m.getSaldo()));

                lista.add(mapa);
            }
        }
        return lista;
    }
}
