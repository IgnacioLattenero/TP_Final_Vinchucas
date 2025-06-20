package observer;

import muestras.Muestra;

public interface FuncionalidadExterna {
	
	public void nuevoEvento(Organizacion organizacion, ZonaDeCobertura zonaDeCobertura, Muestra muestra);
	
}
