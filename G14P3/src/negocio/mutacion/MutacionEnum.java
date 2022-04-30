package negocio.mutacion;

import negocio.mutacion.mutacionInsercion.MutacionInsercion;
import negocio.mutacion.mutacionIntercambio.MutacionIntercambio;
import negocio.mutacion.mutacionBasica.MutacionBasica;
import negocio.mutacion.mutacionFuncional.MutacionFuncional;
import negocio.mutacion.mutacionHeuristica.MutacionHeuristica;
import negocio.mutacion.mutacionInversion.MutacionInversion;
import negocio.mutacion.mutacionTerminal.MutacionTerminal;

public enum MutacionEnum {

	Mutacion_Insercion(new MutacionInsercion()),
	Mutacion_Intercambio(new MutacionIntercambio()),
	Mutacion_Heuristica(new MutacionHeuristica()),
	Mutacion_Inversion(new MutacionInversion()),
	Mutacion_Basica(new MutacionBasica()),
	Mutacion_Terminal(new MutacionTerminal()),
	Mutacion_Funcional(new MutacionFuncional());
	
	private Mutacion mutacion;
	
	private MutacionEnum(Mutacion m) {
		mutacion = m;
	}
	
	public Mutacion getMutacion() {
		return mutacion;
	}
}
