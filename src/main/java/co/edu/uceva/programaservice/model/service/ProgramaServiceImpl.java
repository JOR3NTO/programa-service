package co.edu.uceva.programaservice.model.service;

import co.edu.uceva.programaservice.ProgramaServiceApplication;
import co.edu.uceva.programaservice.model.dao.ProgramaDao;
import co.edu.uceva.programaservice.model.entities.Programa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProgramaServiceImpl implements IProgramaService {

    private final ProgramaServiceApplication programaServiceApplication;
    ProgramaDao programaDao;

    @Autowired
    public ProgramaServiceImpl(ProgramaDao programaDao, ProgramaServiceApplication programaServiceApplication) {
        this.programaDao = programaDao;
        this.programaServiceApplication = programaServiceApplication;
    }

    public List<Programa> getProgramasByFacultadId(Long facultadId) {
        return (List<Programa>) programaDao.findByIdFacultad(facultadId);
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

    /**
     * Este metodo se encarga de guardar un programa
     * @param programa objeto de tipo Programa que se va a guardar
     * @return retorna un objeto de tipo Programa
     */
    @Override
    public Programa save(Programa programa) {
        return programaDao.save(programa);
    }

    /**Este metodo se encarga de listar todos los Programas
   @return retorna una lista de Programas
    */
    @Transactional(readOnly = true)
    @Override
    public List<Programa> listar() {
        return (List<Programa>) programaDao.findAll();
    }
}
