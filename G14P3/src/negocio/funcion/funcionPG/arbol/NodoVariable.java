package negocio.funcion.funcionPG.arbol;

import java.util.Random;

public class NodoVariable extends Nodo{
	private NodoEnum var;
	private int caso;
	
	public NodoVariable(int nCaso) {
		iniVar(nCaso);
		caso = nCaso;
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

	@Override
	public String aString() {
		switch(var) {
		case A0:
			return "A0";
		case A1:
			return "A1";
		case A2:
			return "A2";
		case D0:
			return "D0";
		case D1:
			return "D1";
		case D2:
			return "D2";
		case D3:
			return "D3";
		case D4:
			return "D4";
		case D5:
			return "D5";
		case D6:
			return "D6";
		case D7:
			return "D7";
		default:
			return "";
		}
	}

	@Override
	public int getProfundidad() {
		return 1;
	}
	
	public NodoEnum getRandomVariable() {
		Random rd = new Random();
		int alea;
		switch(caso) {
		case 0:
			alea = rd.nextInt(6);
			switch (alea) {
				case 0:
					return NodoEnum.A0;
				case 1:
					return NodoEnum.A1;
				case 2:
					return NodoEnum.D0;
				case 3:
					return NodoEnum.D1;
				case 4:
					return NodoEnum.D2;
				case 5:
					return NodoEnum.D3;
			}
			break;
		case 1:
			alea = rd.nextInt(11);
			switch (alea) {
				case 0:
					return NodoEnum.A0;
				case 1:
					return NodoEnum.A1;
				case 2:
					return NodoEnum.A2;
				case 3:
					return NodoEnum.D0;
				case 4:
					return NodoEnum.D1;
				case 5:
					return NodoEnum.D2;
				case 6:
					return NodoEnum.D3;
				case 7:
					return NodoEnum.D4;
				case 8:
					return NodoEnum.D5;
				case 9:
					return NodoEnum.D6;
				case 10:
					return NodoEnum.D7;
			}
			break;
		}
		return NodoEnum.A0;//Por si acaso para que el compilador no se queje
	}
	
	public int getCaso() {
		return caso;
	}
}
