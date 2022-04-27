package negocio.mutacion.mutacionFuncional;

import java.util.ArrayList;
import java.util.Random;

import negocio.funcion.Funcion;
import negocio.mutacion.Mutacion;
import negocio.funcion.funcionPG.arbol.*;

public class MutacionFuncional implements Mutacion {
	

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
				int random = 0;
				while(cont && act.getNumHijos() != 0) {//Busco AND u OR
					if (act.getNumHijos() == 2) {//Es OR o AND
						cont = false;
						if (act.getTipo() == NodoEnum.AND) act.setTipo(NodoEnum.OR);
						else act.setTipo(NodoEnum.AND);
					}else {//Buscamos otro nodo
						boolean next = false;
						NodoOperacion operacion = (NodoOperacion)act;
						for (int j = 0; j < operacion.getNumHijos(); j++) {
							if (operacion.getHijos().get(j).getNumHijos() == 2) {
								act = operacion.getHijos().get(j);
								next = true;
							}
						}
						
						if (!next) {
							random = rnd.nextInt(operacion.getNumHijos());
							act = operacion.getHijos().get(random);
						}
					}

				}
				
				
				
				poblacion.get(i).setIndividuo(gen);// Guardo el gen
			}
		}

		return poblacion;
	}

}
