package negocio.cruce;

import negocio.cruce.cruceUniforme.CruceUniforme;
import negocio.cruce.cruceMonopunto.CruceMonopunto;
import negocio.cruce.cruceArbol.CruceArbol;

public enum CruceEnum {
	Cruce_Uniforme(new CruceUniforme()),
	Cruce_Monopunto(new CruceMonopunto()),
	Cruce_Arbol(new CruceArbol());
	
	private Cruce cruce;
	
	private CruceEnum(Cruce c) {
		cruce = c;
	}
	
	public Cruce getCruce() {
		return cruce;
	}
}
