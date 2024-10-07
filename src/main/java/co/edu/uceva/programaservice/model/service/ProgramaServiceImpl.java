package co.edu.uceva.programaservice.model.service;

import co.edu.uceva.programaservice.ProgramaServiceApplication;
import co.edu.uceva.programaservice.model.dao.ProgramaDao;
import co.edu.uceva.programaservice.model.entities.Programa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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

    public List<Programa> filtrarporfacultad(List<Programa> programa, String facultad) {
        // Filtramos los programas por facultad utilizando el metodo getfacultad
        return programa.stream()
                .filter(programaServiceApplication -> programa.getfacultad().equalsIgnoreCase(facultad))
                .collect(Collectors.toList());
    }
}
