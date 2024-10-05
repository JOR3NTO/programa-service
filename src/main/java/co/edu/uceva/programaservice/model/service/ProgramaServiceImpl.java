package co.edu.uceva.programaservice.model.service;

import co.edu.uceva.programaservice.model.dao.ProgramaDao;
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

}
