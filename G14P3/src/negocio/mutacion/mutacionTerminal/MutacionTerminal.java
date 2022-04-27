package negocio.mutacion.mutacionTerminal;

import java.util.ArrayList;
import java.util.Random;

import negocio.funcion.Funcion;
import negocio.mutacion.Mutacion;
import negocio.funcion.funcionPG.arbol.*;

public class MutacionTerminal implements Mutacion {
	

	@Override
	public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, int porcentajeMutacion) {
		int tam_pob = poblacion.size();
		double prob = (double) porcentajeMutacion / 100;
		Random rnd = new Random();

		for (int i = 0; i < tam_pob; i++) {// Recorro toda la población
			if (rnd.nextDouble() < prob) {// Miro si hay que mutar
				Nodo gen = (Nodo)poblacion.get(i).getIndividuo();
				
				boolean cont = true;
				Nodo act = gen;
				NodoVariable cambio = null;
				float random = 0;
				while(cont) {
					if (act.getNumHijos() == 0) { //Hemos encontrado un terminal
						cambio = (NodoVariable)act; 
						cont = false; 
					}else {//Elegimos aleatoriamente un hijo de este nodo
						random = rnd.nextFloat();
						NodoOperacion aux = (NodoOperacion)act;
						int numHijos = act.getNumHijos();
						if (numHijos == 1) act = aux.getHijos().get(0); //Si solo tiene un hijo lo cogemos
						else if (numHijos == 2) {
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
				
				NodoEnum tipo = cambio.getTipo();
				
				NodoEnum nuevo = tipo;
				
				//Comprobamos que el nuevo valor ni es el anterior ni es de operacion
				while (nuevo == tipo || nuevo == NodoEnum.IF || nuevo == NodoEnum.NOT || nuevo == NodoEnum.AND || nuevo == NodoEnum.OR) {
					int x = rnd.nextInt(NodoEnum.class.getEnumConstants().length);
				    nuevo = NodoEnum.class.getEnumConstants()[x];
				}
				
				cambio.setTipo(nuevo);//Cambiamos el tipo
				
				poblacion.get(i).setIndividuo(gen);// Guardo el gen
			}
		}

		return poblacion;
	}

}
