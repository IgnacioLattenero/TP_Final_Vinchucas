package ManejoDeUsuarios;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Voto {
	
	private LocalDateTime fechaEmision;
	private Usuario votante;
	private Opinion opinion;
	
	public Voto(Opinion opinion, Usuario votante, LocalDateTime fechaEmision) {
		
		this.opinion = opinion;
		this.votante = votante;
		this.fechaEmision = fechaEmision;
		
	}
	
	public Usuario getVotante() {
		
		return this.votante;
	}
	
	public LocalDateTime getFechaEmision() {
		
		return this.fechaEmision;
	}
	
	public long antiguedadEnDias() { // long representa a los días
		
		 /**
	     * Calcula la antigüedad del voto en días completos transcurridos desde su emisión hasta hoy.
	     *
	     * @return La cantidad de días transcurridos. Si el voto es de hoy o del futuro, retorna 0.
	     */
	        LocalDateTime fechaDeHoy = LocalDateTime.now(); // es la fecha de hoy

	        // Usamos ChronoUnit.DAYS.between() para calcular la diferencia en días.
	        long dias = ChronoUnit.DAYS.between(this.getFechaEmision(), fechaDeHoy);

	        // Retornamos el máximo entre 0 y los días calculados, para evitar valores negativos
	        // si la fecha de emisión fuera posterior a la fecha actual (lo cual no debería pasar para un voto real).
	        return Math.max(0, dias);
	}
	
	public Opinion votar() {
		
		return this.opinion.GUASAYANA;
	}
}











