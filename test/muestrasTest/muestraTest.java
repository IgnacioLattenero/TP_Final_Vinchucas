package muestrasTest;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ManejoDeUsuarios.Basico;
import ManejoDeUsuarios.Especialista;
import ManejoDeUsuarios.Experto;
import ManejoDeUsuarios.Opinion;
import ManejoDeUsuarios.Usuario;
import ManejoDeUsuarios.Voto;


import muestras.Muestra;
import muestras.VotanSoloExpertos;
import observer.ZonaDeCobertura;
import unidadGeografica.Ubicacion;


class muestraTest {

	//SUT
	Muestra muestra;
	
	//DOC
	Usuario ivan 		 = mock(Usuario.class);
	Voto votoIvan    	 = mock(Voto.class);
	Ubicacion ubicacion  = mock(Ubicacion.class);
	Opinion especie      = mock(Opinion.class);
	
	Usuario maria        = mock(Usuario.class); 
	Voto votoMaria   	 = mock(Voto.class);
	
	Usuario miguel       = mock(Usuario.class); 
	Voto votoMiguel   	 = mock(Voto.class);
	
	Usuario juanExperto  = mock(Usuario.class); 
	Voto votoJuan 	  	 = mock(Voto.class);
	
	Usuario luisExperto  = mock(Usuario.class); 
	Voto votoLuis 	  	 = mock(Voto.class);
	
	Usuario carlaExperto = mock(Usuario.class); 
	Voto votoCarla 	  	 = mock(Voto.class);
	
	Usuario manuelExperto = mock(Usuario.class); 
	Voto votoManuel 	  = mock(Voto.class);
	
	Usuario especialista  = mock(Usuario.class); 
	Voto votoEspecialista = mock(Voto.class);
	
	ZonaDeCobertura zona  = mock(ZonaDeCobertura.class);
	
	@BeforeEach
	public void setUp() throws Exception {
		
		
		
		when(votoIvan.getNivelDelVotante()).thenReturn(new Basico());
		when(votoIvan.getVotante()).thenReturn(ivan);
		when(votoIvan.getOpinion()).thenReturn(Opinion.GUASAYANA);
		
		when(especie.name()).thenReturn("GUASAYANA");
		
		muestra = new Muestra("foto", 
							  "cuestionario", 
							  ivan, 
							  votoIvan,
							  ubicacion,
							  especie);
		
		
		// Maria (basico)
		when(votoMaria.getNivelDelVotante()).thenReturn(new Basico());
		when(votoMaria.getVotante()).thenReturn(maria);
		when(votoMaria.getOpinion()).thenReturn(Opinion.IMAGENPOCOCLARA);
		
		// Miguel (basico)
		when(votoMiguel.getNivelDelVotante()).thenReturn(new Basico());
		when(votoMiguel.getVotante()).thenReturn(miguel);
		when(votoMiguel.getOpinion()).thenReturn(Opinion.IMAGENPOCOCLARA);
		
		// Juan (Experto)
		when(votoJuan.getNivelDelVotante()).thenReturn(new Experto());
		when(votoJuan.getVotante()).thenReturn(juanExperto);
		when(votoJuan.getOpinion()).thenReturn(Opinion.GUASAYANA);
		
		// Luis (Experto)
		when(votoLuis.getNivelDelVotante()).thenReturn(new Experto());
		when(votoLuis.getVotante()).thenReturn(luisExperto);
		when(votoLuis.getOpinion()).thenReturn(Opinion.INFESTANS);
		
		// Carla (Experto)
		when(votoCarla.getNivelDelVotante()).thenReturn(new Experto());
		when(votoCarla.getVotante()).thenReturn(carlaExperto);
		when(votoCarla.getOpinion()).thenReturn(Opinion.GUASAYANA);
		
		// Manuel (Experto)
		when(votoManuel.getNivelDelVotante()).thenReturn(new Experto());
		when(votoManuel.getVotante()).thenReturn(manuelExperto);
		when(votoManuel.getOpinion()).thenReturn(Opinion.GUASAYANA);
		
		// Especialista (Experto)
		when(votoEspecialista.getNivelDelVotante()).thenReturn(new Especialista());
		when(votoEspecialista.getVotante()).thenReturn(especialista);
		when(votoEspecialista.getOpinion()).thenReturn(Opinion.GUASAYANA);
		
		
		
	}
	

	@Test
	void elVotoDelPublicadorFueAgregadoAlInstanciarLaMuestra() {
		
		assertTrue(muestra.getVotos().contains(votoIvan));
	}
	
	@Test
	void seAgreganLosVotosDeMariaYMiguel() throws Exception {
		
		muestra.agregarVoto(votoMaria);
		muestra.agregarVoto(votoMiguel);
		
		assertTrue(muestra.getVotos().contains(votoMaria));
		assertTrue(muestra.getVotos().contains(votoMiguel));
	}
	
	
	@Test
	void resultadoActualDeLaMuestraAbiertaATodaOpinionEsImagenPocoClara() throws Exception {
		
		muestra.agregarVoto(votoMaria);
		muestra.agregarVoto(votoMiguel);
		
		assertEquals("IMAGENPOCOCLARA", muestra.resultadoActual());
	}
	
	
	@Test
	void resultadoActualDeLaMuestraEsNoDefinidaParaSoloExpertos() throws Exception {
		
		muestra.agregarVoto(votoMaria);
		muestra.agregarVoto(votoMiguel);
		muestra.agregarVoto(votoJuan);
		muestra.agregarVoto(votoLuis);
		
		assertEquals("No definida", muestra.resultadoActual());
	}
	
	@Test
	void resultadoActualDeLaMuestraEsInfestansParaSoloExpertos() throws Exception {
		
		when(votoManuel.getOpinion()).thenReturn(Opinion.INFESTANS);
		
		muestra.agregarVoto(votoMaria);
		muestra.agregarVoto(votoMiguel);
		muestra.agregarVoto(votoJuan);
		muestra.agregarVoto(votoLuis);
		muestra.agregarVoto(votoManuel);
		
		assertEquals("INFESTANS", muestra.resultadoActual());
	}
	
	@Test
	void laMuestraVerificadaDevuelveGuasayana() throws Exception {
		
		muestra.agregarVoto(votoMaria);
		muestra.agregarVoto(votoMiguel);
		muestra.agregarVoto(votoJuan);
		muestra.agregarVoto(votoLuis);
		muestra.agregarVoto(votoCarla);
		
		assertEquals("GUASAYANA", muestra.resultadoActual());
	}
	
	@Test
	void elUsuarioIvanYaHaVotado(){
	
		assertTrue(muestra.haVotado(ivan));
	}
	
	
	@Test
	void getFotoRetornaFoto(){
	
		assertEquals("foto", muestra.getFoto());
	}
	
	@Test
	void getCuestionarioRetornaCuestionario(){
	
		assertEquals("cuestionario", muestra.getCuestionario());
	}
	
	@Test
	void getPublicadorRetornaIvan(){
	
		assertEquals(ivan, muestra.getPublicador());
	}
	
	
	@Test
	void getVotosDevuelveUnaListaConLosVotosDeIvanYMaria() throws Exception{

		List<Voto> resultado = new ArrayList<Voto>();
		
		resultado.add(votoIvan);
		resultado.add(votoMaria);
		
		muestra.agregarVoto(votoMaria);
		
		assertEquals(resultado, muestra.getVotos());
	}
	
	
	@Test
	void getFechaDeCreacionRetornaFechaDeHoy(){
		
		LocalDateTime fechaDeHoy = LocalDateTime.now();
		
		assertEquals(fechaDeHoy.getDayOfMonth() , muestra.getFechaDeCreacion().getDayOfMonth());
		assertEquals(fechaDeHoy.getDayOfWeek()  , muestra.getFechaDeCreacion().getDayOfWeek());
		assertEquals(fechaDeHoy.getDayOfYear()  , muestra.getFechaDeCreacion().getDayOfYear());
	}

	
	@Test
	void getUbicacionTest(){
		assertEquals(ubicacion, muestra.getUbicacion());
	}
	
	
	@Test
	void seteoManualDeEstadoASoloExpertosTest(){
		
		VotanSoloExpertos nuevoEstado = new VotanSoloExpertos();
		
		muestra.setEstado(nuevoEstado);
		
		assertEquals(nuevoEstado, muestra.getEstado());
	}
	
	
	
	@Test
	void getEspecieTest(){
		
		assertEquals(especie.name(), muestra.getEspecie().name());
	}

	
	@Test
	void noSePuedeVotarDosVeces() throws Exception {

		// El publicador NO DEBE votar de nuevo, debe lanzar Exception
        Exception exception = assertThrows(Exception.class, () -> {
        	muestra.agregarVoto(votoIvan);
        });

        assertEquals("El votante ya ha votado en esta muestra", exception.getMessage());
	}
	
	
	@Test
	void laMuestraVerificadaNoAdmiteMasVotos() throws Exception {
		
		muestra.agregarVoto(votoMaria);
		muestra.agregarVoto(votoMiguel);
		muestra.agregarVoto(votoJuan);
		muestra.agregarVoto(votoLuis);
		muestra.agregarVoto(votoCarla);
		
		muestra.agregarVoto(votoManuel); //Este voto no se debe poder añadir
		
		assertFalse(muestra.getVotos().contains(votoManuel));
		
	}
	
	
	@Test
	void seAñadioLaZonaDeCoberturaALaMuestra(){
		
		muestra.agregarZona(zona);
		
		assertTrue(muestra.getZonasALasQuePertenece().contains(zona));
	}

	@Test
	void votaUnEspecialistaEnAbiertaATodaOpinion() throws Exception{
		
		muestra.agregarVoto(votoEspecialista);
		
		assertTrue(muestra.getVotos().contains(votoEspecialista));
	}
	
	@Test
	void especialistaVotaEnEstadoASoloExpertos() throws Exception{
		
		VotanSoloExpertos nuevoEstado = new VotanSoloExpertos();
		
		muestra.setEstado(nuevoEstado);
		
		muestra.agregarVoto(votoEspecialista);
		
		assertTrue(muestra.getVotos().contains(votoEspecialista));
	}
	
	
}
