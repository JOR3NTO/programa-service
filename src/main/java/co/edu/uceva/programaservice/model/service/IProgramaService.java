package co.edu.uceva.programaservice.model.service;


import co.edu.uceva.programaservice.model.entities.Programa;

/**
 * Esta interfaz define los metodos que se van a implementar en la clase ProgramaServiceImpl
 */

public interface IProgramaService {
    Programa save(Programa programa);//Guarda un programa y me retorna un objeto de tipo Programa
}
