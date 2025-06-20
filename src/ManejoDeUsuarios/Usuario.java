package ManejoDeUsuarios;

import java.time.LocalDateTime;

public class Usuario {
	
	private NivelDeUsuario nivelDeUsuario;
	private LocalDateTime antiguedadDelUsuario;
	private int cantidadDeEnviosDeOpiniones;
	private int cantidadDeRevisionesDeImagenes;
	
	
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
	
	public Opinion votar(Opinion opinion) {
		
		this.nivelDeUsuario.votar(opinion);
		this.cantidadDeEnviosDeOpiniones = cantidadDeEnviosDeOpiniones + 1;
		this.cantidadDeRevisionesDeImagenes = cantidadDeRevisionesDeImagenes + 1;
		
	}
}
