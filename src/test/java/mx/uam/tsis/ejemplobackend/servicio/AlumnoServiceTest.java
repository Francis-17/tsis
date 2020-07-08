package mx.uam.tsis.ejemplobackend.servicio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.negocio.AlumnoService;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;

import java.util.Optional;

@ExtendWith(MockitoExtension.class) // Uso de Mockito
public class AlumnoServiceTest {
	
	@Mock
	private AlumnoRepository alumnoRepositoryMock; // Mock generado por Mockito
	
	@InjectMocks
	private AlumnoService alumnoService; // La unidad a probar
	
	@Test
	public void testSuccesfulCreate() {
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Simula lo que haría el alumnoRepository real cuando le pasan una matricula de alumno
		// que no ha sido guardado
		when(alumnoRepositoryMock.findById(12345678)).thenReturn(Optional.ofNullable(null));
		
		when(alumnoRepositoryMock.save(alumno)).thenReturn(alumno);
		
		// Aqui ejecuto a la unidad que quiero probar
		alumno = alumnoService.create(alumno);
		
		// Aqui compruebo el resultado
		assertNotNull(alumno); // Probar que la referencia a alumno no es nula
			
		
	}
	
	@Test
	public void testUnsuccesfulCreate() {
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Simula lo que haría el alumnoRepository real cuando le pasan una matricula de alumno
		// que ya ha sido guardado
		when(alumnoRepositoryMock.findById(12345678)).thenReturn(Optional.ofNullable(alumno));
		
		// Aqui ejecuto a la unidad que quiero probar
		alumno = alumnoService.create(alumno);
		
		// Aqui compruebo el resultado
		assertNull(alumno); // Probar que la referencia a alumno es nula por que el alumno ya existía
			
		
	}
	@Test
	public void testSuccesfulGet() {
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Simula lo que haría el alumnoRepository real cuando le pasan una matricula de alumno
		// que no ha sido guardado
		//when(alumnoRepositoryMock.findById(12345678)).thenReturn(Optional.ofNullable(alumno));
		
		
		//when(alumnoRepositoryMock.save(alumno)).thenReturn(alumno);

		// Aqui ejecuto a la unidad que quiero probar
		Iterable <Alumno> resul = alumnoService.retrieveAll();
		// Aqui compruebo el resultado
	}
	@Test
	public void testSuccesfulGetId() {
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Simula lo que haría el alumnoRepository real cuando le pasan una matricula de alumno
		// que no ha sido guardado
		when(alumnoRepositoryMock.findById(12345678)).thenReturn(Optional.ofNullable(alumno));

		// Aqui ejecuto a la unidad que quiero probar
		Optional <Alumno> resul = alumnoService.retrieve(12345678);
		// Aqui compruebo el resultado
		assertNotNull(resul); // Probar que la referencia a alumno no es nula
	}
	
	@Test
	public void testSuccesfulUpdte() {
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Simula lo que haría el alumnoRepository real cuando le pasan una matricula de alumno
		// que no ha sido guardado
		when(alumnoRepositoryMock.findById(12345678)).thenReturn(Optional.ofNullable(alumno));
		
		Alumno alumnoactualizado= new Alumno();
		alumnoactualizado.setCarrera("Fisica");
		alumnoactualizado.setNombre("Pruebin");
		alumno.setNombre(alumnoactualizado.getNombre());
		alumno.setCarrera(alumnoactualizado.getCarrera());
		when(alumnoRepositoryMock.save(alumno)).thenReturn(alumno);

		// Aqui ejecuto a la unidad que quiero probar
		alumno = alumnoService.update(12345678, alumno);
		
		// Aqui compruebo el resultado
		assertNotNull(alumno); // Probar que la referencia a alumno no es nula
			
		
	}
	@Test
	public void testUnSuccesfulUpdte() {
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Simula lo que haría el alumnoRepository real cuando le pasan una matricula de alumno
		// que no ha sido guardado
		when(alumnoRepositoryMock.findById(12345678)).thenReturn(Optional.ofNullable(null));
		
		Alumno alumnoactualizado= new Alumno();
		alumnoactualizado.setCarrera("Fisica");
		alumnoactualizado.setNombre("Pruebin");
		alumno.setNombre(alumnoactualizado.getNombre());
		alumno.setCarrera(alumnoactualizado.getCarrera());
		//when(alumnoRepositoryMock.save(alumno)).thenReturn(alumno);

		// Aqui ejecuto a la unidad que quiero probar
		alumno = alumnoService.update(12345678, alumno);
		
		// Aqui compruebo el resultado
		assertNull(alumno); // Probar que la referencia a alumno  es nula
			
		
	}
	
	@Test
	public void testSuccesfulDelete() {
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Simula lo que haría el alumnoRepository real cuando le pasan una matricula de alumno
		// que no ha sido guardado
		when(alumnoRepositoryMock.findById(12345678)).thenReturn(Optional.ofNullable(alumno));
		
		// Aqui ejecuto a la unidad que quiero probar
		boolean result = alumnoService.delete(12345678);
		
		// Aqui compruebo el resultado
		assertTrue(result);
	}
	@Test
	public void testUnSuccesfulDelete() {
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345676);
		alumno.setNombre("Pruebin");
		
		// Simula lo que haría el alumnoRepository real cuando le pasan una matricula de alumno
		// que no ha sido guardado
		when(alumnoRepositoryMock.findById(12345676)).thenReturn(Optional.ofNullable(null));
		
		     // Aqui ejecuto a la unidad que quiero probar
		     boolean result = alumnoService.delete(123456786);
		
		// Aqui compruebo el resultado
		assertFalse(result);

	}
	

}
