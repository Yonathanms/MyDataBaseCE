package Repositorios_dao;
import Modelo.Persona;

//Esta importacion es del crud y es como un tipo de frameworkweb pero local, hace la tarea del Vue
import org.springframework.data.repository.CrudRepository;
public interface PersonaRepositorioApi extends CrudRepository<Persona, Long> {
}
