package co.edu.uceva.programaservice.model.service;


import co.edu.uceva.programaservice.model.entities.Programa;

/**
 * Esta interfaz define los metodos que se van a implementar en la clase ProgramaServiceImpl
 */

public interface IProgramaService {
    void deletePrograma(Programa programa); // Elimina un programa
    Programa getProgramaById(Long idPrograma); // Busca Programa por id
}
