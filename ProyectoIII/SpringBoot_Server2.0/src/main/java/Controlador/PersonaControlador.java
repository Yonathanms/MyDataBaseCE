package Controlador;

import Modelo.Persona;
import Servicios.PersonaServicioAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonaControlador {

    @Autowired
    private PersonaServicioAPI personaServicioAPI;

    @RequestMapping("/")
    public String index (Model model) {
        model.addAttribute("lista", personaServicioAPI.getAll());
        return "index";
    }

    @GetMapping("/save{id}")
    public  String showSave(@PathVariable("id") Long id, Model model){
        if (id != null) {
            model.addAttribute("persona", personaServicioAPI.get(id));
        }
        return "save";
    }

    /// Los metodos provienen de los servicios genericos
    @PostMapping("/save")
    public String guardar(Persona persona, Model model){
        personaServicioAPI.guardar(persona);
        return "redirect:/";
    }

    @GetMapping ("/delete/{id}")
    public String eliminar(@PathVariable Long id, Model model) {
        personaServicioAPI.eliminar(id);

        return "redirect:/";
    }
}
