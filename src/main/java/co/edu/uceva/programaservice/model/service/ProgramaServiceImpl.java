package co.edu.uceva.programaservice.model.service;

import co.edu.uceva.programaservice.model.dao.ProgramaDao;
import co.edu.uceva.programaservice.model.entities.Programa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class ProgramaServiceImpl implements IProgramaService {

    ProgramaDao programaDao;

    @Autowired
    public ProgramaServiceImpl(ProgramaDao programaDao) {
        this.programaDao = programaDao;
    }

    @Override
    public Programa getProgramaById(Long idPrograma) {
        return programaDao.findById(idPrograma).orElse(null);
    }

    /**
     * Este metodo elimina un programa
     * @param programa a eliminar
     */
    @Override
    public void deletePrograma(Programa programa) {
        programaDao.delete(programa);
    }
}
