package co.edu.uceva.programaservice.model.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity   //Esto es parte de JPA que es Java Persistence API
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor




public class Programa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrograma;
    private String nombrePrograma;
    private int SNIES;
    private String descripcionPrograma;
    private String imagenPrograma;


    /*

    public Long getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Long idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public int getSNIES() {
        return SNIES;
    }

    public void setSNIES(int SNIES) {
        this.SNIES = SNIES;
    }

    public String getDescripcionPrograma() {
        return descripcionPrograma;
    }

    public void setDescripcionPrograma(String descripcionPrograma) {
        this.descripcionPrograma = descripcionPrograma;
    }

    public String getImagenPrograma() {
        return imagenPrograma;
    }

    public void setImagenPrograma(String imagenPrograma) {
        this.imagenPrograma = imagenPrograma;
    }

    */
}
