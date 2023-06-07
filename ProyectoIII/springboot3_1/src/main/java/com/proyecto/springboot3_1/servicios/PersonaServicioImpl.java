package com.proyecto.springboot3_1.servicios;
///los servicios con el termino "Impl" se refiere a la implementacion de los metodos
/// que fueron creados en la interfaz alojados en este mismo paquete de la misma clase

import com.proyecto.springboot3_1.modelo.Persona;
import com.proyecto.springboot3_1.repositorios.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServicioImpl implements PersonaServicio {

    ///incluimos es repositorio de la clase para que los metodos puedan hacer uso
    ///de los atributos de la clase persona en este caso
    @Autowired
    private PersonaRepositorio personaRepositorio;

    @Override
    public Persona agregar_persona(Persona persona) {
        return personaRepositorio.save(persona);   //guarda a la persona
    }

    @Override
    public Persona get_personaID(long id) {
        return personaRepositorio.findById(id).get();  //obtiene a una persona segun su "id"
    }

    @Override
    public List<Persona> get_personas() {
        return (List<Persona>) personaRepositorio.findAll();  //obtiene a todas las personas de la BD
    }

    @Override
    public void eliminar_personaID(long id) {
        personaRepositorio.deleteById(id);  //elimina a una persona dependiendo de su id
    }

    @Override
    public Persona actualizar_Persona(Persona persona) {
        return personaRepositorio.save(persona);  //actualiza a una persona de la base de datos
    }
}
