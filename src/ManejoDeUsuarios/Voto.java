package ManejoDeUsuarios;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Voto {
	
	private LocalDateTime fechaEmision;
	private Usuario votante;
	private Opinion opinion;
	private NivelDeUsuario nivelDelVotante;
	
	public Voto(Opinion opinion, Usuario votante) {
		
		this.opinion = opinion;
		this.votante = votante;
		this.fechaEmision = LocalDateTime.now();
		
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
	
	public long antiguedadEnDias() { // long representa a los días
		
		 /**
	     * calcula la cantidad de días desde la emisión del voto hasta el día de hoy
	     */
	        LocalDateTime fechaDeHoy = LocalDateTime.now(); 

	        // Usamos ChronoUnit.DAYS.between() para calcular la diferencia en días.
	        long dias = ChronoUnit.DAYS.between(this.getFechaEmision(), fechaDeHoy);

	        // Retornamos el máximo entre 0 y los días calculados, para evitar valores negativos
	        // si la fecha de emisión fuera posterior a la fecha actual (lo cual no debería pasar para un voto real).
	        return Math.max(0, dias);
	}
	
	
}











