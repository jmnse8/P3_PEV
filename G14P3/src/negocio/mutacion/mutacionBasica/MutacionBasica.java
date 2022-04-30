package negocio.mutacion.mutacionBasica;

import java.util.ArrayList;
import java.util.Random;

import negocio.funcion.Funcion;
import negocio.mutacion.Mutacion;

public class MutacionBasica implements Mutacion{

	@Override
	public ArrayList<Funcion> execute(ArrayList<Funcion> poblacion, int porcentajeMutacion) {
		int tam_pob = poblacion.size();
		int tam_gen = poblacion.get(0).getTam();
		double prob = (double)porcentajeMutacion/100;
		Random rnd = new Random();
		
		for (int i = 0; i < tam_pob; i++){
			@SuppressWarnings("unchecked")
			ArrayList<Integer> gen = (ArrayList<Integer>) poblacion.get(i).getIndividuo();
			if (rnd.nextDouble() < prob) {
				gen.set(rnd.nextInt(tam_gen),rnd.nextInt(64));
			}
			/*
			for (int j = 0; j < tam_gen; j++) {
				
					
			}*/
			poblacion.get(i).setIndividuo(gen);
		}
		
		return poblacion;
	}

}
