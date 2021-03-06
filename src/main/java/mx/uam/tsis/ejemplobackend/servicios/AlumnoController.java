package mx.uam.tsis.ejemplobackend.servicios;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.AlumnoService;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;

/**
 * Controlador para el API rest
 * 
 * @author Fran
 *
 */
@RestController
@Slf4j
public class AlumnoController {
	
	// La "base de datos"
	//private Map <Integer, Alumno> alumnoRepository = new HashMap <>();
	@Autowired
	private AlumnoService alumnoService;
	
	@ApiOperation(
			value = "Crear alumno",
			notes = "Permite crear un nuevo alumno, la matrícula debe ser única"
			) // Documentacion del api
	
	@PostMapping(path = "/alumnos", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Alumno nuevoAlumno) {
		
		//log.info("Recibí llamada a create con "+nuevoAlumno);
		
		Alumno alumno = alumnoService.create(nuevoAlumno);
		
		if(alumno != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(alumno);			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede crear alumno");
		}
	}
	
	@ApiOperation(
			value = "Recupera  alumnos",
			notes = "Permite recuperar la lista de los alumnos registrados"
			) // Documentacion del api
	@GetMapping(path = "/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		Iterable <Alumno> result = alumnoService.retrieveAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(result); 
		
	}
	
	@ApiOperation(
			value = "recupera  alumno",
			notes = "Permite recuperar un alumno por su matricula, la matrícula debe se existir"
			) // Documentacion del api
	@GetMapping(path = "/alumnos/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieve(@PathVariable("matricula") Integer matricula) {
		Optional <Alumno> alumno = alumnoService.retrieve(matricula);
		if(alumno != null) {
			return  ResponseEntity.status(HttpStatus.OK).body(alumno);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado");
		}
		
		
	}
	
	@ApiOperation(
			value = "Edita alumno",
			notes = "Permite editar un alumno partiendo de su matricula, la matrícula debe de existir"
			) // Documentacion del api
	@PutMapping(path = "/alumnos/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?>update(@PathVariable("matricula") Integer matricula,@RequestBody  @Valid Alumno alumnoActualizado) {
		
        //Alumno alumno = alumnoRepository.put(nuevoAlumno.getMatricula(), nuevoAlumno);
		Alumno alumno = alumnoService.update(matricula,alumnoActualizado);
		if(alumno != null) {
			return ResponseEntity.status(HttpStatus.OK).body(alumno);
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(" alumno no  modificado");
		}
	}
	
	@ApiOperation(
			value = "Elimina alumno",
			notes = "Permite eliminar un alumno apatir de su matricula, la matrícula debe de existir"
			) // Documentacion del api
	@DeleteMapping(path = "/alumnos/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> delete(@PathVariable("matricula") Integer matricula) {
		 //Alumno alumno= alumnoRepository.remove(matricula);
		boolean alumnoBorrado=alumnoService.delete(matricula);
		if(alumnoBorrado == true) {
			return ResponseEntity.status(HttpStatus.OK).body("Alumno con matricula "+ matricula+"borrado");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el alumno "+ matricula+"no borrado");
		}
	}
 
}
