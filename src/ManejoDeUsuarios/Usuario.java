package ManejoDeUsuarios;

import java.time.LocalDateTime;
import java.util.List;

import muestras.Muestra;
import unidadGeografica.Ubicacion;

public class Usuario {
	
	private NivelDeUsuario nivelDelUsuario;

	
	public Usuario(NivelDeUsuario nivelDelUsuario) {
		
		this.nivelDelUsuario = nivelDelUsuario;

	}
	
	public void cambiarNivelDelUsuario(List<Muestra> muestras) {
		
		this.nivelDelUsuario.cambiarNivelDelUsuario(muestras, this);
	}
	
	public NivelDeUsuario getNivelDeUsuario() {
	
		return this.nivelDelUsuario;
	}
	
	public void setNivel(NivelDeUsuario nivel) {
		
		this.nivelDelUsuario = nivel;
	}
	
	public Voto votar(Opinion opinion) {
		

		return new Voto(opinion, this);
	}
	
	public Muestra crearMuestra(String foto,
								String cuestionario,
								Usuario usuario,
								Voto voto,
								Ubicacion ubicacion,
								Opinion especie) throws Exception {

		
		return new Muestra(foto, cuestionario, usuario, voto, ubicacion, especie);
	}
}











































