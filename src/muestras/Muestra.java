package muestras;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ManejoDeUsuarios.Basico;
import ManejoDeUsuarios.Opinion;
import ManejoDeUsuarios.Usuario;
import ManejoDeUsuarios.Voto;
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
	private Opinion especie;
	
	//Constructor
	public Muestra (String foto, 
					String cuestionario, 
					Usuario publicador, 
					Voto votoDelPublicador,
					Ubicacion ubicacion,
					Opinion especie) throws Exception {
		
		this.foto 		  = foto;
		this.especie 	  = especie;
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
	
	public void agregarVoto(Voto voto) throws Exception {
		
		if(!haVotado(voto.getVotante())) {
		  this.estado.agregarVoto(voto, this);
		}else {
			throw new Exception("El Usuario ya ha votado antes en esta muestra!!!");
		}	 

	}

	
	public String resultadoActual() {
		
		return this.estado.resultadoActual(this.votos);
	}

	public boolean haVotado(Usuario usuario) {
		
		return this.votos.stream()
						 .anyMatch(v -> v.getVotante().equals(usuario));
		
	}
	
	public List<Voto> votosDeExpertos() {
		return votos.stream()
				.filter(v -> !v.getNivelDelVotante().equals(new Basico()))//Todos los que no sean basicos
				.toList();
	}
	
	public boolean hayOpinionCoincidenteCon(List<Voto> votosAVerificar, Opinion opinion) {
		return votos.stream().anyMatch(voto -> voto.getOpinion().equals(opinion));
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

	public Opinion getEspecie() {
		
		return this.especie;
	}
	
	
}
