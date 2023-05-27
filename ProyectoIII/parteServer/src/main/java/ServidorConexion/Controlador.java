//Manejador del servidor haciendo uso de SpringBoot

package ServidorConexion;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")

public class Controlador {

    @GetMapping("/instances")
    public String getInstances() {
        // Lógica para obtener las instancias
        return "Obteniendo instancias...";
    }

    @GetMapping("/instances/{id}")
    public String getInstanceById(@PathVariable int id) {
        // Lógica para obtener una instancia por ID
        return "Obteniendo instancia con ID: " + id;
    }

    @PostMapping("/instances")
    public String createInstance() {
        // Lógica para crear una instancia
        return "Creando instancia...";
    }

    @PutMapping("/instances/{id}")
    public String updateInstance(@PathVariable int id) {
        // Lógica para actualizar una instancia por ID
        return "Actualizando instancia con ID: " + id;
    }

    @DeleteMapping("/instances/{id}")
    public String deleteInstance(@PathVariable int id) {
        // Lógica para eliminar una instancia por ID
        return "Eliminando instancia con ID: " + id;
    }
}
