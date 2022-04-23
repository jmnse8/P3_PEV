package negocio.funcion.factoriaFuncion.factoriaFuncionImp;

import negocio.funcion.Funcion;
import negocio.funcion.FuncionEnum;
import negocio.funcion.factoriaFuncion.FactoriaFuncion;
import negocio.funcion.funcionGE.FuncionGE;
import negocio.funcion.funcionPG.FuncionPG;

public class FactoriaFuncionImp extends FactoriaFuncion{

	@Override
	public Funcion generaFuncion(FuncionEnum funcion, int casoIni, int maximaProdundidad) {
		switch (funcion) {
		case Funcion_GE:
			return new FuncionGE();
		case Funcion_PG:
			return new FuncionPG(casoIni, maximaProdundidad);
		default:
			return new FuncionGE();
		}
	}

}
