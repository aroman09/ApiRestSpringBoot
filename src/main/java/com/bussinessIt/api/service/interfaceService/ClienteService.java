package com.bussinessIt.api.service.interfaceService;

import com.bussinessIt.api.entity.Cliente;
import com.bussinessIt.api.entity.Persona;

import java.util.List;

public interface ClienteService {
    public Cliente crearCliente(Cliente cliente);
    public Cliente actualizarCliente(Cliente cliente);
    public List<Cliente> listarClientes();
    public boolean eliminarCliente(Long idCliente);
    public boolean existeCliente(Long idCliente);
    public Cliente obtenerCliente(Long idCliente);
    public Cliente obtenerClientePorPersona(Persona persona);

}
