package com.bussinessIt.api.controller;

import com.bussinessIt.api.entity.Cliente;
import com.bussinessIt.api.entity.Persona;
import com.bussinessIt.api.service.interfaceService.ClienteService;
import com.bussinessIt.api.service.interfaceService.PersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PersonaService personaService;

    @GetMapping("listar")
    public ResponseEntity<List<Cliente>> verClientes(){
        return new ResponseEntity<>(clienteService.listarClientes(),HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente){
        try{
            if(clienteService.obtenerCliente(cliente.getPersonaId().getId_persona())==null){
                Persona persona = cliente.getPersonaId();
                personaService.crearPersona(persona);
                return new ResponseEntity<>("Cliente "+clienteService.crearCliente(cliente).getPersonaId().getNombre()+" creado correctamente!!",HttpStatus.CREATED);
            }
            else return ResponseEntity.ok("El cliente ya existe!!");
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "actualizar")
    public ResponseEntity<?> actualizarCliente(@RequestBody Cliente cliente){
        try{
            if(clienteService.obtenerCliente(cliente.getPersonaId().getId_persona())!=null){
                Persona persona = cliente.getPersonaId();
                personaService.actualizarPersona(persona);
                return new ResponseEntity<>("Cliente "+clienteService.actualizarCliente(cliente).getPersonaId().getNombre()+" actualizado correctamente!!",HttpStatus.OK);
            }
            else return ResponseEntity.ok("El cliente no existe!!");
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("eliminar/{idcliente}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long idcliente){
        try{
            if(clienteService.eliminarCliente(idcliente)){
                return new ResponseEntity<>("Cliente eliminado correctamente!!",HttpStatus.OK);
            }
            else return ResponseEntity.ok("El cliente no existe!!");
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("obtener/{clienteId}")
    public ResponseEntity<?> obtenerCliente(@PathVariable Long clienteId){
        try{
            return new ResponseEntity<>(clienteService.obtenerCliente(clienteId)==null?"Usuario no existe!!":clienteService.obtenerCliente(clienteId), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
