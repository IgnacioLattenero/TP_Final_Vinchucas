package muestras;

import java.time.LocalDateTime;
import java.util.List;

public class Muestra {
	
	//Atributos
	
	private String foto;
	private String cuestionario;
	private Usuario publicador;
	private List<Voto> votos;
	private LocalDateTime fechaDeCreacion;
	private EstadoDeMuestra estado;
	
	//Constructor
	public Muestra (String foto, String cuestionario, Usuario publicador, Voto votoDelPublicador) {
		
		this.foto 		  = foto;
		this.cuestionario = cuestionario;
		this.publicador   = publicador; 
		
		this.votos = new ArrayList<Voto>();
		this.fechaDeCreacion = LocalDateTime.now();
		
		//Establecemos el estado inicial dependiendo del expertise del publicador
		if(!publicador.getRango().equals("BASICO")) {
			this.estado = new VotanSoloExpertos();
		}else {
			this.estado = new AbiertaATodaOpinion();
		}
		//Añadimos entonces el voto del publicador
		this.agregarVoto(votoDelPublicador);
	}
	
	//Metodos
	
	public void agregarVoto(Voto voto) {
		
		if(!haVotado(voto.getUsuario())) {
		  this.estado.agregarVoto(voto);
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
		return foto;
	}

	public String getCuestionario() {
		return cuestionario;
	}

	public Usuario getPublicador() {
		return publicador;
	}

	public List<Voto> getVotos() {
		return votos;
	}

	public LocalDateTime getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public EstadoDeMuestra getEstado() {
		return estado;
	}

	
	
}
