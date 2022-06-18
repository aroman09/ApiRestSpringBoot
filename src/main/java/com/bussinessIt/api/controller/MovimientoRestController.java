package com.bussinessIt.api.controller;

import com.bussinessIt.api.entity.dto.Fechas;
import com.bussinessIt.api.helper.GeneradorPDF;
import com.bussinessIt.api.entity.Cuenta;
import com.bussinessIt.api.entity.Movimiento;
import com.bussinessIt.api.entity.dto.MovimientoDto;
import com.bussinessIt.api.service.interfaceService.CuentaService;
import com.bussinessIt.api.service.interfaceService.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoRestController {
    @Autowired
    private MovimientoService movimientoService;
    @Autowired
    private CuentaService cuentaService;

    @GetMapping("listarMovimientos")
    public ResponseEntity<List<Movimiento>> listarMovimientos(){
        return new ResponseEntity<>(movimientoService.listarMovimientos(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearMovimiento(@RequestBody MovimientoDto movimientoDto){
        try{
            Cuenta cuenta = cuentaService.obtenerCuenta(movimientoDto.getIdCuenta());

            double saldoActual = cuenta.getSaldo_inicial();
            if (saldoActual < movimientoDto.getValor() && movimientoDto.getTipo_movimiento().equals("Debito")) {
                return ResponseEntity.ok("Saldo no disponible");
            }
            else
            {
                double saldo = movimientoService.realizarTransaccion(cuenta, movimientoDto);
                cuenta.setSaldo_inicial(saldo);
                Movimiento movimiento = new Movimiento(movimientoDto.getFecha(), movimientoDto.getTipo_movimiento(),movimientoDto.getValor(), saldo, cuenta);
                cuentaService.actualizarCuenta(cuenta);

                return new ResponseEntity<>(movimientoService.crearMovimientos(movimiento), HttpStatus.CREATED);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "listarXfecha")
    public ResponseEntity<?> listarMovimientosFechaCliente(@RequestBody Fechas movFechasCliente) {
        try {
            List<Map<String, String>> reporte = movimientoService.listarMovimientosDateCliente(movFechasCliente);
            return new ResponseEntity<>(reporte, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "movimientosXcliente/{clienteId}")
    public ResponseEntity<?> listarMovimientosIdCliente(@PathVariable Long clienteId) {
        try {
            List<Movimiento> movimientos = movimientoService.listarMovimientosCliente(clienteId);
            return new ResponseEntity<>(movimientos, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "actualizar")
    public ResponseEntity<?> actualizarMovimiento(@RequestBody MovimientoDto movimientoDto){
        try{
            Cuenta cuenta = cuentaService.obtenerCuenta(movimientoDto.getIdCuenta());

            double saldoActual = cuenta.getSaldo_inicial();
            if (saldoActual < movimientoDto.getValor() && movimientoDto.getTipo_movimiento().equals("Debito")) {
                return ResponseEntity.ok("Saldo no disponible");
            }
            else
            {
                double saldo = movimientoService.realizarTransaccion(cuenta, movimientoDto);
                cuenta.setSaldo_inicial(saldo);
                Movimiento movimiento = new Movimiento(LocalDate.now(), movimientoDto.getTipo_movimiento(),movimientoDto.getValor(), saldo, cuenta);
                cuentaService.actualizarCuenta(cuenta);

                return new ResponseEntity<>(movimientoService.crearMovimientos(movimiento), HttpStatus.CREATED);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("eliminar/{idMovimiento}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long idMovimiento){
        try{
            if(movimientoService.existeMovimiento(idMovimiento)){
                Movimiento movimiento= movimientoService.obtenerMovimiento(idMovimiento);
                Cuenta cuenta = cuentaService.obtenerCuenta(movimiento.getCuentaId().getNum_cuenta());
                cuenta.setSaldo_inicial(movimientoService.reversarTransaccion(movimiento));
                cuentaService.actualizarCuenta(cuenta);
                return new ResponseEntity<>(movimientoService.eliminarMovimientos(idMovimiento)?"Movimiento eliminado correctamente!!":"No se pudo procesar la eliminacion del movimiento"
                        ,movimientoService.eliminarMovimientos(idMovimiento)?HttpStatus.OK:HttpStatus.NOT_ACCEPTABLE);
            }
            else return ResponseEntity.ok("El movimiento no existe!!");
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "reportes", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> estadoMovimiento(@RequestBody Fechas fechas) throws IOException, ParseException {

        List<Map<String, String>> reporte = movimientoService.listarMovimientosDateCliente(fechas);

        ByteArrayInputStream bis = GeneradorPDF.PDFReporteCliente(reporte);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
