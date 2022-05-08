package negocio.funcion.funcionPG;

import java.util.Comparator;
import java.util.Random;
import negocio.funcion.Funcion;
import negocio.funcion.funcionPG.arbol.Nodo;
import negocio.funcion.funcionPG.arbol.NodoOperacion;
import negocio.funcion.funcionPG.arbol.NodoVariable;
import presentacion.mainFrame.MainFrame;

public class FuncionPG implements Funcion, Cloneable {
	
	private Nodo individuo;
	private double fitness;
	private int nCaso;
	private int maximaProdundidad;
	private int profundidadMedia = 4;
	
	final static Comparator<Funcion> comp = new Comparator<Funcion>() {// comparador de esta función mediante el fitness
		@Override
		public int compare(Funcion o1, Funcion o2) {
			Double aux = o1.getFitness(), aux2 = o2.getFitness();
			int sol = aux.compareTo(aux2);
			if (sol > 0)
				return -1;
			else if (sol == 0)
				return 0;
			else
				return 1;
		}
	};
	
	public FuncionPG(int casoIni, int maximaProdundidad) {
		nCaso = MainFrame.getInstance().getNFuncion();
		this.maximaProdundidad = maximaProdundidad;
		switch (casoIni) {//Ramped and Half se decide fuera, en AlgoritmoGenetico en inicialización de la población
		case 1:
			individuo = inicializaCompleta(0);
			break;
		case 0:
			individuo = inicializaCreciente(0);
			break;
		}
	}

	private Nodo inicializaCompleta(int profundidad) {
		Nodo nodo = null;
		if(profundidad < maximaProdundidad - 1) {
			nodo = new NodoOperacion();
			for(int i = 0; i < nodo.getNumHijos(); i++) {
				nodo.addHijo(inicializaCompleta(profundidad + 1));
				nodo.getHijo(i).setPadre(nodo);
			}
		}
		else {
			nodo = new NodoVariable(nCaso);
		}
		return nodo;
	}


	private Nodo inicializaCreciente(int profundidad) {
		Nodo nodo = null;
		if(profundidad == 0) {
			nodo = new NodoOperacion();
			for(int i = 0; i < nodo.getNumHijos(); i++) {
				nodo.addHijo(inicializaCompleta(profundidad + 1));
				nodo.getHijo(i).setPadre(nodo);
			}
		}
		else if(profundidad < maximaProdundidad - 1) {
			//nodo = getNodoCreciente();
			Random rd = new Random();
			nodo = (rd.nextBoolean()) ? new NodoOperacion() : new NodoVariable(nCaso);
			for(int i = 0; i < nodo.getNumHijos(); i++) {
				nodo.addHijo(inicializaCompleta(profundidad + 1));
				nodo.getHijo(i).setPadre(nodo);
			}
		}
		else {
			nodo = new NodoVariable(nCaso);
		}
		return nodo;
	}

	@Override
	public double getFitness() {
		return fitness;
	}

	@Override
	public void calculaFitness() {
		fitness = 0;
		int caso[];
		if(nCaso == 0) 
			caso = new int[6];
		else 
			caso = new int[11];
		calculaCaso(caso, 0);
		
		//Método de BLOATING: le resto al fitness la diferencia con la profundidad media multiplicado por dos para que sea incremental la penalización.
		int diferencia = individuo.getProfundidad() - profundidadMedia;
		if(diferencia > 0)
			fitness -= diferencia * 2;
		//------------
	}
	
	private void calculaCaso(int[] caso, int k) {
		if (k == caso.length) {
			int solucionEsperada = getSolEsp(caso);
			int solucionObtenida = individuo.getResultado(caso, nCaso);
			if(solucionEsperada == solucionObtenida)
				fitness++;
	        return;
	    }
		caso[k] = 0;
		calculaCaso(caso, k + 1);
	 
	    caso[k] = 1;
	    calculaCaso(caso, k + 1);
	}
	
	private int getSolEsp(int[] caso) {
		int i = 0;
		String numBin;
		if(nCaso == 0) {
			numBin = caso[0] + "" + caso[1];
			i = 2;
		}
		else {
			numBin = caso[0] + "" + caso[1] + "" + caso[2];
			i = 3;
		}
		return caso[Integer.parseInt(numBin, 2) + i];
	}

	@Override
	public Object getIndividuo() {
		return individuo;
	}

	@Override
	public Comparator<Funcion> getComp() {
		return comp;
	}

	@Override
	public int getTam() {
		return 0;
	}

	@Override
	public void setIndividuo(Object individuo) {
		this.individuo = (Nodo) individuo;
	}

	@Override
	public boolean getMax() {
		return true;
	}

	@Override
	public Funcion getCopy() {
		Funcion aux = null;
		try {
			aux = (Funcion) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		aux.setIndividuo(getIndividuoCopia());
		return aux;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return individuo.aString() + "\n\tfitness = " + fitness;
	}
	private Nodo getIndividuoCopia() {
		Nodo ind = individuo.getCopia(null);
		return ind;
	}
	/*
	public static void main(String[] args) {
		//for(int i = 0; i < 10; i++) {
		Funcion f = new FuncionPG(1,4);
		
		
		f.calculaFitness();
		//System.out.println(f.toString());
		Funcion f2 = f.getCopy();
		System.out.println(f2.toString());
		//}
	
	}*/
}
