package ManejoDeUsuarios;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import muestras.Muestra;

public abstract class NivelDeUsuario {
	
	
	@Override
	public abstract String toString();
	
	public abstract void cambiarNivelDelUsuario(List<Muestra> muestras, Usuario usuario);
	
	public boolean hizoMasDe10EnviosEnLosUltimos30Dias(List<Muestra> muestras, Usuario usuario) {
		
		long cantidadDeEnviosEn30Dias  = muestras.stream()
                								 .filter(m -> m.getPublicador().equals(usuario))
                								 .filter(m -> laMuestraTieneMenosDe30Dias(m.getFechaDeCreacion()))
                								 .count();

		return cantidadDeEnviosEn30Dias > 10;
	}
	
	public boolean hizoMasDe20RevisionesEnLosUltimos30Dias(List<Muestra> muestras, Usuario usuario) {
		
		long cantidadDeRevisionesEn30Dias = muestras.stream()
				   									.filter(m -> m.haVotado(usuario))
				   									.filter(m -> laMuestraTieneMenosDe30Dias(m.getFechaDeCreacion()))
				   									.count();

		return cantidadDeRevisionesEn30Dias > 20;

	}
	
	public boolean laMuestraTieneMenosDe30Dias(LocalDateTime fechaBuscada) {
		
		LocalDateTime fechaDeHoy = LocalDateTime.now(); 

        long dias = ChronoUnit.DAYS.between(fechaBuscada, fechaDeHoy);
        
        return Math.max(0, dias) <= 30;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Contra la misma instancia da true
        if (obj == null) return false; // Contra null da false
        return this.getClass() == obj.getClass(); //Si son la misma clase son iguales
    }
	
	
}
	












































