package co.edu.uceva.programaservice.model.service;

import co.edu.uceva.programaservice.model.dao.ProgramaDao;
import co.edu.uceva.programaservice.model.entities.Programa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class    ProgramaServiceImpl implements IProgramaService {

    ProgramaDao programaDao;

    @Autowired
    public ProgramaServiceImpl(ProgramaDao programaDao) {
        this.programaDao = programaDao;
    }


    /*Este metodo se encarga de listar todos los Programas
   @return retorna una lista de Programas
    */
    @Override
    public List<Programa> listar() {
        return (List<Programa>) programaDao.findAll();
    }
}

