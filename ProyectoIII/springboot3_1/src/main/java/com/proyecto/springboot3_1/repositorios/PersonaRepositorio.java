package com.proyecto.springboot3_1.repositorios;
///creacion del repositorio para persona, todos los repositorios de las clases
///deben de ir en este paquete y ser una clase de tipo interfaz

import com.proyecto.springboot3_1.modelo.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepositorio extends CrudRepository<Persona, Long> {
    Persona findByCorreoAndClave(String correo, String clave);
}
