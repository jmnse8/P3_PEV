package negocio.cruce.cruceArbol;

import java.util.ArrayList;
import negocio.cruce.Cruce;
import negocio.funcion.Funcion;
import negocio.funcion.funcionPG.FuncionPG;
import negocio.funcion.funcionPG.arbol.*;

import java.util.Random; 

public class CruceArbol implements Cruce{

	@Override
	public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, int porcentajeCruce) {
		// TODO Auto-generated method stub
		
		int tam_pob = poblacion.size();
		Random rnd = new Random();
		double prob = (double)porcentajeCruce / 100.0;
		
		
		
		ArrayList<Integer> cruces;
		cruces = new ArrayList<Integer>();
		
		for(int i = 0; i < tam_pob; i++) { //Seleccionamos los individuos a cruzar
			if (rnd.nextDouble() < prob)
				cruces.add(i);
		}
		
		int numCruces = cruces.size();
		
		if (numCruces % 2 != 0) { //Si son impares eliminamos uno
			cruces.remove(numCruces / 2);
			numCruces--;
		}
		
		for(int i = 0; i < numCruces; i += 2) {
			
			Nodo gen1 = (Nodo) poblacion.get(cruces.get(i)).getIndividuo();
			Nodo gen2 = (Nodo) poblacion.get(cruces.get(i+1)).getIndividuo();
			
			Nodo nodo1 = getNodoIntercambiar(gen1.getHijo(rnd.nextInt(gen1.getNumHijos())), rnd),
					nodo2 = getNodoIntercambiar(gen2.getHijo(rnd.nextInt(gen2.getNumHijos())), rnd); //nodos que vamos a intercambiar
			
			/*
			boolean salir = true;
			Nodo act = gen1; //Nodo actual
			
			
			
			while (salir) { //Seleccionamos el primer nodo a intercambiar
				if (act.getNumHijos() == 0) {
					nodo1 = act; //No se puede bajar más, nos lo quedamos
					salir = false; 
				}else {
					float random = rnd.nextFloat();
					if (random < 0.5) {//50% de probabilidad de quedarnos este nodo
						nodo1 = act;
						salir = false;
					}else {//No nos hemos quedado con el nodo actual así que cogemos un hijo aleatorio
						NodoOperacion aux = (NodoOperacion)act;
						int rndInt = rnd.nextInt(act.getNumHijos()); //Generamos otro numero aleatorio
						act = aux.getHijos().get(rndInt); //Vamos al siguiente nodo
					}
				}
			}
			
			salir = true;
			act = gen2; //Nodo actual
			
			while (salir) { //Seleccionamos el primer nodo a intercambiar
				if (act.getNumHijos() == 0) {
					nodo2 = act; //No se puede bajar más, nos lo quedamos
					salir = false; 
				}else {
					float random = rnd.nextFloat();
					if (random < 0.5) {//50% de probabilidad de quedarnos este nodo
						nodo2 = act;
						salir = false;
					}else {//No nos hemos quedado con el nodo actual así que cogemos un hijo aleatorio
						NodoOperacion aux = (NodoOperacion)act;
						int rndInt = rnd.nextInt(act.getNumHijos()); //Generamos otro numero aleatorio
						act = aux.getHijos().get(rndInt); //Vamos al siguiente nodo
					}
				}
			}*/
			 //Intercambiamos
			Nodo aux = nodo2.getPadre();
			
			nodo1.getPadre().cambiaArbol(nodo1, nodo2);
			aux.cambiaArbol(nodo2, nodo1);
			//nodo1 = nodo2;
			//nodo2 = aux;
			//nodo1.setHijo(rnd.nextInt(nodo1.getNumHijos()),nodo2);
			//nodo2.setHijo(rnd.nextInt(nodo2.getNumHijos()),nodo1);
			poblacion.get(cruces.get(i)).setIndividuo(gen1);
			poblacion.get(cruces.get(i+1)).setIndividuo(gen2);
			
			
		}
		return poblacion;
	}
	
	private Nodo getNodoIntercambiar(Nodo nodo, Random rnd) {
		if(nodo.esOperacion()) {
			float random = rnd.nextFloat();
			if (random < 0.9) {
				return nodo;
			}
			else {
				int rndInt = rnd.nextInt(nodo.getNumHijos()); //Generamos otro numero aleatorio
				return nodo.getHijo(rndInt); 
			}
		}
		else {
			return nodo;
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Funcion> poblacion = new ArrayList<Funcion>();
		for(int i = 0; i < 2; i++) {
		Funcion f = new FuncionPG(0,4);
		
		poblacion.add(f);
		f.calculaFitness();
		System.out.println(f.toString());
		
		}
		CruceArbol cA = new CruceArbol();
		cA.execute(poblacion, 100);
		poblacion.stream().forEach(f ->f.calculaFitness());
		poblacion.stream().forEach(f -> System.out.println(f.toString()));
	
	}

}
