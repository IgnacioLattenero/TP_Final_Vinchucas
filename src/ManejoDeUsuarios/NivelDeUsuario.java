package ManejoDeUsuarios;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import muestras.Muestra;

public abstract class NivelDeUsuario {
    
   
    public abstract void cambiarNivelDelUsuario(List<Muestra> muestras, Usuario usuario);
    public abstract void votarEnMuestraAbierta(Muestra muestra, Voto voto);
    public abstract void votarEnMuestraExpertos(Muestra muestra, Voto voto);
    public abstract boolean esVotoCalificado();
    
    public boolean hizoMasDe10EnviosEnLosUltimos30Dias(List<Muestra> muestras, Usuario usuario) {
        long cantidadDeEnviosEn30Dias = muestras.stream()
                                                .filter(m -> m.getPublicador().equals(usuario))
                                                .filter(m -> laMuestraTieneMenosDe30Dias(m.getFechaDeCreacion(), LocalDateTime.now())) 
                                                .count();

        return cantidadDeEnviosEn30Dias > 10;
    }
    
    public boolean hizoMasDe20RevisionesEnLosUltimos30Dias(List<Muestra> muestras, Usuario usuario) {
        long cantidadDeRevisionesEn30Dias = muestras.stream()
                                                    .filter(m -> m.haVotado(usuario))
                                                    .filter(m -> laMuestraTieneMenosDe30Dias(m.getFechaDeCreacion(), LocalDateTime.now())) 
                                                    .count();

        return cantidadDeRevisionesEn30Dias > 20;
    }
    
    public boolean laMuestraTieneMenosDe30Dias(LocalDateTime fechaBuscada, LocalDateTime fechaActual) {
        long dias = ChronoUnit.DAYS.between(fechaBuscada, fechaActual);
        return Math.max(0, dias) <= 30;
    }
    
    public LocalDateTime getFechaActual() {
    	return LocalDateTime.now();
    }
    
}