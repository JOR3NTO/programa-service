package co.edu.uceva.programaservice.controller;

import co.edu.uceva.programaservice.model.entities.Programa;
import co.edu.uceva.programaservice.model.service.IProgramaService;
import co.edu.uceva.programaservice.model.entities.Programa;
import co.edu.uceva.programaservice.model.service.ProgramaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    /*@GetMapping("/programas/page/{page}")
    public Page<Programa> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);  // Número de página y tamaño de página (4 elementos por página)
        return programaService.listar(pageable);
    }*/

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

}
