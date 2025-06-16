package muestras;

import java.time.LocalDateTime;
import java.util.List;

import unidadGeografica.Ubicacion;

public class Muestra {
	
	//Atributos
	
	private String foto;
	private String cuestionario;
	private Usuario publicador;
	private List<Voto> votos;
	private LocalDateTime fechaDeCreacion;
	private EstadoDeMuestra estado;
	private Ubicacion ubicacion;
	
	//Constructor
	public Muestra (String foto, 
					String cuestionario, 
					Usuario publicador, 
					Voto votoDelPublicador,
					Ubicacion ubicacion) {
		
		this.foto 		  = foto;
		this.cuestionario = cuestionario;
		this.publicador   = publicador; 
		
		this.votos = new ArrayList<Voto>();
		this.fechaDeCreacion = LocalDateTime.now();
		this.ubicacion = ubicacion;
		
		//Establecemos el estado inicial
		this.estado = new AbiertaATodaOpinion();
		
		//Añadimos entonces el voto del publicador (lo que puede cambiar el estado)
		this.agregarVoto(votoDelPublicador);
	}
	
	//Metodos
	
	public void agregarVoto(Voto voto,List<Voto> votos) {
		
		if(!haVotado(voto.getUsuario())) {
		  this.estado.agregarVoto(voto, votos);
		}else {
			throw new Exception("El Usuario ya ha votado antes en esta muestra!!!");
		}	

	}

	
	public String resultadoActual() {
		
		return this.estado.resultadoActual();
	}

	public boolean haVotado(Usuario usuario) {
		
		return this.votos.stream()
						 .anyMatch(v -> voto.getUsuario().equals(usuario));
		
	}
	
	//Getters and setters
	public String getFoto() {
		return this.foto;
	}

	public String getCuestionario() {
		return this.cuestionario;
	}

	public Usuario getPublicador() {
		return this.publicador;
	}

	public List<Voto> getVotos() {
		return this.votos;
	}

	public LocalDateTime getFechaDeCreacion() {
		return this.fechaDeCreacion;
	}

	public EstadoDeMuestra getEstado() {
		return this.estado;
	}
	
	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}
	
	public void setEstado(EstadoDeMuestra nuevoEstado) {
		this.estado = nuevoEstado;
	}
	
	
}
