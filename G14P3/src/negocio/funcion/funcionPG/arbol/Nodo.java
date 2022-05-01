package negocio.funcion.funcionPG.arbol;

public abstract class Nodo {

	public abstract int getResultado(int[] caso, int nCaso);
	public abstract NodoEnum getTipo();
	public abstract void setTipo(NodoEnum tipo);
	public abstract int getNumHijos();
	public abstract void addHijo(Nodo hijo);
	public abstract String aString();
	public abstract int getProfundidad();
	public abstract int getCaso();
}
