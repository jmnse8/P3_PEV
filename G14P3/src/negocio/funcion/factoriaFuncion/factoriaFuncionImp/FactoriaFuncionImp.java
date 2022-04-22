package negocio.funcion.factoriaFuncion.factoriaFuncionImp;

import negocio.funcion.Funcion;
import negocio.funcion.FuncionEnum;
import negocio.funcion.factoriaFuncion.FactoriaFuncion;
import negocio.funcion.funcionGE.FuncionGE;

public class FactoriaFuncionImp extends FactoriaFuncion{

	@Override
	public Funcion generaFuncion(FuncionEnum funcion, double intervalo) {
		switch (funcion) {
		case Funcion_trafico_aereo:
			return new FuncionGE();
		default:
			return new FuncionGE();
		}
	}

}
