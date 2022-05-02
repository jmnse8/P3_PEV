package negocio.funcion.funcionPG.arbol;

import java.util.ArrayList;
import java.util.Random;

public class NodoOperacion extends Nodo{
	private NodoEnum op;
	private ArrayList<Nodo> hijos;
	private Nodo padre;
	
	public NodoOperacion() {
		padre = null;
		hijos = new ArrayList<Nodo>();
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
		hijos = new ArrayList<Nodo>();
		this.op = op;
		padre = null;
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
	

	@Override
	public int getProfundidad() {
		int mayorProf = -1;
		for(Nodo n: hijos) {
			int prof = n.getProfundidad();
			mayorProf = (prof >= mayorProf) ? prof : mayorProf;
		}
		return mayorProf + 1;
	}

	@Override
	public boolean esOperacion() {
		return true;
	}

	@Override
	public Nodo getHijo(int pos) {
		return hijos.get(pos);
	}

	@Override
	public void cambiaArbol(Nodo nodo1, Nodo nodo2) {
		for(int i = 0; i < hijos.size(); i++) {
			if(hijos.get(i) == nodo1) {
				hijos.set(i, nodo2);
				nodo2.setPadre(this);
				break;
			}
			/*else {
				hijos.get(i).cambiaArbol(nodo1, nodo2);
			}*/
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hijos == null) ? 0 : hijos.hashCode());
		result = prime * result + ((op == null) ? 0 : op.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof NodoOperacion))
			return false;
		NodoOperacion other = (NodoOperacion) obj;
		if (hijos == null) {
			if (other.hijos != null)
				return false;
		} else if (!hijos.equals(other.hijos))
			return false;
		if (op != other.op)
			return false;
		return true;
	}

	public void setHijo(int rndInt, NodoVariable newNodo) {
		hijos.set(rndInt, newNodo);
	}

	@Override
	public Nodo getPadre() {
		return padre;
	}

	@Override
	public void setPadre(Nodo padre) {
		this.padre = padre;
	}

	@Override
	public Nodo getCopia(Nodo papa) {
		NodoOperacion nO = new NodoOperacion(op);
		nO.setPadre(papa);
		for (Nodo n : hijos) {
			nO.addHijo(n.getCopia(this));
		}
		return nO;
	}/*
	private NodoEnum getOpCopia() {
		switch(op) {
		case IF:
			return NodoEnum.IF;
		case NOT:
			return NodoEnum.NOT;
		case AND:
			return NodoEnum.AND;
		case OR:
			return NodoEnum.OR;
		default:
			return NodoEnum.NOT;
		}
	}*/
	
}
