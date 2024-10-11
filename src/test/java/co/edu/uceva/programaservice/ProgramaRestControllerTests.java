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
     * Prueba del método POST "/api/programa-service/programa", que comprueba que se crea un nuevo programa correctamente.
     * @throws Exception Se lanza una excepción si no se encuentra el país con el id especificado.
     */

    @Test
    public void testCrearPrograma() throws Exception {
        Programa programa = new Programa();

        this.mockMvc.perform(post("/api/programa-service/programa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(programa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.programa", is(programa.getIdPrograma())));
        programaService.deletePrograma(programa);
    }

    /**
     * Prueba del método GET "/api/programa-service/programa", que comprueba que se recibe el país correcto en la respuesta.
     * @throws Exception Se lanza una excepción si no se encuentra el país con el id especificado.
     */

    @Test
    public void testFiltrarProgramas() throws Exception {
        Programa programas = new Programa(null, 2L,"Derecho", 5678, "Programa que forma abogados", "urlDerecho.png", "A");
        programaService.save(programas);

        this.mockMvc.perform(get("/api/programa-service/programa/{id}", programas.getIdFacultad()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Programas", is(programas.getIdFacultad())));

        programaService.deletePrograma(programas);
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
