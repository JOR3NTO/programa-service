package co.edu.uceva.programaservice;



import co.edu.uceva.programaservice.model.entities.Programa;
import co.edu.uceva.programaservice.model.service.IProgramaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Pruebas unitarias (unit tests) para la API RESTful que se encarga de realizar operaciones CRUD sobre una entidad
 * llamada "Programa".
 * Se importan las clases necesarias para realizar las pruebas (MockMvc, ObjectMapper, etc.), se inyecta el servicio
 * que se encarga de realizar las operaciones sobre la entidad "Programa" (IProgramaService), y se definen varios métodos de
 * prueba para la API RESTful, que comprueban el correcto funcionamiento de los métodos:
 * GET, POST, PUT y DELETE de la API.
 */
@RunWith(SpringRunner.class)
@SpringBootTest


public class ProgramaRestControllerTests {

    /**
     * Esta anotación @Autowired permite la inyección de dependencia, lo que significa que
     * wac (WebApplicationContext) se inicializará automáticamente con el contexto de la aplicación web.
     * El WebApplicationContext proporciona acceso a los componentes y configuraciones de Spring.
     */
    @Autowired
    private WebApplicationContext wac;

    /**
     * MockMvc es una clase que se utiliza para simular las solicitudes HTTP y probar
     * controladores y endpoints sin necesidad de un navegador web en una prueba de integración.
     */
    private MockMvc mockMvc;


    @Autowired
    private IProgramaService programaService;


    /**
     * Inicializa los objetos necesarios para la prueba. En el ejemplo de código dado, este método se utiliza para
     * inicializar el objeto MockMvc, que se utiliza para simular el envío de solicitudes HTTP en la prueba de la
     * clase PaisRestController.
     */
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * Prueba del método GET "/api/programa-service/programa", que comprueba que se recibe una lista de programas en la respuesta.
     * @throws Exception Se lanza una excepción si no devuelve la lista de programas correcta.
     */
    @Test
    public void testListar() throws Exception {
        Programa programa1 = new Programa(null,1L, "Ingeniería de Sistemas", 12345, "Descripción Ingeniería de Sistemas", "imagen1.jpg", "Facultad de Ingeniería");
        Programa programa2 = new Programa(null, 2L, "Derecho", 54321, "Descripción Derecho", "imagen2.jpg", "Facultad de Derecho");
        programaService.save(programa1);
        programaService.save(programa2);
        this.mockMvc.perform(get("/api/programa-service/programas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombrePrograma", is(programa1.getNombrePrograma()))) //Trae la info de la bd y la compara con el json
                .andExpect(jsonPath("$[1].nombrePrograma", is(programa2.getNombrePrograma())));
        programaService.deletePrograma(programa1);
        programaService.deletePrograma(programa2);
    }
    /**
     * Prueba del método POST "/api/programa-service/programa", que comprueba que se crea un nuevo programa correctamente.
     * @throws Exception Se lanza una excepción si no se encuentra el país con el id especificado.
     */

    @Test
    public void testCrearPrograma() throws Exception {
        Programa programa = new Programa(null, 4L, "Ingenieria Industrial",
                465846, "Programa que forma Ingenieros Industriales", "Industriales.png");

        this.mockMvc.perform(post("/api/programa-service/programa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(programa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPrograma").exists()) // Verifica que idPrograma existe en el JSON
                .andExpect(jsonPath("$.nombrePrograma", is(programa.getNombrePrograma())))
                .andExpect(jsonPath("$.descripcionPrograma", is(programa.getDescripcionPrograma())))
                .andExpect(jsonPath("$.snies", is(programa.getSnies())));

        programaService.deletePrograma(programa);
    }


    /**
     * Prueba del método GET "/api/programa-service/programa", que comprueba que se recibe el país correcto en la respuesta.
     * @throws Exception Se lanza una excepción si no se encuentra el país con el id especificado.
     */

    @Test
    public void testFiltrarProgramas() throws Exception {
        // Crea el objeto Programa y guárdalo en la base de datos
        Programa programa = new Programa(null, 4L, "Derecho", 5678,
                "Programa que forma abogados", "urlDerecho.png");
        Programa programaAux = programaService.save(programa);
        System.out.println("ID PROGRAMA: "+programaAux.getIdPrograma());

        this.mockMvc.perform(get("/api/programa-service/programas/facultad/4", programa.getIdFacultad()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].idPrograma", is(programa.getIdFacultad().intValue())))
                .andExpect(jsonPath("$[0].nombrePrograma", is(programa.getNombrePrograma())))
                .andExpect(jsonPath("$[0].descripcionPrograma", is(programa.getDescripcionPrograma())))
                .andExpect(jsonPath("$[0].imagenPrograma", is(programa.getImagenPrograma())));

        programaService.deletePrograma(programa);
    }


    /**
     * Prueba del método PUT "/api/programa-service/pais", que comprueba que se actualiza un programa correctamente.
     * @throws Exception Se lanza una excepción si no se encuentra el país con el id especificado.
     */
    @Test
    public void testEditarPrograma() throws Exception {
        Programa programa = new Programa(null, 3L, "Ingenieria Industrial", 465846,
                "Programa que forma Ingenieros Industriales", "Industriales.png");
        Programa programaAux = programaService.save(programa);

        programaAux.setIdPrograma(2l);
        programaAux.setNombrePrograma("Derecho");
        programaAux.setDescripcionPrograma("Programa que forma abogados");
        programaAux.setImagenPrograma("urlDerecho.png");
        programaAux.setSnies(5678);

        this.mockMvc.perform(put("/api/programa-service/programa/{id}", programaAux.getIdPrograma())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(programaAux)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.programa.idPrograma").exists()) // Verifica que idPrograma existe en el JSON
                .andExpect(jsonPath("$.programa.nombrePrograma", is(programaAux.getNombrePrograma())))
                .andExpect(jsonPath("$.programa.descripcionPrograma", is(programaAux.getDescripcionPrograma())))
                .andExpect(jsonPath("$.programa.snies", is(programaAux.getSnies())));
        programaService.deletePrograma(programaAux);

    }
     /**
     * Prueba del metodo DELETE "/api/programa-service/programa/{idPrograma}", que comprueba que se elimina un programa correctamente.
     * @throws Exception Se lanza una excepcion si no se encuentra el programa con el id especificado.
     */
    @Test
    public void testDeletePrograma() throws Exception {
        Programa programa = new Programa(null, 3L, "Ingenieria Industrial", 465846, "Programa que forma Ingenieros Industriales", "Industriales.png");
        programa = programaService.save(programa);

        this.mockMvc.perform(delete("/api/programa-service/programa/{idPrograma}", programa.getIdPrograma()))
                .andExpect(status().isOk());
        assertNull(programaService.getProgramaById(programa.getIdPrograma()));
    }

    /**
     * Método para convertir un objeto a una cadena JSON
     *
     * @param obj Objeto a convertir
     * @return Cadena JSON
     */
    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
