package co.edu.uceva.programaservice.model.dao;

import co.edu.uceva.programaservice.model.entities.Programa;
import org.springframework.data.repository.CrudRepository;

public interface ProgramaDao extends CrudRepository<Programa, Long> {
}
