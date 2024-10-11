package co.edu.uceva.programaservice.controller;

import co.edu.uceva.programaservice.model.entities.Programa;
import co.edu.uceva.programaservice.model.service.IProgramaService;
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
     * Este metodo se encarga de retornar un saludo con el nombre que se le pase por parametro en la url
     *
     * @param nombrePrograma nombre del programa
     * @return retorna un string con su programa es ...


    @GetMapping("/programas/{nombres}")
    public String holaMundo(@PathVariable("nombres") String nombrePrograma) {
        return "su programa es " + nombrePrograma;
    }
     */




    @GetMapping("/programas")
    public List<Programa> listar(){
        return this.programaService.listar() ;
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


    @DeleteMapping
    public void deletePrograma(@PathVariable Long idPrograma) {
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
            programaActual.setSNIES(programa.getSNIES());


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
