package presentacion.filtro;

import negocio.cruce.CruceEnum;
import negocio.funcion.FuncionEnum;
import negocio.mutacion.MutacionEnum;
import presentacion.mainFrame.MainFrame;

public class Filtro {

	public Filtro() {

	}

	public static boolean aplicaFiltro() {
		MainFrame mF = MainFrame.getInstance();
		CruceEnum cruceTipo = mF.getCruce();
		MutacionEnum mutacionTipo = mF.getMutacion();
		FuncionEnum funcionTipo = mF.getFuncion();

		if (funcionTipo == FuncionEnum.Funcion_GE) {
			if(!compruebaCruce(1,cruceTipo))
				return false;
			if(!compruebaMutacion(1,mutacionTipo))
				return false;
		} else {
			if(!compruebaCruce(2,cruceTipo))
				return false;
			if(!compruebaMutacion(2,mutacionTipo))
				return false;
		}

		return true;
	}
	
	private static boolean compruebaMutacion(int fT, MutacionEnum mutacionTipo) {
		if(fT == 1) {
			if(mutacionTipo == MutacionEnum.Mutacion_Basica || mutacionTipo == MutacionEnum.Mutacion_Heuristica) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(mutacionTipo == MutacionEnum.Mutacion_Contraccion || mutacionTipo == MutacionEnum.Mutacion_Funcional || mutacionTipo == MutacionEnum.Mutacion_Terminal) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	private static boolean compruebaCruce(int fT,CruceEnum cruceTipo) {
		if(fT == 1) {
			if(cruceTipo == CruceEnum.Cruce_Monopunto || cruceTipo == CruceEnum.Cruce_Uniforme) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(cruceTipo == CruceEnum.Cruce_Arbol) {
				return true;
			}
			else {
				return false;
			}
		}
	}
}
