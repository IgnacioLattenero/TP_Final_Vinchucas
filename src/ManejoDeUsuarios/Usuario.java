package ManejoDeUsuarios;

import java.time.LocalDateTime;

import muestras.Especie;
import muestras.Muestra;
import unidadGeografica.Ubicacion;

public class Usuario {
	
	private NivelDeUsuario nivelDeUsuario;
	private LocalDateTime antiguedadDelUsuario;
	private int cantidadDeRevisiones;
	private int cantidadDeEnviosDeMuestras;
	
	
	public Usuario() {
		
		this.nivelDeUsuario = nivelDeUsuario;
		this.antiguedadDelUsuario = antiguedadDelUsuario;
	}
	
	public void subirDeNivel() {
		
		this.nivelDeUsuario.subirDeNivel(this);
	}
	
	public void setNivel(NivelDeUsuario nivel) {
		
		this.nivelDeUsuario = nivel;
	}
	
	public Voto votar(Opinion opinion) {
		
		this.cantidadDeRevisiones = cantidadDeRevisiones + 1;
		
		
		return new Voto(opinion, this);
	}
	
	public Muestra crearMuestra() {
		
		// crear una muestra: para testearlo, creo la muestra, digo usuario crear muestra y debe devolver la misma muestra que creé
		
		this.cantidadDeEnviosDeMuestras = cantidadDeEnviosDeMuestras + 1;
		
		return new Muestra(String foto, 
						   String cuestionario, 
						   Usuario publicador, 
						   Voto votoDelPublicador,
						   Ubicacion ubicacion,
						   Especie especie);
	}
}











































