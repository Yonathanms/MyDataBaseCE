package MetodosCompartidos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/// Son los metodos que se van a aplicar a la clase persona
@Service
public abstract class ServiviosGenericosImplementacion<clase,ID extends Serializable> implements ServiciosGenericosAPI<clase,ID> {
    @Override
    public clase guardar(clase entity) {
        return getDao().save(entity);
    }

    @Override
    public void eliminar(ID id) {
        getDao().deleteById(id);
    }

    @Override
    public clase get(ID id) {
        Optional <clase> pers = getDao().findById(id);
        if (pers.isPresent()) {
            return pers.get();
        }
        return null;
    }

    @Override
    public List<clase> getAll() {
        List<clase> returnList = new ArrayList<>();
        getDao().findAll().forEach(pers -> returnList.add(pers));
        return returnList;
    }

    public abstract CrudRepository<clase, ID> getDao();
}
