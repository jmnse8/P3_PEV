package negocio.mutacion.mutacionContraccion;

import java.util.ArrayList;
import java.util.Random;

import negocio.funcion.Funcion;
import negocio.mutacion.Mutacion;
import presentacion.mainFrame.MainFrame;
import negocio.funcion.funcionPG.arbol.*;

public class MutacionContraccion implements Mutacion {
	

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
				Nodo prev = null; //Padre del elegido
				int rndInt = 0;
				
				while (cont) { //Seleccionamos el primer nodo a intercambiar
					if (act.getNumHijos() == 0) {
						cont = false; 
					}else {
						int totalNietos = 0; //Para ver si todos sus hijos son terminales
						NodoOperacion aux = (NodoOperacion) act;
						for (int x = 0; x < act.getNumHijos(); x++) { //Comprobamos que tenga algun hijo que sea una funcion
							totalNietos += aux.getHijo(x).getNumHijos();
						}
						
						if (totalNietos == 0) cont = false; //Si ningun hijo es funcion nos lo quedamos
						else {
							float rndFloat = rnd.nextFloat();
							if (rndFloat < 0.5 && prev != null) cont = false; //50% de probabilidad de quedarnos este nodo
							else {//si no cogemos el nodo anterior cogemos uno de sus hijos de forma aleatoria
								rndInt = rnd.nextInt(act.getNumHijos());
								while (aux.getHijo(rndInt).getNumHijos() == 0) {
									rndInt = rnd.nextInt(act.getNumHijos());
								}
								prev = act;
								act = aux.getHijo(rndInt);
							}
						}
					}
				}
				
				NodoVariable newNodo = new NodoVariable(MainFrame.getInstance().getNFuncion()); //Creamos el nuevo nodo
				if (prev != null && prev.getNumHijos() != 0) {
					NodoOperacion aux = (NodoOperacion)prev;
					aux.setHijo(rndInt, newNodo);
				}
				poblacion.get(i).setIndividuo(gen);// Guardo el gen
			}
		}

		return poblacion;
	}

}
