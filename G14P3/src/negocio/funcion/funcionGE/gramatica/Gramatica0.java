package negocio.funcion.funcionGE.gramatica;

import java.util.ArrayList;

public class Gramatica0 {
	/*
	  S ::= <expr>
			<expr> ::= ( <expr> AND <expr>) |
			( <expr> OR <expr> ) |
			NOT ( <expr> ) |
			IF ((<expr>) (<expr>) (<expr>)) |
			AO | Al | DO | D1 | D2 | D3
	 */
	private int wraps;
	private int maxWraps;
	private int[] casoProbar;
	private int nCaso;
	private boolean limiteWraps;
	private int i;
	private ArrayList<Integer> codones;

	public Gramatica0(int maxWraps, int[] casoProbar, int nCaso) {
		this.wraps = 0;
		this.maxWraps = maxWraps;
		this.casoProbar = casoProbar;
		this.nCaso = nCaso;
		limiteWraps = false;
	}

	// S ::= <expr>
	public int S(ArrayList<Integer> codones) {
		this.codones = codones;
		i = 0;
		return decodeExpr();
	}

	private int decodeExpr() {
		if (i >= codones.size()) {
			i = 0;
			this.wraps++;
		}
		if (this.wraps >= this.maxWraps) {
			limiteWraps = true;
			return -1;
		}
		if (nCaso == 0) {
			int instruc = codones.get(i) % 10;
			i++;

			switch (instruc) {
				case 0:
					return andOp();
				case 1:
					return orOp();
				case 2:
					return notPre();
				case 3:
					return ifPre();
				case 4:
					return casoProbar[0];
				case 5:
					return casoProbar[1];
				case 6:
					return casoProbar[2];
				case 7:
					return casoProbar[3];
				case 8:
					return casoProbar[4];
				case 9:
					return casoProbar[5];
				default:
					return Integer.MAX_VALUE;
			}
		} else {
			int instruc = codones.get(i) % 15;
			i++;

			switch (instruc) {
				case 0:
					return andOp();
				case 1:
					return orOp();
				case 2:
					return notPre();
				case 3:
					return ifPre();
				case 4:
					return casoProbar[0];
				case 5:
					return casoProbar[1];
				case 6:
					return casoProbar[2];
				case 7:
					return casoProbar[3];
				case 8:
					return casoProbar[4];
				case 9:
					return casoProbar[5];
				case 10:
					return casoProbar[6];
				case 11:
					return casoProbar[7];
				case 12:
					return casoProbar[8];
				case 13:
					return casoProbar[9];
				case 14:
					return casoProbar[10];
				default:
					return Integer.MAX_VALUE;
			}
		}
	}

	private int andOp() {//( <expr> AND <expr>)
		int evaluarIzq = decodeExpr(), evaluarDer = decodeExpr();
		return (evaluarIzq == 1 && evaluarDer == 1)? 1 : 0;
	}
	
	private int orOp() {//( <expr> OR <expr> )
		int evaluarIzq = decodeExpr(), evaluarDer = decodeExpr();
		return (evaluarIzq == 1 || evaluarDer == 1)? 1 : 0;
	}
	
	private int notPre() {// NOT (<expr>)
		return (decodeExpr() == 0) ? 1 : 0;
	}
	
	private int ifPre() {// IF ((<expr>) (<expr>) (<expr>))
		int evaluar = decodeExpr(), verdadero = decodeExpr(), falso = decodeExpr();
		return (evaluar == 1) ? verdadero : falso;
	}
	
	public boolean getLlegadoLimiteWraps() {
		return limiteWraps;
	}
}
