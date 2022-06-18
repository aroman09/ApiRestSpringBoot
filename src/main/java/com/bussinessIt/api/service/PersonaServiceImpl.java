package com.bussinessIt.api.service;

import com.bussinessIt.api.entity.Persona;
import com.bussinessIt.api.service.interfaceService.PersonaService;
import com.bussinessIt.api.repository.PersonaRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@Slf4j
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    private PersonaRepository personaRepository;


    @Override
    public Persona crearPersona(Persona persona) {
        log.info(PersonaService.class.getSimpleName()+" Crear persona: " + persona.toString());
        return personaRepository.save(persona);
    }

    @Override
    public Persona actualizarPersona(Persona persona) {
        log.info(PersonaService.class.getSimpleName()+" Actualizar persona: " + persona.toString());
        return personaRepository.save(persona);
    }

    @Override
    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public boolean eliminarPersona(Long idPersona) {
        log.info(PersonaService.class.getSimpleName()+" Eliminar persona: " + idPersona);
        if(existePersona(idPersona)){
            personaRepository.deleteById(idPersona);
            log.info(PersonaService.class.getSimpleName()+" Cliente eliminado: " + idPersona+" - "+true);
            return true;
        }
        log.info(PersonaService.class.getSimpleName()+" Cliente eliminado: " + idPersona+" - "+false);
        return false;
    }
    @Override
    public boolean existePersona(Long idPersona) {
        return obtenerPersona(idPersona)==null;
    }

    @Override
    public Persona obtenerPersona(Long idPersona) {
        log.info(PersonaService.class.getSimpleName()+" Recuperar persona: " + idPersona);
        Persona persona = null;
        try{
            persona = personaRepository.findById(idPersona).get();
            log.info(PersonaService.class.getSimpleName()+" Persona recuperado: "+persona.toString());
        }catch (Exception ex){
            log.error(PersonaService.class.getSimpleName()+ex.getMessage());
        }finally {
            return persona;
        }
    }
}
