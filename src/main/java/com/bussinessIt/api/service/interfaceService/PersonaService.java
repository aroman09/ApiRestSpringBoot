package com.bussinessIt.api.service.interfaceService;

import com.bussinessIt.api.entity.Persona;

import java.util.List;

public interface PersonaService {
    public Persona crearPersona(Persona persona);
    public Persona actualizarPersona(Persona persona);
    public List<Persona> listarPersonas();
    public boolean eliminarPersona(Long idPersona);
    public boolean existePersona(Long idPersona);
    public Persona obtenerPersona(Long idPersona);
}
