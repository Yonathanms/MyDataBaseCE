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
    public String hola_mundo() {
        return "Hola Mundoo!!";   //metodo de prueba
    }


    ///metodo de agregar persona
    @PostMapping("/add")
    public String agregar_Persona(@RequestBody Persona persona) {
        personaServicio.agregar_persona(persona);
        return "Persona agregada con exito..";
    }

    ///metodo de obtener persona segun su id
    @RequestMapping("/consultapersona/{id}")
    public Persona verificar_personaID(@PathVariable("id") long id) {
        return personaServicio.verificar_personaID(id);
    }

    ///metodo que verifica si la persona ya fue registrada
    @RequestMapping("verificacion/{correo}/{clave}")
    public Boolean verificar_cuenta(@PathVariable("correo") String correo, @PathVariable("clave") String clave) {
        return personaServicio.verificar_cuenta(correo,clave);
    }


}


