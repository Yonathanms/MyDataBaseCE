package com.proyecto.springboot3_1.servicios;
///Los servicios de la clase deben ir en este paquete y ser de tipo interfaz
///En los servicios, se definen los metodos que se realizan sobbre la clase

import com.proyecto.springboot3_1.modelo.Persona;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonaServicio {

    ///metodo para agregar persona
    public Persona agregar_persona(Persona persona);

    ///metodo para obtener persona segun su id
    public Persona verificar_personaID(long id);

    ///metodod para verificar cuenta de persona
    public boolean verificar_cuenta(String correo, String clave);

    public List<Persona> get_personas();
}