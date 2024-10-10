package co.edu.uceva.programaservice.model.dao;

import co.edu.uceva.programaservice.model.entities.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProgramaDao extends JpaRepository<Programa, Long> {
    List<Programa> findByIdFacultad(long idFacultad);
}
