package mx.uam.tsis.ejemplobackend.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import java.util.Optional;
/**
 * clase que contiene la logica del negocio del alumno
 * 
 * @author fran
 *
 */
@Service
public class AlumnoService {

	@Autowired
	private AlumnoRepository alumnoRepository;
	
	/**
	 * Metodo queperomite crear nuevos alumnos 
	 * @param nuevoAlumno
	 * @return el alumno que se acaba de crear si la creacion es exitosa, null de lo contrario
	 */
public Alumno create(Alumno nuevoAlumno) {
		
		// Regla de negocio: No se puede crear más de un alumno con la misma matricula
		Optional <Alumno> alumnoOpt = alumnoRepository.findById(nuevoAlumno.getMatricula());
		
		if(!alumnoOpt.isPresent()) {
			
			return alumnoRepository.save(nuevoAlumno);
			
		} else {
			
			return null;
			
		}
		
	}

	
	/**
	 * 
	 * @return
	 */
/*
	public List <Alumno> retrieveAll () {
		return alumnoRepository.find();
	}
	*/
	public Iterable <Alumno> retrieveAll () {
		return alumnoRepository.findAll();
	}
	
	public  Optional <Alumno> retrieve(Integer matricula) {
		//Alumno alumno = alumnoRepository.findByMatricula(matricula);
		//Optional <Alumno> alumnoOpt = alumnoRepository.findById(matricula);
		//return alumnoRepository.findByMatricula(matricula);
		return alumnoRepository.findById(matricula);
	}
	
	public  Alumno update(Integer matricula, Alumno alumnoactualizado) {
		Optional <Alumno>  alumno = alumnoRepository.findById(matricula);
		if(!alumno.isPresent()) {
			//alumno.setNombre(alumnoactualizado.getNombre());
			//alumno.setCarrera(alumnoactualizado.getCarrera());
			return alumnoRepository.save(alumnoactualizado);
		}else {
			return null;
		}
	}
	
	public boolean delete(Integer matricula) {
		Optional <Alumno> alumno = alumnoRepository.findById(matricula);
		if(alumno!=null) {
			alumnoRepository.deleteById(matricula);
			return true;
		}else {
			return false;
		}
		 
	}
}
