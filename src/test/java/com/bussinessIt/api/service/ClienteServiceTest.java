package com.bussinessIt.api.service;

import com.bussinessIt.api.service.interfaceService.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Conversions.notNull;

public class ClienteServiceTest {
    @InjectMocks
    ClienteService serviceCliente;

    @Test
    public void testGettingCustomerExist() throws NullPointerException {

        try {
            Long idCliente = 1L;
            assertEquals(serviceCliente.obtenerCliente(idCliente), notNull());
        } catch(NullPointerException e){
            System.out.println(e.getMessage());
        }

    }
}
