package Buscador;

import java.time.LocalDateTime;
import java.util.ArrayList; // Asegúrate de importar ArrayList
import java.util.Collections;
import java.util.List;
import java.util.Optional; // Ya lo tenías

import muestras.Muestra;

public class FiltroLaUltimaVotacionOcurrioEnLaFechaDada extends Filtro {
    
    LocalDateTime fechaBuscada;
    
    public FiltroLaUltimaVotacionOcurrioEnLaFechaDada(LocalDateTime fechaBuscada) {
        this.fechaBuscada = fechaBuscada;
    }
    
    public LocalDateTime getFechaBuscada() {
        return this.fechaBuscada;
    }

    @Override
    public boolean pasaFiltro(Muestra muestra) {
        // Obtenemos las fechas de emisión, filtrando nulos
        List<LocalDateTime> fechasInmutables = muestra.getVotos().stream()
                                            .map(v -> v.getFechaEmision())
                                            .filter(fecha -> fecha != null) 
                                            .toList(); // Esto devuelve una lista inmutable
        
        if (fechasInmutables.isEmpty()) {
            return false; 
        }
        
        // ¡Crea una copia mutable antes de ordenar!
        List<LocalDateTime> fechasMutables = new ArrayList<>(fechasInmutables);
        
        Collections.sort(fechasMutables); // Ahora sí puedes ordenar esta lista mutable
        
        LocalDateTime fechaMasActual = fechasMutables.get(fechasMutables.size() - 1);
        
        return fechaMasActual.equals(this.getFechaBuscada());
    }
    
}