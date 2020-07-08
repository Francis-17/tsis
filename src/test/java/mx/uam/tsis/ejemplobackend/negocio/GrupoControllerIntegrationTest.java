package mx.uam.tsis.ejemplobackend.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import mx.uam.tsis.ejemplobackend.datos.GrupoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GrupoControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@BeforeEach
	public void prepare() {
		
		// Aqui se puede hacer cosas para preparar sus casos de prueba, incluyendo
		// agregar datos a la BD
		
	}

	@Test
	public void testaddStudentToGroup() {
		
		// Creo el alumno que voy a enviar
		Grupo grupo = new Grupo();
		grupo.setId(2);
		
		Alumno alumno = new Alumno();
		alumno.setMatricula(12345678);

		// Creo el encabezado
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type",MediaType.APPLICATION_JSON.toString());
		
		// Creo la petición con el grupo como body y el encabezado
		HttpEntity <Grupo> request = new HttpEntity <> (grupo, headers);
		// Creo la petición con el alumno como body y el encabezado
				HttpEntity <Alumno> reques = new HttpEntity <> (alumno, headers);
		
		ResponseEntity <Grupo> responseEntity = restTemplate.exchange("grupos/{id}/alumnos", HttpMethod.POST, request, Grupo.class);
		//ResponseEntity <Grupo> responseEntit = restTemplate.execute("grupos/{id}/alumnos", HttpMethod.POST, request,reques, Grupo.class,Alumno.class);
		//log.info("Me regresó:"+responseEntity.getBody());
		
		// Corroboro que el endpoint me regresa el estatus esperado
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		
		// Corroboro que en la base de datos se guardó el alumno
		Optional <Grupo> optAlumno = grupoRepository.findById(2);
		assertEquals(grupo,optAlumno.get());
		
	}
}
