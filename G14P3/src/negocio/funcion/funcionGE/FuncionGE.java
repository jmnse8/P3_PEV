package negocio.funcion.funcionGE;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import negocio.funcion.Funcion;
import presentacion.mainFrame.MainFrame;

public class FuncionGE implements Funcion, Cloneable {
	
	private ArrayList<Integer> individuo;// individuo array de enteros
	private double fitness;
	private int tamGen;
	
	final static Comparator<Funcion> comp = new Comparator<Funcion>() {// comparador de esta función mediante el fitness
		@Override
		public int compare(Funcion o1, Funcion o2) {
			Double aux = o1.getFitness(), aux2 = o2.getFitness();
			int sol = aux.compareTo(aux2);
			if (sol > 0)
				return 1;
			else if (sol == 0)
				return 0;
			else
				return -1;
		}
	};
	
	public FuncionGE() {
		individuo = new ArrayList<Integer>();// inicializo individuo
		
		Random rd = new Random();
		tamGen = 8;
		int cont = 0;
		while (cont < tamGen) {// meto los nº del individuo mediante aleatorios
			int numero = rd.nextInt(255);
			if (!individuo.contains(numero + 1)) {
				individuo.add(numero + 1);
				cont++;
			}
		}
	}

	@Override
	public double getFitness() {
		return fitness;
	}

	@Override
	public void calculaFitness() {
		int fitnessAct = 0;
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getIndividuo() {
		ArrayList<Integer> aux = new ArrayList<Integer>();
		for (Integer b : individuo) {
			aux.add(b);
		}
		return aux;
	}

	@Override
	public Comparator<Funcion> getComp() {
		return comp;
	}

	@Override
	public int getTam() {
		return tamGen;
	}

	@Override
	public void setIndividuo(Object individuo) {
		@SuppressWarnings("unchecked")
		ArrayList<Integer> i = (ArrayList<Integer>) individuo;
		ArrayList<Integer> aux = new ArrayList<Integer>();
		for (Integer b : i) {
			aux.add(b);
		}
		this.individuo = aux;
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
		return aux;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
