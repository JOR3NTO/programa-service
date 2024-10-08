package co.edu.uceva.programaservice.model.entities;


import jakarta.persistence.*;
import lombok.*;

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
    private String nombrePrograma;

    private int SNIES;

    private String descripcionPrograma;

    private String imagenPrograma;
}
