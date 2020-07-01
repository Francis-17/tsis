package mx.uam.tsis.ejemplobackend.negocio.modelo;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity // Indica que hay que persistir en BD
public class Alumno {
	@NotNull
	@ApiModelProperty(notes = "Matricula del alumno", required = true)
	@Id//indica que es la llave primaria 
	private Integer matricula;
	
	@NotBlank
	@ApiModelProperty(notes = "Nombre del alumno", required = true)
	private String nombre;

	@NotBlank
	@ApiModelProperty(notes = "Carrera del alumno", required = true)
	private String carrera;
    
	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
}
