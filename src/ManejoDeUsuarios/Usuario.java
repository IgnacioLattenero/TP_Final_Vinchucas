package ManejoDeUsuarios;

import java.time.LocalDateTime;
import java.util.List;

import muestras.Especie;
import muestras.Muestra;
import unidadGeografica.Ubicacion;

public class Usuario {
	
	private NivelDeUsuario nivelDelUsuario;
	private LocalDateTime antiguedadDelUsuario;
	private int cantidadDeRevisiones;
	private int cantidadDeEnviosDeMuestras;
	
	
	public Usuario() {
		
		this.nivelDelUsuario = nivelDelUsuario;
		this.antiguedadDelUsuario = antiguedadDelUsuario;
	}
	
	public void subirDeNivel(List<Muestra> muestras) {
		
		this.nivelDelUsuario.subirDeNivel(muestras, this);
	}
	
	public void setNivel(NivelDeUsuario nivel) {
		
		this.nivelDelUsuario = nivel;
	}
	
	public Voto votar(Opinion opinion) {
		
		this.cantidadDeRevisiones = cantidadDeRevisiones + 1;
		
		
		return new Voto(opinion, this);
	}
	
	public Muestra crearMuestra() throws Exception {
		
		// TODO: crear una muestra: para testearlo, creo la muestra, digo usuario crear muestra y debe devolver la misma muestra que creé
		
		this.cantidadDeEnviosDeMuestras = cantidadDeEnviosDeMuestras + 1;
		
		return new Muestra("JPG", "cuantas viste?",
				           new Usuario(),
				           new Voto(Opinion.GUASAYANA, new Usuario()),
				           new Ubicacion(5, 7),
				           Especie.GUASAYANA);
	}
}











































