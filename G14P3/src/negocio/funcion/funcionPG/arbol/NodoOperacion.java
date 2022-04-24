package negocio.funcion.funcionPG.arbol;

import java.util.ArrayList;
import java.util.Random;

public class NodoOperacion extends Nodo{
	private NodoEnum op;
	private ArrayList<Nodo> hijos;
	
	public NodoOperacion() {
		Random rd = new Random();
		int alea = rd.nextInt(4);
		switch (alea) {
			case 0:
				op = NodoEnum.IF;
				break;
			case 1:
				op = NodoEnum.NOT;
				break;
			case 2:
				op = NodoEnum.AND;
				break;
			case 3:
				op = NodoEnum.OR;
				break;
		}
	}

	public NodoOperacion(NodoEnum op) {
		this.op = op;
	}

	@Override
	public int getResultado(int[] caso, int nCaso) {
		return getVar(caso, nCaso);
	}
	
	
	private int getVar(int[] caso, int nCaso) {
		switch(op) {
		case IF:
			return (hijos.get(0).getResultado(caso, nCaso) == 1) ? hijos.get(1).getResultado(caso, nCaso) : hijos.get(2).getResultado(caso, nCaso);
		case NOT:
			return (hijos.get(0).getResultado(caso, nCaso) == 0) ? 1 : 0;
		case AND:
			return (hijos.get(0).getResultado(caso, nCaso) == 1 && hijos.get(1).getResultado(caso, nCaso) == 1)? 1 : 0;
		case OR:
			return (hijos.get(0).getResultado(caso, nCaso) == 1 || hijos.get(1).getResultado(caso, nCaso) == 1)? 1 : 0;
		
		default:
			return 0;
		}
	}


	@Override
	public NodoEnum getTipo() {
		return op;
	}


	@Override
	public void setTipo(NodoEnum tipo) {
		op = tipo;
	}


	@Override
	public int getNumHijos() {
		switch(op) {
		case IF:
			return 3;
		case NOT:
			return 1;
		case AND:
			return 2;
		case OR:
			return 2;
		default:
			return 0;
		}
	}


	@Override
	public void addHijo(Nodo hijo) {
		hijos.add(hijo);
	}

	@Override
	public String aString() {
		switch(op) {
		case IF:
			return "IF(" + hijos.get(0).aString() + " ," + hijos.get(1).aString() + " ," + hijos.get(2).aString() + ")"  ;
		case NOT:
			return "NOT (" + hijos.get(0).aString() + ")";
		case AND:
			return "(" + hijos.get(0).aString() + " AND " + hijos.get(1).aString() + ")";
		case OR:
			return "(" + hijos.get(0).aString() + " OR " + hijos.get(1).aString() + ")";
		
		default:
			return "";
		}
	}
}
