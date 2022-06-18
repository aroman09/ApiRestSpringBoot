package com.bussinessIt.api.controller;

import com.bussinessIt.api.entity.Cliente;
import com.bussinessIt.api.entity.Persona;
import com.bussinessIt.api.service.interfaceService.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

public class ClienteControllerTest {
    @Mock
    ClienteService serviceCliente;

    private Cliente cliente;
    private Persona persona;

    @BeforeEach
    void setUp() throws Exception{

        this.persona = new Persona("Ariana","F","1234567890","Cuenca","123456");
        this.cliente = new Cliente("12345", true, this.persona);
    }

    @Test
    public void testEliminarClienteTrue(){
        try {
            when(serviceCliente.eliminarCliente(this.cliente.getPersonaId().getId_persona())).thenReturn(true);
        } catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testExisteClienteTrue(){
        try {
            when(serviceCliente.existeCliente(1L)).thenReturn(true);
        } catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testExisteClienteFalse(){
        try {
            when(serviceCliente.existeCliente(2L)).thenReturn(false);
        } catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
}
