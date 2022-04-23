package negocio.funcion.funcionGE;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import negocio.funcion.Funcion;
import negocio.funcion.funcionGE.gramatica.Gramatica;
import negocio.funcion.funcionGE.gramatica.GramaticaTexto;
import presentacion.mainFrame.MainFrame;

public class FuncionGE implements Funcion, Cloneable {
	
	private ArrayList<Integer> individuo;// individuo array de enteros
	private double fitness;
	private int tamGen;
	private int nCaso;
	private int maxWraps;
	
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
	
	public FuncionGE() {
		individuo = new ArrayList<Integer>();// inicializo individuo
		
		Random rd = new Random();
		tamGen = 20;
		int cont = 0;
		individuo.add(1);
		individuo.add(3);
		individuo.add(8);
		individuo.add(7);
		individuo.add(4);
		individuo.add(11);
		individuo.add(5);
		individuo.add(0);
		individuo.add(11);
		individuo.add(53);
		individuo.add(14);
		individuo.add(52);
		individuo.add(19);
		individuo.add(17);
		individuo.add(20);
		individuo.add(54);
		individuo.add(23);
		individuo.add(51);
		individuo.add(26);
		individuo.add(50);
		
		/*
		while (cont < tamGen) {// meto los nº del individuo mediante aleatorios
			int numero = rd.nextInt(255);
			if (!individuo.contains(numero + 1)) {
				individuo.add(numero + 1);
				cont++;
			}
		}*/
		nCaso = 0;
		maxWraps = 10;
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
			caso = new int[12];
		/*
		//0 1 0 0 1 0
		caso[0] = 0;caso[1] = 1;caso[2] = 0;caso[3] = 0;caso[4] = 1;caso[5] = 0;
		GramaticaTexto gT = new GramaticaTexto(maxWraps, nCaso); 
		System.out.println(gT.S(individuo));
		
		int solucionEsperada = getSolEsp(caso);
		
		Gramatica g = new Gramatica(maxWraps, caso, nCaso);
		int solucionObtenida = g.S(individuo);
		if(solucionEsperada == solucionObtenida && !g.getLlegadoLimiteWraps())
			fitness++;
		String aux = "";
		for(int j = 0; j < caso.length; j++)
			aux += " " + caso[j];
		
			System.out.println(aux + " | " + solucionEsperada + " " + solucionObtenida);*/
		calculaCaso(caso, 0);
		 
		 
	}

	private void calculaCaso(int[] caso, int k) {
		
		if (k == caso.length) {
			int solucionEsperada = getSolEsp(caso);
			Gramatica g = new Gramatica(maxWraps, caso, nCaso);
			int solucionObtenida = g.S(individuo);
			if(solucionEsperada == solucionObtenida && !g.getLlegadoLimiteWraps())
				fitness++;
			String aux = "";
			for(int j = 0; j < caso.length; j++)
				aux += " " + caso[j];
			if(solucionEsperada != solucionObtenida)
				System.out.println(aux + " | " + solucionEsperada + " " + solucionObtenida);
			
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

	@Override
	public String toString() {
		GramaticaTexto gT = new GramaticaTexto(maxWraps, nCaso);
		return "FuncionGE [individuo=" + individuo + ",\n\t fitness=" + fitness + ",\n\t función" + gT.S(individuo) + "]";
	}
	
	public static void main(String[] args) {
		//for(int i = 0; i < 1; i++) {
		Funcion f = new FuncionGE();
		
		System.out.println(f.getIndividuo().toString());
		
		f.calculaFitness();
		
		System.err.println(f.getFitness() + "\n");
		
		//}
	
	}

}
