package negocio.cruce.cruceArbol;

import java.util.ArrayList;
import java.util.Collections;

import negocio.cruce.Cruce;
import negocio.funcion.Funcion;
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
			
			Nodo gen1 = (Nodo)poblacion.get(cruces.get(i)).getIndividuo();
			Nodo gen2 = (Nodo)poblacion.get(cruces.get(i+1)).getIndividuo();
			
			Nodo nodo1 = gen1,nodo2 = gen2; //nodos que vamos a intercambiar
			
			boolean cont = true;
			Nodo act = gen1; //Nodo actual
			
			while (cont) { //Seleccionamos el primer nodo a intercambiar
				if (act.getNumHijos() == 0) {
					nodo1 = act; //No se puede bajar más, nos lo quedamos
					cont = false; 
				}else {
					float random = rnd.nextFloat();
					if (random < 0.5) {//50% de probabilidad de quedarnos este nodo
						nodo1 = act;
						cont = false;
					}else {//No nos hemos quedado con el nodo actual así que cogemos un hijo aleatorio
						int hijos = act.getNumHijos();
						NodoOperacion aux = (NodoOperacion)act;
						random = rnd.nextFloat(); //Generamos otro numero aleatorio
						if (hijos == 1) act = aux.getHijos().get(0); //Si solo tiene un hijo lo cogemos
						else if (hijos == 2) {
							//Elegimos aleatoriamente unos de los hijos
							if (random < 0.5) act = aux.getHijos().get(0);
							else act = aux.getHijos().get(1);
						}else {
							//Elegimos alatoriamente uno de los hijos
							if (random < 0.33) act = aux.getHijos().get(0);
							else if (random < 0.67) act = aux.getHijos().get(1);
							else act = aux.getHijos().get(2);
						}
					}
				}
			}
			
			cont = true;
			act = gen2; //Nodo actual
			
			while (cont) { //Seleccionamos el primer nodo a intercambiar
				if (act.getNumHijos() == 0) {
					nodo2 = act; //No se puede bajar más, nos lo quedamos
					cont = false; 
				}else {
					float random = rnd.nextFloat();
					if (random < 0.5) {//50% de probabilidad de quedarnos este nodo
						nodo2 = act;
						cont = false;
					}else {//No nos hemos quedado con el nodo actual así que cogemos un hijo aleatorio
						int hijos = act.getNumHijos();
						NodoOperacion aux = (NodoOperacion)act;
						random = rnd.nextFloat(); //Generamos otro numero aleatorio
						if (hijos == 1) act = aux.getHijos().get(0); //Si solo tiene un hijo lo cogemos
						else if (hijos == 2) {
							//Elegimos aleatoriamente unos de los hijos
							if (random < 0.5) act = aux.getHijos().get(0);
							else act = aux.getHijos().get(1);
						}else {
							//Elegimos alatoriamente uno de los hijos
							if (random < 0.33) act = aux.getHijos().get(0);
							else if (random < 0.67) act = aux.getHijos().get(1);
							else act = aux.getHijos().get(2);
						}
					}
				}
			}
			 //Intercambiamos
			Nodo aux = nodo1;
			nodo1 = nodo2;
			nodo2 = aux;
			
			poblacion.get(cruces.get(i)).setIndividuo(gen1);
			poblacion.get(cruces.get(i+1)).setIndividuo(gen2);
			
			
		}
		return poblacion;
	}
	
	

}
