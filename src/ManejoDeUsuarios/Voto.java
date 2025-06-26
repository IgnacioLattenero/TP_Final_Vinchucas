 package ManejoDeUsuarios;
 
 import java.time.LocalDateTime;
 import java.time.temporal.ChronoUnit;


public class Voto {
	
	private LocalDateTime fechaEmision;
	private Usuario votante;
	private Opinion opinion;
	private NivelDeUsuario nivelDelVotante;
	
	// Constructor original: establece la fechaEmision a LocalDateTime.now()
	public Voto(Opinion opinion, Usuario votante) {
		this(opinion, votante, LocalDateTime.now()); // Llama al nuevo constructor
	}
	
	// Nuevo constructor para facilitar el testing: permite inyectar la fechaEmision
	public Voto(Opinion opinion, Usuario votante, LocalDateTime fechaEmision) {
		this.opinion = opinion;
		this.votante = votante;
		this.fechaEmision = fechaEmision;
		
		// Almacenamos el nivel del votante en el voto para que no cambie
		// si el votante cambia de rango.
		this.nivelDelVotante = votante.getNivelDeUsuario();
	}
	
	public Usuario getVotante() {
		return this.votante;
	}
	
	public LocalDateTime getFechaEmision() {
		return this.fechaEmision;
	}
	
	public Opinion getOpinion() {
		return this.opinion;
	}
	
	public NivelDeUsuario getNivelDelVotante() {
		return this.nivelDelVotante;
	}
	
	// Método original: calcula la antigüedad usando la fecha actual del sistema
	public long antiguedadEnDias() {
		return this.antiguedadEnDias(LocalDateTime.now()); // Llama al nuevo método sobrecargado
	}
	
	// Nuevo método sobrecargado para facilitar el testing: recibe la fecha actual
	public long antiguedadEnDias(LocalDateTime fechaDeHoy) {
		 /**
	     * calcula la cantidad de días desde la emisión del voto hasta la fecha especificada
	     */
	        long dias = ChronoUnit.DAYS.between(this.getFechaEmision(), fechaDeHoy);

	        // Retornamos el máximo entre 0 y los días calculados, para evitar valores negativos
	        return Math.max(0, dias);
	}
}































