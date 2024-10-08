package co.edu.uceva.programaservice.model.service;

import co.edu.uceva.programaservice.model.dao.ProgramaDao;
import co.edu.uceva.programaservice.model.entities.Programa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProgramaServiceImpl implements IProgramaService {

    ProgramaDao programaDao;

    @Autowired
    public ProgramaServiceImpl(ProgramaDao programaDao) {
        this.programaDao = programaDao;
    }



    /**
     * Este metodo se encarga de guardar un programa
     * @param programa objeto de tipo Programa que se va a guardar
     * @return retorna un objeto de tipo Programa
     */
    @Override
    public Programa save(Programa programa) {
        return programaDao.save(programa);
    }
}
