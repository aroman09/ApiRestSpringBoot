package com.bussinessIt.api.service;

import com.bussinessIt.api.entity.Cliente;
import com.bussinessIt.api.entity.Persona;
import com.bussinessIt.api.service.interfaceService.ClienteService;
import com.bussinessIt.api.repository.ClienteRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente crearCliente(Cliente cliente) {
        log.info(ClienteService.class.getSimpleName()+" Crear cliente: " + cliente.toString());
        clienteRepository.save(cliente);
        return cliente;
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        log.info(ClienteService.class.getSimpleName()+" Actualizar cliente: " + cliente.toString());
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public boolean eliminarCliente(Long idCliente) {
        log.info(ClienteService.class.getSimpleName()+" Eliminar cliente: " + idCliente);
        if(existeCliente(idCliente)){
            clienteRepository.deleteById(idCliente);
            log.info(ClienteService.class.getSimpleName()+" Cliente eliminado: " + idCliente+" - "+true);
            return true;
        }
        log.info(ClienteService.class.getSimpleName()+" Cliente eliminado: " + idCliente+" - "+false);
        return false;
    }

    @Override
    public boolean existeCliente(Long idCliente) {
        return obtenerCliente(idCliente)!=null;
    }

    @Override
    public Cliente obtenerCliente(Long idCliente) {
        log.info(ClienteService.class.getSimpleName()+" Recuperar cliente: " + idCliente);
        Cliente cliente = null;
        try{
            cliente = clienteRepository.findById(idCliente).get();
            log.info(ClienteService.class.getSimpleName()+" Cliente recuperado: "+cliente.toString());
            log.info(ClienteService.class.getSimpleName()+" IdPersona recuperado: "+cliente.getPersonaId().getId_persona());
        }catch (Exception ex){
            log.error(ClienteService.class.getSimpleName()+ex.getMessage());
        }finally {
            return cliente;
        }
    }

    @Override
    public Cliente obtenerClientePorPersona(Persona persona) {
        List<Cliente> clientes = listarClientes();
        log.info(ClienteService.class.getSimpleName()+"Obtener Cliente Por IdPersona: " + persona.getId_persona());
        for (Cliente c:clientes) {
            if(c.getPersonaId().getId_persona()==persona.getId_persona())
            {
                log.info(ClienteService.class.getSimpleName()+"Cliente Por IdPersona: " + c.getPersonaId().getId_persona());
                return c;
            }
        }
        return null;
    }


}
