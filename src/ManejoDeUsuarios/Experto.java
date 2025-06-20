package ManejoDeUsuarios;

public class Experto implements NivelDeUsuario {

	public Experto() {
		
	}
	
	
	
	@Override
	public void subirDeNivel(Usuario usuario) {
		
		if (usuario.cantidadDeEnvios() >= 10 && usuario.cantidadDeRevisiones() >= 20) {
			
			// no hace nada
		} else {
			usuario.setNivel(new Basico());
		}
		
		
	}


}
