package co.edu.uceva.programaservice.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity   //Esto es parte de JPA que es Java Persistence API
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor



public class Programa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrograma;
    private Long idFacultad;
    private String nombrePrograma;
    private int snies;
    private String descripcionPrograma;
    private String imagenPrograma;
    private String facultad;

    /**
     private long idFacultad;*
     @ManyToOne
     private Facultad facultad*/

}
