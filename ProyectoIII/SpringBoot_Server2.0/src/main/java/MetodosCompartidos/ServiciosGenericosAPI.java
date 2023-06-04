package MetodosCompartidos;

/// Esta clase usa como parametro otras clases, y a partir de eso , empieza a trabajar
/// sobre ellas por medio de método, es una forma de reciclar código

import java.io.Serializable;
import java.util.List;

public interface ServiciosGenericosAPI <clase, ID extends Serializable> {
    clase guardar(clase entity);
    void eliminar(ID id);
    clase get(ID id);
    List<clase> getAll();
}
