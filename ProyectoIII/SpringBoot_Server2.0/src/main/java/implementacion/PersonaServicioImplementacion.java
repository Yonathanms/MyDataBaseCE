package implementacion;

import MetodosCompartidos.ServiviosGenericosImplementacion;
import Modelo.Persona;
import Repositorios_dao.PersonaRepositorioApi;
import Servicios.PersonaServicioAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaServicioImplementacion extends ServiviosGenericosImplementacion<Persona, Long> implements PersonaServicioAPI {

    @Autowired
    private PersonaRepositorioApi personaRepositorioApi;
    @Override
    public CrudRepository<Persona, Long> getDao() {
        return personaRepositorioApi;
    }
}
