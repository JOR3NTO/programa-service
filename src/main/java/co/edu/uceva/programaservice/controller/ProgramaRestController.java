package co.edu.uceva.programaservice.controller;

import co.edu.uceva.programaservice.model.entities.Programa;
import co.edu.uceva.programaservice.model.service.IProgramaService;
import co.edu.uceva.programaservice.model.service.ProgramaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
     * Este metodo se encarga de retornar un saludo con el nombre que se le pase por parametro en la url
     *
     * @param nombrePrograma nombre del programa
     * @return retorna un string con su programa es ...
     */

    @GetMapping("/programas/{nombres}")
    public String holaMundo(@PathVariable("nombres") String nombrePrograma) {
        return "su programa es " + nombrePrograma;
    }
    @GetMapping("/programas")
    public List<Programa> listar(){
        return this.programaService.listar() ;
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

}
