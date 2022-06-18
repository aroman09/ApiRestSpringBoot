package com.bussinessIt.api.controller;

import com.bussinessIt.api.entity.Cliente;
import com.bussinessIt.api.entity.Cuenta;
import com.bussinessIt.api.entity.dto.CuentaDto;
import com.bussinessIt.api.service.interfaceService.ClienteService;
import com.bussinessIt.api.service.interfaceService.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cuentas")
public class CuentaRestController {
    @Autowired
    private CuentaService cuentaService;
    @Autowired
    private ClienteService clienteService;


    @GetMapping("listar")
    public ResponseEntity<List<Cuenta>> listarCuentas(){
        return new ResponseEntity<>(cuentaService.listarCuentas(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearCuenta(@RequestBody CuentaDto cuentaDto){
        try{
            if(cuentaService.obtenerCuenta(cuentaDto.getNum_cuenta()) == null){
                Cliente cliente = clienteService.obtenerCliente(cuentaDto.getClienteId());
                Cuenta cuenta = new Cuenta(cuentaDto.getNum_cuenta(), cuentaDto.getTipo_cuenta(), cuentaDto.getSaldo_inicial(), cuentaDto.getEstado(), cliente);
                return new ResponseEntity<>("Cuenta: "+cuentaService.crearCuenta(cuenta)+" creada correctamente!!", HttpStatus.CREATED);
            }
            else return ResponseEntity.ok("El cuenta ya existe!!");
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "actualizar")
    public ResponseEntity<?> actualizarCuenta(@RequestBody CuentaDto cuentaDto){
        try{
            if(cuentaService.obtenerCuenta(cuentaDto.getNum_cuenta())!=null) {
                Cliente cliente = clienteService.obtenerCliente(cuentaDto.getClienteId());
                Cuenta cuenta = new Cuenta(cuentaDto.getNum_cuenta(), cuentaDto.getTipo_cuenta(), cuentaDto.getSaldo_inicial(), cuentaDto.getEstado(), cliente);
                return new ResponseEntity<>("Cuenta "+cuentaService.actualizarCuenta(cuenta)+" actualizada correctamente!!", HttpStatus.CREATED);
            }
            else return ResponseEntity.ok("El cuenta no existe!!");
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("eliminar/{idcuenta}")
    public ResponseEntity<?> eliminarCuenta(@PathVariable Long idcuenta){
        try{
            if(cuentaService.eliminarCuenta(idcuenta)){
                return new ResponseEntity<>("Cuenta eliminado correctamente!!",HttpStatus.OK);
            }
            else return ResponseEntity.ok("El cuenta no existe!!");
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("obtenerXcuenta/{cuentaId}")
    public ResponseEntity<?> obtenerCuenta(@PathVariable Long cuentaId){
        try{
            return new ResponseEntity<>(cuentaService.obtenerCuenta(cuentaId)==null?"Cuenta "+cuentaId+" no existe!!":cuentaService.obtenerCuenta(cuentaId), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("obtenerXcliente/{clienteId}")
    public ResponseEntity<?> obtenerCuentasPorCliente(@PathVariable Long clienteId){
        try{
            List<Cuenta> listaCuentas = cuentaService.listarCuentasDeCliente(clienteId);
            return new ResponseEntity<>(listaCuentas.size()>0?listaCuentas:"No existen cuentas registradas!!", HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}

