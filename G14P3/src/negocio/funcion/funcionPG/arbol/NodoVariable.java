package negocio.funcion.funcionPG.arbol;

import java.util.Random;

public class NodoVariable extends Nodo{
	private NodoEnum var;

	public NodoVariable(int nCaso) {
		iniVar(nCaso);
	}

	public NodoVariable(NodoEnum var) {
		this.var = var;
	}

	@Override
	public int getResultado(int[] caso, int nCaso) {
		return getVar(caso, nCaso);
	}
	
	
	private int getVar(int[] caso, int nCaso) {
		switch(var) {
		case A0:
			return caso[0];
		case A1:
			return caso[1];
		case A2:
			return caso[2];
		case D0:
			return (nCaso == 0) ? caso[2] : caso[3];
		case D1:
			return (nCaso == 0) ? caso[3] : caso[4];
		case D2:
			return (nCaso == 0) ? caso[4] : caso[5];
		case D3:
			return (nCaso == 0) ? caso[5] : caso[6];
		case D4:
			return caso[7];
		case D5:
			return caso[8];
		case D6:
			return caso[9];
		case D7:
			return caso[10];
		default:
			return 0;
		}
	}


	@Override
	public NodoEnum getTipo() {
		return var;
	}


	@Override
	public void setTipo(NodoEnum tipo) {
		var = tipo;
	}


	@Override
	public int getNumHijos() {
		return 0;
	}


	@Override
	public void addHijo(Nodo hijo) {
		
	}

	public void iniVar(int nCaso) {
		Random rd = new Random();
		int alea;
		switch(nCaso) {
		case 0:
			alea = rd.nextInt(6);
			switch (alea) {
				case 0:
					var = NodoEnum.A0;
					break;
				case 1:
					var = NodoEnum.A1;
					break;
				case 2:
					var = NodoEnum.D0;
					break;
				case 3:
					var = NodoEnum.D1;
					break;
				case 4:
					var = NodoEnum.D2;
					break;
				case 5:
					var = NodoEnum.D3;
					break;
			}
			break;
		case 1:
			alea = rd.nextInt(11);
			switch (alea) {
				case 0:
					var = NodoEnum.A0;
					break;
				case 1:
					var = NodoEnum.A1;
					break;
				case 2:
					var = NodoEnum.A2;
					break;
				case 3:
					var = NodoEnum.D0;
					break;
				case 4:
					var = NodoEnum.D1;
					break;
				case 5:
					var = NodoEnum.D2;
					break;
				case 6:
					var = NodoEnum.D3;
					break;
				case 7:
					var = NodoEnum.D4;
					break;
				case 8:
					var = NodoEnum.D5;
					break;
				case 9:
					var = NodoEnum.D6;
					break;
				case 10:
					var = NodoEnum.D7;
					break;
			}
			break;
		}
	}
}
