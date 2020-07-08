package mx.uam.tsis.ejemplobackend.servicio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;

import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.datos.GrupoRepository;
import mx.uam.tsis.ejemplobackend.negocio.AlumnoService;
import mx.uam.tsis.ejemplobackend.negocio.GrupoService;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;

import java.util.Optional;

@ExtendWith(MockitoExtension.class) // Uso de Mockito
public class GrupoServiceTest {

	@Mock
	private GrupoRepository grupoRepositoryMock;
	
	@Mock
	private AlumnoService alumnoServiceMock;
	
	@InjectMocks
	private GrupoService grupoService;
	
	@Test
	public void testSuccesfulAddStudentToGroup (){
		
		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("TST01");

		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Stubbing para el alumnoService
		when(alumnoServiceMock.findByMatricula(12345678)).thenReturn(alumno);
		
		// Stubbing para grupoRepository
		when(grupoRepositoryMock.findById(grupo.getId())).thenReturn(Optional.of(grupo));
		
		
		boolean result = grupoService.addStudentToGroup(1, 12345678);
		
		assertEquals(true,result);
		
		assertEquals(grupo.getAlumnos().get(0),alumno);
		
	}
	
	@Test
	public void testUnsuccesfulAddStudentToGroup (){
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computación");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Stubbing para el alumnoService
		when(alumnoServiceMock.findByMatricula(12345678)).thenReturn(alumno);
		
		// Stubbing para grupoRepository
		when(grupoRepositoryMock.findById(anyInt())).thenReturn(Optional.ofNullable(null));
		
		
		boolean result = grupoService.addStudentToGroup(1, 12345678);
		
		assertEquals(false,result);
		
		
	}
	
	@Test
	public void testSuccesfulGet() {
		
		Grupo grupo = new Grupo();
		grupo.setId(2);
		grupo.setClave("CLK4");
		grupo.setAlumnos(null);

		// Aqui ejecuto a la unidad que quiero probar
		Iterable <Grupo> resul = grupoService.retrieveAll();
		// Aqui compruebo el resultado
	}
	@Test
	public void testSuccesfulGetId() {
		
		Grupo grupo = new Grupo();
		grupo.setId(2);
		grupo.setClave("CLK4");
		grupo.setAlumnos(null);
		
		when(grupoRepositoryMock.findById(2)).thenReturn(Optional.ofNullable(grupo));

		// Aqui ejecuto a la unidad que quiero probar
		Optional <Grupo> resul = grupoService.retrieve(2);
		// Aqui compruebo el resultado
		assertNotNull(resul); 
	}
	
	@Test
	public void testSuccesfulUpdte() {
		
		Grupo grupo = new Grupo();
		grupo.setId(2);
		grupo.setClave("CLK4");
		grupo.setAlumnos(null);
		
		
		when(grupoRepositoryMock.findById(2)).thenReturn(Optional.ofNullable(grupo));
		
		Grupo grupoactualizado= new Grupo();
		grupoactualizado.setClave("CH02");
		grupo.setClave(grupoactualizado.getClave());
		when(grupoRepositoryMock.save(grupo)).thenReturn(grupo);

		// Aqui ejecuto a la unidad que quiero probar
		grupo = grupoService.update(2, grupo);
		
		// Aqui compruebo el resultado
		assertNotNull(grupo); // Probar que la referencia a alumno no es nula
			
		
	}
	@Test
	public void testUnSuccesfulUpdte() {
		Grupo grupo = new Grupo();
		grupo.setId(2);
		grupo.setClave("CLK4");
		grupo.setAlumnos(null);
		
		
		when(grupoRepositoryMock.findById(2)).thenReturn(Optional.ofNullable(null));
		
		Grupo grupoactualizado= new Grupo();
		grupoactualizado.setClave("CH02");
		grupo.setClave(grupoactualizado.getClave());
		when(grupoRepositoryMock.save(grupo)).thenReturn(grupo);

		// Aqui ejecuto a la unidad que quiero probar
		grupo = grupoService.update(2, grupo);
		
		// Aqui compruebo el resultado
		
		assertNotNull(grupo); // Probar que la referencia a alumno  es nula
			
		
	}
	
	@Test
	public void testSuccesfulDelete() {
		
		Grupo grupo = new Grupo();
		grupo.setId(2);
		grupo.setClave("CLK4");
		grupo.setAlumnos(null);
		
		
		when(grupoRepositoryMock.findById(2)).thenReturn(Optional.ofNullable(grupo));
		
		// Aqui ejecuto a la unidad que quiero probar
		boolean result = grupoService.delete(2);
		
		// Aqui compruebo el resultado
		assertTrue(result);
	}
	@Test
	public void testUnSuccesfulDelete() {
		
		Grupo grupo = new Grupo();
		grupo.setId(2);
		grupo.setClave("CLK4");
		grupo.setAlumnos(null);
		
		
		when(grupoRepositoryMock.findById(2)).thenReturn(Optional.ofNullable(null));
		
		     // Aqui ejecuto a la unidad que quiero probar
		     boolean result = grupoService.delete(2);
		
		// Aqui compruebo el resultado
		assertFalse(result);

	}
	
}
