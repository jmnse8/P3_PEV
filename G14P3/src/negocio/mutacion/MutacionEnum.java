package negocio.mutacion;

import negocio.mutacion.mutacionBasica.MutacionBasica;
import negocio.mutacion.mutacionFuncional.MutacionFuncional;
import negocio.mutacion.mutacionHeuristica.MutacionHeuristica;
import negocio.mutacion.mutacionTerminal.MutacionTerminal;
import negocio.mutacion.mutacionContraccion.MutacionContraccion;

public enum MutacionEnum {

	Mutacion_Heuristica(new MutacionHeuristica()),
	Mutacion_Basica(new MutacionBasica()),
	Mutacion_Terminal(new MutacionTerminal()),
	Mutacion_Funcional(new MutacionFuncional()),
	Mutacion_Contraccion(new MutacionContraccion());
	
	private Mutacion mutacion;
	
	private MutacionEnum(Mutacion m) {
		mutacion = m;
	}
	
	public Mutacion getMutacion() {
		return mutacion;
	}
}
