package co.edu.uceva.programaservice.model.service;


import co.edu.uceva.programaservice.model.entities.Programa;

import java.util.List;

import co.edu.uceva.programaservice.model.entities.Programa;

/**
 * Esta interfaz define los metodos que se van a implementar en la clase ProgramaServiceImpl
 */

public interface IProgramaService {

    List<Programa> listar();
    void deletePrograma(Programa programa); // Elimina un programa
    Programa getProgramaById(Long idPrograma); // Busca Programa por id
    Programa save(Programa programa);//Guarda un programa y me retorna un objeto de tipo Programa
    Programa editar(Programa programa);
}
