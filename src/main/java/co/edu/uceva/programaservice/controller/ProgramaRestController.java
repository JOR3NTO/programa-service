package co.edu.uceva.programaservice.controller;

import co.edu.uceva.programaservice.model.entities.Programa;
import co.edu.uceva.programaservice.model.service.IProgramaService;
import co.edu.uceva.programaservice.model.entities.Programa;
import co.edu.uceva.programaservice.model.service.ProgramaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Esta clase se encarga de exponer los servicios rest de la entidad programa y se mapea con la url /api/programa-service
 */
@RestController
@RequestMapping("/api/programa-service")
public class ProgramaRestController {
    @Autowired
    private ProgramaServiceImpl programaService; //Servicio de la entidad pais para realizar las operaciones CRUD

    // Inyectamos el servicio mediante el constructor
    public ProgramaRestController(ProgramaServiceImpl programaService) {
        this.programaService = programaService;
    }


    /**
     * Metodo que busca un programa por su id
     *
     * @param id id del programa a buscar
     * @return Programa encontrado
     */
    @GetMapping("/programas/{idPrograma}")
    public Programa buscarPrograma(@PathVariable("idPrograma") Long idPrograma) {
        return programaService.getProgramaById(idPrograma);
    }

    /**
     * Metodo que crea un programa
     *
     * @param programa programa a crear
     * @return Programa creado
     */
    @PostMapping("/programa")
    public Programa crearPrograma(@RequestBody Programa programa){
        return programaService.save(programa);
    }

     /**
     * Metodo que lista los programas
     *
     * @return Lista de programas
     */
    @GetMapping("/programas")
    public List<Programa> listar(){
        return this.programaService.listar() ;
    }

    /**
     * Este metodo se encarga de filtrar programas por facultad
     *
     * @param idFacultad nombre de facultad a filtrar
     * @return lista de programas pertenecientes a la facultad
     */
    @GetMapping("/programas/facultad/{id}")
    public List<Programa> filtrarPorFacultad(@PathVariable("id") Long idFacultad){
        List<Programa> programas = (List<Programa>) this.programaService.getProgramasByFacultadId(idFacultad);
        return programas;

    }


    /**
     * Metodo que elimina un Programa por id
     * @param idPrograma del Programa a eliminar
     */
    @DeleteMapping("/programa/{idPrograma}")
    public void deletePrograma(@PathVariable("idPrograma") Long idPrograma) {
        Programa programa = this.programaService.getProgramaById(idPrograma);
        this.programaService.deletePrograma(programa);
    }

    /**
     * Metodo que editar/actualizar un programa
     *
     * @param programa programa a editar
     * @return Programa editado
     */

    @PutMapping("/programa/{id}")

    public ResponseEntity<?> editarPrograma(@RequestBody Programa programa, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Programa programaActual = null;
        try {
            programaActual = this.programaService.getProgramaById(id);
            if (programaActual == null) {
                response.put("mensaje", "El programa con ID " + id + " no existe.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            programaActual.setIdPrograma(programa.getIdPrograma());
            programaActual.setNombrePrograma(programa.getNombrePrograma());
            programaActual.setDescripcionPrograma(programa.getDescripcionPrograma());
            programaActual.setImagenPrograma(programa.getImagenPrograma());
            programaActual.setSnies(programa.getSnies());


            Programa programaActualizado = this.programaService.save(programaActual);
            response.put("mensaje", "El programa ha sido actualizado con éxito.");
            response.put("programa", programaActualizado);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("mensaje", "Error al actualizar el programa.");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }


    /**
    public Programa actualizarPrograma(@RequestBody Programa programa){
        return programaService.editar(programa);
    }*/



}
