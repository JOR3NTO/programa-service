package co.edu.uceva.programaservice.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Entity   //Esto es parte de JPA que es Java Persistence API
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="programa")


public class Programa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrograma;
    @Column(name = "facultad")
    private String facultad;
    @Column(name = "nombre_programa")
    private String nombrePrograma;
    @Column(name = "snies")
    private int SNIES;
    @Column(name = "descripcion_programa")
    private String descripcionPrograma;
    @Column(name = "imagen_programa")
    private String imagenPrograma;


}
