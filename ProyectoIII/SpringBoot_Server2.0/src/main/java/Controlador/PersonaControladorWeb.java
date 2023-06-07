package Controlador;

import Modelo.Persona;
import Servicios.PersonaServicioAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class PersonaControladorWeb {

    @Autowired
    private PersonaServicioAPI personaServicioAPI;

    ///Mostrar toda la lista
    @GetMapping(value = "/all")
    public List<Persona> getAll(){
        return personaServicioAPI.getAll();
    }

    ///funcion de guardar personas
    @PostMapping(value = "/save")
    public ResponseEntity<Persona> guardar(@RequestBody Persona persona){
        Persona obj = personaServicioAPI.guardar(persona);
        return new ResponseEntity<Persona>(obj, HttpStatus.OK);
    }

    ///funcion de eliminar personas
    @GetMapping("/delet/{id}")
    public ResponseEntity<Persona> eliminar(@PathVariable Long id){
        Persona persona = personaServicioAPI.get(id);
        personaServicioAPI.eliminar(id);
        if (persona != null){
            personaServicioAPI.eliminar(id);
        } else {
            return new ResponseEntity<Persona>(persona, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }
}
