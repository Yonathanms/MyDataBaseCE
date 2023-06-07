package com.proyecto.springboot3_1.controlador;
///El controlador de una clase es el que permite comunicar el servidor y base de datos
/// al cliente, se usa un formato de comunicacion http

import com.proyecto.springboot3_1.modelo.Persona;
import com.proyecto.springboot3_1.servicios.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaWebControlador {

    ///importamos los servicios de esta clase al controlador
    @Autowired
    private PersonaServicio personaServicio;


    @RequestMapping("/")
    public String hola_mundo(){
        return "Hola Mundoo!!";   //metodo de prueba
    }


    ///metodo de agregar persona
    @PostMapping("/add")
    public String agregar_Persona(@RequestBody Persona persona){
        personaServicio.agregar_persona(persona);
        return "Persona agregada con exito..";
    }

    ///metodo de obtener persona segun su id
    @RequestMapping("/consultapersona/{id}")
    public  Persona get_PersonaID(@PathVariable("id") long id){
        return personaServicio.get_personaID(id);
    }

    ///metodo de obtener a todas las personas de la BD
    @RequestMapping("/personas")
    public List<Persona> get_Personas(){
        return personaServicio.get_personas();
    }

    ///metodo que actualiza a la persona
    @PutMapping("/persona")
    public Persona actualizar_Persona(@RequestBody Persona persona){
        return personaServicio.actualizar_Persona(persona);
    }

    ///metodo que elimina a una persona segun su id
    @DeleteMapping("/eliminarpersona/{id}")
    public String eliminar_PersonaID(@PathVariable("id") long id){
        personaServicio.eliminar_personaID(id);
        return "Persona eliminada con exito..";
    }
}
