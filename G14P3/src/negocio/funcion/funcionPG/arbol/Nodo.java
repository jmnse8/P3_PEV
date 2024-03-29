package negocio.funcion.funcionPG.arbol;

public abstract class Nodo {

	public abstract int getResultado(int[] caso, int nCaso);
	public abstract NodoEnum getTipo();
	public abstract void setTipo(NodoEnum tipo);
	public abstract int getNumHijos();
	public abstract void addHijo(Nodo hijo);
	public abstract Nodo getHijo(int pos);
	public abstract String aString();
	public abstract int getProfundidad();
	public abstract boolean esOperacion();
	public abstract void cambiaArbol(Nodo nodo1, Nodo nodo2);
	public abstract Nodo getPadre();
	public abstract void setPadre(Nodo padre);
	public abstract Nodo getCopia(Nodo papa);
}
