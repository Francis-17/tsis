package mx.uam.tsis.ejemplobackend.datos;


import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import org.springframework.data.repository.CrudRepository;

/**
 * Se encarga de almacenar y recuperar alumnos
 * 
 * @author Fan
 *
 */
@Component
@Slf4j
public interface  AlumnoRepository extends CrudRepository <Alumno, Integer>{
	
	
 
}